package com.jfaker.app.modules.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.snaker.engine.entity.Order;

import com.jfaker.app.flow.web.SnakerController;
import com.jfaker.app.modules.model.Borrow;
import com.jfaker.framework.security.shiro.ShiroUtils;

/**
 * 借款流程controller类
 * 流程特点:标准的审批流程、decision节点的表达式写法->${result}
 * 业务数据:
 *     借款申请业务数据以flow_borrow表保存，通过orderId来关联
 *     审批环节数据以通用的审批表flow_approval表保存，通过orderId、taskName关联
 * @author yuqs
 * @since 1.0
 */
public class BorrowController extends SnakerController {
	/**
	 * 借款申请路由方法
	 */
	public void apply() {
		//将请求参数继续传递给视图页面
		keepPara();
		String orderId = getPara(PARA_ORDERID);
		String taskId = getPara(PARA_TASKID);
		if(StringUtils.isNotEmpty(orderId)) {
			//借款申请数据保存在业务表中，此处根据orderId获取业务数据
			setAttr("borrow", Borrow.dao.findByOrderId(orderId));
		}
		//根据taskId是否为空来标识当前请求的页面是否为活动任务的节点页面
		if(StringUtils.isEmpty(orderId) || StringUtils.isNotEmpty(taskId)) {
			setAttr("operateTime", new DateTime().toString("yyyy-MM-dd"));
			//如果实例id为空或者驳回情况下，返回apply.jsp
			render("apply.jsp");
		} else {
			//返回申请的查看页面
			render("applyView.jsp");
		}
	}
	
	/**
	 * 借款申请的提交处理
	 */
	public void applySave() {
		/** 流程数据构造开始 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("apply.operator", ShiroUtils.getUsername());
		params.put("approval.operator", ShiroUtils.getUsername());
		/** 流程数据构造结束 */
		
		/**
		 * 启动流程并且执行申请任务
		 */
		String orderId = getPara(PARA_ORDERID);
		String taskId = getPara(PARA_TASKID);
		if (StringUtils.isEmpty(orderId) && StringUtils.isEmpty(taskId)) {
			Order order = startAndExecute(getPara(PARA_PROCESSID), ShiroUtils.getUsername(), params);
			/** 业务数据处理开始*/
			Borrow model = getModel(Borrow.class);
			model.set("orderId", order.getId());
			model.set("operateTime", new DateTime().toString("yyyy-MM-dd"));
			model.set("operator", ShiroUtils.getFullname());
			model.save();
		} else {
			execute(taskId, ShiroUtils.getUsername(), params);
			/** 业务数据处理开始*/
			getModel(Borrow.class).update();
		}
		
		/** 业务数据处理结束 */
		redirectActiveTask();
	}
}