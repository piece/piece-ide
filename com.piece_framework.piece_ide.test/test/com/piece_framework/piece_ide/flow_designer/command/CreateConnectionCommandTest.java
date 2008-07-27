// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * 遷移作成コマンドテスト.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.command
 *                  .CreateConnectionCommand
 *
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 */
public class CreateConnectionCommandTest extends TestCase {

    /**
     * コマンドを実行することで、ビューステートからアクションステート
     * への遷移が作成されることをテストする.
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
