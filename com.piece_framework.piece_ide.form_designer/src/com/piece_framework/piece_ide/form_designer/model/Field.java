// $Id$
package com.piece_framework.piece_ide.form_designer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * �t�B�[���h�N���X.
 * 
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public final class Field {
    private PropertyChangeSupport fSupport;

    private String fName;
    private String fDescription;
    private boolean fRequired;
    private boolean fForceValidation;
    private String fMessage;

    private List<Validator> fValidators;

    /**
     * �R���X�g���N�^.
     * 
     * @param name �t�B�[���h��
     */
    public Field(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        fName = name;
        fDescription = "";
        fRequired = false;
        fForceValidation = false;
        fMessage = "";
        
        fValidators = new ArrayList<Validator>();

        fSupport = new PropertyChangeSupport(this);
    }

    /**
     * �t�B�[���h����Ԃ�.
     * 
     * @return �t�B�[���h��
     */
    public String getName() {
        return fName;
    }

    /**
     * �t�B�[���h����ݒ肷��.
     * 
     * @param name �t�B�[���h��
     */
    public void setName(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        String oldValue = fName;
        fName = name;
        firePropertyChange("Field#Name", oldValue, fName); //$NON-NLS-1$
    }

    /**
     * ������Ԃ�.
     * 
     * @return ����
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * ������ݒ肷��.
     * 
     * @param description ����
     */
    public void setDescription(final String description) {
        if (description == null) {
            throw new NullPointerException();
        }
        String oldValue = fDescription;
        fDescription = description;
        firePropertyChange("Field#Description", 
                           oldValue, 
                           fDescription);
    }

    /**
     * �K�{���ǂ�����Ԃ�.
     * 
     * @return �K�{�̏ꍇ��true
     */
    public boolean isRequired() {
        return fRequired;
    }

    /**
     * �K�{���ǂ�����ݒ肷��.
     * 
     * @param required �K�{�̏ꍇ��true
     */
    public void setRequired(final boolean required) {
        boolean oldValue = fRequired;
        fRequired = required;
        firePropertyChange("Field#Required", 
                oldValue, 
                fRequired);
    }

    /**
     * �t�B�[���h�̏�Ԃɂ�����炸�o���f�[�g���s������Ԃ�.
     * 
     * @return �t�B�[���h�̏�Ԃɂ�����炸�o���f�[�g���s���ꍇ��true
     */
    public boolean isForceValidation() {
        return fForceValidation;
    }

    /**
     * �t�B�[���h�̏�Ԃɂ�����炸�o���f�[�g���s������ݒ肷��.
     * 
     * @param forceValidation �t�B�[���h�̏�Ԃɂ�����炸�o���f�[�g���s���ꍇ��true
     */
    public void setForceValidation(final boolean forceValidation) {
        boolean oldValue = fForceValidation;
        fForceValidation = forceValidation;
        firePropertyChange("Field#ForceValidation", 
                oldValue, 
                fForceValidation);
    }

    /**
     * �K�{�`�F�b�N���̃G���[���b�Z�[�W��Ԃ�.
     * 
     * @return �K�{�`�F�b�N���̃G���[���b�Z�[�W
     */
    public String getMessage() {
        return fMessage;
    }

    /**
     * �K�{�`�F�b�N���̃G���[���b�Z�[�W��ݒ肷��.
     * 
     * @param message �K�{�`�F�b�N���̃G���[���b�Z�[�W
     */
    public void setMessage(final String message) {
        if (fMessage == null) {
            throw new NullPointerException();
        }
        String oldValue = fMessage;
        fMessage = message;
        firePropertyChange("Field#Message", oldValue, fMessage); //$NON-NLS-1$
    }

    public List<Validator> getValidators() {
        if (fValidators == null) {
            return null;
        }
        return Collections.unmodifiableList(fValidators);
    }

    public void addValidator(Validator validator) {
        if (validator == null) {
            throw new NullPointerException();
        }
        fValidators.add(new Validator(validator));
    }

    /**
     * �I�u�W�F�N�g�f�[�^�̐ݒ�ύX�ʒm���s��.
     * 
     * @param name �ύX�ӏ���
     * @param oldValue �ύX�O������
     * @param newValue �ύX�㕶����
     */
    protected void firePropertyChange(String name, 
                                    Object oldValue, 
                                    Object newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * boolean�^�f�[�^�̐ݒ�ύX�ʒm���s��.
     * 
     * @param name �ύX�ӏ���
     * @param oldValue �ύX�O�l
     * @param newValue �ύX��l
     */
    protected void firePropertyChange(String name, 
                                    boolean oldValue, 
                                    boolean newValue) {
        fSupport.firePropertyChange(name, oldValue, newValue);
    }

    /**
     * �ݒ�ύX�ʒm���X�i�[��ǉ�����.
     * 
     * @param listener �ݒ�ύX�ʒm���X�i�[
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        fSupport.addPropertyChangeListener(listener);
    }

    /**
     * �ݒ�ύX�ʒm���X�i�[���폜����.
     * 
     * @param listener �ݒ�ύX�ʒm���X�i�[
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        fSupport.removePropertyChangeListener(listener);
    }
    
    /**
     * �ӂ���Field�I�u�W�F�N�g�����ւ���.
     * ����ւ�����I�u�W�F�N�g�̎Q�Ƃ͕ύX�����A�v���p�e�B�[�̒l�݂̂����ւ��܂��B
     * 
     * @param field1 Field�I�u�W�F�N�g
     * @param field2 Field�I�u�W�F�N�g
     */
    public static void swap(final Field field1, final Field field2) {
        Field tmp = new Field(field1.getName());
        copy(field1, tmp);
        copy(field2, field1);
        copy(tmp, field2);

        field1.firePropertyChange("Field#Swap", null, null);
        field2.firePropertyChange("Field#Swap", null, null);
    }

    /**
     * Field�I�u�W�F�N�g�̃v���p�e�B�[�̒l���R�s�[����.
     * 
     * @param source �R�s�[��Field�I�u�W�F�N�g
     * @param destnation �R�s�[��Field�I�u�W�F�N�g
     */
    private static void copy(final Field source, final Field destnation) {
        destnation.fName = source.fName;
        destnation.fDescription = source.fDescription;
        destnation.fRequired = source.fRequired;
        destnation.fForceValidation = source.fForceValidation;
        destnation.fMessage = source.fMessage;
        destnation.fSupport = new PropertyChangeSupport(destnation);
        for (PropertyChangeListener listener
                : source.fSupport.getPropertyChangeListeners()) {
            destnation.addPropertyChangeListener(listener);
        }
    }
}
