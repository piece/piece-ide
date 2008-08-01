// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import java.util.ArrayList;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

/**
 * 遷移コネクタ用フィギュア.
 *
 * @author Seiichi Sugimoto
 * @since 0.1.0
 */
public class TransitionFigure extends PolylineConnection {

    /** ラベル. */
    private Label fNameLabel;

    private static final int SCALE_X = 5;

    private static final int SCALE_Y = 3;

    private static final int UDISTANCE = 10;

    private static final int VDISTANCE = -10;

    /**
     * コンストラクタ.
    */
    public TransitionFigure() {
        setTargetDecoration();
        setRoutingConstraint(new ArrayList());
        addNameLabel();
    }

    /**
     * 装飾を設定.
    */
    private void setTargetDecoration() {
        PolygonDecoration arrow = new PolygonDecoration();
        arrow.setTemplate(PolygonDecoration.TRIANGLE_TIP);
        arrow.setBackgroundColor(ColorConstants.black);
        arrow.setOpaque(true);
        arrow.setScale(SCALE_X, SCALE_Y);
        setTargetDecoration(arrow);
    }

    /**
     * ラベル追加.
    */
    private void addNameLabel() {
        ConnectionEndpointLocator relationshipLocator
                         = new ConnectionEndpointLocator(this, false);
        relationshipLocator.setUDistance(UDISTANCE);
        relationshipLocator.setVDistance(VDISTANCE);
        fNameLabel = new Label();
        fNameLabel.setForegroundColor(ColorConstants.darkGray);
        add(fNameLabel, relationshipLocator);
        fNameLabel.setVisible(true);
    }

    /**
     * ラベル取得.
     * @return ラベル
    */
    public Label getNameLabel() {
        return fNameLabel;
    }
}
