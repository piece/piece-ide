// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * テスト用のリスナー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class TestPropertyChangeListener implements PropertyChangeListener {
    private PropertyChangeEvent fEvent;
    
    /**
     * プロパティー変更イベントを処理する.
     * 
     * @param event プロパティー変更イベント
     * @see java.beans.PropertyChangeListener
     *          #propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        fEvent = event;
    }
    
    /**
     * プロパティー変更イベントを返す.
     * 
     * @return プロパティー変更イベント
     */
    public PropertyChangeEvent getPropertyChangeEvent() {
        return fEvent;
    }
    
    /**
     * プロパティー変更イベントを初期化する.
     * 
     */
    public void initializePropertyChangeEvent() {
        fEvent = null;
    }
}
