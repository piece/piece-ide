// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.util.List;

/**
 * コンテナインターフェイス.
 * 子を持つ場合にはこのインターフェイスを実装する必要がある。
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public interface IFlow {
    
    /**
     * 保持しているステートリストを返す.
     * 
     * @return ステートリスト.
     */
    List getStates();
    
    /**
     * ステートを追加する.
     * 
     * @param state ステート
     */
    void addState(State state);
    
    /**
     * ステートを削除する.
     * 
     * @param state ステート
     */
    void removeState(State state);
}
