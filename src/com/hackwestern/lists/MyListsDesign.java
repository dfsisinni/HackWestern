package com.hackwestern.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	

	private multiFormControl control;
	
	private VerticalLayout separateTableHolder;
	private Map <String, Table> map;
	private Map <String, Table> tables;
	
	public MyListsDesign () {
		map = new HashMap <String, Table> ();
		
		control = new multiFormControl(null, null);
		
		separateTableHolder = new VerticalLayout();
		split.setFirstComponent(slTable);
		split.setSecondComponent(control);
		split.setSplitPosition(650, Unit.PIXELS, true);
		
		Filter userFilter = new Compare.Equal("user", ((HackwesternUI) UI.getCurrent()).getUser());
		((HackwesternUI) UI.getCurrent()).items.addContainerFilter(userFilter);
		
		for (java.util.Iterator<Object> j = ((HackwesternUI) UI.getCurrent()).tags.getItemIds().iterator(); j.hasNext();) {
			int iid = (Integer) j.next();
			Item item = ((HackwesternUI) UI.getCurrent()).tags.getItem(iid);
			System.out.println("ID: " + "   TAG: " + item.getItemProperty("tag") + item.getItemProperty("itemId"));
		}
		
		slTable.setContainerDataSource(((HackwesternUI) UI.getCurrent()).items);
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
		
		slTable.addValueChangeListener(new ValueChangeListener () {

			@Override
			public void valueChange(ValueChangeEvent event) {
				control.receiveData((int) event.getProperty().getValue()); 
			}
			
		});
		
		
		tables = new HashMap <String, Table> ();
		
		separateTableHolder.setWidth("100%");
		separateTableHolder.setHeightUndefined();
		
		int count = 0;
		
		for (java.util.Iterator<Object> j = ((HackwesternUI) UI.getCurrent()).tags.getItemIds().iterator(); j.hasNext();) {
			int iid = (Integer) j.next();
			Item itm = ((HackwesternUI) UI.getCurrent()).tags.getItem(iid);
			
			if (!tables.containsKey(itm.getItemProperty("tag").getValue())) {
				Label temp = new Label(String.valueOf(itm.getItemProperty("tag").getValue()));
				temp.setWidthUndefined();
				separateTableHolder.addComponent(temp);
				separateTableHolder.setComponentAlignment(temp, Alignment.MIDDLE_CENTER);
				
				temp.addStyleName("tag-title");
				
				Table table = new Table();
				table.setId(String.valueOf(count));
				count ++;
				table.addContainerProperty("Name", Label.class, null);
				table.addContainerProperty("Type", Label.class, null);
				table.setColumnAlignment("Name", Align.CENTER);
				table.setColumnAlignment("Type", Align.CENTER);
				table.setColumnExpandRatio("Name", (float) 0.5);
				table.setColumnExpandRatio("Type", (float) 0.5);
				separateTableHolder.addComponent(table);
				Filter filter = new Compare.Equal("tag", itm.getItemProperty("tag"));
				((HackwesternUI) UI.getCurrent()).tags.addContainerFilter(filter);
				for (java.util.Iterator<Object> k = ((HackwesternUI) UI.getCurrent()).tags.getItemIds().iterator(); k.hasNext();) {
					int jjd = (Integer) k.next();
					Label label1 = new Label(((Items) ((HackwesternUI) UI.getCurrent()).tags.getItem(jjd).getItemProperty("itemId").getValue()).getName());
					Label label2 = new Label(((Items) ((HackwesternUI) UI.getCurrent()).tags.getItem(jjd).getItemProperty("itemId").getValue()).getType());
					table.addItem(new Object [] {label1, label2}, ((Items) ((HackwesternUI) UI.getCurrent()).tags.getItem(jjd).getItemProperty("itemId").getValue()).getId());
				}
				tables.put(String.valueOf(itm.getItemProperty("tag").getValue()), table);
				table.setWidth("100%");
				table.setPageLength(table.size());
				
				table.setSelectable(true);
				
				table.addValueChangeListener(new ValueChangeListener () {

					@Override
					public void valueChange(ValueChangeEvent event) {
						List<Table> list = new ArrayList<Table>(tables.values());
						try {
						int str = (int) event.getProperty().getValue();
						for (int i = 0; i < list.size(); i++) {
							try {
							if (list.get(i).getValue() == null) {
								
							} else if (list.get(i).getValue() == null || !event.getProperty().getValue().equals(list.get(i).getValue())) {
								list.get(i).select(null);
							}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							
						}
						control.receiveData((int) event.getProperty().getValue()); 
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
						
						
						
					}
					
				});
				
				((HackwesternUI) UI.getCurrent()).tags.removeContainerFilter(filter);
				separateTableHolder.addComponent(new Label());
			}
			
			//System.out.println("ID: " + "   TAG: " + item.getItemProperty("tag") + item.getItemProperty("itemId"));
		}
		
	}
	
	public void receiveNewItem (Items item, List <Tags> tag) {
		((HackwesternUI) UI.getCurrent()).items.addEntity(item);
		for (int i = 0; i < tag.size(); i++) {
			((HackwesternUI) UI.getCurrent()).tags.addEntity(tag.get(i));
		}
	}

	
}

