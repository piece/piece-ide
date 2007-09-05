// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * �J�ڍ쐬�R�}���h�e�X�g.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.flow_designer.command
 *                  .CreateConnectionCommand
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class CreateConnectionCommandTest extends TestCase {
    
    /**
     * �R�}���h�����s���邱�ƂŁA�r���[�X�e�[�g����A�N�V�����X�e�[�g
     * �ւ̑J�ڂ��쐬����邱�Ƃ��e�X�g����.
     *
     */
    public void testTheCommandShouldConnectFromViewStateToActionState() {
        Flow flow = new Flow(null, null);
        State viewState1 = new State(State.VIEW_STATE);
        viewState1.setName("DisplayForm1");
        State actionState1 = new State(State.ACTION_STATE);
        actionState1.setName("Process1");
        
        flow.addState(viewState1);
        flow.addState(actionState1);
        
        Event transtionEvent = new Event(Event.TRANSITION_EVENT);
        
        CreateConnectionCommand command = 
            new CreateConnectionCommand(transtionEvent);
        
        command.setFlow(flow);
        command.setState(viewState1);
        command.setNextState(actionState1);
        command.execute();
        
        assertNotNull(transtionEvent.getName());
        assertEquals(actionState1, transtionEvent.getNextState());
    }
}
