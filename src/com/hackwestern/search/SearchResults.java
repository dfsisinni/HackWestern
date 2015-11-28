package com.hackwestern.search;

import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import se.walkercrou.places.Place;

public class SearchResults extends VerticalLayout {

	public SearchResults (List <Place> places) {
		for (int i = 0; i < places.size(); i++) {
			SearchItemDesign temp = new SearchItemDesign(places.get(i));
			this.addComponent(temp);
			
			this.setComponentAlignment(temp, Alignment.MIDDLE_CENTER);
			
			this.addComponent(new Label(""));
		}
	}
	
}
