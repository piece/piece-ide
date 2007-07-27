// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * �e�X�g�p�̃��X�i�[.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class TestPropertyChangeListener implements PropertyChangeListener {
    private PropertyChangeEvent fEvent;
    
    /**
     * �v���p�e�B�[�ύX�C�x���g����������.
     * 
     * @param event �v���p�e�B�[�ύX�C�x���g
     * @see java.beans.PropertyChangeListener
     *          #propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        fEvent = event;
    }
    
    /**
     * �v���p�e�B�[�ύX�C�x���g��Ԃ�.
     * 
     * @return �v���p�e�B�[�ύX�C�x���g
     */
    public PropertyChangeEvent getPropertyChangeEvent() {
        return fEvent;
    }
    
    /**
     * �v���p�e�B�[�ύX�C�x���g������������.
     * 
     */
    public void initializePropertyChangeEvent() {
        fEvent = null;
    }
}
