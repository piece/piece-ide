// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.piece_framework.piece_ide.plugin.PieceIDEPlugin;

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
    
    private static final RGB STATE_COLOR = new RGB(200, 120, 100); 
    
    private static final int MARGIN = 20;
    
    /**
     * コンストラクタ.
     * ステートに描画するフィギュアーを作成する。
     * 
     */
    public ActionStateFigure() {
        Color bg = new Color(Display.getCurrent(), STATE_COLOR);
        setBackgroundColor(bg);
        
        setLayoutManager(new BorderLayout());
        fNameLabel = new Label();
        setBorder(new MarginBorder(MARGIN));
        add(fNameLabel, BorderLayout.CENTER);
        
        ImageFigure image = new ImageFigure();
        image.setImage(
                PieceIDEPlugin.getImageDescriptor(
                        "icons/ActionState.gif").createImage());
        add(image, BorderLayout.TOP);
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
}
