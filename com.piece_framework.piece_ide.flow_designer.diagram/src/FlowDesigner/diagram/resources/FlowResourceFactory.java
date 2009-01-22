// $Id$

package FlowDesigner.diagram.resources;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class FlowResourceFactory extends XMIResourceFactoryImpl {
    @Override
    public Resource createResource(URI uri) {
        return new FlowResource(uri);
    }
}
