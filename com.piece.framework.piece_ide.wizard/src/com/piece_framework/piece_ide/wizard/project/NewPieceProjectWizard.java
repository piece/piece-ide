// $Id: NewPieceProjectWizard.java 9 2007-03-14 16:31:24Z sugimoto $
package com.piece_framework.piece_ide.wizard.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.piece_framework.piece_ide.wizard.plugin.Messages;
import com.piece_framework.piece_ide.wizard.plugin.WizardPlugin;

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
    
    private NewPieceProjectWizardPage fPage1;
    
    /** 作業量. */
    public static final int WORK_TASK = 2000;
    
    /** サブ作業量. */
    public static final int SUB_WORK_TASK = 1000;

    /** 初期設定ファイル格納ディレクトリ名. */
    public static final String PROJECT_RESOURCES = "project_resources";

    /**
     * コンストラクタ.
     */
    public NewPieceProjectWizard() {
        super();
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.getString(
                         "NewPieceProjectWizard.WindowTitle")); //$NON-NLS-1$
    }

    /**
     * 初期処理.
     * @param workbench ワークベンチ
     * @param selection 選択した構成
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }   
    
    /**
     * ページ追加.
     */
    public void addPages() {
        this.fPage1 = new NewPieceProjectWizardPage("page1"); //$NON-NLS-1$
        this.fPage1.setTitle(Messages.getString(
                                "NewPieceProjectWizard.PageTitle"));
        this.fPage1.setDescription(Messages.getString(
                                "NewPieceProjectWizard.PageDescription"));
        
        addPage(this.fPage1);
    }
    
    /**
     * 完了ボタンクリック時処理.
     * @return 処理結果
     */
    public boolean performFinish() {
        final IProject newProjectHandle = this.fPage1.getProjectHandle();
        final IProjectDescription description =
                               createProjectDescription(newProjectHandle);
 
        WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
            protected void execute(IProgressMonitor monitor)
                                                throws CoreException {
                createProject(description, newProjectHandle, monitor);
            }
        };
        try {
            runProjectCreationOperation(op, newProjectHandle);
            
            URL pluginURL =
              Platform.getBundle(
                          WizardPlugin.PLUGIN_ID).getEntry(PROJECT_RESOURCES);
            
            //プラグイン側からスキーマフォルダ内のファイル群を取得
            File pluginFolder =
                           new File(FileLocator.toFileURL(pluginURL).getPath());
    
            //初期ファイル作成
            createInitFile(newProjectHandle, pluginFolder, "");
        } catch (IOException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        } catch (CoreException e2) {
            // TODO 自動生成された catch ブロック
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            // TODO 自動生成された catch ブロック
            e3.printStackTrace();
        } catch (InterruptedException e4) {
            // TODO 自動生成された catch ブロック
            e4.printStackTrace();
        }
        return true;
    }

    /**
     * 完了ボタンクリック時処理.
     * @param newProjectHandle 新規プロジェクトハンドラ
     * @return プロジェクトディスクリプション
     */
    private IProjectDescription createProjectDescription(
                                          IProject newProjectHandle) {
        IPath newPath = null;
        if (!fPage1.useDefaults()) {
            newPath = fPage1.getLocationPath();
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
     * @throws CoreException コア例外
     */
    private void createProject(IProjectDescription description,
                                IProject projectHandle,
                                IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask("", WORK_TASK);
            projectHandle.create(description,
                           new SubProgressMonitor(monitor, SUB_WORK_TASK));
            
            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }
            projectHandle.open(IResource.BACKGROUND_REFRESH,
                           new SubProgressMonitor(monitor, SUB_WORK_TASK));
        } finally {
            monitor.done();
        }
    }

    /**
     * 新規プロジェクト作成起動時処理.
     * @param op ワークスペースオペレーション
     * @param newProjectHandle 新規プロジェクトハンドラ
     * @throws InterruptedException インターセプト例外
     * @throws InvocationTargetException 例外
     */
    private void runProjectCreationOperation(WorkspaceModifyOperation op,
            IProject newProjectHandle)
        throws InvocationTargetException, InterruptedException {
        getContainer().run(false, true, op);
    }
    
    /**
     * 初期ファイル作成処理.
     * @param project 新規プロジェクト
     * @param pluginFile プラグイン側のファイル群
     * @param path プロジェクト側のファイルパス
     * @throws CoreException コア例外
     * @throws FileNotFoundException  ファイル検索エラー
     */
    private static void createInitFile(IProject project,
                                             File pluginFile,
                                             String path)
    throws CoreException, FileNotFoundException {

        String[] pluginFiles = pluginFile.list();
        
        if (pluginFiles.length == 0) {
            return;
        }
        
        for (int i = 0; i < pluginFiles.length; i++) {
            if (pluginFiles[i].equals(".svn")) {
                continue;
            }
            
            File pluginChildFile =
                         new File(pluginFile, pluginFiles[i]);

            IFolder newProjectFolder =
                         project.getFolder(path + "/" + pluginFiles[i]);
            
            if (pluginChildFile.isDirectory()) {
                //フォルダ作成
                newProjectFolder.create(false, true, null);
                
                //再帰呼出し
                createInitFile(project, pluginChildFile,
                                  path + "/" + pluginChildFile.getName());
            } else {
                //ファイル作成
                IFile projectFile =
                               project.getFile(path + "/" + pluginFiles[i]);
                projectFile.create(
                           new FileInputStream(pluginChildFile), true, null);
            }
        }
    }
}
