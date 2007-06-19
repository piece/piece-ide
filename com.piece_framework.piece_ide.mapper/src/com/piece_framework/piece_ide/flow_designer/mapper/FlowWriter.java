// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.ho.yaml.Yaml;

import com.piece_framework.piece_ide.flow_designer.model.Flow;
import com.piece_framework.piece_ide.flow_designer.model.State;
import com.piece_framework.piece_ide.mapper.jyaml.YamlFlow;

public class FlowWriter {
    
    private Flow fFlow;

    public Flow getFlow() {
        return fFlow;
    }

    public void setFlow(Flow flow) {
        this.fFlow = flow;
    }



    public static void write(Flow flow) {
        FileOutputStream fileOut;
        
        try {
            fileOut = new FileOutputStream("c:\\test.flow");
            
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(flow);
            objectOut.close();
            
            fileOut.close();
            
        } catch (IOException ioe) {
            // TODO: オブジェクト読み込み時の例外処理
            ioe.printStackTrace();
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public void readFlowFile(String filename){
        //flowファイルの読み込み
        try {
            
            FileInputStream inFile = new FileInputStream(filename);
            ObjectInputStream inObject = new ObjectInputStream(inFile);

            fFlow = (Flow) inObject.readObject();
            
            inObject.close();
            inFile.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }


    public void writeFlowFile(String outfile) {
        //flowファイルの書き込み
        try {
            FileOutputStream outFile = new FileOutputStream(outfile);
            ObjectOutputStream outObject = new ObjectOutputStream(outFile);
            
            outObject.writeObject(fFlow);
            
            outObject.close();
            outFile.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        } finally {
        	
        }
    }
    

    public void writeYamlFile(String outdir) {
        
        if (fFlow == null) {
            //フローファイルがオープンされていません。
            return;
        }
        
        if (fFlow.getName() == null) {
            //フロー名が定義されていません。 
            return;
        }
        
        if (outdir == null) {
            outdir = "";
        }
        
       
        //JYamlへ渡すオブジェクトを作成
        YamlFlow fYamlFlow = createYamlFlow();
        
        
        //JYAMLによる出力
        try {
            String outfile = outdir + fFlow.getName() + ".yaml";
            Yaml.dump(fYamlFlow, new File(outfile), true);
        } catch (FileNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    
    }
    
    
    public YamlFlow createYamlFlow() {
        
        YamlFlow fYamlFlow = new YamlFlow();
        StateWriterFactory factory = new StateWriterFactory();
        for (State state : fFlow.getStates()) {
            
            IStateWriter stateWiter = factory.getWriter(state);
            if (stateWiter != null) {
                stateWiter.createYamlState(fYamlFlow, state);
            }
        } 
        return fYamlFlow;
        
    }
    
    
    
    
    
}

