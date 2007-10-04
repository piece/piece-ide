package com.piece_framework.piece_ide.flow_designer.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

public class AdjustEventCommand extends Command {
    private Flow fFlow;
    private Map<Event, Event> fEventMap;
    
    public AdjustEventCommand(Flow flow) {
        fFlow = flow;
        fEventMap = new HashMap<Event, Event>();
    }

    @Override
    public boolean canExecute() {
        return MessageDialog.openQuestion(null, "イベントの調整",  // Adjust events
                "イベント名・イベントハンドラ名をステート名に合わせて更新します。\n"
                + "よろしいですか？");
    }

    @Override
    public void execute() {
        if (fFlow == null) {
            return;
        }
        
        for (State state : fFlow.getStateList()) {
            List<Event> currentEventList = new ArrayList<Event>();
            for (Event event : state.getEventList()) {
                currentEventList.add(event);
            }
            
            for (Event event : currentEventList) {
                state.removeEvent(event);

                Event newEvent = new Event(event.getType());
                if (event.getType() == Event.BUILTIN_EVENT) {
                    newEvent.setName(event.getName());
                } else {
                    newEvent.setName(
                        state.generateEventName(event.getNextState().getName()));
                    newEvent.setNextState(event.getNextState());
                }
                System.out.println(event.getName() + "->" + newEvent.getName());
                if (event.getEventHandler() != null) {
                    boolean isNormalState =
                                state.getType() == State.VIEW_STATE
                                || state.getType() == State.ACTION_STATE;
                    boolean isBuildinEvent = 
                                event.getType() == Event.BUILTIN_EVENT;

                    String className = event.getEventHandlerClassName();
                    String methodName = null;
                    if (isNormalState && isBuildinEvent) {
                        methodName = newEvent.generateEventHandlerMethodName() 
                                     + "On" + state.getName();
                    } else {
                        methodName = newEvent.generateEventHandlerMethodName();
                    }
                    if (className != null) {
                        newEvent.setEventHandler(className + ":" + methodName);
                    } else {
                        newEvent.setEventHandler(methodName);
                    }
                }
                newEvent.setGuardEventHandler(event.getGuardEventHandler());
                
                fEventMap.put(newEvent, event);
                
                state.addEvent(newEvent);
            }
        }
    }
    
    @Override
    public void undo() {
        for (State state : fFlow.getStateList()) {
            List<Event> currentEventList = new ArrayList<Event>();
            for (Event event : state.getEventList()) {
                currentEventList.add(event);
            }
            
            for (Event event : currentEventList) {
                Event oldEvent = fEventMap.get(event);
                
                state.removeEvent(event);
                state.addEvent(oldEvent);
            }
        }
    }
}
