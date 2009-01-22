// $Id$

package FlowDesigner.diagram.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import FlowDesigner.Flow;
import FlowDesigner.impl.FlowDesignerFactoryImpl;

public class YAMLResource extends ResourceImpl {
    public YAMLResource() {
        super();
    }

    public YAMLResource(URI uri) {
        super(uri);
    }

    @Override
    protected void doLoad(InputStream inputStream,
                          Map<?, ?> options
                          ) throws IOException {
        EList<EObject> eList = getContents();
        Flow eFlow = FlowDesignerFactoryImpl.eINSTANCE.createFlow();
        eList.add(eFlow);
    }

    @Override
    protected void doSave(OutputStream outputStream,
                          Map<?, ?> options
                          ) throws IOException {
        // TODO Auto-generated method stub
        super.doSave(outputStream, options);
    }
}
