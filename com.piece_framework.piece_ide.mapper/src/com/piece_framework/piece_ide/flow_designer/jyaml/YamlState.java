package com.piece_framework.piece_ide.flow_designer.jyaml;


import java.util.ArrayList;
import java.util.List;

public class YamlState {
    
    private String name;
    private String view;
    private List <YamlTransition> transition = new ArrayList<YamlTransition>();
    private YamlService entry;
    private YamlService exit;
    private YamlService activity;
    
    public String getName() {
                return name;
    }
    public void setName(String name) {
        this.name = name;
    }    
    
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }

    
    public List<YamlTransition> getTransition() {
        return transition;
    }
    public void setTransition(List<YamlTransition> trans) {
        this.transition = trans;
    }

    public YamlService getEntry() {
        return entry;
    }
    public void setEntry(YamlService service) {
        this.entry = service;
    }
    public YamlService getExit() {
        return exit;
    }
    public void setExit(YamlService service) {
        this.exit = service;
    }
    public YamlService getActivity() {
        return activity;
    }
    public void setActivity(YamlService service) {
        this.activity = service;
    }
    
    
    
    public void setSpecialEvent(String eventName, YamlService specalEvent) {
        
        if ("Entry".equals(eventName)) {
            setEntry(specalEvent);                        
        } else if ("Exit".equals(eventName)) {
            setExit(specalEvent);                    
        } else if ("Activity".equals(eventName)) {
            setActivity(specalEvent);                    
        }
    }
    
}
