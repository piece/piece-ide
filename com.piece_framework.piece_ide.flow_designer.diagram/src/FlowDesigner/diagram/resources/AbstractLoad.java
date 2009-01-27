// $Id$

package FlowDesigner.diagram.resources;

import java.util.Map;

import FlowDesigner.Flow;

abstract class AbstractLoad {
    abstract void load(Flow flow,
                       Map<?, ?> flowMap
                       );
}
