// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.editpart;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.ui.figure.ActionStateFigure;
import com.piece_framework.piece_ide.flow_designer.ui.figure.EventListFigure;

/**
 * アクションステート・エディットパート.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class ActionStateEditPart extends StateEditPart implements MouseListener {

    /**
     * フィギュアーを作成する.
     * 
     * @return フィギュアー
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        ActionStateFigure figure = new ActionStateFigure();
        State state = (State) getModel();
        
        figure.setName(state.getName());
        
        figure.addMouseListener(this);
        
        return figure;
    }

    /**
     * エディットポリシーを作成する.
     * 
     * @see com.piece_framework.piece_ide.flow_designer.
     *          ui.editpart.StateEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
    }

    /**
     * プロパティ変更イベントを処理する.
     * 
     * @param evt プロパティ変更イベント
     * @see com.piece_framework.piece_ide.flow_designer.
     *          ui.editpart.StateEditPart
     *              #propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("name")) {
            refreshName();
        }
        super.propertyChange(evt);
    }
    
    /**
     * ステート名をリフレッシュする.
     */
    private void refreshName() {
        ActionStateFigure figure = (ActionStateFigure) getFigure();
        State state = (State) getModel();
        
        figure.setName(state.getName());
    }
    
    /**
     * リクエストを処理する.
     * 
     * @param request リクエスト情報
     * @see org.eclipse.gef.editparts.AbstractEditPart
     *          #performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(Request request) {
        if (request.getType().equals(RequestConstants.REQ_OPEN)) {
            State state = (State) getModel();
            List<Event> eventList = state.getEventList();
            
            StringBuffer events = new StringBuffer();
            
            Iterator ite = eventList.iterator();
            while (ite.hasNext()) {
                Event event = (Event) ite.next();
                events.append(event.getName());
                events.append("\n");
            }
            
            MessageBox msg = new MessageBox(
                    Display.getDefault().getActiveShell(), SWT.OK);
            msg.setText("イベント一覧");
            msg.setMessage(events.toString());
            msg.open();
        }
        super.performRequest(request);
    }

    public void mouseDoubleClicked(MouseEvent mouseEvent) {
        // TODO 自動生成されたメソッド・スタブ
        System.out.println("DoubleClick:" + mouseEvent.getSource());
    }

    public void mousePressed(MouseEvent mouseEvent) {
        ActionStateFigure figure = (ActionStateFigure) mouseEvent.getSource();
        
        if (figure.isEventLabelClicked(mouseEvent.x, mouseEvent.y)) {
            EventListFigure eventListFigure = new EventListFigure();
            figure.setToolTip(eventListFigure);
            
            System.out.println("Press:" + mouseEvent.getSource());
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        // TODO 自動生成されたメソッド・スタブ
        System.out.println("Release:" + mouseEvent.getSource());
    }
    
    
    
}
