// $Id$
package com.piece_framework.piece_ide.wizard;

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

import com.piece_framework.piece_ide.plugin.PieceIDEPlugin;

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
    
    /** YAML スキーマフォルダ名. */
    public static final String INIT_FILES = ".init_files"; //$NON-NLS-1$

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
        this.selection = selection;
    }   
    
    /**
     * ページ追加.
     */
    public void addPages() {
        this.page1 = new NewPieceProjectWizardPage("page1"); //$NON-NLS-1$
        this.page1.setTitle(Messages.getString(
                                "NewPieceProjectWizard.PageTitle"));
        this.page1.setDescription(Messages.getString(
                                "NewPieceProjectWizard.PageDescription"));
        
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
        try {
            runProjectCreationOperation(op, newProjectHandle);
            
            URL pluginURL =
              Platform.getBundle(PieceIDEPlugin.PLUGIN_ID).getEntry(INIT_FILES);
            
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
     * @throws CoreException コア例外
     */
    private void createProject(IProjectDescription description,
                                IProject projectHandle,
                                IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask("", 2000); //$NON-NLS-1$
            projectHandle.create(description,
                          new SubProgressMonitor(monitor, 1000));
            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }
            projectHandle.open(IResource.BACKGROUND_REFRESH,
                    new SubProgressMonitor(monitor, 1000));
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
        
        for (int j = 0; j < pluginFiles.length; j++) {
            File pluginChildFile =
                         new File(pluginFile, pluginFiles[j]);

            IFolder newProjectFolder =
                         project.getFolder(path + "/" + pluginFiles[j]);
            
            if (pluginChildFile.isDirectory()) {
                //フォルダ作成
                newProjectFolder.create(false, true, null);
                
                //再帰呼出し
                createInitFile(project, pluginChildFile,
                                  path + "/" + pluginChildFile.getName());
            } else {
                //ファイル作成
                IFile projectFile =
                               project.getFile(path + "/" + pluginFiles[j]);
                projectFile.create(
                           new FileInputStream(pluginChildFile), true, null);
            }
        }

    }
}
