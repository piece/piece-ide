package com.piece_framework.piece_ide.mapper.jyaml;

import java.util.ArrayList;
import java.util.List;



public class YamlFlow {
    
    private String firstState; 
    private List <YamlState>   viewState = new ArrayList<YamlState>();
    private List <YamlState> actionState = new ArrayList<YamlState>();
    private YamlState lastState; 
    private YamlService initial;
    private YamlService final_;
    
    public List<YamlState> getActionState() {
        return actionState;
    }
    public void setActionState(List<YamlState> state) {
        this.actionState = state;
    }
    public YamlService getFinal_() {
        return final_;
    }
    public void setFinal_(YamlService service) {
        this.final_ = service;
    }
    public String getFirstState() {
        return firstState;
    }
    public void setFirstState(String state) {
        this.firstState = state;
    }
    public YamlService getInitial() {
        return initial;
    }
    public void setInitial(YamlService service) {
        this.initial = service;
    }
    public YamlState getLastState() {
        return lastState;
    }
    public void setLastState(YamlState state) {
        this.lastState = state;
    }
    public List<YamlState> getViewState() {
        return viewState;
    }
    public void setViewState(List<YamlState> state) {
        this.viewState = state;
    }    
    
    
    
    
    
}
