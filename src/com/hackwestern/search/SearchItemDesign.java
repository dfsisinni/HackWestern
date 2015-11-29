package com.hackwestern.search;




import java.io.BufferedReader;
import java.io.File;

import com.example.hackwestern.HackwesternUI;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import com.vaadin.client.ui.Icon;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.themes.ValoTheme;

import se.walkercrou.places.Place;

public class SearchItemDesign extends SearchItem {
	
	private Place place;
	
	public SearchItemDesign (Place place) {
		initialUI();
		System.out.println("-");
		place = place.getDetails();
		System.out.println("yolo");
		this.place = place;
		this.itemName.setValue(place.getName());
		this.address.setValue(place.getAddress());
		this.website.setValue(place.getWebsite());
		this.phoneNumber.setValue(place.getPhoneNumber());
		
		
		
		try{
			String string = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
			string += place.getName().replaceAll(" ", "%20");
            URL url = new URL(string);
            URLConnection connection = url.openConnection();

            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            line = reader.readLine();
            builder.append(line);

            JSONObject json = new JSONObject(builder.toString());
            String imageUrl = json.getJSONObject("responseData").getJSONArray("results").getJSONObject(0).getString("url");

         
        	this.image.setSource(new ExternalResource(imageUrl));
           
            
            
        } catch(Exception e){
            
            e.printStackTrace();
        }
	}
		//pictureButton.setIcon((Resource) place.getIconImage());
	
	public void initialUI() {
		pictureButton.setStyleName(ValoTheme.BUTTON_LINK); 
		pictureButton.setIcon(new ExternalResource("http://i.imgur.com/UFBwkDJ.png"));
		pictureButton.addClickListener(new ClickListener () {
			@Override
			public void buttonClick(ClickEvent event) {
				final Window window = new Window("Add to My Lists");
				window.setWidth(400.0f,Unit.PIXELS);
				final FormLayout content = new FormLayout();
				window.setContent(new Display(place));
				window.center();
				window.setHeight("365px");
				window.setWidth("400px");
				window.setResizable(false);
				window.setDraggable(false);
				UI.getCurrent().addWindow(window);
				((HackwesternUI) UI.getCurrent()).setWindow(window);;
		
			}
			
		});
	}

}
