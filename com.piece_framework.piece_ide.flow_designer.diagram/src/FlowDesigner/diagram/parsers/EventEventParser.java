// $Id$
package FlowDesigner.diagram.parsers;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import FlowDesigner.Event;
import FlowDesigner.NamedState;

public class EventEventParser extends MessageFormatParser {
    private EObject fElement;

    public EventEventParser(EAttribute[] features) {
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
        if (fElement != null && fElement.eContainer() instanceof NamedState) {
            NamedState state = (NamedState) fElement.eContainer();
            for (Event event: state.getEvents()) {
                if (event.getEvent().equals(value)
                    && event != fElement
                    ) {
                    return new InvalidValue(value + " already exists.");
                }
            }
        }
        return super.getValidNewValue(feature, value);
    }
}
