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

import com.piece_framework.piece_ide.flow_designer.plugin.FlowDesignerPlugin;

/**
 * フローシリアラズユーティリティ.
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 */
public final class FlowSerializeUtility {
    private static final String FLOW_PATH =
        ".settings/" + FlowDesignerPlugin.PLUGIN_ID + "/"; //$NON-NLS-1$
    private static final String FLOW_SERIALIZE_EXT = "_obj"; //$NON-NLS-1$

    /**
     * コンストラクタ.
     */
    private FlowSerializeUtility() {
    }

    /**
     * フローシリアライズファイルを作成する.
     * フォルダーがなければ、フォルダーも合わせて作成する。
     *
     * @param yamlFile YAMLファイル
     * @return フローシリアラズファイル
     * @throws CoreException コア例外
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
     * @param yamlFile YAMLファイル
     * @return フローシリアライズファイル
     */
    public static IFile getFlowSeirializeFile(IFile yamlFile) {
        if (yamlFile.getProject() == null) {
            return null;
        }
        return yamlFile.getProject().getFile(getSerializePathString(yamlFile));
    }

    /**
     * フローシリアライズファイルへのパスを返す.
     *
     * @param yamlFile YAMLファイル
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
     * @param file ファイル
     * @throws CoreException コア例外
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
     * 指定されたフォルダーが空の場合、削除する.
     * その後、同様の処理を親フォルダーに対し再帰的に行う。
     *
     * @param folder 削除対象となるフォルダー
     */
    private static void deleteFolders(IContainer folder) {
        try {
            if (folder instanceof IProject) {
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
     * YAMLファイルに対応したフローシリアラズファイルが存在するかを返す.
     *
     * @param yamlPath YAMLファイルのパス
     * @return フローシリアラズファイルが存在するか
     */
    public static boolean hasSerializeFile(IPath yamlPath) {
        if (yamlPath == null) {
            return false;
        }

        IFile serializeFile = getFlowSeirializeFile(
                ResourcesPlugin.getWorkspace().getRoot().getFile(yamlPath));
        if (serializeFile == null) {
            return false;
        }
        return serializeFile.exists();
    }

    /**
     * 移動されたYAMLファイルに対応したフローシリアラズファイルを作成する.
     *
     * @param addedList ワークスペースの変更情報
     * @throws CoreException コア例外
     */
    public static void moveSerializeFiles(ArrayList<IResourceDelta> addedList)
                                                       throws CoreException {
        for (IResourceDelta delta : addedList) {
            moveSerializeFile(delta.getMovedFromPath(), delta.getFullPath());
        }
    }

    /**
     * 移動されたYAMLファイルに対応したフローシリアライズファイルを作成する.
     *
     * @param fromPath 移動元のパス
     * @param toPath 移動先のパス
     * @throws CoreException コア例外
     */
    public static void moveSerializeFile(IPath fromPath, IPath toPath)
                                                       throws CoreException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        IFile fromSerializeFile = getFlowSeirializeFile(root.getFile(fromPath));

        IFile toFile = root.getFile(toPath);
        IFile toSerializeFile = FlowSerializeUtility
                .createFlowSeirializeFile(toFile);
        toSerializeFile.delete(true, null);
        fromSerializeFile.copy(toSerializeFile.getFullPath(), true, null);
    }

    /**
     * 削除されたYAMLファイルに対応したフローシリアライズファイルを削除する.
     *
     * @param removedList ワークスペースの変更情報
     * @throws CoreException コア例外
     */
    public static void removeSerializeFiles(
            ArrayList<IResourceDelta> removedList) throws CoreException {
        for (IResourceDelta delta : removedList) {
            removeSerializeFile(delta.getFullPath());
        }
    }

    /**
     * 削除されたYAMLファイルに対応したフローシリアラズファイルを削除する.
     *
     * @param removedPath 削除されたファイルへのパス
     * @throws CoreException コア例外
     */
    public static void removeSerializeFile(IPath removedPath)
                                                throws CoreException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IFile removedFile = root.getFile(removedPath);
        IFile sirializeFile = FlowSerializeUtility
                .getFlowSeirializeFile(removedFile);

        sirializeFile.delete(true, null);
        IFolder folder = root.getFolder(sirializeFile.getFullPath()
                .removeLastSegments(1));
        deleteFolders(folder);
    }
}
