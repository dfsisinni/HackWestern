package com.hackwestern.search;
import com.vaadin.ui.Button.ClickEvent;
import javax.persistence.EntityManager;
import com.example.hackwestern.HackwesternUI;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.hackwestern.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import com.vaadin.ui.themes.ValoTheme;

import se.walkercrou.places.Place;

import com.vaadin.ui.Table.Align;

public class Display extends DisplayTags{
	int i = 0;
	
	private Place place;
	
	public Display (Place place) {
		this.place = place;
		tagTable.addContainerProperty("Tags", Label.class, null);
		tagTable.setColumnAlignment("Tags", Align.CENTER);
		HashMap map = new HashMap();
		
		addTag.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addTag.setClickShortcut(KeyCode.ENTER);
		
		cancel.addStyleName(ValoTheme.BUTTON_DANGER);
		save.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		save.addClickListener(new ClickListener () {

			@Override
			public void buttonClick(ClickEvent event) {
				EntityManager em = Persistence.createEntityManagerFactory("HackWestern").createEntityManager();
				Query q = em.createQuery("SELECT x FROM Items AS x");
				List <Items> items = q.getResultList();
				
				Items item = new Items();
				item.setId(items.size() + 1);
				item.setName(place.getName());
				item.setType("Google");
				item.setLocationId(place.getPlaceId());
				item.setUser(((HackwesternUI) UI.getCurrent()).getUser());
				
				em.getTransaction().begin();
				em.persist(item);
				em.getTransaction().commit();
				
				
				q = em.createQuery("SELECT x FROM Tags AS x");
				List <Tags> tags = q.getResultList();
				
				for (int i = 1; i <= tagTable.size(); i++) {
					Tags tag = new Tags();
					tag.setId(tags.size()+i);
					tag.setItemId(item);
					tag.setTag(((Label) tagTable.getItem(i).getItemProperty("Tags").getValue()).getValue());
					tag.setUser(((HackwesternUI) UI.getCurrent()).getUser());
					
					em.getTransaction().begin();
					em.persist(tag);
					em.getTransaction().commit();
				}
				
				((HackwesternUI) UI.getCurrent()).getWindow().close();
				((HackwesternUI) UI.getCurrent()).setWindow(null);
				((HackwesternUI) UI.getCurrent()).getMainLayout().newMyLists();
				((HackwesternUI) UI.getCurrent()).getMainLayout().getMyLists().receiveNewItem(item, tags);
				
				
				Notification note = new Notification(place.getName() + " Saved", Type.TRAY_NOTIFICATION);
				note.setPosition(Position.BOTTOM_CENTER);
				note.show(Page.getCurrent());
				
				
			}
			
		});
		
		super.addTag.addClickListener(new ClickListener () {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!map.containsKey(textBox.getValue())){
					Object id = tagTable.addItem();
					Item item = tagTable.getItem(id);
					Property p_field = (Property) item.getItemProperty("Tags");
					p_field.setValue(new Label(textBox.getValue()));
					map.put(textBox.getValue(),i);
					textBox.clear();
					i++;
				} else 
					Notification.show("Form Error:", "Tag already exists!", Type.WARNING_MESSAGE);
				textBox.clear();
			}
		});
		super.cancel.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				((HackwesternUI) UI.getCurrent()).getWindow().close();
				((HackwesternUI) UI.getCurrent()).setWindow(null);
			}
		});
		//super.cancel.addClickListener(new ClickListener());
		//super.save.addClickListener(new ClickListener());
	}	
}