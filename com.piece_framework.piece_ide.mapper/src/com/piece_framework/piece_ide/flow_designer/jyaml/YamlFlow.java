// $Id: FlowWriter.java 56 2007-06-19 16:05:23Z nonoyan $
package com.piece_framework.piece_ide.flow_designer.jyaml;

import java.util.ArrayList;
import java.util.List;


/**
 * YAML出力用 フロークラス.
 * YAML定義ファイルの flow に対応する。
 * 
 * @author 
 * @version 
 * @since 
 *
 */
public class YamlFlow {
    
    private String firstState; 
    private List <YamlState>   viewState = new ArrayList<YamlState>();
    private List <YamlState> actionState = new ArrayList<YamlState>();
    private YamlState lastState; 
    private YamlService initial;
    private YamlService final_;
    

    /**
     * ファーストステート名を返す.
     * 
     * @return ファーストステート名
     */    
    public String getFirstState() {
        return firstState;
    }
    
    /**
     * ファーストステート名を設定する.
     * 
     * @param fFirstState ファーストステート名
     */
    public void setFirstState(String fFirstState) {
        this.firstState = fFirstState;
    }
    
    
    /**
     * ビューステートのリストを返す.
     * 
     * @return ビューステートのリスト
     */
    public List<YamlState> getViewState() {
        return viewState;
    }
    
    /**
     * ビューステートのリストを設定する.
     * 
     * @param fViewState ビューステートのリスト
     */
    public void setViewState(List<YamlState> fViewState) {
        this.viewState = fViewState;
    }
    
    /**
     * アクションステートのリストを返す.
     * 
     * @return アクションステートのリスト
     */
    public List<YamlState> getActionState() {
        return actionState;
    }
    
    /**
     * アクションステートのリストを設定する.
     * 
     * @param fActionState アクションステートのリスト
     */
    public void setActionState(List<YamlState> fActionState) {
        this.actionState = fActionState;
    }
    
    /**
     * ラストステートを返す.
     * 
     * @return ラストステート
     */
    public YamlState getLastState() {
        return lastState;
    }
    
    /**
     * ラストステートを設定する.
     * 
     * @param fLastState ラストステート
     */
    public void setLastState(YamlState fLastState) {
        this.lastState = fLastState;
    }
    
    

    /**
     * イニシャルイベントを返す.
     * 
     * @return イニシャルイベント
     */
    public YamlService getInitial() {
        return initial;
    }
    
    /**
     * イニシャルイベントを設定する.
     * 
     * @param fInitial イニシャルイベント
     */
    public void setInitial(YamlService fInitial) {
        this.initial = fInitial;
    }
    

    /**
     * ファイナルイベントを返す.
     * 
     * @return ファイナルイベント
     */
    public YamlService getFinal_() {
        return final_;
    }
    
    /**
     * ファイナルイベントを設定する.
     * 
     * @param fFinal ファイナルイベント
     */
    public void setFinal_(YamlService fFinal) {
        this.final_ = fFinal;
    }
    
    
}
