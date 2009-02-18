// $Id$
package FlowDesigner.diagram.parsers;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public abstract class IdentifierAttributeParser extends MessageFormatParser {
    protected EObject fElement;

    public IdentifierAttributeParser(EAttribute[] features) {
        super(features);
    }

    @Override
    protected Object getValue(EObject element, EAttribute feature) {
        fElement = element;
        return super.getValue(element, feature);
    }

    @Override
    protected Object getValidNewValue(EAttribute feature, Object value) {
        if (value == null || value.equals("")) { //$NON-NLS-1$
            return new InvalidValue(feature.getName() + " is required.");
        }
        if (existsElement((String) value)) {
            return new InvalidValue(value + " already exists.");
        }
        return super.getValidNewValue(feature, value);
    }

    protected abstract boolean existsElement(String value);
}
