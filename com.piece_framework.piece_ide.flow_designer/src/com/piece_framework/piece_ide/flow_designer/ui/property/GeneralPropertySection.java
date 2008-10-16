// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * プロパティシートの一般の抽象セクション.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public abstract class GeneralPropertySection extends
        FlowDesignerPropertySection {
    private TextListener fListener = new TextListener() {
        @Override
        public void changeText(Control control) {
            String attributeName = getAttributeName(control);
            String attributeValue = ((Text) control).getText();

            executeCommand(getAttributeCommand(attributeName, attributeValue));
            refresh();
        }
    };

    /**
     * テキストボックスにリスナーをセットする.
     *
     * @param text テキストボックス
     */
    protected void setTextListener(Text text) {
        text.addListener(SWT.FocusOut, fListener);
        text.addListener(SWT.Modify, fListener);
        text.addListener(SWT.KeyDown, fListener);
    }

    /**
     * コントロールから属性名を返す.
     *
     * @param control コントロール
     * @return 属性名
     */
    abstract String getAttributeName(Control control);

    /**
     * 属性を設定するコマンドを返す.
     *
     * @param attributeName 属性名
     * @param attributeValue 属性値
     * @return 属性を設定するコマンド
     */
    abstract Command getAttributeCommand(
                        String attributeName, String attributeValue);
}
