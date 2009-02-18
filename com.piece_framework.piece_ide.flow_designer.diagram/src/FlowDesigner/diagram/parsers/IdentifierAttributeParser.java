// $Id$
package FlowDesigner.diagram.parsers;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import FlowDesigner.diagram.part.Messages;

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
            return new InvalidValue(NLS.bind(Messages.IdentifierAttributeParser_RequiredMessage,
                                             feature.getName()
                                             ));
        }
        if (existsElement((String) value)) {
            return new InvalidValue(NLS.bind(Messages.IdentifierAttributeParser_ExistsMessage,
                                    value
                                    ));
        }
        return super.getValidNewValue(feature, value);
    }

    protected abstract boolean existsElement(String value);
}
