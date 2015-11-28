package com.example.hackwestern;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.themes.ValoTheme;

public class MainLayoutDesign extends MainLayout {

	public MainLayoutDesign () {
		initialUI();
		clickListeners();
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
		
	
	}
	
	public void clickListeners() {
		
	}
	
}
