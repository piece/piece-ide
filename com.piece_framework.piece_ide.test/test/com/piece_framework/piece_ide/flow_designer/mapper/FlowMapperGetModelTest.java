package com.piece_framework.piece_ide.flow_designer.mapper;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piede_framework.piece_ide.flow_designer.mapper.FlowMapper;

/**
 * �t���[�}�b�p�[���f���擾�e�X�g.
 * �e�X�g�ΏہFcom.piece_framework.piece_ide.flow_designer.mapper.FlowMapper
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FlowMapperGetModelTest extends TestCase {

    /**
     * �e�X�g���\�b�h���s�O����.
     * 
     * @exception Exception ��ʗ�O
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    }
    
    /**
     * getModel ���\�b�h�e�X�g.
     * YAML����ȉ��̃t���[���擾�ł��邱�Ƃ��e�X�g����B<br>
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
        
        assertNotNull(initialState);
        assertNotNull(viewState);
        assertNotNull(finalState);
        
//        viewState.setName("DisplayForm1");
//        viewState.setView("Form1");
//        
//        finalState.setName("FinalState");
//        
//        Event initialToView = new Event(Event.TRANSITION_EVENT);
//        initialToView.setNextState(viewState);
//        initialState.addEvent(initialToView);
//        
//        Event viewToFinal = new Event(Event.TRANSITION_EVENT);
//        viewToFinal.setNextState(finalState);
//        viewState.addEvent(viewToFinal);
    }

}
