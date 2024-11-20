package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.impl.GillesCodeImpl;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GillesCompletionContributor extends CompletionContributor {

    private final Map<String, Set<String>> firstSets = new HashMap<>();

    public GillesCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement()
                        .inside(PlatformPatterns.psiElement(GillesCodeImpl.class)),
                new CompletionProvider<>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {
                        // Add possible instructions
                        Set<String> instructions = Set.of("IF", "WHILE", "OUT", "FOR", "IN", "VARNAME");
                        for (String instruction : instructions) {
                            resultSet.addElement(LookupElementBuilder.create(instruction));
                        }
                    }
                });
    }
}
