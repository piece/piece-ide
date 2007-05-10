// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeElement extends AbstractModel {
    
    private int fX;
    private int fY;
    
    private Container fParent;
    
    private List<Transition> fIncomings = new ArrayList<Transition>();
    private List<Transition> fOutgoings = new ArrayList<Transition>();
    
    private List<Event> fEvents = new ArrayList<Event>();
    
    public int getX() {
        return fX;
    }
    
    public void setX(int x) {
        int old = fX;
        fX = x;
        firePropertyChagen("x", old, fX);
    }
    
    public int getY() {
        return fY;
    }

    public void setY(int y) {
        int old = fY;
        fY = y;
        firePropertyChagen("y", old, fY);
    }
    
    public Container getParent() {
        return fParent;
    }
    
    public void setParent(Container parent) {
        fParent = parent;
        firePropertyChange("parent", null, null);
    }
    
    public List getIncomings() {
        return fIncomings;
    }
    
    public void addIncoming(Transition transition) {
        fIncomings.add(transition);
        firePropertyChange("incoming", null, null);
    }
    
    public void removeIncoming(Transition transition) {
        fIncomings.remove(transition);
        firePropertyChange("incoming", null, null);
    }
    
    public List getOutgoings() {
        return fOutgoings;
    }
    
    public void addOutgoing(Transition transition) {
        fOutgoings.add(transition);
        firePropertyChange("outgoing", null, null);
    }
    
    public void removeOutgoing(Transition transition) {
        fOutgoings.remove(transition);
        firePropertyChange("outgoing", null, null);
    }
    
    public void addEvent(Event event) {
    	fEvents.add(event);
    }
    
    public void removeEvent(Event event) {
    	fEvents.remove(event);
    }
    
}
