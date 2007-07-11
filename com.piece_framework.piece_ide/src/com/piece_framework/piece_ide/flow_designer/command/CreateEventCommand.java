package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;

import com.piece_framework.piece_ide.flow_designer.model.Event;
import com.piece_framework.piece_ide.flow_designer.model.State;

/**
 * イベント作成コマンド.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.1.0
 * @since 0.1.0
 *
 */
public class CreateEventCommand extends Command {

    private State fState;
    private Event fEvent;
    
    /**
     * コンストラクタ.
     * 
     * @param state ステート
     * @param event イベント
     */
    public CreateEventCommand(State state, Event event) {
        super();
        fState = state;
        fEvent = event;
    }

    /**
     * イベントを作成する.
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        fState.addEvent(fEvent);
    }

    /**
     * 作成したイベントを元に戻す.
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        fState.removeEvent(fEvent);
    }
}
