// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.SimpleRaisedBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
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
    
    private EventListFigure fSpecialEventList;
    private boolean fVisibleEventList;
    
    private static final RGB STATE_COLOR = new RGB(200, 120, 100);
    
    private static final RGB EVENT_SPECIAL_TITLE_COLOR = new RGB(150, 150, 100);
    private static final RGB EVENT_SPECIAL_LIST_COLOR = new RGB(100, 100, 100);
    
    
    private static final int MARGIN_HEIGHT = 5;
    
    /**
     * コンストラクタ.
     * ステートに描画するフィギュアーを作成する。
     * 
     */
    public ActionStateFigure() {
        Color bg = new Color(Display.getCurrent(), STATE_COLOR);
        setBackgroundColor(bg);
        
        setLayoutManager(new XYLayout());
        
        fNameLabel = new Label();
        fNameLabel.setTextAlignment(Label.CENTER);
        add(fNameLabel);
        
        fSpecialEventList = 
            new EventListFigure(
                    "スペシャルイベント",
                    new Color(Display.getCurrent(), EVENT_SPECIAL_TITLE_COLOR),
                    new Color(Display.getCurrent(), EVENT_SPECIAL_LIST_COLOR));
        fSpecialEventList.addEvent("イベントはありません。");
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
            (int) ((stateSize.width - nameLabelSize.width) / 2), MARGIN_HEIGHT, 
            -1, -1);
        setConstraint(fNameLabel, constraint);
        
        if (isVisibleEventList()) {
            Dimension eventListSize = fSpecialEventList.getSize();
            
            constraint = new Rectangle(
                (int) ((stateSize.width - eventListSize.width) / 2), 
                MARGIN_HEIGHT + nameLabelSize.height + MARGIN_HEIGHT, 
                -1, -1);
            setConstraint(fSpecialEventList, constraint);
        }
    }

    /**
     * ステート名を設定する.
     * 
     * @param name ステート名
     */
    public void setName(String name) {
        if (name != null) {
            fNameLabel.setText("  "  + name + "  ");
        }
    }
    
    public boolean isVisibleEventList() {
        return fVisibleEventList;
    }
    
    public void setVisibleEventList(boolean visible) {
        if (visible) {
            add(fSpecialEventList);
        } else {
            remove(fSpecialEventList);
        }
        fVisibleEventList = visible;
        repaint();
    }
}
