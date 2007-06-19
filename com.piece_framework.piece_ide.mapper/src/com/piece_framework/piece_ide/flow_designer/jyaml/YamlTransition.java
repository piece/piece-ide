package com.piece_framework.piece_ide.flow_designer.jyaml;



public class YamlTransition {
    
    private String event;
    private String nextState;
    private YamlService action;
    private YamlService guard;
    

    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public YamlService getGuard() {
        return guard;
    }
    public void setGuard(YamlService guard) {
        this.guard = guard;
    }
    public String getNextState() {
        return nextState;
    }
    public void setNextState(String nextState) {
        this.nextState = nextState;
    }
    public YamlService getAction() {
        return action;
    }
    public void setAction(YamlService action) {
        this.action = action;
    }
    

}
