// $Id$
package com.piece_framework.piece_ide.flow_designer.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ステートクラス.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class State extends AbstractModel {

    private static final long serialVersionUID = -7778980617394743980L;

    /** ステートタイプ定数：イニシャルステート. */
    public static final int INITIAL_STATE = 1;
    /** ステートタイプ定数：ファイナルステート. */
    public static final int FINAL_STATE = 2;
    /** ステートタイプ定数：アクションステート. */
    public static final int ACTION_STATE = 3;
    /** ステートタイプ定数：ビューステート. */
    public static final int VIEW_STATE = 4;
    /** ステートタイプ定数：不明. */
    public static final int UNKNOWN_STATE = 0;

    private int fType;
    private String fName;
    private String fSummary;
    private String fView;
    private List<Event> fEvents = new ArrayList<Event>();

    private int fX;
    private int fY;

    /**
     * コンストラクタ.
     * ステートタイプは以下の定数のいずれかである必要があります。<br>
     * イニシャルステート：INITIAL_STATE<br>
     * ファイナルステート：FINAL_STATE<br>
     * アクションステート：ACTION_STATE<br>
     * ビューステート：VIEW_STATE<br>
     * これら以外のステートタイプが指定された場合は、UNKNOWN_STATEが
     * セットされます。
     *
     * @param type ステートタイプ
     */
    public State(int type) {
        if (type == INITIAL_STATE || type == FINAL_STATE
            || type == VIEW_STATE || type == ACTION_STATE) {
            fType = type;
            createBuiltinEventByStateType();
        } else {
            fType = UNKNOWN_STATE;
        }
    }

    /**
     * ステートタイプごとに必要なビルトインイベントを作成する.
     *
     */
    private void createBuiltinEventByStateType() {
        if (fType == INITIAL_STATE) {
            fEvents.add(createBuiltinEvent("Initial")); //$NON-NLS-1$
        } else if (fType == ACTION_STATE
                    || fType == VIEW_STATE) {
            fEvents.add(createBuiltinEvent("Entry")); //$NON-NLS-1$
            fEvents.add(createBuiltinEvent("Activity")); //$NON-NLS-1$
            fEvents.add(createBuiltinEvent("Exit")); //$NON-NLS-1$
        } else if (fType == FINAL_STATE) {
            fEvents.add(createBuiltinEvent("Final")); //$NON-NLS-1$
        }
    }

    /**
     * ビルトインイベントオブジェクトを作成する.
     *
     * @param eventName ビルトインイベント名
     * @return ビルトインイベント
     */
    private Event createBuiltinEvent(String eventName) {
        Event event = new Event(Event.BUILTIN_EVENT);
        event.setName(eventName);

        return event;
    }

    /**
     * リスナー追加時、イベントにも同様のリスナーを登録する.
     *
     * @param listener 登録するリスナー
     * @see com.piece_framework.piece_ide.flow_designer.model.AbstractModel
     *          #addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        for (Event event : fEvents) {
            event.addPropertyChangeListener(listener);
        }
    }

    /**
     * リスナー削除時、イベントからも同様にリスナーを削除する.
     *
     * @param listener 削除するリスナー
     * @see com.piece_framework.piece_ide.flow_designer.model.AbstractModel
     *          #removePropertyChangeListener(java.beans.PropertyChangeListener)
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        for (Event event : fEvents) {
            event.removePropertyChangeListener(listener);
        }
    }

    /**
     * ステートタイプを返す.
     * ステートタイプは以下の定数のいずれかで返される。<br>
     * イニシャルステート：INITIAL_STATE<br>
     * ファイナルステート：FINAL_STATE<br>
     * アクションステート：ACTION_STATE<br>
     * ビューステート：VIEW_STATE<br>
     * 不明：UNKNOWN_STATE<br>
     *
     * @return ステートタイプ
     */
    public int getType() {
        return fType;
    }

    /**
     * ステート名を返す.
     *
     * @return ステート名
     */
    public String getName() {
        return fName;
    }

    /**
     * ステート名を設定する.
     * ステート名が付加されていないビルトインイベントがある場合は、
     * 付加する。
     *
     * @param name ステート名
     */
    public void setName(String name) {
        String oldValue = fName;
        if (name != null && name.length() > 0) {
            fName = name;
        } else {
            fName = null;
        }
        firePropertyChange("State#Name", oldValue, fName); //$NON-NLS-1$
    }

    /**
     * 概要を取得する.
     *
     * @return 概要
     */
    public String getSummary() {
        return fSummary;
    }

    /**
     * 概要を設定する.
     *
     * @param summary 概要
     */
    public void setSummary(String summary) {
        String oldValue = fSummary;
        if (summary != null && summary.length() > 0) {
            fSummary = summary;
        } else {
            fSummary = null;
        }
        firePropertyChange("State#Summary", oldValue, fSummary); //$NON-NLS-1$
    }

    /**
     * ビュー名を返す.
     * ステートタイプが VIEW_STATE でなければ、NULL を返す。
     *
     * @return ビュー名
     */
    public String getView() {
        if (fType != VIEW_STATE) {
            return null;
        }
        return fView;
    }

    /**
     * ビュー名を設定する.
     * ステートタイプが VIEW_STATE でなければなにもしない。
     *
     * @param view ビュー名
     */
    public void setView(String view) {
        if (fType == VIEW_STATE) {
            String oldValue = fView;
            if (view != null && view.length() > 0) {
                fView = view;
            } else {
                fView = null;
            }
            firePropertyChange("State#View", oldValue, fView); //$NON-NLS-1$
        }
    }

    /**
     * イベント一覧を返す.
     *
     * @return イベント一覧
     */
    public List<Event> getEventList() {
        return fEvents;
    }

    /**
     * 指定されたイベント名に合致するイベントを返す.
     * 該当するイベントがない場合はnull を返す。
     *
     * @param eventName イベント名
     * @return イベント
     */
    public Event getEventByName(String eventName) {
        if (eventName == null) {
            return null;
        }
        for (Event event : getEventList()) {
            if (eventName.equals(event.getName())) {
                return event;
            }
        }
        return null;
    }

    /**
     * 遷移イベント一覧を返す.
     *
     * @return 遷移イベント一覧
     */
    public List<Event> getTransitionEventList() {
        List<Event> transitionEventList = new ArrayList<Event>();
        for (Event event : getEventList()) {
            if (event.getType() == Event.TRANSITION_EVENT) {
                transitionEventList.add(event);
            }
        }
        return transitionEventList;
    }

    /**
     * イベントを追加する.
     * イベントを追加する前にステートに登録されているリスナーをセットする。<br>
     * また遷移イベントの場合は遷移先のステートに変更を通知する。
     *
     * @param event イベント
     */
    public void addEvent(Event event) {
        for (PropertyChangeListener listener : getPropertyChangeListener()) {
            event.addPropertyChangeListener(listener);
        }
        fEvents.add(event);
        firePropertyChange("State#Event", null, event); //$NON-NLS-1$

        if (event.getType() == Event.TRANSITION_EVENT
            && event.getNextState() != null) {
            event.getNextState().firePropertyChange(
                    "State#TransitionEvent", null, event); //$NON-NLS-1$
        }
    }

    /**
     * イベントを削除する.
     * イベントが保持しているリスナーを削除する。<br>
     * また遷移イベントの場合は遷移先のステートに変更を通知する。
     *
     * @param event イベント
     */
    public void removeEvent(Event event) {
        List<PropertyChangeListener> removeListenerList =
                new ArrayList<PropertyChangeListener>();
        removeListenerList.addAll(
                Arrays.asList(event.getPropertyChangeListener()));
        for (PropertyChangeListener listener : removeListenerList) {
            event.removePropertyChangeListener(listener);
        }

        fEvents.remove(event);
        firePropertyChange("State#Event", event, null); //$NON-NLS-1$

        if (event.getType() == Event.TRANSITION_EVENT
            && event.getNextState() != null) {
            event.getNextState().firePropertyChange(
                    "State#TransitionEvent", event, null); //$NON-NLS-1$
        }
    }

    /**
     * イベント名を生成する.
     * イベント名は、以下の規則で生成される。<br>
     * 遷移先ステート名 + "From" + 遷移元ステート名<br>
     * 但し、内部イベントの場合は、<br>
     * "On" + ステート名<br>
     * とする。
     *
     * @param nextStateName 遷移先ステート名
     * @return イベント名
     */
    public String generateEventName(String nextStateName) {
        if (nextStateName == null) {
            return null;
        }
        if (getType() == State.INITIAL_STATE) {
            return "(FirstState)"; //$NON-NLS-1$
        }
        if (getName() == null) {
            return null;
        }
        String baseEventName = null;
        if (!nextStateName.equals(getName())) {
            baseEventName = nextStateName + "From" + getName(); //$NON-NLS-1$
        } else {
            baseEventName = "On" + getName(); //$NON-NLS-1$
        }
        String eventName = baseEventName;
        int eventSequenceNo = 2;
        while (!checkUsableEventName(eventName)) {
            eventName = baseEventName + Integer.toString(eventSequenceNo);
            eventSequenceNo++;
        }
        return eventName;
    }

    /**
     * 指定されたイベント名が使用可能かチェックする.
     *
     * @param eventName イベント名
     * @return 使用可能なイベント名の場合はtrue
     */
    public boolean checkUsableEventName(String eventName) {
        if (eventName == null) {
            return false;
        }
        for (Event event : getEventList()) {
            if (event.getName() == null) {
                continue;
            }
            if (event.getName().equals(eventName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * ステートのX座標を返す.
     *
     * @return X座標
     */
    public int getX() {
        return fX;
    }

    /**
     * ステートのX座標を設定する.
     *
     * @param x X座標
     */
    public void setX(int x) {
        int oldValue = fX;
        fX = x;
        firePropertyChagen("State#X", oldValue, fX); //$NON-NLS-1$
    }

    /**
     * ステートのY座標を返す.
     *
     * @return Y座標
     */
    public int getY() {
        return fY;
    }

    /**
     * ステートのY座標を設定する.
     *
     * @param y Y座標
     */
    public void setY(int y) {
        int oldValue = fY;
        fY = y;
        firePropertyChagen("State#Y", oldValue, fY); //$NON-NLS-1$
    }
}
