package com.example.hackwestern;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hackwestern.persistence.Users;
import com.hackwestern.search.SearchItemDesign;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import se.walkercrou.places.Place;

@SuppressWarnings("serial")
@Theme("hackwestern")
public class HackwesternUI extends UI {
	
	private Subject currentUser;
	private MainLayoutDesign main;
	private Window window;
	private Users user;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = HackwesternUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		main = new MainLayoutDesign();
		currentUser = SecurityUtils.getSubject();
		
		if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
		} 

			setContent(new LoginFormDesign());

		

	}
	
	public void SecondarySearch (List <Place> places) {
		main.receiveResults(places);
	}
	
	public MainLayoutDesign getMainLayout () {
		return this.main;
	}
	
	public Window getWindow () {
		return this.window;
	}
	
	public void setWindow (Window window) {
		this.window = window;
	}
	
	public void setUser (Users user) {
		this.user = user;
	}
	
	public Users getUser() {
		return this.user;
	}

}