// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * ノーマルステートマッパークラス.
 * ビューステート、アクションステートのマッパークラス.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class NormalStateMapper extends AbstractStateMapper {

    private int fStateType;

    /**
     * コンストラクタ.
     * ステートタイプを設定する。ステートタイプに、
     * State.VIEW_STATE, State.ACTION_STATE 以外が
     * 指定されている場合はState.UNKNOWN_STATEを設
     * 定する。
     *
     * @param stateType ステートタイプ
     */
    public NormalStateMapper(int stateType) {
        fStateType = State.UNKNOWN_STATE;
        if (stateType == State.VIEW_STATE
            || stateType == State.ACTION_STATE) {
            fStateType = stateType;
        }
    }

    /**
     * ビューステートまたはアクションステートの一覧を
     * 返す.
     * 但し、ファイナルステートに遷移しているステート
     * はファイナルステートに定義するので除外する。
     *
     * @return ビューステートまたはアクションステートのリスト
     * @see com.piece_framework.piece_ide.flow_designer.mapper
     *          .AbstractStateMapper#getStateList()
     */
    @Override
    protected List<State> getStateList() {
        List<State> stateList = new ArrayList<State>();
        if (fStateType == State.UNKNOWN_STATE) {
            return stateList;
        }

        for (State state : getFlow().getStateList()) {
            if (state.getType() == fStateType) {
                // ファイナルステートへの遷移がある場合はなにもしない
                boolean finalStateFlag = false;
                for (Event event : state.getTransitionEventList()) {
                    if (event.getNextState().getType() == State.FINAL_STATE) {
                        finalStateFlag = true;
                        break;
                    }
                }
                if (!finalStateFlag) {
                    stateList.add(state);
                }
            }
        }
        return stateList;
    }

    /**
     * ビューステートまたはアクションステートをMapオブジェクト
     * にセットして返す.
     *
     * @param stateList ビューステートまたはアクションステートのリスト
     * @return YAML出力のためのMapオブジェクト
     * @see com.piece_framework.piece_ide.flow_designer.mapper
     *          .AbstractStateMapper#getMapForYAML(java.util.List)
     */
    @Override
    protected Map<String, Object> getMapForYAML(List<State> stateList) {
        List<Map> stateListForYAML = new ArrayList<Map>();
        for (State state : stateList) {
            Map<String, Object> stateMap =
                    new LinkedHashMap<String, Object>();
            addStateInformationToMap(state, stateMap);
            addBuiltinEventToMap(state, stateMap);
            addTransitionAndInternalEventToMap(state, stateMap);

            stateListForYAML.add(stateMap);
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (stateListForYAML.size() > 0) {
            if (fStateType == State.VIEW_STATE) {
                map.put("viewState", stateListForYAML); //$NON-NLS-1$
            } else if (fStateType == State.ACTION_STATE) {
                map.put("actionState", stateListForYAML); //$NON-NLS-1$
            }
        }

        return map;
    }
}
