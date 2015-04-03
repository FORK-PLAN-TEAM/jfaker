/**
 * @author：OSC车
 * 
 * @date：2015年3月19日 下午9:56:02
 */
package com.jfaker.app;

import java.beans.PropertyVetoException;
import java.io.File;
import java.util.Properties;

import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author OSC车
 * 由于C3p0Plugin并没对外提供ComboPooledDataSource对象
 * 如果遇到数据与应用之间有防火墙阻隔的情况，数据库连接
 * 会被关闭。
 * 因此，通过继承C3p0Plugin把ComboPooledDataSource原有
 * 的方法和参数暴露出来，属性及方法参照C3p0
 */
public class MyC3p0Plugin extends C3p0Plugin {
	/**
	 * @param jdbcUrl
	 * @param user
	 * @param password
	 * @param driverClass
	 */
	public MyC3p0Plugin(String jdbcUrl, String user, String password,
			String driverClass) {
		super(jdbcUrl, user, password, driverClass);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param propertyfile
	 */
	public MyC3p0Plugin(File propertyfile){
		super(propertyfile);
	}
	/**
	 * 
	 * @param properties
	 */
	public MyC3p0Plugin(Properties properties){
		super(properties);
	}
	
	public boolean start() {
	    boolean result = super.start();
	    ComboPooledDataSource ds = (ComboPooledDataSource)super.getDataSource();
	   
	    if(acquireRetryAttempts!=-1){
	    	ds.setAcquireIncrement(acquireRetryAttempts);
	    }
	    if(acquireRetryDelay!=-1){
	    	ds.setAcquireIncrement(acquireRetryDelay);
	    }
	    if(autoCommitOnClose){
	    	ds.setAutoCommitOnClose(autoCommitOnClose);
	    }
	    if(automaticTestTable!=null){
	    	ds.setAutomaticTestTable(automaticTestTable);
	    }
	    if(breakAfterAcquireFailure){
	    	ds.setBreakAfterAcquireFailure(breakAfterAcquireFailure);
	    }
	    if(checkoutTimeout!=-1){
	    	ds.setAcquireIncrement(checkoutTimeout);
	    }
	    if(connectionCustomizerClassName!=null){
	    	ds.setConnectionCustomizerClassName(connectionCustomizerClassName);
	    }
	    if(connectionTesterClassName!=null){
	    	try {
				ds.setConnectionTesterClassName(connectionTesterClassName);
			} catch (PropertyVetoException e) {
				
			}
	    }
	    if(dataSourceName!=null){
	    	ds.setDataSourceName(dataSourceName);
	    }
	    if(debugUnreturnedConnectionStackTraces){
	    	ds.setDebugUnreturnedConnectionStackTraces(debugUnreturnedConnectionStackTraces);
	    }
	    if(factoryClassLocation!=null){
	    	ds.setFactoryClassLocation(factoryClassLocation);
	    }
	    if(forceIgnoreUnresolvedTransactions){
	    	ds.setForceIgnoreUnresolvedTransactions(forceIgnoreUnresolvedTransactions);
	    }
	    if(idleConnectionTestPeriod!=-1){
	    	ds.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
	    }
	    if(jdbcUrl!=null){
	    	ds.setJdbcUrl(jdbcUrl);
	    }
	    if(maxAdministrativeTaskTime!=-1){
	    	ds.setMaxAdministrativeTaskTime(maxAdministrativeTaskTime);
	    }
	    if(maxConnectionAge!=-1){
	    	ds.setMaxConnectionAge(maxConnectionAge);
	    }
	    if(maxIdleTimeExcessConnections!=-1){
	    	ds.setMaxIdleTimeExcessConnections(maxIdleTimeExcessConnections);
	    }
	    if(maxStatements!=-1){
	    	ds.setMaxStatements(maxStatements);
	    }
	    if(maxStatementsPerConnection!=-1){
	    	ds.setMaxStatementsPerConnection(maxStatementsPerConnection);
	    }
	    if(numHelperThreads!=-1){
	    	ds.setNumHelperThreads(numHelperThreads);
	    }
	    if(overrideDefaultUser!=null){
	    	ds.setOverrideDefaultUser(overrideDefaultUser);
	    }
	    if(overrideDefaultPassword!=null){
	    	ds.setOverrideDefaultPassword(overrideDefaultPassword);
	    }
	    if(password!=null){
	    	ds.setPassword(password);
	    }
	    if(preferredTestQuery!=null){
	    	ds.setPreferredTestQuery(preferredTestQuery);
	    }
	    if(propertyCycle!=-1){
	    	ds.setPropertyCycle(propertyCycle);
	    }
	    if(testConnectionOnCheckin){
	    	ds.setTestConnectionOnCheckin(testConnectionOnCheckin);
	    }
	    if(testConnectionOnCheckout){
	    	ds.setTestConnectionOnCheckout(testConnectionOnCheckout);
	    }
	    if(unreturnedConnectionTimeout!=-1){
	    	ds.setUnreturnedConnectionTimeout(unreturnedConnectionTimeout);
	    }
	    if(user!=null){
	    	ds.setUser(user);
	    }
	   
	    return result;
	  }
	
	
	private int acquireRetryAttempts=-1;
	private int acquireRetryDelay=-1;
	private boolean autoCommitOnClose=false;
	private String automaticTestTable;
	private boolean breakAfterAcquireFailure=false;
	private int checkoutTimeout=-1;
	private String connectionCustomizerClassName;
	private String connectionTesterClassName;
	private String dataSourceName;
	private boolean debugUnreturnedConnectionStackTraces=false;
	private String factoryClassLocation;
	private boolean forceIgnoreUnresolvedTransactions=false;
	private int idleConnectionTestPeriod=-1;
	private String jdbcUrl;
	private int maxAdministrativeTaskTime=-1;
	private int maxConnectionAge=-1;
	private int maxIdleTimeExcessConnections=-1;
	private int maxStatements=-1;
	private int maxStatementsPerConnection=-1;
	private int numHelperThreads=-1;
	private String overrideDefaultUser;
	private String overrideDefaultPassword;
	private String password;
	private String preferredTestQuery;
	private int propertyCycle=-1;
	private boolean testConnectionOnCheckin=false;
	private boolean testConnectionOnCheckout=false;
	private int unreturnedConnectionTimeout=-1;
	private String user;
	
	public static void main(String[] args){
		
	}
	
	public int getAcquireRetryAttempts() {
		return acquireRetryAttempts;
	}
	
	/**
	 * 定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 
	 * @param acquireRetryAttempts
	 */
	public void setAcquireRetryAttempts(int acquireRetryAttempts) {
		this.acquireRetryAttempts = acquireRetryAttempts;
	}
	public int getAcquireRetryDelay() {
		return acquireRetryDelay;
	}
	
	/**
	 * 两次连接中间隔时间，单位毫秒。Default: 1000 
	 * @param acquireRetryDelay
	 */
	public void setAcquireRetryDelay(int acquireRetryDelay) {
		this.acquireRetryDelay = acquireRetryDelay;
	}
	public boolean isAutoCommitOnClose() {
		return autoCommitOnClose;
	}
	
	/**
	 * 连接关闭时默认将所有未提交的操作回滚。Default: false 
	 * @param autoCommitOnClose
	 */
	public void setAutoCommitOnClose(boolean autoCommitOnClose) {
		this.autoCommitOnClose = autoCommitOnClose;
	}
	public String getAutomaticTestTable() {
		return automaticTestTable;
	}
	
	/**
	 * c3p0将建一张名为Test的空表，并使用其自带的查询语句进行测试。
	 * 如果定义了这个参数那么属性preferredTestQuery将被忽略。
	 * 你不能在这张Test表上进行任何操作，它将只供c3p0测试使用。
	 * Default: null
	 * @param automaticTestTable
	 */
	public void setAutomaticTestTable(String automaticTestTable) {
		this.automaticTestTable = automaticTestTable;
	}
	public boolean isBreakAfterAcquireFailure() {
		return breakAfterAcquireFailure;
	}
	
	/**
	 * 获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常。
	 * 但是数据源仍有效   保留，并在下次调用getConnection()的时候继
	 * 续尝试获取连接。如果设为true，那么在尝试   获取连接失败后该数
	 * 据源将申明已断开并永久关闭。Default: false

	 * @param breakAfterAcquireFailure
	 */
	public void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure) {
		this.breakAfterAcquireFailure = breakAfterAcquireFailure;
	}
	public int getCheckoutTimeout() {
		return checkoutTimeout;
	}
	
