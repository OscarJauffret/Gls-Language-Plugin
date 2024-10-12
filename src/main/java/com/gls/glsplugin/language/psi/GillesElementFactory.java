package com.gls.glsplugin.language.psi;

import com.gls.glsplugin.language.GillesFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;

public class GillesElementFactory {
    public static GillesAssign createProperty(Project project, String name) {
        String text = "LET Dummy_progam BE " + name + " = 42: END";
        GillesFile file = createFile(project, text);
        GillesAssign assign = PsiTreeUtil.findChildOfType(file, GillesAssign.class);
        if (assign != null) {
            return assign;
        }
        throw new IllegalArgumentException("No GillesAssign found in created file");
    }

    public static GillesFile createFile(Project project, String text) {
        String name = "dummy.gls";
        return (GillesFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, GillesFileType.INSTANCE, text);
    }
}