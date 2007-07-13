package com.piece_framework.piece_ide.flow_designer.mapper;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import com.piece_framework.piece_ide.flow_designer.model.Flow;

/**
 * FlowMapperクラスのテストケース.
 * @author nonoyama
 *
 */
public class FlowMapperTest extends TestCase {
    
    /**
     * コンストラクタ.
     * @param name 名前
     */
    public FlowMapperTest(String name) {
        super(name);
    }

    /**
     * Dumpテスト.
     *
     */
    public void testDump() {
        fail("まだ実装されていません。");
    }
    
    
    
//    /**
//     * テストケース イメージ(仮).
//     * 
//     * yaml と model とで、それぞれ比較できれば
//     * mapper のテストとしては十分では？
//     * model の中身はできれば知らないフリをしたいところです。
//     */
//    public void testYaml001() {
//        String scrYaml ;
//        String expectedYaml ;
//        String resultYaml ;
//        Flow   scrYamlFlow ;
//        Flow   resultYamlFlow ;
//        FlowMapper mapper = new FlowMapper();
//        
//        
//        scrYaml = "--- \r\n" 
//                + "firstState: \r\n" 
//                + "actionState: ・・・・・";
//        
//        expectedYaml = scrYaml;
//        
//        //yaml read
//        scrYamlFlow = mapper.read(scrYaml); //未実装        
//        //yaml dump
//        resultYaml = mapper.dump(scrYamlFlow);
//        //yaml read 　-- 出力されたyamlを読込み
//        resultYamlFlow = mapper.read(resultYaml); //未実装
//        
//
//        
//        ////場合によっては、モデルを調べる（ポイントになるところだけ）
//        //String name = scrYamlFlow.getStateList().get(0).getName();
//        //assertEquals(name, null);
//        
//        //yamlの比較
//        assertEquals("yaml出力結果が一致しない", expectedYaml, resultYaml);
//        
//        //modelの比較
//        assertEquals("モデルが一致しない", scrYamlFlow, resultYamlFlow);
//        
//    }
// 
    
    
    
    
    /**
     * 仮のテスト.
     *
     */
    public void tempTestDump() {
        String workDir = "D:\\";
        String fileName = "test.flow";
        
        String infile   = workDir + fileName;
        //String outfile  = workDir + "test_out.flow";
        
        
        //flowファイルの読み込み        
        Flow f = readFlowFile(infile);
        if (f == null) {
            System.out.println("error:flowファイルを読込めませんでした。");
            return;
        }
        
        
        FlowMapper mapper = new FlowMapper();
        System.out.println(mapper.dump(f));
        
    }
    
    
    /**
     * ファイルの読み込み.
     * @param filename 読み込みファイル
     * @return Flow 読込んだFlowオブジェクト
     */
    public static Flow readFlowFile(String filename) {
        Flow fFlow;
        FileInputStream inFile;
        ObjectInputStream inObject;
        
        try {
            
            inFile = new FileInputStream(filename);            
            inObject = new ObjectInputStream(inFile);
            fFlow = (Flow) inObject.readObject();
            inObject.close();
            inFile.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return fFlow;
    }
    

}
