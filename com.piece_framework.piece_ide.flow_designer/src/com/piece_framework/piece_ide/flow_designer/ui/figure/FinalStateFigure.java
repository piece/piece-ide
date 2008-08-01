// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.Image;
import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * ファイナルステート・フィギュアー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class FinalStateFigure extends SpecialStateFigure {

    /**
     * ファイナルステート・フィギュアーのイメージを返す.
     *
     * @return ファイナルステート・フィギュアーのイメージ
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .SpecialStateFigure#getFigureImage()
     */
    @Override
    Image getFigureImage() {
        return FlowDesignerPlugin.getImageDescriptor(
                    "icons/FinalStateLarge.gif").createImage(); //$NON-NLS-1$
    }
}
