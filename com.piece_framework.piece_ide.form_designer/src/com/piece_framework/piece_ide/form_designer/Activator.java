// $Id$
package com.piece_framework.piece_ide.form_designer;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * プラグインのライフサイクルを管理する.
 * 
 * @since 0.1.0
 * 
 */
public class Activator extends AbstractUIPlugin {

    /** プラグインID. */
    public static final String PLUGIN_ID = 
        "com.piece_framework.piece_ide.form_designer";

    /** インスタンス. */
    private static Activator fPlugin;
    
    /**
     * コンストラクタ.
     */
    public Activator() {
    }

    /**
     * プラグインを開始する.
     * 
     * @param context コンテキスト
     * @exception Exception 一般的な例外
     * @see org.eclipse.ui.plugin.AbstractUIPlugin
     *          #start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        fPlugin = this;
    }

    /**
     * プラグインを停止する.
     * カラーマネージャーの終了処理を行う.
     * 
     * @param context コンテキスト
     * @throws Exception 一般的な例外
     * @see org.eclipse.ui.plugin.AbstractUIPlugin
     *          #stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        fPlugin = null;
        super.stop(context);
    }

    /**
     * 共有インスタンスを取得する.
     *
     * @return 共有インスタンス
     */
    public static Activator getDefault() {
        return fPlugin;
    }
}
