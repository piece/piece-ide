// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.io.Serializable;

public class Event implements Serializable {

	private static final long serialVersionUID = 2259605742197882832L;
	
	private String fName;
    
    public String getName() {
        return fName;
    }
    
    public void setName(String name) {
        fName = name;
    }
}
