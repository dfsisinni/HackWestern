package com.hackwestern.search;
import com.vaadin.ui.Button.ClickEvent;
import com.example.hackwestern.HackwesternUI;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

import java.util.HashMap;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
<<<<<<< HEAD
=======
import com.vaadin.ui.themes.ValoTheme;
>>>>>>> 40dad2c488bf22203c5985ade5f2b73710d87711
import com.vaadin.ui.Table.Align;

public class Display extends DisplayTags{
	int i = 0;
	
	public Display () {
		tagTable.addContainerProperty("Tags", Label.class, null);
		tagTable.setColumnAlignment("Tags", Align.CENTER);
		HashMap map = new HashMap();
		
<<<<<<< HEAD
		
=======
		cancel.addStyleName(ValoTheme.BUTTON_DANGER);
		save.addStyleName(ValoTheme.BUTTON_FRIENDLY);
>>>>>>> 40dad2c488bf22203c5985ade5f2b73710d87711
		
		super.addTag.addClickListener(new ClickListener () {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!map.containsKey(textBox.getValue())){
					Object id = tagTable.addItem();
					Item item = tagTable.getItem(id);
					Property p_field = (Property) item.getItemProperty("Tags");
					p_field.setValue(new Label(textBox.getValue()));
					map.put(textBox.getValue(),i);
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