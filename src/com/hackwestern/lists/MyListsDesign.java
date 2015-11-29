package com.hackwestern.lists;

import com.example.hackwestern.HackwesternUI;
import com.example.hackwestern.SearchMenu;
import com.hackwestern.search.Display;
import com.hackwestern.search.SearchResults;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.themes.ValoTheme;

public class MyListsDesign extends MyListsSuperView{
	private boolean initial;
	public MyListsDesign () {
		super.dropmenu.setNullSelectionAllowed(false);
		super.dropmenu.select("Single List");
		slTable.addContainerProperty("Name", Label.class, null);
		slTable.addContainerProperty("Type", Label.class, null);
		slTable.setColumnAlignment("Name", Align.LEFT);
		slTable.setColumnAlignment("Type", Align.RIGHT);
		slTable.setSelectable(true);	
		dropmenu.addValueChangeListener(new ValueChangeListener () {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(dropmenu.getValue().equals("Single List")){
					System.out.println("works");
					Object id = slTable.addItem();
					Item item = slTable.getItem(id);
					Property p_field = (Property) item.getItemProperty("Name");
					p_field.setValue(new Label("test"));
					//right.addComponent(pictureButton);
					//right.setVisible(true);
					//slTable.setPageLength(slTable.getItemIds().size() + 1);
					final Button window = new Button("Window");
					window.setWidth(400.0f,Unit.PIXELS);
					right.addComponent(window);
				}
				else{
					System.out.println("error"); //debug
				}
				}
			});
		}
	
	}

