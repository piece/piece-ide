// $Id: FlowWriter.java 56 2007-06-19 16:05:23Z nonoyan $
package com.piece_framework.piece_ide.flow_designer.jyaml;


import java.util.ArrayList;
import java.util.List;

/**
 * YAML出力用 ステートクラス.
 * YAML定義ファイルの firstState / viewState /
 * actionState / lastState に対応する。
 * 
 * @author 
 * @version 
 * @since 
 *
 */
public class YamlState {
    
    private String name;
    private String view;
    private List <YamlTransition> transition = new ArrayList<YamlTransition>();
    private YamlService entry;
    private YamlService exit;
    private YamlService activity;
    
    /**
     * ステート名を返す.
     * 
     * @return ステート名
     */    
    public String getName() {
                return name;
    }
    
    /**
     * ステート名を設定する.
     * 
     * @param fName ステート名
     */
    public void setName(String fName) {
        this.name = fName;
    }    
    
    /**
     * ビュー名を返す.
     * 
     * @return ビュー名
     */   
    public String getView() {
        return view;
    }
    
    /**
     * ビュー名を設定する.
     * 
     * @param fView ビュー名
     */
    public void setView(String fView) {
        this.view = fView;
    }

    /**
     * 遷移イベントのリストを返す.
     * 
     * @return 遷移イベントのリスト
     */  
    public List<YamlTransition> getTransition() {
        return transition;
    }
    
    /**
     * 遷移イベントのリストを設定する.
     * 
     * @param fTransition 遷移イベントのリスト
     */
    public void setTransition(List<YamlTransition> fTransition) {
        this.transition = fTransition;
    }

    /**
     * entryイベントを返す.
     * 
     * @return entryイベント
     */    
    public YamlService getEntry() {
        return entry;
    }
    
    /**
     * entryイベントを設定する.
     * 
     * @param fEntry entryイベント
     */
    public void setEntry(YamlService fEntry) {
        this.entry = fEntry;
    }
    
    /**
     * exitイベントを返す.
     * 
     * @return exitイベント
     */    
    public YamlService getExit() {
        return exit;
    }
    
    /**
     * exitイベントを設定する.
     * 
     * @param fExit exitイベント
     */
    public void setExit(YamlService fExit) {
        this.exit = fExit;
    }
    
    /**
     * activityイベントを返す.
     * 
     * @return activityイベント
     */     
    public YamlService getActivity() {
        return activity;
    }
    
    /**
     * exitイベントを返すを設定する.
     * 
     * @param fActivity activityイベントを返す
     */
    public void setActivity(YamlService fActivity) {
        this.activity = fActivity;
    }
    
    
    /**
     * スペシャルイベントを設定する.
     * イベント名をもとにスペシャルイベントを設定する
     * 
     * @param eventName 　イベント名
     * @param specalEvent 設定するスペシャルイベント
     */
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
