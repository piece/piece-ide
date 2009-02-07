// $Id$
package FlowDesigner.diagram.edit.parts;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class RequiredTextCellEditor extends TextCellEditor {
    public RequiredTextCellEditor() {
    }

    public RequiredTextCellEditor(Composite parent) {
        super(parent);
    }

    @Override
    protected void handleDefaultSelection(SelectionEvent event) {
        if (text.getText() == null || text.getText().equals("")) {
            return;
        }
        super.handleDefaultSelection(event);
    }

    @Override
    protected void focusLost() {
        if (text.getText() == null || text.getText().equals("")) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    text.setFocus();
                }
            });
            return;
        }
        super.focusLost();
    }
}
