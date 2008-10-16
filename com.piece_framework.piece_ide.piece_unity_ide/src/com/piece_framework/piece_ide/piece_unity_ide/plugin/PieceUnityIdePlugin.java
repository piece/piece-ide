// $Id$
package com.piece_framework.piece_ide.piece_unity_ide.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * プラグインのライフサイクルを管理する.
 * 
 * @author Seiichi Sugimoto
 * @since 0.1.0
 * 
 */
public class PieceUnityIdePlugin extends AbstractUIPlugin {

    /** プラグインID. */
    public static final String PLUGIN_ID
                     = "com.piece_framework.piece_ide.piece_unity_ide";

    /** インスタンス. */
    private static PieceUnityIdePlugin fPlugin;
    
    /**
     * コンストラクタ.
     */
    public PieceUnityIdePlugin() {
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
     * マネージャーの終了処理を行う.
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
    public static PieceUnityIdePlugin getDefault() {
        return fPlugin;
    }
}
