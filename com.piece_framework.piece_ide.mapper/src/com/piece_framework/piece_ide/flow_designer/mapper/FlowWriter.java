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
import com.piece_framework.piece_ide.flow_designer.jyaml.YamlFlow;

/**
 * フローライタクラス.
 * @author nonoyama
 *
 */
public class FlowWriter {
    
    private Flow fFlow;

    /**
     * フローを返す.
     * @return フロー
     */
    public Flow getFlow() {
        return fFlow;
    }

    
    /**
     *  フローを設定する.
     * @param flow フロー
     */
    public void setFlow(Flow flow) {
        this.fFlow = flow;
    }


    /**
     * flowファイルの作成.
     * @param flow フロー
     */
    public static void write(Flow flow) {
        FileOutputStream fileOut;
        
        try {
            fileOut = new FileOutputStream("c:\\test.flow");
            
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(flow);
            objectOut.close();
            
            fileOut.close();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
            
        }
    }
    
    
    
    /**
     * flowファイルの読み込み.
     * @param filename ファイル名
     */
    public void readFlowFile(String filename) {
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


    
    /**
     * flowファイルの書き込み.
     * @param outfile 出力先ファイル名
     */
    public void writeFlowFile(String outfile) {
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
        }
    }
    

    /**
     * Yamlファイルへの出力.
     * @param outdir 出力ディレクトリ
     */
    public void writeYamlFile(String outdir) {
        
        if (fFlow == null) {
            return;
        }
        
        if (fFlow.getName() == null) {
            return;
        }
        
        if (outdir == null) {
            outdir = "";
        }
        
       
        YamlFlow fYamlFlow = createYamlFlow();
        
        
        try {
            String outfile = outdir + fFlow.getName() + ".yaml";
            Yaml.dump(fYamlFlow, new File(outfile), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
    }
    
    
    /**
     * Flowの作成.
     * @return YamlFlow
     */
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

