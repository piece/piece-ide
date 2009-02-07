// $Id$
package FlowDesigner.diagram.parsers;

import org.eclipse.emf.ecore.EAttribute;

public class EventEventParser extends MessageFormatParser {
    public EventEventParser(EAttribute[] features) {
        super(features);
    }

    @Override
    protected Object getValidNewValue(EAttribute feature, Object value) {
        if (value == null || value.equals("")) {
            return new InvalidValue(feature.getName() + " is required.");
        }
        return super.getValidNewValue(feature, value);
    }
}
