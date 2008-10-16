// $Id$
package com.piece_framework.piece_ide.piece_unity_ide.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * �v���O�C���̃��C�t�T�C�N�����Ǘ�����.
 * 
 * @author Seiichi Sugimoto
 * @since 0.1.0
 * 
 */
public class PieceUnityIdePlugin extends AbstractUIPlugin {

    /** �v���O�C��ID. */
    public static final String PLUGIN_ID
                     = "com.piece_framework.piece_ide.piece_unity_ide";

    /** �C���X�^���X. */
    private static PieceUnityIdePlugin fPlugin;
    
    /**
     * �R���X�g���N�^.
     */
    public PieceUnityIdePlugin() {
    }

    /**
     * �v���O�C�����J�n����.
     * 
     * @param context �R���e�L�X�g
     * @exception Exception ��ʓI�ȗ�O
     * @see org.eclipse.ui.plugin.AbstractUIPlugin
     *          #start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        fPlugin = this;
    }

    /**
     * �v���O�C�����~����.
     * �}�l�[�W���[�̏I���������s��.
     * 
     * @param context �R���e�L�X�g
     * @throws Exception ��ʓI�ȗ�O
     * @see org.eclipse.ui.plugin.AbstractUIPlugin
     *          #stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        fPlugin = null;
        super.stop(context);
    }

    /**
     * ���L�C���X�^���X���擾����.
     *
     * @return ���L�C���X�^���X
     */
    public static PieceUnityIdePlugin getDefault() {
        return fPlugin;
    }
}
