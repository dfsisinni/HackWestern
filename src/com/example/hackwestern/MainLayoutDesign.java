package com.example.hackwestern;

import java.util.List;

import com.hackwestern.lists.MyListsDesign;
import com.hackwestern.search.SearchResults;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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
	
	private MyListsDesign myLists;
	
	private boolean initial;
	

	public MainLayoutDesign () {
		initialUI();
		clickListeners();
		results = null;
	}
	
	public void initialUI() {
		initial = true;
		logo.setSource(new ExternalResource("http://i.picresize.com/images/2015/11/28/N4a0.jpg"));
		myLists = new MyListsDesign();
		
		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.addContainerProperty("label", Label.class, null);
		table.addItem(new Object [] {new Label("Search")}, 0);
		table.addItem(new Object [] {new Label("My Lists")}, 1);
		table.addItem(new Object [] {new Label("Settings")}, 2);
		
		table.select(0);
		table.setSelectable(true);
		table.setPageLength(table.getItemIds().size() + 1);
		searchMenu = new SearchMenu();
		
		split.setSecondComponent(searchMenu);
		split.setLocked(true);
	
	}
	
	public void clickListeners() {
		table.addValueChangeListener(new ValueChangeListener () {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
				if ((int) event.getProperty().getValue() == 0) {
					if (initial) {
						split.setSecondComponent(searchMenu);
					} else {
						split.setSecondComponent(holder);
					}
					
				} else if ((int) event.getProperty().getValue() == 1) {
					split.setSecondComponent(myLists);
				} else {
					
				}
				}
				
			}
			
		});
	}
	
	public void receiveResults (List <Place> places) {
		holder = new VerticalSplitPanel();
		holder.setLocked(true);
		results = new SearchResults(places);
		searchMenu = new SearchMenu();
		
		split.setSecondComponent(holder);
		holder.setSecondComponent(results);
		holder.setFirstComponent(searchMenu);
		
		initial = false;
		
		holder.setSplitPosition((float) 200.0, Unit.PIXELS);
		
		
	}
	
	public MyListsDesign getMyLists () {
		return this.myLists;
	}
	
	public void newMyLists () {
		myLists = new MyListsDesign();
	}
	
}
