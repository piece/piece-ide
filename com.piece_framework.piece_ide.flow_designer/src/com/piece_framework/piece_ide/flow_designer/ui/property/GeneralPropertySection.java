// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * プロパティシートの一般の抽象セクション.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 * 
 */
public abstract class GeneralPropertySection extends
        FlowDesignerPropertySection {

    private static final int TEXT_WIDTH_PERCENT = 70;
    
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
     * ラベル・テキストのグループを配置する.
     * 配置はラベル・テキストの順で、表形式で並べる。<br>
     * 各コントロールの幅はラベルはSTANDARD_LABEL_WIDTH、
     * テキストはTEXT_WIDTH_PERCENTに従って配置される。<br>
     * 従って、このメソッドでは各コントロールの幅を調整す
     * ることはできません。
     * 
     * @param label ラベルの配列
     * @param text テキストの配列
     */
    protected void arrangeGroup(CLabel[] label, Text[] text) {
        for (int i = 0; i < label.length; i++) {
            FormData labelFormData = new FormData();
            labelFormData.left = new FormAttachment(0, 0);
            labelFormData.right = new FormAttachment(0, STANDARD_LABEL_WIDTH);
            if (i > 0) {
                labelFormData.top = new FormAttachment(label[i - 1], 0);
            } else {
                labelFormData.top = new FormAttachment(0, 0);
            }
            label[i].setLayoutData(labelFormData);
            
            FormData textFormData = new FormData();
            textFormData.left = new FormAttachment(label[i], 0);
            textFormData.right = new FormAttachment(TEXT_WIDTH_PERCENT, 0);
            if (i > 0) {
                textFormData.top = new FormAttachment(text[i - 1], 0);
            } else {
                textFormData.top = new FormAttachment(0, 0);
            }
            text[i].setLayoutData(textFormData);
        }
    }
    
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
