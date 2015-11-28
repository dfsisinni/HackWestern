package com.example.hackwestern;

import java.util.List;

import com.hackwestern.search.SearchResults;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.ValoTheme;

import se.walkercrou.places.Place;

public class MainLayoutDesign extends MainLayout {
	
	private SearchResults results;
	private VerticalSplitPanel holder;
	private SearchMenu searchMenu;

	public MainLayoutDesign () {
		initialUI();
		clickListeners();
		results = null;
	}
	
	public void initialUI() {
		logo.setSource(new ExternalResource("http://i.picresize.com/images/2015/11/28/N4a0.jpg"));
		
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.addContainerProperty("label", Label.class, null);
		table.addItem(new Object [] {new Label("Search")}, 0);
		table.addItem(new Object [] {new Label("My Lists")}, 1);
		table.addItem(new Object [] {new Label("Settings")}, 2);
		
		table.select(0);
		table.setSelectable(true);
		table.setPageLength(table.getItemIds().size() + 1);
		
		split.setSecondComponent(new SearchMenu());
		split.setLocked(true);
	
	}
	
	public void clickListeners() {
		
	}
	
	public void receiveResults (List <Place> places) {
		holder = new VerticalSplitPanel();
		holder.setLocked(true);
		results = new SearchResults(places);
		searchMenu = new SearchMenu();
		
		split.setSecondComponent(holder);
		holder.setSecondComponent(results);
		holder.setFirstComponent(searchMenu);
		
		holder.setSplitPosition((float) 200.0, Unit.PIXELS);
		
		
	}
	
}
