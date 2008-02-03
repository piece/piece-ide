// $Id$
package com.piece_framework.piece_ide.form_designer.ui.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public final class WidgetUtility {

    /**
     * Textオブジェクトを生成する.
     * 
     * @param parent 親コンポジット
     * @param caption キャプション 
     * @param focusListener フォーカスリスナー
     * @param toolkit ツールキット
     * @return 生成したTextオブジェクト
     */
    public static Text createText(
                    Composite parent,
                    String caption,
                    FocusListener focusListener,
                    FormToolkit toolkit) {
        toolkit.createLabel(parent, caption);
        toolkit.createLabel(parent, ":");
        Text text = toolkit.createText(parent, "");
        text.setLayoutData(
                new GridData(
                        GridData.FILL_HORIZONTAL
                        | GridData.GRAB_HORIZONTAL));
        text.addFocusListener(focusListener);

        return text;
    }

    /**
     * チェックボックスのButtonオブジェクトを生成する.
     * 
     * @param parent 親コンポジット
     * @param caption キャプション
     * @param focusListener フォーカスリスナー
     * @param toolkit ツールキット
     * @return 生成したButtonオブジェクト
     */
    public static Button createCheckBox(
                        Composite parent,
                        String caption,
                        FocusListener focusListener,
                        FormToolkit toolkit) { 
        toolkit.createLabel(parent, caption);
        toolkit.createLabel(parent, ":");
        Button button = toolkit.createButton(parent, "", SWT.CHECK);
        button.addFocusListener(focusListener);
        
        return button;
    }
}
