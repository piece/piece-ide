package com.piece_framework.piece_ide.piece_orm.mapper.editor;

import org.antlr.runtime.Token;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.openarchitectureware.xtext.parser.model.NodeUtil;
import org.openarchitectureware.xtext.parser.parsetree.Node;

public class NodeForContentAssist implements Node {
    public Node fNode;

    public NodeForContentAssist(Node node) {
        fNode = node;
    }

    public NodeForContentAssist(Node node, int offset) {
        if (node == null) {
            return;
        }
        fNode = NodeUtil.getNodeBeforeOffset(node, offset);
        if (fNode != null
            && !fNode.getErrors().isEmpty()
            && fNode.getStart() == 0
            ) {
            fNode = null;
        }
    }

    public EList getChildren() {
        if (fNode != null) {
            return fNode.getChildren();
        }
        return null;
    }

    public int getEnd() {
        if (fNode != null) {
            return fNode.getEnd();
        }
        return 0;
    }

    public EList getErrors() {
        if (fNode != null) {
            return fNode.getErrors();
        }
        return null;
    }

    public EObject getGrammarElement() {
        if (fNode != null) {
            return fNode.getGrammarElement();
        }
        return null;
    }

    public int getLine() {
        if (fNode != null) {
            return fNode.getLine();
        }
        return 0;
    }

    public EObject getModelElement() {
        if (fNode != null) {
            return fNode.getModelElement();
        }
        return null;
    }

    public Node getParent() {
        if (fNode != null) {
            return fNode.getParent();
        }
        return null;
    }

    public int getStart() {
        if (fNode != null) {
            return fNode.getStart();
        }
        return 0;
    }

    public Token getToken() {
        if (fNode != null) {
            return fNode.getToken();
        }
        return null;
    }

    public void setEnd(int end) {
        if (fNode != null) {
            fNode.setEnd(end);
        }
    }

    public void setGrammarElement(EObject element) {
        if (fNode != null) {
            fNode.setGrammarElement(element);
        }
    }

    public void setLine(int line) {
        if (fNode != null) {
            fNode.setLine(line);
        }
    }

    public void setModelElement(EObject element) {
        if (fNode != null) {
            fNode.setModelElement(element);
        }
    }

    public void setParent(Node parent) {
        if (fNode != null) {
            fNode.setParent(parent);
        }
    }

    public void setStart(int start) {
        if (fNode != null) {
            fNode.setStart(start);
        }
    }

    public void setToken(Token token) {
        if (fNode != null) {
            fNode.setToken(token);
        }
    }

    public TreeIterator<EObject> eAllContents() {
        if (fNode != null) {
            return fNode.eAllContents();
        }
        return null;
    }

    public EClass eClass() {
        if (fNode != null) {
            return fNode.eClass();
        }
        return null;
    }

    public EObject eContainer() {
        if (fNode != null) {
            return fNode.eContainer();
        }
        return null;
    }

    public EStructuralFeature eContainingFeature() {
        if (fNode != null) {
            return fNode.eContainingFeature();
        }
        return null;
    }

    public EReference eContainmentFeature() {
        if (fNode != null) {
            return fNode.eContainmentFeature();
        }
        return null;
    }

    public EList<EObject> eContents() {
        if (fNode != null) {
            return fNode.eContents();
        }
        return null;
    }

    public EList<EObject> eCrossReferences() {
        if (fNode != null) {
            return fNode.eCrossReferences();
        }
        return null;
    }

    public Object eGet(EStructuralFeature feature) {
        if (fNode != null) {
            return fNode.eGet(feature);
        }
        return null;
    }

    public Object eGet(EStructuralFeature feature, boolean resolve) {
        if (fNode != null) {
            return fNode.eGet(feature, resolve);
        }
        return null;
    }

    public boolean eIsProxy() {
        if (fNode != null) {
            return fNode.eIsProxy();
        }
        return false;
    }

    public boolean eIsSet(EStructuralFeature feature) {
        if (fNode != null) {
            return fNode.eIsSet(feature);
        }
        return false;
    }

    public Resource eResource() {
        if (fNode != null) {
            return fNode.eResource();
        }
        return null;
    }

    public void eSet(EStructuralFeature feature, Object newValue) {
        if (fNode != null) {
            fNode.eSet(feature, newValue);
        }
    }

    public void eUnset(EStructuralFeature feature) {
        if (fNode != null) {
            fNode.eUnset(feature);
        }
    }

    public EList<Adapter> eAdapters() {
        if (fNode != null) {
            return fNode.eAdapters();
        }
        return null;
    }

    public boolean eDeliver() {
        if (fNode != null) {
            return fNode.eDeliver();
        }
        return false;
    }

    public void eNotify(Notification notification) {
        if (fNode != null) {
            fNode.eNotify(notification);
        }
    }

    public void eSetDeliver(boolean deliver) {
        if (fNode != null) {
            fNode.eSetDeliver(deliver);
        }
    }

    public boolean hasParent() {
        return fNode != null
               && fNode.getParent() != null;
    }
}
