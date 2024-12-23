package com.gls.glsplugin.language;

import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NotNull;

public class GillesContext extends TemplateContextType {

    protected GillesContext() {
        super("Gilles");
    }

    @Override
    public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
        return templateActionContext.getFile().getName().endsWith(".gls");
    }
}
