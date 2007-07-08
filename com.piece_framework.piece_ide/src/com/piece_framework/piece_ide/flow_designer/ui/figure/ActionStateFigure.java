// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.RGB;

/**
 * アクションステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ActionStateFigure extends NormalStateFigure {

    private static final RGB STATE_COLOR = new RGB(200, 120, 100);
    
    private static final RGB EVENT_BUILTIN_TITLE_COLOR = new RGB(0, 0, 255);
    private static final RGB EVENT_BUILTIN_LIST_COLOR = new RGB(156, 207, 255);
    
    private static final RGB EVENT_TRANSITION_TITLE_COLOR = new RGB(0, 255, 0);
    private static final RGB EVENT_TRANSITION_LIST_COLOR = 
                                            new RGB(206, 255, 206);
    
    private static final RGB EVENT_INTERNAL_TITLE_COLOR = new RGB(255, 0, 0);
    private static final RGB EVENT_INTERNAL_LIST_COLOR = new RGB(255, 154, 206);
    
    /**
     * ステート色を返す.
     * 
     * @return ステート色.
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getStateColor()
     */
    @Override
    RGB getStateColor() {
        return STATE_COLOR;
    }
    
    /**
     * ビルトインイベントのタイトル色を返す.
     * 
     * @return ビルトインイベントのタイトル色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getBuiltinEventTitleColor()
     */
    @Override
    RGB getBuiltinEventTitleColor() {
        return EVENT_BUILTIN_TITLE_COLOR;
    }
    
    /**
     * ビルトインイベントのリスト色を返す.
     * 
     * @return ビルトインイベントのリスト色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getBuiltinEventListColor()
     */
    @Override
    RGB getBuiltinEventListColor() {
        return EVENT_BUILTIN_LIST_COLOR;
    }
    
    /**
     * 遷移イベントのタイトル色を返す.
     * 
     * @return 遷移イベントのタイトル色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getTransitionEventTitleColor()
     */
    @Override
    RGB getTransitionEventTitleColor() {
        return EVENT_TRANSITION_TITLE_COLOR;
    }
    
    /**
     * 遷移イベントのリスト色を返す.
     * 
     * @return 遷移イベントのリスト色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getTransitionEventListColor()
     */
    @Override
    RGB getTransitionEventListColor() {
        return EVENT_TRANSITION_LIST_COLOR;
    }
    
    /**
     * 内部イベントのタイトル色を返す.
     * 
     * @return 内部イベントのタイトル色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getInternalEventTitleColor()
     */
    @Override
    RGB getInternalEventTitleColor() {
        return EVENT_INTERNAL_TITLE_COLOR;
    }
    
    /**
     * 内部イベントのリスト色を返す.
     * 
     * @return 内部イベントのリスト色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getInternalEventListColor()
     */
    @Override
    RGB getInternalEventListColor() {
        return EVENT_INTERNAL_LIST_COLOR;
    }
}
