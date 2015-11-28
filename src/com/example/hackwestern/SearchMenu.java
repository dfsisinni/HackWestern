package com.example.hackwestern;
import java.util.List;

import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.AWSECommerceServicePortType;
import com.ECS.client.jax.Item;
import com.ECS.client.jax.Items;
import com.google.gwt.dev.shell.remoteui.MessageTransport.RequestException;
import com.hackwestern.search.SearchResults;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

public class SearchMenu extends MainSearchMenu{
	double latitude = -81.15443;
	double longitude = 42.59395;
	double radius = 5000.0;
	
	
	public SearchMenu () {
		
		super.search.setClickShortcut(KeyCode.ENTER);
		super.search.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (tf.getValue() != null){
					if(menu.getValue().equals("Google")){
						GoogleClient();	
					} else {
						AmazonClient();
					}
				}
				
			}
			
		});
		super.menu.setNullSelectionAllowed(false);
		super.menu.addItem("Google");
		super.menu.addItem("Amazon");

		super.menu.select("Google");
	}
	
	public void GoogleClient(){
		GooglePlaces client = new GooglePlaces("AIzaSyCmD_vHmFOqI-FLMGP4t4YWjM7J2HB1K68");
		//List<Place> places = client.getPlacesByQuery(tf.getValue());  s
		List<Place> places = client.getPlacesByQuery(tf.getValue(), Param.name("latitude").value(String.valueOf(latitude)),Param.name("longitude").value(String.valueOf(longitude)),Param.name("radius").value(String.valueOf(radius)));
		/*for (int i = 0; i < places.size(); i++) {
			printPlace(places.get(i));
		}*/
		((HackwesternUI) UI.getCurrent()).SecondarySearch(places);
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
	
	public void AmazonClient() {

        String secretKey = "V2vykYfHR7u2n9SpM04qxhOsRnZ0pfxtH0FMq4i3";
        String awsKey = "AKIAJ7QGTFG5SQQXIT3Q";

        System.out.println("API Test started");


        AWSECommerceService service = new AWSECommerceService();
        service.setHandlerResolver(new AwsHandlerResolver(secretKey)); // important
        AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

        // Get the operation object:
        com.ECS.client.jax.ItemSearchRequest itemRequest = new com.ECS.client.jax.ItemSearchRequest();

        // Fill in the request object:
        itemRequest.setSearchIndex("ALL");;
        itemRequest.setKeywords(tf.getValue());
        itemRequest.getResponseGroup().add("Large");
        itemRequest.getResponseGroup().add("Images");
        itemRequest.setCondition("2011-08-01");
        com.ECS.client.jax.ItemSearch ItemElement = new com.ECS.client.jax.ItemSearch();
        ItemElement.setAWSAccessKeyId(awsKey);
        ItemElement.setAssociateTag("lio03-20");
        ItemElement.getRequest().add(itemRequest);

        // Call the Web service operation and store the response
        // in the response object:
        com.ECS.client.jax.ItemSearchResponse response = port
                .itemSearch(ItemElement);

        String r = response.toString();
        System.out.println("response: " + r);

        for (Items itemList : response.getItems()) {
            System.out.println(itemList);

            for (Item itemObj : itemList.getItem()) {

                System.out.println(itemObj.getItemAttributes().getTitle()); // Title
                System.out.println(itemObj.getDetailPageURL()); // Amazon URL
            }
        }

        System.out.println("API Test stopped");

    }
	

}
