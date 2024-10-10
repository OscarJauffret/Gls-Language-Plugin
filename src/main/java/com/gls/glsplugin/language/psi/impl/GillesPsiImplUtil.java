package com.gls.glsplugin.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.gls.glsplugin.language.psi.GillesProperty;
import com.gls.glsplugin.language.psi.GillesTypes;

public class GillesPsiImplUtil {

    public static String getKey(GillesProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(GillesTypes.VARNAME);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(GillesProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(GillesTypes.EXPR_ARITH);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }
}
