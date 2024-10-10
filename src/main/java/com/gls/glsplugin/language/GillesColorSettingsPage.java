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
                !! HI !!
                LET Euclidean_algorithm BE
                  IN(a):
                  IN(b):
                  WHILE {0 < b} REPEAT
                      c = b:
                      WHILE {b <= a} REPEAT $ Euclidean Algo
                        a = a-b:
                      END:
                      b = a:
                      a = c:
                  END:
                  OUT(a):
                END
                
                UnexpecTed = 1
                
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
