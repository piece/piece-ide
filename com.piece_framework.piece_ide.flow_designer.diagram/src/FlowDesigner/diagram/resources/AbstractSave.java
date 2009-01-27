// $Id$

package FlowDesigner.diagram.resources;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

abstract class AbstractSave {
    abstract void save(Map<?, ?> map, EObject eObject);
}
