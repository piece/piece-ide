// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * イベントリスト・フィギュアー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class EventListFigure extends GradientRectangleFigure {

    private static final String MESSAGE_EVENT_NOTING =
            Messages.getString("EventListFigure.NothingEvent"); //$NON-NLS-1$

    private Label fEventListLabel;
    private List<String>  fEventList;

    /**
     * コンストラクタ.
     *
     * @param eventTitle イベントタイトル
     * @param titleColor タイトル背景色
     * @param listColor 一覧背景色
     * @param outlineColor アウトライン色
     */
    public EventListFigure(String eventTitle,
                            Color titleColor,
                            Color listColor,
                            Color outlineColor) {
        setBackgroundColor(listColor);

        setOutlineColor(outlineColor);

        setLayoutManager(new ToolbarLayout(ToolbarLayout.VERTICAL));
        setBorder(new LineBorder(outlineColor));

        RectangleFigure back = new RectangleFigure();
        back.setBorder(new LineBorder(listColor));
        back.setBackgroundColor(titleColor);
        back.setLayoutManager(new ToolbarLayout());

        Label titleLabel = new Label();
        titleLabel.setTextAlignment(Label.CENTER);
        titleLabel.setText(eventTitle);

        back.add(titleLabel);

        add(back);

        fEventListLabel = new Label();
        fEventListLabel.setTextAlignment(Label.LEFT);
        fEventListLabel.setText(MESSAGE_EVENT_NOTING);
        add(fEventListLabel);

        fEventList = new ArrayList<String>();
    }

    /**
     * イベントを追加する.
     *
     * @param eventName 追加するイベント名
     */
    public void addEvent(String eventName) {
        fEventList.add(eventName);
        setEventList();
    }

    /**
     * イベントを削除する.
     *
     * @param eventName 削除するイベント名
     */
    public void removeEvent(String eventName) {
        fEventList.remove(eventName);
        setEventList();
    }

    /**
     * イベントをすべて削除する.
     *
     */
    public void removeAllEvent() {
        fEventList = new ArrayList<String>();
        fEventListLabel.setText(MESSAGE_EVENT_NOTING);
    }

    /**
     * イベントリストをつなげた文字列を生成し、ラベルにセットする.
     *
     */
    private void setEventList() {
        StringBuffer sb = new StringBuffer();

        Iterator<String> ite = fEventList.iterator();
        while (ite.hasNext()) {
            sb.append(ite.next());
            sb.append("\n"); //$NON-NLS-1$
        }
        fEventListLabel.setText(sb.toString());
        repaint();
    }
}
