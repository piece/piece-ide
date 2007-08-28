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

    private static final RGB STATE_COLOR = new RGB(204, 204, 204);
    private static final RGB STATE_OUTLINE_COLOR = new RGB(153, 153, 153);

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
     * ステートのアウトライン色を返す.
     *
     * @return ステートのアウトライン色
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getStateOutlineColor()
     */
    @Override
    RGB getStateOutlineColor() {
        return STATE_OUTLINE_COLOR;
    }
}
