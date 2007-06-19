// $Id: FlowWriter.java 56 2007-06-19 16:05:23Z nonoyan $
package com.piece_framework.piece_ide.flow_designer.jyaml;

/**
 * YAML出力用 サービスクラス.
 * YAML定義ファイルの service に対応する。
 * 
 * @author 
 * @version 
 * @since 
 *
 */
public class YamlService {
    
    private String class_;
    private String method_;
    
    
    /**
     * クラス名を返す.
     * 
     * @return クラス名
     */    
    public String getClass_() {
        return class_;
    }
    
    /**
     * クラス名を設定する.
     * 
     * @param fClass クラス名
     */
    public void setClass_(String fClass) {
        this.class_ = fClass;
    }
    
    /**
     * メソッド名を返す.
     * 
     * @return メソッド名
     */  
    public String getMethod_() {
        return method_;
    }
    
    /**
     * メソッド名を設定する.
     * 
     * @param fMethod メソッド名
     */
    public void setMethod_(String fMethod) {
        this.method_ = fMethod;
    }    
    
}
