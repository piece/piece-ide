// $Id$
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.Image;
import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * イニシャルステート・フィギュアー.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class InitialStateFigure extends SpecialStateFigure {

    /**
     * イニシャルステート・フィギュアーのイメージを返す.
     *
     * @return イニシャルステート・フィギュアーのイメージ
     * @see com.piece_framework.piece_ide.flow_designer.ui.figure
     *          .SpecialStateFigure#getFigureImage()
     */
    @Override
    Image getFigureImage() {
        return FlowDesignerPlugin.getImageDescriptor(
                    "icons/InitialStateLarge.gif").createImage(); //$NON-NLS-1$
    }
}
