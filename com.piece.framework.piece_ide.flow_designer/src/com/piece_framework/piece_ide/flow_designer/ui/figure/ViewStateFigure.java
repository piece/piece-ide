// $Id: ViewStateFigure.java 212 2007-08-07 23:51:15Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

/**
 * ビューステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ViewStateFigure extends NormalStateFigure {

    private Label fViewLabel;

    private static final RGB STATE_COLOR = new RGB(80, 80, 200);
    
    private static final RGB EVENT_BUILTIN_TITLE_COLOR = new RGB(0, 0, 255);
    private static final RGB EVENT_BUILTIN_LIST_COLOR = new RGB(156, 207, 255);
    
    private static final RGB EVENT_TRANSITION_TITLE_COLOR = new RGB(0, 255, 0);
    private static final RGB EVENT_TRANSITION_LIST_COLOR = 
                                                    new RGB(206, 255, 206);
    
    private static final RGB EVENT_INTERNAL_TITLE_COLOR = new RGB(255, 0, 0);
    private static final RGB EVENT_INTERNAL_LIST_COLOR = new RGB(255, 154, 206);
    
    /**
     * コンストラクタ.
     * ステートに描画するフィギュアーを作成する。
     * 
     */
    public ViewStateFigure() {
        super();
        
        fViewLabel = new Label();
        fViewLabel.setTextAlignment(Label.CENTER);
        add(fViewLabel);
    }
    
    /**
     * 子フィギュアのサイズを調整する.
     * 
     * @param graphics グラフィックス
     * @see org.eclipse.draw2d.Figure#paint(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        
        fitNameLabel();
        fitViewLabel();
        
        if (isVisibleEventList()) {
            Dimension nameLabelSize = getNameLabel().getSize();
            Dimension viewLabelSize = fViewLabel.getSize();
            
            fitEventList(nameLabelSize.height
                         + viewLabelSize.height
                         + getMargin());
        }
    }
    
    /**
     * ビューラベルのサイズ・位置をステートに合わせる.
     * 
     */
    private void fitViewLabel() {
        Dimension stateSize = getSize();
        Dimension nameLabelSize = getNameLabel().getSize();
        Dimension viewLabelSize = fViewLabel.getSize();
        
        Rectangle constraint = new Rectangle(
                (int) ((stateSize.width - viewLabelSize.width) / 2)
                            - getMargin(), 
                nameLabelSize.height, 
                -1, -1);
        setConstraint(fViewLabel, constraint);
    }
    
    /**
     * ビュー名を設定する.
     * 
     * @param view ビュー名
     */
    public void setView(String view) {
        if (view != null && view.length() > 0) {
            fViewLabel.setText("[" + view + "]");
        } else {
            fViewLabel.setText("");
        }
    }

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
