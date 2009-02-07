// $Id$
package FlowDesigner.diagram.parsers;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Flow;
import FlowDesigner.NamedState;

public class NamedStateNameParser extends MessageFormatParser {
    private EObject fElement;

    public NamedStateNameParser(EAttribute[] features) {
        super(features);
    }

    @Override
    protected Object getValue(EObject element, EAttribute feature) {
        fElement = element;
        return super.getValue(element, feature);
    }

    @Override
    protected Object getValidNewValue(EAttribute feature, Object value) {
        if (value == null || value.equals("")) {
            return new InvalidValue(feature.getName() + " is required.");
        }
        if (fElement != null) {
            Flow flow = (Flow) fElement.eContainer();
            NamedState state = flow.findStateByName((String) value);
            if (state !=null && state != fElement) {
                return new InvalidValue(value + " already exists.");
            }
        }
        return super.getValidNewValue(feature, value);
    }
}
