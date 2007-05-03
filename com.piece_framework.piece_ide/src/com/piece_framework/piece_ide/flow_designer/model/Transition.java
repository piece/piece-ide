// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

public class Transition extends AbstractModel {

    private static final long serialVersionUID = 3334842354811260446L;
    
    private NodeElement fSource;
    private NodeElement fTarget;
    
    public NodeElement getSource() {
        return fSource;
    }
    
    public void setSource(NodeElement source) {
        fSource = source;
        firePropertyChange("source", null, fSource);
    }
    
    public NodeElement getTarget() {
        return fTarget;
    }
    
    public void setTarget(NodeElement target) {
        fTarget = target;
        firePropertyChange("target", null, fTarget);
    }
}
