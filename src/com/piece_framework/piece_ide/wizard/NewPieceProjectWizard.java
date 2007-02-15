// $Id$
package com.piece_framework.piece_ide.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * 新規プロジェクトウィザード.
 * 新規プロジェクト作成ウィザードを管理する。
 * 
 * @author Seiichi Sugimoto
 * @version 0.1.0
 * @since 0.1.0
 * 
 */
public class NewPieceProjectWizard extends Wizard implements INewWizard {
    
    private IStructuredSelection selection;
    private NewPieceProjectWizardPage page1;
    
    /**
     * コンストラクタ.
     */
    public NewPieceProjectWizard() {
        super();
        setNeedsProgressMonitor(true);
        setWindowTitle("新規Pieceプロジェクト");
    }

    /**
     * 初期処理.
     * @param workbench ワークベンチ
     * @param selection 
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
    }   
    
    /**
     * ページ追加.
     */
    public void addPages() {
        this.page1 = new NewPieceProjectWizardPage("page1");
        this.page1.setTitle("新規Pieceプロジェクト");
        this.page1.setDescription("新規Pieceプロジェクトを作成します。");
        
        addPage(this.page1);
    }
    
    /**
     * 完了ボタンクリック時処理.
     * @return 処理結果
     */
    public boolean performFinish() {
        final IProject newProjectHandle = this.page1.getProjectHandle();
        final IProjectDescription description =
                               createProjectDescription(newProjectHandle);
 
        WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
            protected void execute(IProgressMonitor monitor)
                                                throws CoreException {
                createProject(description, newProjectHandle, monitor);
            }
        };
        runProjectCreationOperation(op, newProjectHandle);
        createFolder(newProjectHandle);
        return true;
    }
    
    private IProjectDescription createProjectDescription(
            IProject newProjectHandle) {
        IPath newPath = null;
        if (!page1.useDefaults()) {
            newPath = page1.getLocationPath();
        }
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProjectDescription description = workspace
                .newProjectDescription(newProjectHandle.getName());
        description.setLocation(newPath);
        return description;
    }
        
    /**
     * プロジェクト作成処理.
     * @param description 　
     * @param projectHandle プロジェクトハンドラ
     * @param monitor プログレスモニター 
     * @throws CoreException 
     * @throws OperationCanceledException 
     */
    private void createProject(IProjectDescription description,
                                IProject projectHandle,
                                IProgressMonitor monitor) throws CoreException,
                                                    OperationCanceledException {
        try {
            monitor.beginTask("", 2000);
            projectHandle.create(description, new SubProgressMonitor(monitor,
                    1000));
            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }
            projectHandle.open(IResource.BACKGROUND_REFRESH,
                    new SubProgressMonitor(monitor, 1000));
        } finally {
            monitor.done();
        }
    }
    
    private void runProjectCreationOperation(WorkspaceModifyOperation op,
            IProject newProjectHandle) {
        try {
            getContainer().run(false, true, op);
        } catch (InterruptedException e) {
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t instanceof CoreException) {
                // handleCoreException(newProjectHandle, (CoreException) t);
            } else {
                // handleOtherProblem(t);
            }
        }
    }
    
    private static void createFolder(IProject project) {
        //プロジェクト内のフォルダを取得。
        IFolder projectFolder = project.getFolder("web");
        
        //フォルダが存在しなければフォルダを作成。
        if (!projectFolder.exists()) {
            try {
                projectFolder.create(false, true, null);
            } catch (CoreException e) {
                //エラーダイアログ表示
            }
        }
        projectFolder = project.getFolder("docs");
        //フォルダが存在しなければフォルダを作成。
        if (!projectFolder.exists()) {
            try {
                projectFolder.create(false, true, null);
            } catch (CoreException e) {
                //エラーダイアログ表示
            }
        }
    }
}
