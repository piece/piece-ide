// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.io.Serializable;

public class Event implements Serializable {

    private static final long serialVersionUID = 2259605742197882832L;
    
    private String fName;
    private String fClassName;
    private String fMethodName;
    
    public String getName() {
        return fName;
    }
    
    public void setName(String name) {
        fName = name;
    }
    
    public String getClassName() {
        return fClassName;
    }
    
    public void setClassName(String className) {
        fClassName = className;
    }
    
    public String getMethodName() {
        return fMethodName;
    }
    
    public void setMethodName(String methodName) {
        fMethodName = methodName;
    }
}
