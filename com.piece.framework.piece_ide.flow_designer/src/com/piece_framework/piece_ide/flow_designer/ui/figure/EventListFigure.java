// $Id: EventListFigure.java 180 2007-07-27 00:57:08Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

/**
 * イベントリスト・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class EventListFigure extends RectangleFigure {
    
    private Label fEventListLabel; 
    private List<String>  fEventList;
    
    /**
     * コンストラクタ.
     * 
     * @param eventTitle イベントタイトル
     * @param titleColor タイトル背景色
     * @param listColor 一覧背景色
     */
    public EventListFigure(String eventTitle, 
                            Color titleColor, 
                            Color listColor) {
        setBackgroundColor(listColor);
        
        setLayoutManager(new ToolbarLayout(ToolbarLayout.VERTICAL));
        setBorder(new LineBorder());
        
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
            sb.append("\n");
        }
        fEventListLabel.setText(sb.toString());
        repaint();
    }
}
