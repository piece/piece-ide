package com.piece_framework.piece_ide.flow_designer.ui.property;

import java.util.Iterator;

import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

import com.piece_framework.piece_ide.flow_designer.ui.editpart.ActionStateEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.FinalStateEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.FlowEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.InitialStateEditPart;
import com.piece_framework.piece_ide.flow_designer.ui.editpart.ViewStateEditPart;

public class FlowElementLabelProvider extends LabelProvider {

    private ITypeMapper fMapper;
    
    public FlowElementLabelProvider() {
        super();
        fMapper = new FlowElementTypeMapper();
    }

    @Override
    public String getText(Object elements) {
        if (elements == null || elements.equals(StructuredSelection.EMPTY)) {
            return "フロー";
        }
        final boolean multiple[] = {false};
        final Object object = getObject(elements, multiple);
        if (object == null || ((IStructuredSelection) elements).size() > 1) {
            return ((IStructuredSelection) elements).size() + " items selected";//$NON-NLS-1$
        } else {
            if (object instanceof ActionStateEditPart) {
                return "アクションステート";
            } else if (object instanceof ViewStateEditPart) {
                return "ビューステート";
            } else if (object instanceof InitialStateEditPart) {
                return "イニシャルステート";
            } else if (object instanceof FinalStateEditPart) {
                return "ファイナルステート";
            } else if (object instanceof FlowEditPart) {
                return "フロー";
            }
            String name = fMapper.mapType(object).getName();
            return name.substring(name.lastIndexOf('.') + 1);
        }
    }
    
    private Object getObject(Object objects, boolean multiple[]) {
        Assert.isNotNull(objects);
        Object object = null;
        if (objects instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) objects;
            object = selection.getFirstElement();
            if (selection.size() == 1) {
                // one element selected
                multiple[0] = false;
                return object;
            }
            // multiple elements selected
            multiple[0] = true;
            Class firstClass = fMapper.mapType(object);
            // determine if all the objects in the selection are the same type
            if (selection.size() > 1) {
                for (Iterator i = selection.iterator(); i.hasNext();) {
                    Object next = i.next();
                    Class nextClass = fMapper.mapType(next);
                    if (!nextClass.equals(firstClass)) {
                        // two elements not equal == multiple selected unequal
                        multiple[0] = false;
                        object = null;
                        break;
                    }
                }
            }
        } else {
            multiple[0] = false;
            object = objects;
        }
        return object;
    }
    
}
