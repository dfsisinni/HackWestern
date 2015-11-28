package com.hackwestern.search;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table.Align;

public class Display extends DisplayTags{
	
	public Display () {
		tagTable.addContainerProperty("Tags", Label.class, null);
		tagTable.setColumnAlignment("Tag", Align.CENTER);
		
		super.addTag.addClickListener(new ClickListener () {
			@Override
			public void buttonClick(ClickEvent event) {
				tagTable.addItem(new Object [] {new Label(textBox)},textBox.getValue());
			}
			
		});
		//super.cancel.addClickListener(new ClickListener());
		//super.save.addClickListener(new ClickListener());
	}	
}
