// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.io.File;

import junit.framework.TestCase;


import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.EventHandler;

public class FlowWriterTestCase extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWrite() {
        
    	String workDir = "C:\\";
        String fileName = "test.flow";
        
        String infile   = workDir + fileName;
        String outfile  = workDir + "test2.flow";
        
        
        //フローライター作成
        FlowWriter fwtr = new FlowWriter();        

        
        //flowファイルの読み込み        
        fwtr.readFlowFile(infile);
            
     
        //フローファイルの更新
        if (!infile.equals(outfile)) {
            updateFlowFile(fwtr);   
            fwtr.writeFlowFile(outfile);
        }
        
        
        //YAMLファイル出力
        fwtr.writeYamlFile(workDir);
        
        
        
      //ファイル名からフロー名・アクションクラス名を生成
        String flowName = null;        
        flowName = fileName.substring(0, fileName.indexOf('.'));
        fwtr.getFlow().setName(flowName);
        
        
        
        //ファイルの存在を確認
        File file = new File(workDir + flowName + ".yaml");        
        assertTrue(file.exists());
        
        //file.delete();
    }
    
    //フローファイルを元に、イベントの設定されてたテスト用のフローファイルを作成する
    private static void updateFlowFile(FlowWriter fwtr) {
        
        if (fwtr.getFlow() == null) {
            return;
        }
        
        Flow flow = fwtr.getFlow();
        String flowName = flow.getName();
        
        //フローファイルの作成    
        //Event event ;
        
        //先にステートの名前だけ設定する
        for (State state : flow.getStates()) {
            
            if (state == null) {
                continue;
            }
            
            switch (state.getStateType()) {
            case State.INITIAL_STATE:
                state.setName("DisplayForm");
                break;                
            case State.FINAL_STATE:
                state.setName("Finish");
                state.setView("Finish");
                break;
            default:
                break;
            }
        }
        
        
        for (State state : flow.getStates()) {
            if (state == null) {
                continue;
            }
                        
               
               switch (state.getStateType()) {
                case State.INITIAL_STATE:
                    //state.setName("DisplayForm");
                    for(Event event : state.getEventList()){
                        
                        if("Initialize".equals(event.getName())){
                            if(event.getEventHandler()==null){
                                event.setEventHandler(new EventHandler("Piece_FlowTestCaseAction","initialize"));
                            }else{
                                event.getEventHandler().setClassName("Piece_FlowTestCaseAction");
                                event.getEventHandler().setMethodName("initialize");
                            }
                        }
                    }
                    break;                
                case State.FINAL_STATE:
                    //state.setName("Finish");
                    //state.setView("Finish");
                    
                    for(Event event : state.getEventList()){
                        if("Finalize".equals(event.getName())){
                            if(event.getEventHandler()==null){
                                event.setEventHandler(new EventHandler("Piece_FlowTestCaseAction","finalize"));
                            }else{
                                event.getEventHandler().setClassName("Piece_FlowTestCaseAction");
                                event.getEventHandler().setMethodName("finalize");
                            }
                        }
                    }
                    
                    Event event;
                    event = createEvent(flow ,"Entry",true ,null
                                                ,new EventHandler("Piece_FlowTestCaseAction","setupForm") 
                                                ,null);        
                    state.getEventList().add(event);
                    

                    event = createEvent(flow ,"Exit",true ,null
                                                ,new EventHandler("Piece_FlowTestCaseAction","teardownForm") 
                                                ,null);        
                    state.getEventList().add(event);

                    event = createEvent(flow ,"Activity",true ,null
                                                ,new EventHandler("Piece_FlowTestCaseAction","countDisplay") 
                                                ,null);        
                    state.getEventList().add(event);
                    
                    
                    
                    break;
                case State.ACTION_STATE:
                    if("processSubmitDisplayForm".equals(state.getName())){
                        
                        event = createEvent(flow ,"raiseError",false ,"DisplayForm",null ,null);    
                        state.getEventList().add(event);
                        
                        event = createEvent(flow ,"succeed",false ,"ConfirmForm",null    ,null);    
                        state.getEventList().add(event);                        
                        
                    } else if("processSubmitConfirmForm".equals(state.getName())) {
                        
                        event = createEvent(flow ,"raiseError",false ,"DisplayForm",null ,null);    
                        state.getEventList().add(event);
                        
                        event = createEvent(flow ,"succeed",false ,"Register"
                                                ,new EventHandler("Piece_FlowTestCaseAction","register") 
                                                ,null);        
                        state.getEventList().add(event);                        
                        
                    } else if("Register".equals(state.getName())) {
                        
                        event = createEvent(flow ,"raiseError",false ,"DisplayForm", null, null);    
                        state.getEventList().add(event);
                        
                        event = createEvent(flow, "succeed", false, "Finish", null, null);
                        state.getEventList().add(event);
                        
                    }
                    break;
                case State.VIEW_STATE:
                    if("DisplayForm".equals(state.getName())){
                        state.setView("Form");
                        
                        event = createEvent(flow ,"Entry",true ,null
                                                    ,new EventHandler("Piece_FlowTestCaseAction","setupForm") 
                                                    ,null);        
                        state.getEventList().add(event);                        
                    
                        event = createEvent(flow ,"Exit",true ,null
                                                    ,new EventHandler("Piece_FlowTestCaseAction","teardownForm") 
                                                    ,null);        
                        state.getEventList().add(event);
                    
                        event = createEvent(flow ,"Activity",true ,null
                                                    ,new EventHandler("Piece_FlowTestCaseAction","countDisplay") 
                                                    ,null);        
                        state.getEventList().add(event);
                        

                        event = createEvent(flow ,"submit",false ,"processSubmitDisplayForm"
                                                ,new EventHandler("Piece_FlowTestCaseAction","validateInput") 
                                                ,new EventHandler("Piece_FlowTestCaseAction","isPermitted"));        
                        state.getEventList().add(event);
                                            
                    }else if("ConfirmForm".equals(state.getName())){
                        state.setView("Confirmation");
                        event = createEvent(flow ,"submit",false ,"processSubmitConfirmForm"
                                ,new EventHandler("Piece_FlowTestCaseAction","validateConfirmation") 
                                ,null);        
                        state.getEventList().add(event);                
                    }    
                    break;
                default :
                    break;
            }
       }
       
    }
    
    private static Event createEvent(Flow flow ,String name,boolean sp,String nestState,EventHandler eH,EventHandler gH){
        Event event = new Event();
        event.setName(name);
        event.setSpecialEvent(sp);
        event.setNextState(getStateByName(flow, nestState));
        event.setEventHandler(eH);
        event.setGuardEventHandler(gH);
        return event;
    }
    
    private static State getStateByName(Flow flow, String name) {
        if (name == null) {
            return null;
        }
        for (State state : flow.getStates()) {
           if (name.equals(state.getName())) {
               return state;
           }            
        }
        return null;
    }

}
