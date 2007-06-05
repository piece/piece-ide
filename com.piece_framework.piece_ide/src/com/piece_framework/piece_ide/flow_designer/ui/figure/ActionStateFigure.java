// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Figure;
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
    private Label fEventLabel;
    
    private EventListFigure fEventList;
    
    private static final RGB STATE_COLOR = new RGB(200, 120, 100); 
    
    private static final int DEFAULT_WIDTH = 80;
    private static final int DEFAULT_HEIGHT = 50;
    
    /**
     * コンストラクタ.
     * ステートに描画するフィギュアーを作成する。
     * 
     */
    public ActionStateFigure() {
        Color bg = new Color(Display.getCurrent(), STATE_COLOR);
        setBackgroundColor(bg);
        
        setLayoutManager(new XYLayout());
        setPreferredSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        fNameLabel = new Label();
        fNameLabel.setTextAlignment(Label.CENTER);
        add(fNameLabel);
        
        fEventLabel = new Label();
        fEventLabel.setBorder(new SimpleRaisedBorder());
        fEventLabel.setTextAlignment(Label.CENTER);
        fEventLabel.setText("E");
        add(fEventLabel);
        
        fEventList = new EventListFigure();
        add(fEventList);
        
        // 位置・サイズを設定
        Dimension stateSize = getSize();
        Dimension nameLabelSize = fNameLabel.getSize();
        
        Rectangle constraint = new Rectangle(
                0, (int) ((stateSize.height - nameLabelSize.height) / 2), 
                DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setConstraint(fNameLabel, constraint);
        
        setConstraint(fEventLabel, 
                new Rectangle(DEFAULT_WIDTH - 13, 3, -1, -1));
        
        setConstraint(fEventList,
                new Rectangle(30, 3, -1, -1));
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
    
    public boolean isEventLabelClicked(int x, int y) {
        Point location = fEventLabel.getLocation();
        Dimension size = fEventLabel.getSize();
        
        if (location.x <= x && location.x + size.width >= x 
            && location.y <= y && location.y + size.height >= y) {
            return true;
        }
        
        return false;
    }
}
