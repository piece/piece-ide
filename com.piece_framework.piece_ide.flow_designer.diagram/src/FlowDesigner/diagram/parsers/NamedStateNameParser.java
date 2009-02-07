// $Id$
package FlowDesigner.diagram.parsers;

import org.eclipse.emf.ecore.EAttribute;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

public class NamedStateNameParser extends IdentifierAttributeParser {
    public NamedStateNameParser(EAttribute[] features) {
        super(features);
    }

    @Override
    protected boolean existsElement(String value) {
        if (fElement == null) {
            return false;
        }

        Flow flow = (Flow) fElement.eContainer();
        NamedState state = flow.findStateByName((String) value);
        if (state !=null && state != fElement) {
            return true;
        }
        return false;
    }
}
