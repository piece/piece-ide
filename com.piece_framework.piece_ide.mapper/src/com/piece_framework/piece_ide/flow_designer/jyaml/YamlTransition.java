// $Id: YamlTransition.java 56 2007-06-19 16:05:23Z nonoyan $
package com.piece_framework.piece_ide.flow_designer.jyaml;


/**
 * YAML出力用 遷移イベントクラス.
 * YAML定義ファイルの transition に対応する。
 * 
 * @author 
 * @version 
 * @since 
 *
 */
public class YamlTransition {
    
    private String event;
    private String nextState;
    private YamlService action;
    private YamlService guard;
    
    /**
     * イベント名を返す.
     * 
     * @return イベント名
     */    
    public String getEvent() {
        return event;
    }  
    
    /**
     * イベント名を設定する.
     * 
     * @param fEvent イベント名
     */
    public void setEvent(String fEvent) {
        this.event = fEvent;
    }
    
    /**
     * 遷移先ステート名を返す.
     * 
     * @return 遷移先ステート名
     */ 
    public String getNextState() {
        return nextState;
    }    
    
    /**
     * 遷移先ステート名を設定する.
     * 
     * @param fNextState 遷移先ステート名
     */
    public void setNextState(String fNextState) {
        this.nextState = fNextState;
    }
    
    /**
     * アクションを返す.
     * 
     * @return アクション
     */  
    public YamlService getAction() {
        return action;
    }
    
    /**
     * アクションを設定する.
     * 
     * @param fAction アクション
     */
    public void setAction(YamlService fAction) {
        this.action = fAction;
    }
    
    /**
     * ガードを返す.
     * 
     * @return ガード
     */  
    public YamlService getGuard() {
        return guard;
    }
    
    /**
     * ガードを設定する.
     * 
     * @param fGuard ガード
     */
    public void setGuard(YamlService fGuard) {
        this.guard = fGuard;
    }    

}
