package com.midland.base;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * Created by 'ms.x' on 2017/7/27.
 */
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
