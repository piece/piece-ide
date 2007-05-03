// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class AbstractModel implements Serializable {

    private PropertyChangeSupport fSupport;
    
    public AbstractModel() {
        fSupport = new PropertyChangeSupport(this);
    }
    
    protected void firePropertyChange(String name, 
                                        Object oldValue, 
                                        Object newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    protected void firePropertyChagen(String name,
                                        int oldValue,
                                        int newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }
    
    protected void firePropertyChagen(String name,
                                        boolean oldValue,
                                        boolean newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        fSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        fSupport.removePropertyChangeListener(listener);
    }

}

