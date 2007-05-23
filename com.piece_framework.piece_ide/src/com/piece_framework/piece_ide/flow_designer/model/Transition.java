// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

/**
 * 遷移モデルクラス.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class Transition extends AbstractModel {

    private static final long serialVersionUID = 3334842354811260446L;
    
    private State fSource;
    private State fTarget;
    
    /**
     * 遷移元ステートを返す.
     * 
     * @return 遷移元ステート
     */
    public State getSource() {
        return fSource;
    }
    
    /**
     * 遷移元ステートを設定する.
     * 
     * @param source 遷移元ステート
     */
    public void setSource(State source) {
        fSource = source;
        firePropertyChange("source", null, fSource);
    }
    
    /**
     * 遷移先ステートを返す.
     * 
     * @return 遷移先ステート
     */
    public State getTarget() {
        return fTarget;
    }
    
    /**
     * 遷移先ステートを設定する.
     * 
     * @param target 遷移先ステート
     */
    public void setTarget(State target) {
        fTarget = target;
        firePropertyChange("target", null, fTarget);
    }
}
