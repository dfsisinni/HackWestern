package com.example.hackwestern;
import java.util.List;

import com.google.gwt.dev.shell.remoteui.MessageTransport.RequestException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

public class SearchMenu extends MainSearchMenu{
	double latitude = -81.15443;
	double longitude = 42.59395;
	double radius = 5000.0;
	
	
	public SearchMenu () {
		super.search.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (tf.getValue().equals("hello")){
						System.out.println("thanks for clicking!!!");
				}
				else{
					GoogleClient();
				}
			}
			
		});
	}
	
	public void GoogleClient(){
		GooglePlaces client = new GooglePlaces("AIzaSyCmD_vHmFOqI-FLMGP4t4YWjM7J2HB1K68");
		//List<Place> places = client.getPlacesByQuery(tf.getValue());  s
		List<Place> places = client.getPlacesByQuery(tf.getValue(), Param.name("latitude").value(String.valueOf(latitude)),Param.name("longitude").value(String.valueOf(longitude)),Param.name("radius").value(String.valueOf(radius)));
		for (int i = 0; i < places.size(); i++) {
			printPlace(places.get(i));
		}
	}
	
	public void printPlace (Place detailedtest) {
		detailedtest = detailedtest.getDetails();
	    System.out.println("Name: " + detailedtest.getName());
	    System.out.println("Phone: " + detailedtest.getPhoneNumber());
	    System.out.println("Address: " + detailedtest.getAddress());
	    //BufferedImage image = detailedtest.downloadIcon().getIconImage();
	}
	protected void init(VaadinRequest request) {

	}

}
