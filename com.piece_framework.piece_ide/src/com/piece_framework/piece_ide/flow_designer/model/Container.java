// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.util.List;

public interface Container {
    
    List getContents();
    
    void addContents(NodeElement element);
    
    void removeContents(NodeElement element);
}
