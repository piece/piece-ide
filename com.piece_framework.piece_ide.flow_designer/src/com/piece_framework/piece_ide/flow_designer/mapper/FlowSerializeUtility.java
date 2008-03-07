// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * フローシリアラズユーティリティ.
 * 
 * @author MATSUFUJI Hideharu
 * @version 0.2.0
 * @since 0.1.0
 * 
 */
public final class FlowSerializeUtility {

    private static final String FLOW_PATH = ".settings/flow/"; //$NON-NLS-1$

    private static final String FLOW_SERIALIZE_EXT = "_obj"; //$NON-NLS-1$

    /**
     * コンストラクタ.
     * 
     */
    private FlowSerializeUtility() {
    }

    /**
     * フローシリアライズファイルを作成する. フォルダーがなければ、フォルダーも合わせて作成する。
     * 
     * @param yamlFile
     *            YAMLファイル(.flowファイル)
     * @return フローシリアラズファイル
     * @throws CoreException
     *             コア例外
     */
    public static IFile createFlowSeirializeFile(IFile yamlFile)
            throws CoreException {
        if (yamlFile.getProject() == null) {
            return null;
        }

        IFile serializeFile = yamlFile.getProject().getFile(
                new Path(getSerializePathString(yamlFile)));
        if (!serializeFile.exists()) {
            createFolder(serializeFile);
        }

        return serializeFile;
    }

    /**
     * フローシリアライズファイルを返す.
     * 
     * @param yamlFile
     *            YAMLファイル(.flowファイル)
     * @return フローシリアライズファイル
     */
    public static IFile getFlowSeirializeFile(IFile yamlFile) {
        if (yamlFile.getProject() == null) {
            return null;
        }

        return yamlFile.getProject().getFile(getSerializePathString(yamlFile));
    }

    /**
     * フローシリアライズファイルへのパスをString型で返す.
     * 
     * @param yamlFile
     *            YAMLファイル(.flowファイル)
     * @return フローシリアライズファイル
     */
    public static String getSerializePathString(IFile yamlFile) {
        return FLOW_PATH
                + yamlFile.getFullPath().removeFirstSegments(1).toString()
                + FLOW_SERIALIZE_EXT;
    }

    /**
     * 指定されたファイルに到達するためのフォルダーを作成する.
     * 
     * @param file
     *            ファイル
     * @throws CoreException
     *             コア例外
     */
    private static void createFolder(IFile file) throws CoreException {
        String[] folders = file.getFullPath().toString().split("/");
        StringBuffer folderPath = new StringBuffer();

        // 最初の2要素には空文字、プロジェクト名が入るので2からはじめる
        int startIndex = 2;
        // 最後の要素にはファイル名が入っているので除く
        int endIndex = folders.length - 1;

        for (int i = startIndex; i < endIndex; i++) {
            folderPath.append("/" + folders[i]); //$NON-NLS-1$
            IFolder folder = file.getProject().getFolder(
                    new Path(folderPath.toString()));
            if (!folder.exists()) {
                folder.create(true, true, null);
            }
        }
    }

    /**
     * 指定されたフォルダーが空の場合、削除する。その後、同様の処理を親フォルダーに対し再帰的に行う.
     * 
     * @param folder
     *            削除対象となるフォルダー
     */
    private static void deleteFolders(IContainer folder) {
        try {
            if ((folder instanceof IProject)) {
                return;
            }

            if (folder.members().length == 0) {
                folder.delete(true, null);
                deleteFolders(folder.getParent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移動されたYAMLファイルに対応したフローシリアラズファイルを作成する.
     * 
     * @param addedList
     *            ワークスペースの変更情報を表現するIResourceDeltaを要素とするArrayList
     */
    public static void moveSerializeFiles(ArrayList<IResourceDelta> addedList) {
        for (IResourceDelta delta : addedList) {
            moveSerializeFile(delta.getMovedFromPath(), delta.getFullPath());
        }
    }

    /**
     * 移動されたYAMLファイルに対応したフローシリアライズファイルを作成する.
     * 
     * 移動元のパスがnullであったり、フローシリアライズファイルが存在しなかった場合は
     * 処理を行わずfalseを返す。
     * 
     * @param fromPath 移動元のパス
     * @param toPath 移動先のパス
     * @return 処理をおこなった場合はtrue.;処理を行わなかった場合はfalse
     */
    public static boolean moveSerializeFile(IPath fromPath, IPath toPath) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        if (fromPath == null) {
            return false;
        }

        IFile fromSerializeFile = FlowSerializeUtility
                .getFlowSeirializeFile(root.getFile(fromPath));

        if (fromSerializeFile == null || !fromSerializeFile.exists()) {
            return false;
        }

        try {
            IFile toFile = root.getFile(toPath);
            IFile toSerializeFile = FlowSerializeUtility
                    .createFlowSeirializeFile(toFile);
            fromSerializeFile.copy(toSerializeFile.getFullPath(), true, null);
        } catch (CoreException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 削除されたYAMLファイルに対応したフローシリアライズファイルを削除する.
     * 
     * @param removedList
     *            ワークスペースの変更情報を表現するIResourceDeltaを要素とするArrayList
     */
    public static void removeSerializeFiles(
            ArrayList<IResourceDelta> removedList) {
        for (IResourceDelta delta : removedList) {
            removeSerializeFile(delta.getFullPath());
        }
    }

    /**
     * 削除されたYAMLファイルに対応したフローシリアラズファイルを削除する.
     * 
     * 
     * 
     * @param removedPath
     *            削除されたファイルへのパス
     * @return 処理をおこなった場合はtrue.;処理を行わなかった場合はfalse
     */
    public static boolean removeSerializeFile(IPath removedPath) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IFile removedFile = root.getFile(removedPath);
        try {
            IFile sirializeFile = FlowSerializeUtility
                    .getFlowSeirializeFile(removedFile);
            if (!sirializeFile.exists()) {
                return false;
            }
            sirializeFile.delete(true, null);
            IFolder folder = root.getFolder(sirializeFile.getFullPath()
                    .removeLastSegments(1));
            deleteFolders(folder);
        } catch (CoreException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
