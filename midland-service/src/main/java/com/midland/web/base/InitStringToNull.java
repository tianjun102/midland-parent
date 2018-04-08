package com.midland.web.base;

import org.springframework.beans.propertyeditors.PropertiesEditor;
public class InitStringToNull extends PropertiesEditor {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals("") || text.equals("null")) {
			text = null;
		}
		setValue(text);
	 }
	 @Override
	 public String getAsText() {
		 return null;
	 }
}
