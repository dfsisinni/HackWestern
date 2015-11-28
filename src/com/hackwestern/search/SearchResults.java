package com.hackwestern.search;

import java.util.List;

import com.vaadin.ui.VerticalLayout;
import se.walkercrou.places.Place;

public class SearchResults extends VerticalLayout {

	public SearchResults (List <Place> places) {
		for (int i = 0; i < places.size(); i++) {
			this.addComponent(new SearchItemDesign(places.get(i)));
		}
	}
	
}
