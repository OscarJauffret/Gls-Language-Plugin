package com.gls.glsplugin.language.psi.impl;

import com.gls.glsplugin.language.psi.GillesElementFactory;
import com.intellij.lang.ASTNode;
import com.gls.glsplugin.language.psi.GillesProgram;
import com.gls.glsplugin.language.psi.GillesTypes;
import com.intellij.psi.PsiElement;

public class GillesPsiImplUtil {

    public static String getProgName(GillesProgram element) {
        ASTNode keyNode = element.getNode().findChildByType(GillesTypes.PROGNAME);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getBody(GillesProgram element) {
        ASTNode valueNode = element.getNode().findChildByType(GillesTypes.CODE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(GillesProgram element) {
        return getProgName(element);
    }

    public static PsiElement setName(GillesProgram element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(GillesTypes.PROGNAME);
        if (keyNode != null) {
            GillesProgram property =
                    GillesElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(GillesProgram element) {
        ASTNode keyNode = element.getNode().findChildByType(GillesTypes.PROGNAME);
        return keyNode != null ? keyNode.getPsi() : null;
    }
}
