package com.gls.glsplugin.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.gls.glsplugin.language.psi.GillesTypes;


public class GillesCompletionContributor extends CompletionContributor {

    public GillesCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(GillesTypes.PROGNAME),
                new CompletionProvider<>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {

                    }
                }
        );
    }
}
