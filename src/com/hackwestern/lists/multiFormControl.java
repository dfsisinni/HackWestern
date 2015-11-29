package com.hackwestern.lists;

import com.hackwestern.persistence.Items;
import com.hackwestern.persistence.*;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Table.Align;
import com.example.hackwestern.HackwesternUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

public class multiFormControl extends multiForm{
	
	int i;
	private Items item;
	private Map<String, Tags> map = new HashMap <String, Tags>();
	
	EntityManager em = Persistence.createEntityManagerFactory("HackWestern").createEntityManager();
	
	public multiFormControl(Place place, Table table){
	tagTable.addContainerProperty("Tags", Label.class, null);
	tagTable.setColumnAlignment("Tags", Table.ALIGN_CENTER);
	tagTable.setSelectable(true);
	i=tagTable.size() + 1;
	item = null;
	
	
	
	add.addClickListener(new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			try {
				
			
			if(!map.containsKey(textField.getValue())){
				tagTable.addItem(new Object [] {new Label(textField.getValue())}, textField.getValue());
				Tags tag = new Tags();
				
				
				Query q = em.createQuery("SELECT x FROM Tags AS x");
				
				tag.setId(q.getResultList().size() + 1);
				tag.setItemId(item);
				tag.setTag(textField.getValue());
				tag.setUser(((HackwesternUI) UI.getCurrent()).getUser());
				map.put(textField.getValue(),tag);

				((HackwesternUI) UI.getCurrent()).tags.addEntity(tag);
				
				
				Notification note = new Notification("Tag Saved", Type.TRAY_NOTIFICATION);
				note.setPosition(Position.BOTTOM_CENTER);
				note.show(Page.getCurrent());
				
				
			} else 
				Notification.show("Form Error:", "Tag already exists!", Type.WARNING_MESSAGE);
			textField.clear();
			} catch (Exception ex) {
				
			}
		}
		});
	delete.addClickListener(new Button.ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {
			try {
				
			
			if (tagTable.size() == 1) {
				Notification.show("The item must have at least one tag!");
			} else if (tagTable.getValue() == null) {
				Notification.show("A tag must be selected for removal.");
			} else {
				em.getTransaction().begin();
				em.remove(map.get(tagTable.getValue()));
				em.getTransaction().commit();
				
				map.remove(tagTable.getValue());
				tagTable.removeItem(tagTable.getValue());
				
				tagTable.setValue(null);
				
				Notification note = new Notification("Tag Removed", Type.TRAY_NOTIFICATION);
				note.setPosition(Position.BOTTOM_CENTER);
				note.show(Page.getCurrent());
				
			}
			} catch (Exception ex) {
				
			}
			
		}
		
		
	});
	save.addClickListener(new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				
			
			if (item == null ) {
				Notification.show("No item selected!");
			} else {
				em.getTransaction().begin();
				item.setNotes(notes.getValue());
				em.getTransaction().commit();
			}
			} catch (Exception ex) {
				
			}
		}
	});
	cancel.addClickListener(new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			try {
				
			
			if (item.getNotes() != null) {
				notes.setValue(item.getNotes());
			} else {
				notes.setValue("");
			}
			
			} catch (Exception ex) {
				
			}
		}
	});
	
	
}
	
	public void receiveData (int id) {
		
		Query q = em.createQuery("SELECT x FROM Items AS x where x.id = '" + String.valueOf(id) + "'");
		item = (Items) q.getResultList().get(0);
		if (item.getNotes() != null) {
			notes.setValue(item.getNotes());
		} else {
			notes.setValue("");
		}
	
	
		
		List <Tags> list = new ArrayList<Tags>();
		list.addAll(item.getTagsCollection());
		
		tagTable.removeAllItems();
		map = new HashMap <String, Tags>();
		
		for (int i = 0; i < list.size(); i++) {
			tagTable.addItem(new Object [] {new Label(list.get(i).getTag())}, list.get(i).getTag());
			map.put(list.get(i).getTag(), list.get(i));
		}
		
	}
	
}
