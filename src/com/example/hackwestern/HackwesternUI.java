package com.example.hackwestern;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hackwestern.search.SearchItemDesign;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("hackwestern")
public class HackwesternUI extends UI {
	
	Subject currentUser;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = HackwesternUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
<<<<<<< HEAD
=======

>>>>>>> 69a4d88cbba2fbed652299362273f7ae9ddf2ee4
		currentUser = SecurityUtils.getSubject();
		
		if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
			
<<<<<<< HEAD
		} 
			setContent(new LoginFormDesign());
		
			
=======
		}
			setContent(new LoginFormDesign());
		
			

>>>>>>> 69a4d88cbba2fbed652299362273f7ae9ddf2ee4
	}

}