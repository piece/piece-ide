// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * マッパー抽象クラス.
 * すべてのYAML-モデル間マッパーのスーパークラスとなる。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public abstract class AbstractMapper {

    /**
     * YAML文字列を取得する.
     *
     * @param flow フロー
     * @return YAML文字列
     */
    public abstract String getYAML(Flow flow);

    /**
     * YAML文字列を整形する.
     * 以下の規則に従って整形する。<br>
     * ・"\r\n" → \n<br>
     * ・"--- \n" → 空文字列<br>
     * ・"\"" → 空文字列<br>
     * ・" !java.util.LinkedHashMap" → 空文字列<br>
     * ・"-\n *" → "- "<br>
     * ・": \n" → ":\n"<br>
     * ・最後の2連続改行 → ひとつの改行のみ<br>
     *
     * @param yamlString YAML文字列
     * @return 整形したYAML文字列
     */
    protected String formatYAMLString(String yamlString) {
        String result = yamlString;

        result = result.replace("\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        result = result.replace("--- \n", ""); //$NON-NLS-1$ //$NON-NLS-2$
        result = result.replace("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
        result = result.replace(
                " !java.util.LinkedHashMap", ""); //$NON-NLS-1$ //$NON-NLS-2$
        result = result.replaceAll("-\n *", "- "); //$NON-NLS-1$ //$NON-NLS-2$
        result = result.replace(": \n", ":\n"); //$NON-NLS-1$ //$NON-NLS-2$
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }
}
