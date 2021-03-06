// $Id$
package com.piece_framework.piece_ide.piece_orm.mapper.oaw;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.osgi.framework.BundleContext;

/**
 * プラグインクラス.
 * プラグインのライフサイクルとユーティリティオブジェクトを管理する。
 *
 * @author MATSUFUJI Hideharu
 * @since 0.1.0
 *
 */
public class Piece_ORM_MapperEditorPlugin extends AbstractXtextEditorPlugin {
   private static Piece_ORM_MapperEditorPlugin plugin;
   public static Piece_ORM_MapperEditorPlugin getDefault() {
      return plugin;
   }

   private Piece_ORM_MapperUtilities utilities = new Piece_ORM_MapperUtilities();
   public LanguageUtilities getUtilities() {
      return utilities;
   }

   public Piece_ORM_MapperEditorPlugin() {
      plugin = this;
   }

   public void stop(BundleContext context) throws Exception {
      super.stop(context);
      plugin = null;
   }
}
