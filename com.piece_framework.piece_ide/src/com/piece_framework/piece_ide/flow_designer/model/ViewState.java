// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ViewState extends ActionState implements IPropertySource {
    
    private static final long serialVersionUID = -3813245475047801764L;
    
    private String fView;
    
    public ViewState() {
        super();
    }
    
    public String getView() {
        return fView;
    }
    
    public void setView(String view) {
        String old = fView;
        fView = view;
        firePropertyChange("view", old, fView);
    }

    public Object getEditableValue() {
        return this;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[] {
                new TextPropertyDescriptor("view", "ビュー"),
                new TextPropertyDescriptor("name", "名前"),
                new TextPropertyDescriptor("activity", "activity"),
                new TextPropertyDescriptor("entry", "entry"),
                new TextPropertyDescriptor("exit", "exit")};
    }

    public Object getPropertyValue(Object id) {
    	
    	Object ret = super.getPropertyValue(id);
        
        if (ret != null) {
            return ret;
        }
        if (id.equals("view")) {
            return fView != null ? fView : "";
        }        
        
        return null;
    }

    public boolean isPropertySet(Object id) {
    	return id.equals("view") || super.isPropertySet(id);
    }

    public void resetPropertyValue(Object id) {
    }

    public void setPropertyValue(Object id, Object value) {
    	
    	super.setPropertyValue(id, value);
    	
    	if (id.equals("view")) {
            setView(String.valueOf(value));
        }
    }
}
