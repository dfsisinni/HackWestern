package com.hackwestern.search;

import com.vaadin.client.ui.Icon;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import se.walkercrou.places.Place;

public class SearchItemDesign extends SearchItem {
	
	public SearchItemDesign (Place place) {
		initialUI();
		this.itemName.setValue(place.getName());
		this.address.setValue(place.getAddress());
		this.website.setValue(place.getWebsite());
		//this.
	}
	
	public void initialUI() {
		pictureButton.setStyleName(ValoTheme.BUTTON_LINK); 
		pictureButton.setIcon(new ExternalResource("http://s30.postimg.org/llhyw1pyl/12309206_10203989762744010_1568647486_n.jpg?noCache=1448696269"));
		pictureButton.addClickListener(new ClickListener () {

			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("Hi");
				
			}
			
		});
	}

}
