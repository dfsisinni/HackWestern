package com.hackwestern.lists;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.walkercrou.places.Place;

public class multiFormControl extends multiForm{
	
	int i;
	
	public multiFormControl(Place place, Table table){
	tagTable.addContainerProperty("Tags", Label.class, null);
	tagTable.setColumnAlignment("Tags", Align.CENTER);
	tagTable.setSelectable(true);
	i=tagTable.size() + 1;
	HashMap map = new HashMap();
	
	add.addClickListener(new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(!map.containsKey(textField.getValue())){
				tagTable.addItem(new Object [] {new Label(textField.getValue())}, i);
				map.put(textField.getValue(),i);
				i++;
			} else 
				Notification.show("Form Error:", "Tag already exists!", Type.WARNING_MESSAGE);
			textField.clear();
		}
		});
	delete.addClickListener(new Button.ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			tagTable.removeItem(tagTable.getValue());
		}
		
		
	});
	save.addClickListener(new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			System.out.println(notes.getValue());
		}
	});
	cancel.addClickListener(new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			notes.clear();
		}
	});
}
}
