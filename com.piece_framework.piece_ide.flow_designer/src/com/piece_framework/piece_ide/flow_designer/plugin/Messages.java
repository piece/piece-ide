// $Id$
package com.piece_framework.piece_ide.flow_designer.plugin;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 外部文字列アクセサクラス.
 *
 * @author Hideharu Matsufuji
 * @since 0.1.0
 */
public final class Messages {
    private static final String BUNDLE_NAME =
        "com.piece_framework.piece_ide."   //$NON-NLS-1$
        + "flow_designer.plugin.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE =
                                ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * コンストラクタ.
     *
     */
    private Messages() {
    }

    /**
     * 指定されたキーに対応する文字列を返す.
     *
     * @param key キー
     * @return キーに対応する文字列
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
