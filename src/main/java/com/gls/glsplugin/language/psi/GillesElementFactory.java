package com.gls.glsplugin.language.psi;

import com.gls.glsplugin.language.GillesFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;

public class GillesElementFactory {
    public static GillesProgram createProperty(Project project, String name) {
        GillesFile file = createFile(project, name);
        return (GillesProgram) file.getFirstChild();
    }

    public static GillesFile createFile(Project project, String text) {
        String name = "dummy.gls";
        return (GillesFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, GillesFileType.INSTANCE, text);
    }
}
