// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * ビューステート・フィギュアー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class ViewStateFigure extends NormalStateFigure {

    private Label fViewLabel;

    private static final RGB STATE_COLOR = new RGB(225, 221, 170);
    private static final RGB STATE_OUTLINE_COLOR = new RGB(174, 161, 90);

    private static final String ICON = "icons/ViewState.gif"; //$NON-NLS-1$

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

        setFigureHorizontalCenter(getNameLabel(), null);
        setFigureHorizontalCenter(fViewLabel, getNameLabel());

        if (isVisibleEventList()) {
            setFigureHorizontalCenter(getEventList(), fViewLabel);
        }
    }

    /**
     * ビュー名を設定する.
     *
     * @param view ビュー名
     */
    public void setView(String view) {
        if (view != null && view.length() > 0) {
            fViewLabel.setText("[" + view + "]"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            fViewLabel.setText(""); //$NON-NLS-1$
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

    /**
     * ステートのアイコンを返す.
     *
     * @return ステートのアイコンイメージ
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .NormalStateFigure#getIcon()
     */
    @Override
    Image getIcon() {
        return FlowDesignerPlugin.getImageDescriptor(ICON).createImage();
    }
}
