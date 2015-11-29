package com.example.hackwestern;
import java.util.List;

import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.AWSECommerceServicePortType;
import com.ECS.client.jax.Item;
import com.ECS.client.jax.Items;
import com.google.gwt.dev.shell.remoteui.MessageTransport.RequestException;
import com.hackwestern.search.SearchResults;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;
import se.walkercrou.places.exception.NoResultsFoundException;

public class SearchMenu extends MainSearchMenu{
	double latitude = -81.15443;
	double longitude = 42.59395;
	double radius = 5000.0;
	boolean local = false;
	
	
	public SearchMenu () {
		super.chkbox.addValueChangeListener(new ValueChangeListener(){

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if(chkbox.getValue()==true){
					local = true;
				}
				else
					local=false;
			}
		});
		super.menu.addItem("Google");
		super.menu.addItem("Amazon");
		super.menu.setNullSelectionAllowed(false);
		super.menu.select("Google");
		super.search.setClickShortcut(KeyCode.ENTER);
		super.search.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try{
				if (tf.getValue() != null){
					if(menu.getValue().equals("Google")&&local){
						GoogleClientLocal();	
					}
					else if(menu.getValue().equals("Google")&&!local){
						GoogleClientGlobal();
					}
					else {
						AmazonClient();
					}}
				}
				catch(Exception e){
					Notification.show("Error:", "No Search Results Found!", Type.ERROR_MESSAGE);
				}	
			};
	
	public void GoogleClientLocal(){
		GooglePlaces client = new GooglePlaces("AIzaSyA6m4Jhq1WD5OGl74jWH5Y8-FAY7s80XYI");
		//List<Place> places = client.getPlacesByQuery(tf.getValue());  s
		List<Place> places = client.getNearbyPlaces(43.011657714844, -81.277519226074, 10000, Param.name("keyword").value(tf.getValue()));
		for (int i = 0; i < places.size(); i++) {
			printPlace(places.get(i));
			
		}
	
		((HackwesternUI) UI.getCurrent()).SecondarySearch(places);
	}
	public void GoogleClientGlobal(){
		GooglePlaces client = new GooglePlaces("AIzaSyA6m4Jhq1WD5OGl74jWH5Y8-FAY7s80XYI");
		List<Place> places = client.getPlacesByQuery(tf.getValue(),15);
		//List<Place> places = client.getNearbyPlaces(43.011657714844, -81.277519226074, 10000, Param.name("keyword").value(tf.getValue()));
		for (int i = 0; i < places.size(); i++) {
			printPlace(places.get(i));
		}
	
		((HackwesternUI) UI.getCurrent()).SecondarySearch(places);
	}
	
	public void printPlace (Place detailedtest) {
		//detailedtest = detailedtest.getDetails();
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
        itemRequest.setCondition("2013-08-01");
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
	

});
	}
}
