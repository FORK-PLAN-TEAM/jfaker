package com.jfaker.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.access.ScriptRunner;
import org.snaker.engine.access.jdbc.JdbcHelper;

import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 脚本执行类
 * @author yuqs
 */
public class ScriptsPlugin implements IPlugin {
    private static final Logger log = LoggerFactory.getLogger(ScriptsPlugin.class);
    //数据源
    private static boolean isStarted = false;
	private static DataSource dataSource;
	private static IDataSourceProvider dataSourceProvider;
    
	/**
	 * 根据DataSource构造插件
	 * @param dataSource 数据源
	 */
	public ScriptsPlugin(DataSource dataSource) {
		ScriptsPlugin.dataSource = dataSource;
	}
	
	/**
	 * 根据数据源提供者构造插件
	 * @param dataSourceProvider 数据源提供接口
	 */
	public ScriptsPlugin(IDataSourceProvider dataSourceProvider) {
		ScriptsPlugin.dataSourceProvider = dataSourceProvider;
	}

	@Override
	public boolean start() {
		if (isStarted)
			return true;
		if (dataSourceProvider != null)
			dataSource = dataSourceProvider.getDataSource();
		if (dataSource == null)
			throw new RuntimeException("ScriptsPlugin start error: ScriptsPlugin need DataSource");
        log.info("scripts running......");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            if(JdbcHelper.isExec(conn)) {
                log.info("script has completed execution.skip this step");
                return true;
            }
            String databaseType = JdbcHelper.getDatabaseType(conn);
            String[] schemas = new String[]{"db/schema-" + databaseType + ".sql", "db/init-data.sql"};
            ScriptRunner runner = new ScriptRunner(conn, true);
            for(String schema : schemas) {
                runner.runScript(schema);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcHelper.close(conn);
            } catch (SQLException e) {
                //ignore
            }
        }
        isStarted = true;
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}
}