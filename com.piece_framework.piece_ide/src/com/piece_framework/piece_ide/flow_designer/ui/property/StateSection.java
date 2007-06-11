package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class StateSection extends AbstractPropertySection {

    private ModifyListener listener = new ModifyListener() {

        public void modifyText(ModifyEvent e) {
            // TODO 自動生成されたメソッド・スタブ
            
        }
        
    };
    
    public StateSection() {
        // TODO 自動生成されたコンストラクター・スタブ
        System.out.println("プロパティ");
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        // TODO 自動生成されたメソッド・スタブ
        super.createControls(parent, aTabbedPropertySheetPage);
        System.out.println("プロパティ");
    }

    
}
