/**
 * @author：OSC车
 * 
 * @date：2015年3月16日 下午10:04:27
 */
package com.jfaker.app.modules.web;

import org.snaker.engine.Assignment;
import org.snaker.engine.core.Execution;
import org.snaker.engine.model.TaskModel;

/**
 * @author OSC车
 * Assignment Demo
 */
public class MyAssignment extends Assignment {

	/* (non-Javadoc)
	 * @see org.snaker.engine.Assignment#assign(org.snaker.engine.model.TaskModel, org.snaker.engine.core.Execution)
	 */
	@Override
	public Object assign(TaskModel model, Execution execution) {
		
		// TODO Auto-generated method stub
		System.out.println("===================");
		System.out.println("======MyAssignment======");		
		System.out.println("===================");
		return "10416";
	}

}
