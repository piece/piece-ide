/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FlowDesigner.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.ho.yaml.Yaml;
import org.ho.yaml.exception.YamlException;

import FlowDesigner.Flow;
import FlowDesigner.impl.FlowDesignerFactoryImpl;
import FlowDesigner.resource.FlowLoad;
import FlowDesigner.resource.FlowSave;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see FlowDesigner.util.FlowDesignerResourceFactoryImpl
 * @generated
 */
public class FlowDesignerResourceImpl extends ResourceImpl {
    /**
     * Creates an instance of the resource.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param uri the URI of the new resource.
     * @generated
     */
    public FlowDesignerResourceImpl(URI uri) {
        super(uri);
    }

    @Override
    protected void doLoad(InputStream inputStream,
                          Map<?, ?> options
                          ) throws IOException {
        Flow flow = FlowDesignerFactoryImpl.eINSTANCE.createFlow();
        FlowLoad load = new FlowLoad();
        try {
            Map<?, ?> flowMap = Yaml.loadType(inputStream, HashMap.class);
            load.load(flow, flowMap);
        } catch (YamlException e) {
            throw new IOException(e.getMessage());
        } finally {
            getContents().add(flow);
        }
    }

    @Override
    protected void doSave(OutputStream outputStream,
                          Map<?, ?> options
                          ) throws IOException {
        Flow eFlow = (Flow) getContents().get(0);
        FlowSave save = new FlowSave();
        Map<String, Object> flowMap = new LinkedHashMap<String, Object>();
        save.save(flowMap, eFlow);

        outputStream.write(getYaml(flowMap).getBytes());
    }

    private String getYaml(Map<String, Object> map) {
        StringBuffer yamlBuffer = new StringBuffer();
        for (String key: map.keySet()) {
            Map<String, Object> topStatementMap = new LinkedHashMap<String, Object>();
            topStatementMap.put(key, map.get(key));

            yamlBuffer.append(Yaml.dump(topStatementMap));
            yamlBuffer.append("\n");
        }

        String yaml = yamlBuffer.toString();
        yaml = yaml.replace("\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace("--- !java.util.LinkedHashMap\n", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace("--- \n", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace(
                " !java.util.LinkedHashMap", ""); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replaceAll("-\n *", "- "); //$NON-NLS-1$ //$NON-NLS-2$
        yaml = yaml.replace(": \n", ":\n"); //$NON-NLS-1$ //$NON-NLS-2$
        if (yaml.length() > 0) {
            yaml = yaml.substring(0, yaml.length() - 1);
        }

        return yaml;
    }
} //FlowDesignerResourceImpl
