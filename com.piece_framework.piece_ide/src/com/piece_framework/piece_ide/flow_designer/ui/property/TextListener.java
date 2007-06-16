package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * プロパティシートのテキストボックスのイベントを処理する.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public abstract class TextListener implements Listener {
    
    /**
     * キー・ダウン、フォーカスアウトのイベントを処理する.
     * 
     * @param event イベントオブジェクト
     * @see org.eclipse.swt.widgets.Listener
     *          #handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.KeyDown:
            if (event.character == SWT.CR) {
                changeText((Control) event.widget);
            }
            break;
        case SWT.FocusOut:
            changeText((Control) event.widget);
            break;
        default:
            break;
        }
    }
    
    /**
     * テキストボックスで Enter キーが押されたときか、
     * フォーカスアウトしたときに発生するイベント.
     * 
     * @param control イベントが発生したコントロール
     */
    public abstract void changeText(Control control);
}
