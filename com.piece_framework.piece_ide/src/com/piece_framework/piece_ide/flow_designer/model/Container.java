// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.util.List;

public interface Container {
    
    List getContents();
    
    void addContents(State element);
    
    void removeContents(State element);
}
