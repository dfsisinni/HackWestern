package com.example.hackwestern;

import com.vaadin.ui.themes.ValoTheme;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;

public class LoginFormDesign extends LoginForm {

	public LoginFormDesign () {
		super();
		initialUI();
		clickListeners();
	}
	
	public void initialUI() {
		logo.setSource(new ExternalResource("http://i.picresize.com/images/2015/11/28/N4a0.jpg"));
	}
	
	public void clickListeners () {
		login.setClickShortcut(KeyCode.ENTER);
		login.addClickListener(new ClickListener () {

			@Override
			public void buttonClick(ClickEvent event) {
				if (username.getValue().trim().length() == 0) {
					Notification.show("Form Error:", "Invalid Username!", Type.WARNING_MESSAGE);
				} else if (password.getValue().trim().length() == 0) {
					Notification.show("Form Error:", "Invalid Password!", Type.WARNING_MESSAGE);
				} else {
					Subject currentUser = SecurityUtils.getSubject();
					UsernamePasswordToken token = new UsernamePasswordToken(username.getValue(), password.getValue());
					token.setRememberMe(checkbox.getValue()); 
					
					try {
						currentUser.login(token); //tries to authenticate user
						clear();
						UI.getCurrent().setContent(((HackwesternUI) UI.getCurrent()).getMainLayout());
					} catch (Exception ex) { //if authentication is unsuccessful
						clear();
						Notification.show("Login Error:", "Invalid username/password combination.", Type.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
			
		});
	}
	
	public void clear() {
		username.clear();
		password.clear();
		checkbox.clear();
	}
	
}
