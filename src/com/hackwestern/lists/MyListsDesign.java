package com.hackwestern.lists;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.lang.WordUtils;
import com.example.hackwestern.HackwesternUI;
import com.example.hackwestern.SearchMenu;
import com.hackwestern.persistence.Items;
import com.hackwestern.persistence.Tags;
import com.hackwestern.search.Display;
import com.hackwestern.search.SearchResults;
import com.ibm.icu.util.BytesTrie.Iterator;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.ValoTheme;


import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

public class MyListsDesign extends MyListsSuperView{
	private boolean initial;
	
	private JPAContainer <Items> items;
	private JPAContainer <Tags> tags;
	
	private VerticalLayout separateTableHolder;
	private Map <String, Table> map;
	
	public MyListsDesign () {
		items = JPAContainerFactory.make(Items.class, "HackWestern");
		tags = JPAContainerFactory.make(Tags.class, "HackWestern");
		map = new HashMap <String, Table> ();
		
		separateTableHolder = new VerticalLayout();
		split.setFirstComponent(slTable);
		
		Filter userFilter = new Compare.Equal("user", ((HackwesternUI) UI.getCurrent()).getUser());
		items.addContainerFilter(userFilter);
		
		for (java.util.Iterator<Object> j = tags.getItemIds().iterator(); j.hasNext();) {
			int iid = (Integer) j.next();
			Item item = tags.getItem(iid);
			System.out.println("ID: " + "   TAG: " + item.getItemProperty("tag") + item.getItemProperty("itemId"));
		}
		
		slTable.setContainerDataSource(items);
		String [] visCols = {"name", "type"};
		slTable.setVisibleColumns(visCols);
		for (int i = 0; i < visCols.length; i++) {
			slTable.setColumnAlignment(visCols[i], Align.CENTER);
			slTable.setColumnExpandRatio(visCols[i], 1/visCols.length);
			slTable.setColumnHeader(visCols[i], WordUtils.capitalize(visCols[i]));
		}
		
		super.dropmenu.setNullSelectionAllowed(false);
		super.dropmenu.select("Single List");
		
		/*slTable.addContainerProperty("Name", Label.class, null);
		slTable.addContainerProperty("Type", Label.class, null);
		slTable.setColumnAlignment("Name", Align.LEFT);
		slTable.setColumnAlignment("Type", Align.RIGHT);*/
		
		slTable.setSelectable(true);	
		dropmenu.addValueChangeListener(new ValueChangeListener () {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (dropmenu.getValue().equals("Single List")){
					split.setFirstComponent(slTable);
				} else {
					split.setFirstComponent(separateTableHolder);
				}
			}
		});
		
		
		Map <String, Table> tables = new HashMap <String, Table> ();
		
		separateTableHolder.setWidth("100%");
		separateTableHolder.setHeightUndefined();
		
		for (java.util.Iterator<Object> j = tags.getItemIds().iterator(); j.hasNext();) {
			int iid = (Integer) j.next();
			Item itm = tags.getItem(iid);
			
			if (!tables.containsKey(itm.getItemProperty("tag").getValue())) {
				Label temp = new Label(String.valueOf(itm.getItemProperty("tag").getValue()));
				temp.setWidthUndefined();
				separateTableHolder.addComponent(temp);
				separateTableHolder.setComponentAlignment(temp, Alignment.MIDDLE_CENTER);
				
				temp.addStyleName("tag-title");
				
				Table table = new Table();
				table.addContainerProperty("Name", Label.class, null);
				table.addContainerProperty("Type", Label.class, null);
				table.setColumnAlignment("Name", Align.CENTER);
				table.setColumnAlignment("Type", Align.CENTER);
				table.setColumnExpandRatio("Name", (float) 0.5);
				table.setColumnExpandRatio("Type", (float) 0.5);
				separateTableHolder.addComponent(table);
				Filter filter = new Compare.Equal("tag", itm.getItemProperty("tag"));
				tags.addContainerFilter(filter);
				for (java.util.Iterator<Object> k = tags.getItemIds().iterator(); k.hasNext();) {
					int jjd = (Integer) k.next();
					Label label1 = new Label(((Items) tags.getItem(jjd).getItemProperty("itemId").getValue()).getName());
					Label label2 = new Label(((Items) tags.getItem(jjd).getItemProperty("itemId").getValue()).getType());
					table.addItem(new Object [] {label1, label2}, ((Items) tags.getItem(jjd).getItemProperty("itemId").getValue()).getLocationId());
				}
				tables.put(String.valueOf(itm.getItemProperty("tag").getValue()), table);
				table.setWidth("100%");
				table.setPageLength(table.size());
				
				tags.removeContainerFilter(filter);
				separateTableHolder.addComponent(new Label());
			}
			
			//System.out.println("ID: " + "   TAG: " + item.getItemProperty("tag") + item.getItemProperty("itemId"));
		}
		
	}
	
	public void receiveNewItem (Items item, List <Tags> tag) {
		items.addEntity(item);
		for (int i = 0; i < tag.size(); i++) {
			tags.addEntity(tag.get(i));
		}
		
		
	}

	
}

