// $Id$
package com.piece_framework.piece_ide.form_designer;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * �v���O�C���̃��C�t�T�C�N�����Ǘ�����.
 * 
 * @since 0.1.0
 * 
 */
public class Activator extends AbstractUIPlugin {

    /** �v���O�C��ID. */
    public static final String PLUGIN_ID = 
        "com.piece_framework.piece_ide.form_designer";

    /** �C���X�^���X. */
    private static Activator fPlugin;
    
    /**
     * �R���X�g���N�^.
     */
    public Activator() {
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
     * �J���[�}�l�[�W���[�̏I���������s��.
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
    public static Activator getDefault() {
        return fPlugin;
    }
}