	/**
	 * 当连接池用完时客户端调用getConnection()后等待获取新连接的时间，
	 * 超时后将抛出   SQLException,如设为0则无限期等待。单位毫秒。
	 * Default: 0
	 * @param checkoutTimeout
	 */
	public void setCheckoutTimeout(int checkoutTimeout) {
		this.checkoutTimeout = checkoutTimeout;
	}
	public String getConnectionCustomizerClassName() {
		return connectionCustomizerClassName;
	}
	
	
	public void setConnectionCustomizerClassName(
			String connectionCustomizerClassName) {
		this.connectionCustomizerClassName = connectionCustomizerClassName;
	}
	public String getConnectionTesterClassName() {
		return connectionTesterClassName;
	}
	
	/**
	 * 通过实现ConnectionTester或QueryConnectionTester的类来测试连接。
	 * 类名需制定全路径。   
	 * Default: com.mchange.v2.c3p0.impl.DefaultConnectionTester
	 * @param connectionCustomizerClassName
	 */
	public void setConnectionTesterClassName(String connectionTesterClassName) {
		this.connectionTesterClassName = connectionTesterClassName;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public boolean isDebugUnreturnedConnectionStackTraces() {
		return debugUnreturnedConnectionStackTraces;
	}
	public void setDebugUnreturnedConnectionStackTraces(
			boolean debugUnreturnedConnectionStackTraces) {
		this.debugUnreturnedConnectionStackTraces = debugUnreturnedConnectionStackTraces;
	}
	
	public String getFactoryClassLocation() {
		return factoryClassLocation;
	}
	/**
	 * 指定c3p0 libraries的路径，如果（通常都是这样）在本地即可获得那么无需设置，
	 * 默认null即可   Default: null
	 * @param factoryClassLocation
	 */
	public void setFactoryClassLocation(String factoryClassLocation) {
		this.factoryClassLocation = factoryClassLocation;
	}
	public boolean isForceIgnoreUnresolvedTransactions() {
		return forceIgnoreUnresolvedTransactions;
	}
	/**
	 * Strongly disrecommended. Setting this to true may lead to 
	 * subtle and bizarre bugs. 
	 * （文档原文）作者强烈建议不使用的一个属性
	 * @param forceIgnoreUnresolvedTransactions
	 */
	public void setForceIgnoreUnresolvedTransactions(
			boolean forceIgnoreUnresolvedTransactions) {
		this.forceIgnoreUnresolvedTransactions = forceIgnoreUnresolvedTransactions;
	}
	public int getIdleConnectionTestPeriod() {
		return idleConnectionTestPeriod;
	}
	/**
	 * 每60秒检查所有连接池中的空闲连接。Default: 0
	 * @param idleConnectionTestPeriod
	 */
	public void setIdleConnectionTestPeriod(int idleConnectionTestPeriod) {
		this.idleConnectionTestPeriod = idleConnectionTestPeriod;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public int getMaxAdministrativeTaskTime() {
		return maxAdministrativeTaskTime;
	}
	public void setMaxAdministrativeTaskTime(int maxAdministrativeTaskTime) {
		this.maxAdministrativeTaskTime = maxAdministrativeTaskTime;
	}
	public int getMaxConnectionAge() {
		return maxConnectionAge;
	}
	public void setMaxConnectionAge(int maxConnectionAge) {
		this.maxConnectionAge = maxConnectionAge;
	}
	public int getMaxIdleTimeExcessConnections() {
		return maxIdleTimeExcessConnections;
	}
	public void setMaxIdleTimeExcessConnections(int maxIdleTimeExcessConnections) {
		this.maxIdleTimeExcessConnections = maxIdleTimeExcessConnections;
	}
	public int getMaxStatements() {
		return maxStatements;
	}
	public void setMaxStatements(int maxStatements) {
		this.maxStatements = maxStatements;
	}
	public int getMaxStatementsPerConnection() {
		return maxStatementsPerConnection;
	}
	public void setMaxStatementsPerConnection(int maxStatementsPerConnection) {
		this.maxStatementsPerConnection = maxStatementsPerConnection;
	}
	
	public int getNumHelperThreads() {
		return numHelperThreads;
	}
	public void setNumHelperThreads(int numHelperThreads) {
		this.numHelperThreads = numHelperThreads;
	}
	public String getOverrideDefaultUser() {
		return overrideDefaultUser;
	}
	public void setOverrideDefaultUser(String overrideDefaultUser) {
		this.overrideDefaultUser = overrideDefaultUser;
	}
	public String getOverrideDefaultPassword() {
		return overrideDefaultPassword;
	}
	public void setOverrideDefaultPassword(String overrideDefaultPassword) {
		this.overrideDefaultPassword = overrideDefaultPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPreferredTestQuery() {
		return preferredTestQuery;
	}
	/**
	 * 应用与数据库被防火墙阻隔时，建议设置该值，目的就是定时执行一次查询，防止连接因过长时间不活动被防火墙关闭。
	 * @param preferredTestQuery
	 */
	public void setPreferredTestQuery(String preferredTestQuery) {
		this.preferredTestQuery = preferredTestQuery;
	}
	public int getPropertyCycle() {
		return propertyCycle;
	}
	public void setPropertyCycle(int propertyCycle) {
		this.propertyCycle = propertyCycle;
	}
	public boolean isTestConnectionOnCheckin() {
		return testConnectionOnCheckin;
	}
	public void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
		this.testConnectionOnCheckin = testConnectionOnCheckin;
	}
	public boolean isTestConnectionOnCheckout() {
		return testConnectionOnCheckout;
	}
	public void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
		this.testConnectionOnCheckout = testConnectionOnCheckout;
	}
	public int getUnreturnedConnectionTimeout() {
		return unreturnedConnectionTimeout;
	}
	public void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout) {
		this.unreturnedConnectionTimeout = unreturnedConnectionTimeout;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
