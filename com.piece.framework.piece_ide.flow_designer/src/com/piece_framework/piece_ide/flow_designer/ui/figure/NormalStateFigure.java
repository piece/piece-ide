// $Id: NormalStateFigure.java 180 2007-07-27 00:57:08Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * ノーマルステート・抽象フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public abstract class NormalStateFigure extends GradientRoundedRectangle {
    private static final RGB EVENT_BUILTIN_TITLE_COLOR = 
                                            new RGB(192, 212, 205);
    private static final RGB EVENT_BUILTIN_LIST_COLOR = 
                                            new RGB(210, 237, 221);
    private static final RGB EVENT_BUILTIN_LIST_OUTLINE_COLOR = 
                                            new RGB(121, 181, 160);

    private static final RGB EVENT_TRANSITION_TITLE_COLOR = 
                                            new RGB(209, 211, 141);
    private static final RGB EVENT_TRANSITION_LIST_COLOR = 
                                            new RGB(238, 240, 180);
    private static final RGB EVENT_TRANSITION_LIST_OUTLINE_COLOR = 
                                            new RGB(212, 190, 44);
    
    private static final RGB EVENT_INTERNAL_TITLE_COLOR = 
                                            new RGB(233, 178, 152);
    private static final RGB EVENT_INTERNAL_LIST_COLOR = 
                                            new RGB(239, 218, 188);
    private static final RGB EVENT_INTERNAL_LIST_OUTLINE_COLOR = 
                                            new RGB(240, 170, 138);
    
    private Label fNameLabel;
    
    private RectangleFigure fEventList;
    private EventListFigure fBuiltinEventList;
    private EventListFigure fTransitionEventList;
    private EventListFigure fInternalEventList;
    private boolean fVisibleEventList;
    
    private static final int MARGIN = 5;
    
    /**
     * コンストラクタ.
     * ステートに描画するフィギュアーを作成する。
     * 
     */
    public NormalStateFigure() {
        Color bg = new Color(Display.getCurrent(), getStateColor());
        setBackgroundColor(bg);
        
        Color outline = new Color(Display.getCurrent(), getStateOutlineColor());
        setOutlineColor(outline);
        
        setLayoutManager(new XYLayout());
        setBorder(new MarginBorder(getMargin()));
        
        fNameLabel = new Label();
        fNameLabel.setTextAlignment(Label.CENTER);
        add(fNameLabel);
        
        fEventList = new RectangleFigure();
        fEventList.setBorder(null);
        fEventList.setLayoutManager(new ToolbarLayout());
        
        fBuiltinEventList = 
            new EventListFigure(
                "ビルトインイベント",
                new Color(Display.getCurrent(), EVENT_BUILTIN_TITLE_COLOR),
                new Color(Display.getCurrent(), EVENT_BUILTIN_LIST_COLOR),
                new Color(Display.getCurrent(), 
                        EVENT_BUILTIN_LIST_OUTLINE_COLOR));
        fEventList.add(fBuiltinEventList);
        
        fTransitionEventList = 
            new EventListFigure(
                "遷移イベント",
                new Color(Display.getCurrent(), EVENT_TRANSITION_TITLE_COLOR),
                new Color(Display.getCurrent(), EVENT_TRANSITION_LIST_COLOR),
                new Color(Display.getCurrent(), 
                        EVENT_TRANSITION_LIST_OUTLINE_COLOR));
        fEventList.add(fTransitionEventList);
        
        fInternalEventList = 
            new EventListFigure(
                "内部イベント",
                new Color(Display.getCurrent(), EVENT_INTERNAL_TITLE_COLOR),
                new Color(Display.getCurrent(), EVENT_INTERNAL_LIST_COLOR),
                new Color(Display.getCurrent(), 
                        EVENT_INTERNAL_LIST_OUTLINE_COLOR));
        fEventList.add(fInternalEventList);
        
        fVisibleEventList = false;
    }
    
    /**
     * ステート色を返す.
     * 
     * @return ステート色
     */
    abstract RGB getStateColor();
    
    /**
     * ステートのアウトライン色を返す.
     *
     * @return ステートのアウトライン色
     */
    abstract RGB getStateOutlineColor();

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
        
        if (isVisibleEventList()) {
            Dimension nameLabelSize = fNameLabel.getSize();
            
            fitEventList(nameLabelSize.height + getMargin());
        }
    }
    
    /**
     * ステート名ラベルを返す.
     * 
     * @return ステート名
     */
    protected Label getNameLabel() {
        return fNameLabel;
    }
    
    /**
     * イベントリストフィギュアーを返す.
     * 
     * @return イベントリストフィギュアー
     */
    protected RectangleFigure getEventList() {
        return fEventList;
    }
    
    /**
     * ステート名ラベルのサイズ・位置をステートに合わせる.
     * 
     */
    protected void fitNameLabel() {
        Dimension stateSize = getSize();
        Dimension nameLabelSize = getNameLabel().getSize();
        
        Rectangle constraint = new Rectangle(
            (int) ((stateSize.width - nameLabelSize.width) / 2)
                        - getMargin(), -1, 
            -1, -1);
        setConstraint(getNameLabel(), constraint);
    }
    
    /**
     * イベントリスト・フィギュアーのサイズ・位置をステートに合わせる.
     * 
     * @param y Y位置
     */
    protected void fitEventList(int y) {
        Dimension stateSize = getSize();
        Dimension eventListSize = fEventList.getSize();
        
        Rectangle constraint = new Rectangle(
            (int) ((stateSize.width - eventListSize.width) / 2)
                        - getMargin(), y, 
            -1, -1);
        setConstraint(fEventList, constraint);
    }
    
    /**
     * フィギュアー間のマージンを返す.
     * 
     * @return マージン
     */
    protected int getMargin() {
        return MARGIN;
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
     * ビルトインイベントを追加する.
     * 
     * @param eventName イベント名
     */
    public void addBuiltinEvent(String eventName) {
        fBuiltinEventList.addEvent(eventName);
    }
    
    /**
     * ビルトインイベント一覧をすべて削除する.
     * 
     */
    public void removeAllBuiltinEvent() {
        fBuiltinEventList.removeAllEvent();
    }
    
    /**
     * 遷移イベントを追加する.
     * 
     * @param eventName イベント名
     */
    public void addTransitionEvent(String eventName) {
        fTransitionEventList.addEvent(eventName);
    }
    
    /**
     * 遷移イベント一覧をすべて削除する.
     * 
     */
    public void removeAllTransitionEvent() {
        fTransitionEventList.removeAllEvent();
    }
    
    /**
     * 内部イベントを追加する.
     * 
     * @param eventName イベント名
     */
    public void addInternalEvent(String eventName) {
        fInternalEventList.addEvent(eventName);
    }
    
    /**
     * 内部イベント一覧をすべて削除する.
     * 
     */
    public void removeAllInternalEvent() {
        fInternalEventList.removeAllEvent();
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