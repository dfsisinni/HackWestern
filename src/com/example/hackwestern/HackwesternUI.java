package com.example.hackwestern;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hackwestern.lists.multiForm;
import com.hackwestern.lists.multiFormControl;
import com.hackwestern.persistence.Items;
import com.hackwestern.persistence.Tags;
import com.hackwestern.persistence.Users;
import com.hackwestern.search.SearchItemDesign;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import se.walkercrou.places.Place;

@SuppressWarnings("serial")
@Theme("hackwestern")
public class HackwesternUI extends UI {
	
	private Subject currentUser;
	public MainLayoutDesign main;
	private Window window;
	private Users user;
	
	public JPAContainer <Items> items;
	public JPAContainer <Tags> tags;

	
	private Map <String, Table> map;
	private Map <String, Table> tables;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = HackwesternUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		currentUser = SecurityUtils.getSubject();
		
		items = JPAContainerFactory.make(Items.class, "HackWestern");
		tags = JPAContainerFactory.make(Tags.class, "HackWestern");
		
		if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
			main = new MainLayoutDesign();
			setContent(main);
		} else {
			setContent(new LoginFormDesign());
		}
			
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
	
	public void makeMainDesign () {
		main = new MainLayoutDesign();
	}

}