// $Id$

package FlowDesigner.diagram.resources;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

abstract class AbstractLoad {
    abstract void load(EObject eObject,
                       Map<?, ?> map
                       );
}
