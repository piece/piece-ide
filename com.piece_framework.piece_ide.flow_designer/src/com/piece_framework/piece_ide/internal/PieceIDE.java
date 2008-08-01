// $Id$
package com.piece_framework.piece_ide.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Piece_IDEユーティリティクラス.
 * Piece_IDE全体で使用されるユーティリティクラス。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public final class PieceIDE {

    /**
     * コンストラクタ.
     *
     */
    private PieceIDE() {
    }

    /**
     * メソッドを実行し、その結果を比較する.
     * 渡されたオブジェクトがStringの場合、空文字とnullは同じであると
     * 判断されます。
     *
     * @param object1 オブジェクト1
     * @param object2 オブジェクト2
     * @return 比較の結果同じであればtrueを返す。
     */
    public static boolean compare(Object object1, Object object2) {
        if (object1 == null || object2 == null) {
            return compareNullAndEmpty(object1, object2);
        }
        return object1.equals(object2);
    }

    /**
     * メソッドを実行し、その結果を比較する.
     * 渡されたオブジェクトがStringの場合、空文字とnullは同じであると
     * 判断されます。<br>
     * 渡されたオブジェクトがnull・空文字でなければ、指定されたメソッド
     * を実行します。<br>
     * メソッドは以下の条件を満たすものでなければなりません。<br>
     * ・引数が必要ないこと。<br>
     * ・オブジェクトを返すこと。<br>
     *
     * @param object1 オブジェクト1
     * @param object2 オブジェクト2
     * @param method メソッドオブジェクト
     * @return 比較の結果同じであればtrueを返す。
     */
    public static boolean compare(
                                Object object1,
                                Object object2,
                                Method method) {
        if (object1 == null || object2 == null) {
            return compareNullAndEmpty(object1, object2);
        }
        if (method == null) {
            return object1.equals(object2);
        }

        Object returnValue1 = null;
        Object returnValue2 = null;
        try {
            returnValue1 = method.invoke(object1, (Object[]) null);
            returnValue2 = method.invoke(object2, (Object[]) null);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            return false;
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
            return false;
        }

        if (returnValue1 == null || returnValue2 == null) {
            return compareNullAndEmpty(returnValue1, returnValue2);
        }
        return returnValue1.equals(returnValue2);
    }

    /**
     * null、空文字の比較を行う.
     * 以下の規則で比較を行う。<br>
     * ・両方nullは同じオブジェクトと見なす。<br>
     * ・片方nullでもう片方が空文字は同じオブジェクトと見なす。<br>
     * 　(nullでないオブジェクトがString型の場合のみ)<br>
     *
     * @param object1 オブジェクト1
     * @param object2 オブジェクト2
     * @return 比較の結果同じであればtrueを返す。
     */
    private static boolean compareNullAndEmpty(Object object1, Object object2) {
        if (object1 == null && object2 == null) {
            return true;
        } else if (object1 == null || object2 == null) {
            Object notNullObject = null;
            if (object1 != null) {
                notNullObject = object1;
            } else if (object2 != null) {
                notNullObject = object2;
            }

            return notNullObject
                    instanceof String && notNullObject.equals(""); //$NON-NLS-1$
        }
        return false;
    }
}
