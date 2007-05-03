// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ViewState extends NodeElement implements IPropertySource {
    
    private static final long serialVersionUID = -3813245475047801764L;

    private String fName;
    
    private String fView;
    
    public ViewState() {
        super();
    }
    
    public String getName() {
        return fName;
    }
    
    public void setName(String name) {
        String old = fName;
        fName = name;
        firePropertyChange("name", old, fName);
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
                new TextPropertyDescriptor("name", "名前")};
    }

    public Object getPropertyValue(Object id) {
        if (id.equals("name")) {
            return fName != null ? fName : "";
        } else if (id.equals("view")) {
            return fView != null ? fView : "";
        }
        return null;
    }

    public boolean isPropertySet(Object id) {
        return id.equals("name") || id.equals("view");
    }

    public void resetPropertyValue(Object id) {
    }

    public void setPropertyValue(Object id, Object value) {
        if (id.equals("name")) {
            setName(String.valueOf(value));
        } else if (id.equals("view")) {
            setView(String.valueOf(value));
        }
    }
}
