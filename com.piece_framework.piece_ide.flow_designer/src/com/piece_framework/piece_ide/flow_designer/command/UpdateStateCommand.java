package com.piece_framework.piece_ide.flow_designer.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public class UpdateStateCommand extends Command {
    
    private Flow fFlow;
    
    public UpdateStateCommand(Flow flow) {
        fFlow = flow;
    }

    @Override
    public boolean canExecute() {
        return MessageDialog.openQuestion(null, "ステート更新", 
                "イベント名・イベントハンドラ名をステート名に合わせて更新します。\n"
                + "よろしいですか？");
    }

    @Override
    public void execute() {
        System.out.println(fFlow);
    }
}
