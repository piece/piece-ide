// $Id$
package FlowDesigner.diagram.edit.parts;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class StrictTextCellEditor extends TextCellEditor {
    private boolean fUseValidator;

    public StrictTextCellEditor() {
    }

    public StrictTextCellEditor(Composite parent) {
        super(parent);
    }

    @Override
    protected void handleDefaultSelection(SelectionEvent event) {
        if (isValueValid() == false) {
            return;
        }
        super.handleDefaultSelection(event);
    }

    @Override
    protected void focusLost() {
        if (isValueValid() == false) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    text.setFocus();
                }
            });
            return;
        }
        super.focusLost();
    }

    @Override
    protected boolean isCorrect(Object value) {
        fUseValidator = getValidator() != null;
        return super.isCorrect(value);
    }

    @Override
    public boolean isValueValid() {
        if (fUseValidator == false
            && getValidator() != null
            ) {
            setValue(text.getText());
        }

        return super.isValueValid();
    }
}
