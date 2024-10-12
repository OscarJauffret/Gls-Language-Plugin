package com.gls.glsplugin.language.psi.impl;

import com.gls.glsplugin.language.psi.GillesElementFactory;
import com.intellij.lang.ASTNode;
import com.gls.glsplugin.language.psi.GillesAssign;
import com.gls.glsplugin.language.psi.GillesTypes;
import com.intellij.psi.PsiElement;

public class GillesPsiImplUtil {

    public static String getKey(GillesAssign element) {
        ASTNode keyNode = element.getNode().findChildByType(GillesTypes.VARNAME);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(GillesAssign element) {
        ASTNode valueNode = element.getNode().findChildByType(GillesTypes.EXPR_ARITH);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(GillesAssign element) {
        return getKey(element);
    }

    public static PsiElement setName(GillesAssign element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(GillesTypes.VARNAME);
        if (keyNode != null) {
            GillesAssign property =
                    GillesElementFactory.createProperty(element.getProject(), newName);
            if (property != null) {
                ASTNode newKeyNode = property.getFirstChild().getNode();
                element.getNode().replaceChild(keyNode, newKeyNode);
            }
        }
        return element;
    }

    public static PsiElement getNameIdentifier(GillesAssign element) {
        ASTNode keyNode = element.getNode().findChildByType(GillesTypes.VARNAME);
        return keyNode != null ? keyNode.getPsi() : null;
    }
}
