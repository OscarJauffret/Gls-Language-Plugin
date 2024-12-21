package com.gls.glsplugin.language;

import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

final class GillesColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Key", GillesSyntaxHighlighter.KEY),
            new AttributesDescriptor("Separator", GillesSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("VarName", GillesSyntaxHighlighter.VALUE),
            new AttributesDescriptor("Bad value", GillesSyntaxHighlighter.BAD_CHARACTER),
            new AttributesDescriptor("ProgName", GillesSyntaxHighlighter.FUNCTION),
            new AttributesDescriptor("Line comment", GillesSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Block comment", GillesSyntaxHighlighter.BLOCK_COMMENT),
    };

    @Override
    public Icon getIcon() {
        return GillesIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new GillesSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return """
                !! Compute the square root of x !!
                FUNCTION SquareRoot(FLOAT x) RETURNS FLOAT
                    INT exp = -2:
                    FLOAT result = 1.0:
                    FOR INT i = 0 TO -exp REPEAT
                        result = result / x:
                    END:
                    RETURN result
                END
                
                FUNCTION Delta(FLOAT a, FLOAT b, FLOAT c) RETURNS FLOAT
                    RETURN b * b - 4 * a * c            $ Compute delta
                END
                
                FUNCTION GetRoots(FLOAT a, FLOAT b, FLOAT c) RETURNS INT
                    FLOAT d = Delta(a, b, c):
                    INT hasRoots = 0:
                    IF {d >= 0} THEN
                        IF {d == 0} THEN
                            OUT(-b / (2 * a)):
                            hasRoots = 1:
                        ELSE
                            OUT((-b + SquareRoot(d)) / (2 * a)):
                            OUT((-b - SquareRoot(d)) / (2 * a)):
                            hasRoots = 2:
                        END:
                    END:
                    RETURN hasRoots
                END
                
                LET Main BE
                    FLOAT a = 1.0:
                    FLOAT b = -3.0:
                    FLOAT c = 2.0:
                    INT dummy = GetRoots(a, b, c):
                END
                
                invalid_placement
                """;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Gilles";
    }
}
