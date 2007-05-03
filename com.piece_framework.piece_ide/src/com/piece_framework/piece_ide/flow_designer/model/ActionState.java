// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ActionState extends NodeElement implements IPropertySource {
    
    private static final long serialVersionUID = -3753390580717540401L;
    
    private String fName;
    
    public ActionState() {
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
    
    public Object getEditableValue() {
        return this;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        return new IPropertyDescriptor[] {
                new TextPropertyDescriptor("name", "名前")};
    }

    public Object getPropertyValue(Object id) {
        if (id.equals("name")) {
            return fName != null ? fName : "";
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
        }
    }
}
