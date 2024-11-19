package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class GillesCompletionContributor extends CompletionContributor {

    private final Map<String, Set<String>> firstSets = new HashMap<>();

    public GillesCompletionContributor() {
        initFirstSets();
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement()
                        .afterLeaf(PlatformPatterns.psiElement(GillesTypes.LET)),
                new CompletionProvider<>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {
                        // Suggestions pour le nom du programme
                        resultSet.addElement(LookupElementBuilder.create("My_program"));
                        resultSet.addElement(LookupElementBuilder.create("Another_program"));
                    }
                });

//        extend(CompletionType.BASIC, PlatformPatterns.psiElement(), new CompletionProvider<>() {
//            @Override
//            protected void addCompletions(@NotNull CompletionParameters parameters,
//                                          @NotNull ProcessingContext context,
//                                          @NotNull CompletionResultSet resultSet) {
//                PsiElement position = parameters.getPosition();
//                String contextKey = getContextKey(position);
//                if (contextKey == null) {
//                    return;
//                }
//                Set<String> possibleContexts = parseContext(contextKey);
//                System.out.println("possibleContexts: " + possibleContexts);
//                for (String possibleContext : possibleContexts) {
//                    Set<String> suggestions = firstSets.get(possibleContext);
//                    if (suggestions != null) {
//                        for (String suggestion : suggestions) {
//                            resultSet.addElement(LookupElementBuilder.create(suggestion));
//                        }
//                    } else {
//                        if (possibleContext.startsWith("GillesTokenType.")) {
//                            resultSet.addElement(LookupElementBuilder.create(possibleContext.substring(16)));
//                        }
//                    }
//                }
//            }
//        });
    }
    private void initFirstSets() {
        firstSets.put("<program>", Set.of("LET"));
        firstSets.put("<code>", Set.of("IF", "WHILE", "OUT", "IN", "VARNAME", "END", "ELSE"));
        firstSets.put("<instruction>", Set.of("IF", "WHILE", "OUT", "IN", "VARNAME"));
        firstSets.put("<assign>", Set.of("VARNAME"));
        firstSets.put("<expr arith>", Set.of( "-", "VARNAME", "NUMBER", "("));
        firstSets.put("<expr arith tail>", Set.of("+", "-", ":", ")", "==", "<=", "<", "->", "|", "}"));
        firstSets.put("<prod>", Set.of( "-", "VARNAME", "NUMBER", "("));
        firstSets.put("<prod tail>", Set.of("*", "/", "+", "-", ":", ")", "==", "<=", "<", "->", "|", "}"));
        firstSets.put("<factor>", Set.of( "-", "VARNAME", "NUMBER", "("));
        firstSets.put("<atom>", Set.of("VARNAME", "NUMBER", "("));
        firstSets.put("<if>", Set.of("IF"));
        firstSets.put("<if tail>", Set.of("ELSE", "END"));
        firstSets.put("<cond>", Set.of( "|", "-", "VARNAME", "NUMBER", "("));
        firstSets.put("<cond tail>", Set.of("->", "}", "|"));
        firstSets.put("<final cond>", Set.of( "|", "-", "VARNAME", "NUMBER", "("));
        firstSets.put("<comp>", Set.of("<", "<=", "=="));
        firstSets.put("<while>", Set.of("WHILE"));
        firstSets.put("<output>", Set.of("OUT"));
        firstSets.put("<input>", Set.of("IN"));
    }

    private String getContextKey(PsiElement position) {
        PsiElement parent = position.getParent();
        while (parent != null && parent.getNode() != null) {
            if (parent instanceof PsiErrorElement) {
                String errorMessage = ((PsiErrorElement) parent).getErrorDescription();
                if (errorMessage.contains("expected")) {
                    return errorMessage.substring(0, errorMessage.indexOf("expected"));
                }
            } else {
                String elementType = parent.getNode().getElementType().toString();
                if (firstSets.containsKey(elementType)) {
                    return elementType;
                }
            }
            parent = parent.getParent();
        }
        return null;
    }

    private Set<String> parseContext(String contextKey) {
        Set<String> possibleContexts = new HashSet<>();
        String[] elements = contextKey.split(",|\\s+or\\s+");
        for (String element : elements) {
            possibleContexts.add(element.trim());
        }
        return possibleContexts;
    }
}
