// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.util.ArrayList;
import java.util.List;

public class Diagram extends AbstractModel implements Container {

    private static final long serialVersionUID = 4080106758569031141L;
    
    private List<NodeElement> fContents;
    
    public Diagram() {
        super();
        fContents = new ArrayList<NodeElement>();
    }
    
    public void addContents(NodeElement element) {
        fContents.add(element);
        element.setParent(this);
        firePropertyChange("contents", null, null);
    }

    public List getContents() {
        return fContents;
    }

    public void removeContents(NodeElement element) {
        fContents.remove(element);
        element.setParent(null);
        firePropertyChange("contents", null, null);
    }

}
