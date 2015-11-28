package com.hackwestern.search;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import com.vaadin.client.ui.Icon;
import com.vaadin.server.ExternalResource;
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
	
	public SearchItemDesign (Place place) {
		initialUI();
		place = place.getDetails();
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
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }

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
		pictureButton.setIcon(new ExternalResource("http://s30.postimg.org/llhyw1pyl/12309206_10203989762744010_1568647486_n.jpg?noCache=1448696269"));
		pictureButton.addClickListener(new ClickListener () {
			@Override
			public void buttonClick(ClickEvent event) {
				final Window window = new Window("Window");
				window.setWidth(400.0f,Unit.PIXELS);
				final FormLayout content = new FormLayout();
				window.setContent(new Display());
				window.setPosition(500, 200);
				window.setHeight("400px");
				window.setResizable(false);
				window.setDraggable(false);
				UI.getCurrent().addWindow(window);
				
		
			}
			
		});
	}

}
