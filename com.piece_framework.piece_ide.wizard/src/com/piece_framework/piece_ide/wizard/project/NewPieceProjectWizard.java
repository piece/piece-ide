// $Id$
package com.piece_framework.piece_ide.wizard.project;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
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
 * @version 0.2.0
 * @since 0.1.0
 */
public class NewPieceProjectWizard extends Wizard implements INewWizard {
    private NewPieceProjectWizardPage fPage1;

    private static final int WORK_TASK = 2000;
    private static final int SUB_WORK_TASK = 1000;
    private static final String PROJECT_RESOURCES = "project_resources";
    private static final String DEFAULT_PROJECT_NAME = "MyApp";

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
     *
     * @param workbench ワークベンチ
     * @param selection 選択した構成
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    /**
     * ページ追加.
     */
    public void addPages() {
        fPage1 = new NewPieceProjectWizardPage("page1"); //$NON-NLS-1$
        fPage1.setTitle(Messages.getString(
                                "NewPieceProjectWizard.PageTitle"));
        fPage1.setDescription(Messages.getString(
                                "NewPieceProjectWizard.PageDescription"));
        fPage1.setInitialProjectName(DEFAULT_PROJECT_NAME);

        addPage(fPage1);
    }

    /**
     * 完了ボタンクリック時処理.
     *
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
            File pluginFolder =
                           new File(FileLocator.toFileURL(pluginURL).getPath());

            createResource(newProjectHandle, pluginFolder, "");

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
     *
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
     *
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
     *
     * @param op ワークスペースオペレーション
     * @param newProjectHandle 新規プロジェクトハンドラ
     * @throws InterruptedException インターセプト例外
     * @throws InvocationTargetException 例外
     */
    private void runProjectCreationOperation(
                        WorkspaceModifyOperation op,
                        IProject newProjectHandle)
                    throws InvocationTargetException, InterruptedException {
        getContainer().run(false, true, op);
    }

    /**
     * 初期ファイル作成処理.
     *
     * @param project 新規プロジェクト
     * @param pluginFile プラグイン側のファイル群
     * @param path プロジェクト側のファイルパス
     * @throws CoreException コア例外
     * @throws FileNotFoundException  ファイル検索エラー
     */
    private void createResource(
                                IProject project,
                                File pluginFile,
                                String path)
                        throws CoreException, FileNotFoundException {
        String[] pluginFiles = pluginFile.list();

        for (int i = 0; i < pluginFiles.length; i++) {
            if (pluginFiles[i].equals(".svn")) {
                continue;
            }

            File pluginChildFile =
                     new File(pluginFile, pluginFiles[i]);
            String resourceName =
                    (path + "/" + pluginFiles[i]).replace(
                            DEFAULT_PROJECT_NAME, project.getName());

            if (pluginChildFile.isDirectory()) {
                project.getFolder(resourceName).create(false, true, null);
                createResource(project,
                               pluginChildFile,
                               path + "/" + pluginChildFile.getName());
            } else {
                IFile projectFile = project.getFile(resourceName);
                projectFile.create(
                           new FileInputStream(pluginChildFile), true, null);
                if (checkExtension(projectFile)) {
                    replaceProjectName(projectFile);
                }
            }
        }
    }

    /**
     * 拡張子のチェックを行う.
     * 拡張子が以下のものに該当するかをチェックする。<br>
     * ・php<br>
     * ・yaml<br>
     * ・flow<br>
     * ・html<br>
     * ・css<br>
     * ・js<br>
     *
     * @param file チェック対象ファイル
     * @return 該当する場合はtrueを返す。
     */
    private boolean checkExtension(IFile file) {
        final String[] replaceExtensions =
                                {
                                    "php",
                                    "yaml",
                                    "flow",
                                    "html",
                                    "css",
                                    "js"
                                };
        for (int i = 0; i < replaceExtensions.length; i++) {
            if (replaceExtensions[i].equals(file.getFileExtension())) {
                return true;
            }
        }
        return false;
    }

    /**
     * ファイルコンテンツ内に含まれているDEFAULT_PROJECT_NAMEの値を
     * プロジェクト名に置換する.
     * パフォーマンスを考慮して、置換前後の文字列が同じ場合は処理を
     * 行わない。
     *
     * @param file ファイル
     * @throws CoreException コア例外
     */
    private void replaceProjectName(IFile file) throws CoreException {
        BufferedInputStream bufferedIn = null;
        ByteArrayOutputStream byteOut = null;
        ByteArrayInputStream byteIn = null;

        try {
            bufferedIn = new BufferedInputStream(file.getContents());
            byteOut = new ByteArrayOutputStream();
            int read = 0;
            while ((read = bufferedIn.read()) != -1) {
                byteOut.write(read);
            }

            String replaceData = byteOut.toString().replace(
                                        DEFAULT_PROJECT_NAME,
                                        file.getProject().getName());
            if (!replaceData.equals(byteOut.toString())) {
                byteIn = new ByteArrayInputStream(replaceData.getBytes());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bufferedIn != null) {
                    bufferedIn.close();
                }
            } catch (IOException ioe) {
            }
            try {
                if (byteOut != null) {
                    byteOut.close();
                }
            } catch (IOException ioe) {
            }
        }

        if (byteIn != null) {
            file.setContents(byteIn, true, false, null);
        }
    }
}
