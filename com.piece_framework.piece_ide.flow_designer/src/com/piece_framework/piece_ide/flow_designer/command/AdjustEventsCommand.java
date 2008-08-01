// $Id$
package com.piece_framework.piece_ide.flow_designer.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.flow_designer.plugin.Messages;

/**
 * イベント調整コマンド.
 * ステート名に合わせて、すべてのイベント名及びイベントハンドラ名を調整する。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.2.0
 *
 */
public class AdjustEventsCommand extends Command {
    private Flow fFlow;
    private Map<Event, Event> fEventMap;

    /**
     * コンストラクタ.
     *
     * @param flow フロー
     */
    public AdjustEventsCommand(Flow flow) {
        fFlow = flow;
        fEventMap = new HashMap<Event, Event>();
    }

    /**
     * すべてのステートが保持するイベントが調整対象となるので、
     * ユーザ問い合わせを行う.
     *
     * @return コマンドを実行できるか
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    @Override
    public boolean canExecute() {
        return MessageDialog.openQuestion(
                null,
                Messages.getString("AdjustEvents.Label"), //$NON-NLS-1$
                Messages.getString("AdjustEvents.Message")); //$NON-NLS-1$
    }

    /**
     * ステート名に合わせて、すべてのイベント名及びイベントハンドラ名を調整する.
     * {@link State#generateEventName(String)}は既に同名のイベントがある場合、
     * 末尾に連番をセットする。そのため関連するステート名が変更されていないイベ
     * ントに関して、イベント名を変更しようとすると連番がセットされてしまう。<br>
     * そこでイベントは一度削除して、新しく作成する。<br>
     * このため、随時ステートからイベントを取得するのが望ましい。
     *
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (fFlow == null) {
            return;
        }

        for (State state : fFlow.getStateList()) {
            for (Event event : getCurrentEventList(state)) {
                replaceEvent(state, event);
            }
        }
    }

    /**
     * Mapオブジェクトの保持された旧イベントに差し替える.
     *
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        for (State state : fFlow.getStateList()) {
            for (Event event : getCurrentEventList(state)) {
                Event oldEvent = fEventMap.get(event);

                state.removeEvent(event);
                state.addEvent(oldEvent);
            }
        }
    }

    /**
     * ステートが保持するイベントリストを新しいリストとして返す.
     *
     * @param state ステート
     * @return イベントリスト
     */
    private List<Event> getCurrentEventList(State state) {
        List<Event> currentEventList = new ArrayList<Event>();
        for (Event event : state.getEventList()) {
            currentEventList.add(event);
        }
        return currentEventList;
    }

    /**
     * 新しいイベントを作成し、既存のイベントと差し替える.
     * イベント名生成に影響しないようにするために、必ず先に既存の
     * イベントを削除する。
     *
     * @param state ステート
     * @param event 既存のイベント
     */
    private void replaceEvent(State state, Event event) {
        state.removeEvent(event);

        Event newEvent = new Event(event.getType());
        newEvent.setName(getEventName(state, event));
        newEvent.setNextState(event.getNextState());
        newEvent.setEventHandler(getEventHandler(state, event, newEvent));
        newEvent.setGuardEventHandler(event.getGuardEventHandler());

        state.addEvent(newEvent);

        fEventMap.put(newEvent, event);
    }

    /**
     * イベント名を返す.
     * ビルトインイベントの場合はそのまま、それ以外の場合は新たに生成
     * したイベント名を返す.
     *
     * @param state ステート
     * @param event 既存のイベント
     * @return イベント名
     */
    public String getEventName(State state, Event event) {
        String eventName = null;
        if (event.getType() == Event.BUILTIN_EVENT) {
            eventName = event.getName();
        } else {
            eventName = state.generateEventName(
                            event.getNextState().getName());
        }
        return eventName;
    }

    /**
     * イベントハンドラ名を返す.
     * ノーマルステートのビルトインイベントの場合は末尾に "On" + ステート名を
     * をセットする。
     *
     * @param state ステート名
     * @param currentEvent 既存のステート名
     * @param newEvent 新たしいステート名
     * @return イベントハンドラ名
     */
    public String getEventHandler(
                        State state,
                        Event currentEvent,
                        Event newEvent) {
        if (currentEvent.getEventHandler() == null) {
            return null;
        }

        String eventHandler = null;
        boolean isNormalState =
                    state.getType() == State.VIEW_STATE
                    || state.getType() == State.ACTION_STATE;
        boolean isBuildinEvent =
                    currentEvent.getType() == Event.BUILTIN_EVENT;

        String className = currentEvent.getEventHandlerClassName();
        String methodName = null;
        if (isNormalState && isBuildinEvent) {
            methodName = newEvent.generateEventHandlerMethodName()
                         + "On" + state.getName(); //$NON-NLS-1$
        } else {
            methodName = newEvent.generateEventHandlerMethodName();
        }
        if (className != null) {
            eventHandler = className + ":" + methodName; //$NON-NLS-1$
        } else {
            eventHandler = methodName;
        }
        return eventHandler;
    }
}
