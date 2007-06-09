package com.piece_framework.piece_ide.flow_designer.ui.figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

public class EventListFigure extends RectangleFigure {
    
    private Label fEventListLabel; 
    private List<String>  fEventList;
    
    public EventListFigure(String eventTitle, Color titleColor, Color listColor) {
        setBackgroundColor(listColor);
        
        setLayoutManager(new ToolbarLayout(ToolbarLayout.VERTICAL));
        setBorder(new LineBorder());
        
        RectangleFigure back = new RectangleFigure();
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
    
    public void addEvent(String eventName) {
        fEventList.add(eventName);
        setEventList();
    }
    
    public void removeEvent(String eventName) {
        fEventList.remove(eventName);
        setEventList();
    }
    
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
