// $Id$
package com.piece_framework.piece_ide.wizard;

import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;


/**
 * 新規�プロジェクトウィザードページ.
 * 新規�プロジェクト作成�ウィザードページを管理する
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class NewPieceProjectWizardPage extends WizardNewProjectCreationPage {
    
    private String dtdURL = null;
    private String rootElement = null;
    
    /**
     * コンストラクタ.
     */
    public NewPieceProjectWizardPage(String pageName) {
        super(pageName);
    }
        
    /**
     * DTDURLをセットする.
     * @param dtdURL DTDURL
     */
    public void setDTDURL(String dtdURL) {
        this.dtdURL = dtdURL;
    }
    
    /**
     * ルートエレメントをセットする.
     * @param rootElement ルートエレメント
     */
    public void setRootElement(String rootElement) {
        this.rootElement = rootElement;
    }

}
