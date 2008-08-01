// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.property;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
 * エディットパートとモデルをマッピングする.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class FlowElementTypeMapper implements ITypeMapper {

    /**
     * エディットパートとモデルをマッピングする.
     *
     * @param object 対象オブジェクト(エディットパート)
     * @return オブジェクトに対応するクラス
     * @see org.eclipse.ui.views.properties.tabbed.ITypeMapper
     *          #mapType(java.lang.Object)
     */
    public Class mapType(Object object) {
        Class type = object.getClass();
        if (object instanceof EditPart) {
            type = ((EditPart) object).getModel().getClass();
        }
        return type;
    }
}
