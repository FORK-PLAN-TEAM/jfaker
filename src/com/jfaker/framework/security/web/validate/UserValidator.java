/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.jfaker.framework.security.web.validate;

import java.util.List;

import com.jfaker.framework.security.model.Role;
import com.jfaker.framework.security.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * UserValidator.
 */
public class UserValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("user.username", "usernameMsg", "请输入用户ID!");
		validateRequiredString("user.fullname", "fullnameMsg", "请输入用户姓名!");
		
		String actKey=getActionKey();
		String passwordConfirm=controller.getPara("passwordConfirm");
		String password=controller.getPara("user.plainPassword");
		if("/security/user/update".equals(actKey)){
			if(password!=null && !password.equals("") && !password.equals(passwordConfirm)){
				validateEqualField("user.plainPassword", "passwordConfirm", "passwordMsg", "两次输入的密码不一致!");
			}
			
		}else{
			validateEqualField("user.plainPassword", "passwordConfirm", "passwordMsg", "两次输入的密码不一致!");
		}
		
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(User.class);
		
		String actionKey = getActionKey();
		
		List<Role> roles = Role.dao.getAll();
		
		
		
		if (actionKey.equals("/security/user/save"))
			controller.render("userAdd.jsp");
		else if (actionKey.equals("/security/user/update")){
			List<Role> rs = User.dao.getRoles(controller.getModel(User.class).getBigDecimal("id").intValue());
			for(Role role : roles) {
				for(Role r : rs) {
					if(role.getBigDecimal("id").intValue() == r.getBigDecimal("id").intValue())
					{
						role.put("selected", 1);
					}
					if(role.get("selected") == null)
					{
						role.put("selected", 0);
					}
				}
			}}
		
		controller.setAttr("roles", roles);
		controller.render("userEdit.jsp");
	}
}
