// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イニシャルステートマッパークラス.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public class InitialStateMapper extends AbstractStateMapper {

    /**
     * イニシャルステートを返す.
     * イニシャルステートはフロー内にひとつしかないはずなので、
     * ひとつ見つかった時点で処理は終了する。
     *
     * @return イニシャルステートを含むリスト
     * @see com.piece_framework.piece_ide.flow_designer.mapper
     *          .AbstractStateMapper#getStateList()
     */
    @Override
    protected List<State> getStateList() {
        List<State> stateList = new ArrayList<State>();
        for (State state : getFlow().getStateList()) {
            if (state.getType() == State.INITIAL_STATE) {
                stateList.add(state);
                break;
            }
        }
        return stateList;
    }

    /**
     * イニシャルステートから遷移しているステートをMapオブジェクト
     * にセットして返す.
     *
     * @param stateList イニシャルステートを含むリスト
     * @return YAML出力のためのMapオブジェクト
     * @see com.piece_framework.piece_ide.flow_designer.mapper
     *          .AbstractStateMapper#getMapForYAML(java.util.List)
     */
    @Override
    protected Map<String, Object> getMapForYAML(List<State> stateList) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (stateList.size() != 1) {
            return map;
        }

        State initialState = stateList.get(0);
        for (Event event : initialState.getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                map.put(
                    "firstState", event.getNextState().getName()); //$NON-NLS-1$
            } else if (event.getType() == Event.BUILTIN_EVENT
                        && event.getName().equals("Initial")) { //$NON-NLS-1$
                addEventHandlerToMap(
                        event.getEventHandlerClassName(),
                        event.getEventHandlerMethodName(),
                        "initial", //$NON-NLS-1$
                        map);
            }
        }

        return map;
    }
}
