package com.hackwestern.lists;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { … }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class multiForm extends VerticalLayout {
	protected VerticalLayout vertBar;
	protected TextArea notes;
	protected Button save;
	protected Button cancel;
	protected Table tagTable;
	protected TextField textField;
	protected Button add;
	protected Button delete;

	public multiForm() {
		Design.read(this);
	}
}
