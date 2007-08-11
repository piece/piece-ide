package com.piece_framework.piece_ide.flow_designer.mapper;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piede_framework.piece_ide.flow_designer.mapper.FlowMapper;

/**
 * フローマッパーモデル取得テスト.
 * テスト対象：com.piece_framework.piece_ide.flow_designer.mapper.FlowMapper
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperGetModelTest extends TestCase {

    /**
     * テストメソッド実行前処理.
     * 
     * @exception Exception 一般例外
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    }
    
    /**
     * getModel メソッドテスト.
     * YAMLから以下のフローを取得できることをテストする。<br>
     * [Initial]-->[View]-->[Final]
     *
     */
    public void testGetModelShouldReturn_Initial_View_Final_Flow() {
        String yamlString = "firstState: DisplayForm1\n"
                          + "\n"
                          + "lastState:\n"
                          + "  name: DisplayForm1\n"
                          + "  view: Form1\n"
                          + "  entry:\n"
                          + "    method: ActionClass:doEntryOnDisplayForm1\n"
                          + "  activity:\n"
                          + "    method: ActionClass:doActivityOnDisplayForm1\n"
                          + "  exit:\n"
                          + "    method: ActionClass:doExitOnDisplayForm1\n";
        
        FlowMapper flowMapper = new FlowMapper();
        Flow flow = (Flow) flowMapper.getModel(yamlString);
        
        assertNotNull(flow);
        assertEquals(3, flow.getStateList().size());
        
        State initialState = null;
        State viewState = null;
        State finalState = null;
        
        for (State state : flow.getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                initialState = state;
            } else if (state.getType() == State.FINAL_STATE) {
                finalState = state;
            } else if (state.getType() == State.VIEW_STATE) {
                viewState = state;
            } else {
                fail();
            }   
        }
        
        // ステートのアサーション
        assertNotNull(initialState);
        
        assertNotNull(viewState);
        assertEquals("DisplayForm1", viewState.getName());
        assertEquals("Form1", viewState.getView());
        
        assertNotNull(finalState);
        
        // イベントのアサーション
        assertEquals(2, initialState.getEventList().size());
        Event initialToView = null;
        for (Event event : initialState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                initialToView = event;
            }
        }
        assertNotNull(initialToView);
        assertEquals(viewState, initialToView.getNextState());
        
        assertEquals(4, viewState.getEventList().size());
        Event viewToFinal = null;
        for (Event event : viewState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                viewToFinal = event;
            }
        }
        assertNotNull(viewToFinal);
        assertEquals(finalState, viewToFinal.getNextState());
        
        assertEquals(1, finalState.getEventList().size());
    }
    
    // YAMLが不正な場合
    // 不正なキーが含まれている
    
}
