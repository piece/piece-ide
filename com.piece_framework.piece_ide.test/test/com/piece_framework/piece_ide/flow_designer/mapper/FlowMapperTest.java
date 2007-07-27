// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piede_framework.piece_ide.flow_designer.mapper.FlowMapper;

/**
 * イベントテストクラス.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.model.event
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperTest extends TestCase {

    /**
     * getYAML メソッドテスト.
     * 以下のフローを YAML に出力できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *
     */
    public void testGetYAML_InitialViewFinal_Flow_IsOutputWithYAML() {
        Flow flow = new Flow("FlowName", "ActionClass");
        State initialState = new State(State.INITIAL_STATE);
        State viewState = new State(State.VIEW_STATE);
        viewState.setName("DisplayForm1");
        viewState.setView("Form1");
        State finalState = new State(State.FINAL_STATE);
        
        Event initialToView = new Event(Event.TRANSITION_EVENT);
        initialToView.setNextState(viewState);
        initialState.addEvent(initialToView);
        
        Event viewToFinal = new Event(Event.TRANSITION_EVENT);
        viewToFinal.setNextState(finalState);
        viewState.addEvent(viewToFinal);
        
        String yaml = FlowMapper.getYAML(flow);
        
        EventHandler activityEventHandler = null;
        EventHandler entryEventHandler = null;
        EventHandler exitEventHandler = null;
        for (Event event : viewState.getEventList()) {
            if (event.getType() == Event.BUILTIN_EVENT) {
                if (event.getName().indexOf("Activity") != -1) {
                    activityEventHandler = event.getEventHandler();
                } else if (event.getName().indexOf("Entry") != -1) {
                    entryEventHandler = event.getEventHandler();
                } else if (event.getName().indexOf("Exit") != -1) {
                    exitEventHandler = event.getEventHandler();
                }
            }
        }
        
        StringBuffer expectedYAMLBuffer = new StringBuffer();
        
        expectedYAMLBuffer.append(
            "firstState: " + viewState.getName() + "\n");
        
        expectedYAMLBuffer.append("\n");
        
        expectedYAMLBuffer.append(
            "lastState:\n"
          + "  name: " + viewState.getName() + "\n"
          + "  view: " + viewState.getView() + "\n"
          + "  activity:\n"
          + "    method: " + flow.getActionClassName() + ":"
          +                  activityEventHandler.getMethodName() + "\n"
          + "  entry:\n"
          + "    method: " + flow.getActionClassName() + ":"
                           + entryEventHandler.getMethodName() + "\n"
          +  "  exit:\n"
          +  "    method: " + flow.getActionClassName() + ":"
                            + exitEventHandler.getMethodName() + "\n");
        
        assertEquals(expectedYAMLBuffer.toString(), yaml);
    }
}
