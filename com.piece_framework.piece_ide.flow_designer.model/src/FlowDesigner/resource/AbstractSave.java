// $Id$

package FlowDesigner.resource;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

abstract class AbstractSave {
    abstract void save(Map<String, Object> map,
                       EObject eObject
                       );
}
