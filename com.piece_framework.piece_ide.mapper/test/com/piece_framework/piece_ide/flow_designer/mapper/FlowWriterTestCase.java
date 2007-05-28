// $Id$
package com.piece_framework.piece_ide.flow_designer.mapper;

import java.io.File;

import junit.framework.TestCase;

import com.piece_framework.piece_ide.flow_designer.model.Flow;

public class FlowWriterTestCase extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWrite() {
        Flow flow = new Flow("Test", "TestAction");
        
        FlowWriter.write(flow);
        
        File file = new File("C:\\test.flow");
        
        assertTrue(file.exists());
        
        file.delete();
    }
}
