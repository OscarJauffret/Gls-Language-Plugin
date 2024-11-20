package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesAssignBlock;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GillesAnnotator implements Annotator {
    // Define strings for the Gilles language prefix - used for annotations, line markers, etc.
    public static final String GILLES_PREFIX_STR = "gilles";
    public static final String GILLES_SEPARATOR_STR = ":";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the PSI Element is an expression
        if (!(element instanceof PsiLiteralExpression literalExpression)) {
            return;
        }

        // Ensure the PSI element contains a string that starts with the prefix and separator
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if (value == null || !value.startsWith(GILLES_PREFIX_STR + GILLES_SEPARATOR_STR)) {
            return;
        }

        // Define the text ranges (start is inclusive, end is exclusive)
        // "gilles:key"
        //  01234567890
        TextRange prefixRange = TextRange.from(element.getTextRange().getStartOffset(), GILLES_PREFIX_STR.length() + 1);
        TextRange separatorRange = TextRange.from(prefixRange.getEndOffset(), GILLES_SEPARATOR_STR.length());
        TextRange keyRange = new TextRange(separatorRange.getEndOffset(), element.getTextRange().getEndOffset() - 1);

        // highlight "gilles" prefix and ":" separator
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(prefixRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(separatorRange).textAttributes(GillesSyntaxHighlighter.SEPARATOR).create();


        // Get the list of properties for given key
        String key = value.substring(GILLES_PREFIX_STR.length() + GILLES_SEPARATOR_STR.length());
        List<GillesAssignBlock> properties = GillesUtil.findProperties(element.getProject(), key);
        if (properties.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved property")
                    .range(keyRange)
                    .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                    .create();
        } else {
            // Found at least one property, force the text attributes to Gilles syntax value character
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(keyRange).textAttributes(GillesSyntaxHighlighter.VALUE).create();
        }
    }
}
