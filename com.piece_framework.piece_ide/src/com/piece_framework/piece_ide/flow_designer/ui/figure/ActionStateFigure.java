// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * アクションステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ActionStateFigure extends RoundedRectangle {

    private Label fNameLabel;
    
    private RectangleFigure fEventList;
    private EventListFigure fSpecialEventList;
    private EventListFigure fTransitionEventList;
    private EventListFigure fInternalEventList;
    private boolean fVisibleEventList;
    
    private static final RGB STATE_COLOR = new RGB(200, 120, 100);
    
    private static final RGB EVENT_SPECIAL_TITLE_COLOR = new RGB(0, 0, 255);
    private static final RGB EVENT_SPECIAL_LIST_COLOR = new RGB(156, 207, 255);
    
    private static final RGB EVENT_TRANSITION_TITLE_COLOR = new RGB(0, 255, 0);
    private static final RGB EVENT_TRANSITION_LIST_COLOR = 
                                                    new RGB(206, 255, 206);
    
    private static final RGB EVENT_INTERNAL_TITLE_COLOR = new RGB(255, 0, 0);
    private static final RGB EVENT_INTERNAL_LIST_COLOR = new RGB(255, 154, 206);
    
    private static final int MARGIN = 5;
    
    /**
     * コンストラクタ.
     * ステートに描画するフィギュアーを作成する。
     * 
     */
    public ActionStateFigure() {
        Color bg = new Color(Display.getCurrent(), STATE_COLOR);
        setBackgroundColor(bg);
        
        setLayoutManager(new XYLayout());
        setBorder(new MarginBorder(MARGIN));
        
        fNameLabel = new Label();
        fNameLabel.setTextAlignment(Label.CENTER);
        add(fNameLabel);
        
        fEventList = new RectangleFigure();
        fEventList.setBorder(null);
        fEventList.setLayoutManager(new ToolbarLayout());
        
        fSpecialEventList = 
            new EventListFigure(
                "スペシャルイベント",
                new Color(Display.getCurrent(), EVENT_SPECIAL_TITLE_COLOR),
                new Color(Display.getCurrent(), EVENT_SPECIAL_LIST_COLOR));
        fSpecialEventList.addEvent("イベントはありません。");
        fEventList.add(fSpecialEventList);
        
        fTransitionEventList = 
            new EventListFigure(
                "遷移イベント",
                new Color(Display.getCurrent(), EVENT_TRANSITION_TITLE_COLOR),
                new Color(Display.getCurrent(), EVENT_TRANSITION_LIST_COLOR));
        fTransitionEventList.addEvent("イベントはありません。");
        fEventList.add(fTransitionEventList);
        
        fInternalEventList = 
            new EventListFigure(
                "内部イベント",
                new Color(Display.getCurrent(), EVENT_INTERNAL_TITLE_COLOR),
                new Color(Display.getCurrent(), EVENT_INTERNAL_LIST_COLOR));
        fInternalEventList.addEvent("イベントはありません。");
        fEventList.add(fInternalEventList);
        
        fVisibleEventList = false;
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
        
        Dimension stateSize = getSize();
        Dimension nameLabelSize = fNameLabel.getSize();
        
        Rectangle constraint = new Rectangle(
            (int) ((stateSize.width - nameLabelSize.width) / 2) - MARGIN, -1, 
            -1, -1);
        setConstraint(fNameLabel, constraint);
        
        if (isVisibleEventList()) {
            Dimension eventListSize = fEventList.getSize();
            
            constraint = new Rectangle(
                (int) ((stateSize.width - eventListSize.width) / 2) - MARGIN, 
                nameLabelSize.height + MARGIN, 
                -1, -1);
            setConstraint(fEventList, constraint);
        }
    }

    /**
     * ステート名を設定する.
     * 
     * @param name ステート名
     */
    public void setName(String name) {
        if (name != null) {
            fNameLabel.setText(name);
        }
    }
    
    /**
     * イベントリストが表示されているかを返す.
     * 
     * @return イベントリストが表示されているか
     */
    public boolean isVisibleEventList() {
        return fVisibleEventList;
    }
    
    /**
     * イベントリストの表示・非表示を切り替える.
     * 
     * @param visible イベントリストを表示するか
     */
    public void setVisibleEventList(boolean visible) {
        if (visible) {
            add(fEventList);
        } else {
            remove(fEventList);
        }
        fVisibleEventList = visible;
        repaint();
    }
}
