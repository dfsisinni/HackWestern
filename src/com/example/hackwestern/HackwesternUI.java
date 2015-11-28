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
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(new SearchMenu());

		Button button = new Button("Clickretert Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(button);
=======
		currentUser = SecurityUtils.getSubject();
		
		if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
			
		} else {
			//setContent(new SearchItemDesign());
		}

>>>>>>> 55a6be97ea26dae681fea52cfc95e7973e966b01
	}

}