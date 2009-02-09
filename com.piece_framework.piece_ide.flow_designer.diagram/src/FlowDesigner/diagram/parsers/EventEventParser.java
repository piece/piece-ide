// $Id$
package FlowDesigner.diagram.parsers;

import org.eclipse.emf.ecore.EAttribute;

import FlowDesigner.Event;
import FlowDesigner.InitialState;
import FlowDesigner.NamedState;

public class EventEventParser extends IdentifierAttributeParser {
    public EventEventParser(EAttribute[] features) {
        super(features);
    }

    @Override
    protected boolean existsElement(String eventName) {
        if (fElement == null || fElement.eContainer() instanceof InitialState) {
            return false;
        }

        NamedState state = (NamedState) fElement.eContainer();
        for (Event event: state.getEvents()) {
            if (event.getEvent() != null
                && event.getEvent().equals(eventName)
                && event != fElement
                ) {
                return true;
            }
        }
        return false;
    }
}
