// $Id$
package com.piece_framework.piece_ide.flow_designer.plugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * プラグインのライフサイクルを管理する.
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class FlowDesignerPlugin extends AbstractUIPlugin {

    /** プラグインID. */
    public static final String PLUGIN_ID = 
                "com.piece_framework.piece_ide.flow_designer"; //$NON-NLS-1$

    /** インスタンス. */
    private static FlowDesignerPlugin fPlugin;
    
    /**
     * コンストラクタ.
     */
    public FlowDesignerPlugin() {
        fPlugin = this;
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
    public static FlowDesignerPlugin getDefault() {
        return fPlugin;
    }
    
    /**
     * イメージディスクリプターを取得する.
     * 
     * @param path イメージのパス
     * @return イメージディスクリプター
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
