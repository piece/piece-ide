// $Id: FinalStateFigure.java 115 2007-07-09 11:21:17Z matsufuji $
package com.piece_framework.piece_ide.flow_designer.ui.figure;

import org.eclipse.swt.graphics.Image;
import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * ファイナルステート・フィギュアー.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class FinalStateFigure extends SpecialStateFigure {
    
    private static Image FIGURE_IMG
    = FlowDesignerPlugin.getImageDescriptor("icons/FinalStateLarge.gif").createImage();

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
                        "icons/FinalStateLarge.gif").createImage();
    }
    
}
