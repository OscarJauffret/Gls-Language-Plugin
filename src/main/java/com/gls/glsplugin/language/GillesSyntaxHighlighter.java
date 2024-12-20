package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesTokenSets;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.gls.glsplugin.language.psi.GillesTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GillesSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("GILLES_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEY =
            createTextAttributesKey("GILLES_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey FUNCTION =
            createTextAttributesKey("GILLES_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("GILLES_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("GILLES_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("GILLES_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("GILLES_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("GILLES_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey TYPE =
            createTextAttributesKey("GILLES_TYPE", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("GILLES_NUMBER", DefaultLanguageHighlighterColors.NUMBER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] FUNCTION_KEYS = new TextAttributesKey[]{FUNCTION};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] TYPE_KEYS = new TextAttributesKey[]{TYPE};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};


    /**
     * Returns the lexer used for highlighting the file. The lexer is invoked incrementally when the file is changed, so it must be
     * capable of saving/restoring state and resuming lexing from the middle of the file.
     *
     * @return The lexer implementation.
     */
    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new GillesLexerAdapter();
    }

    /**
     * Returns the list of text attribute keys used for highlighting the specified token type. The attributes of all attribute keys
     * returned for the token type are successively merged to obtain the color and attributes of the token.
     *
     * @param tokenType The token type for which the highlighting is requested.
     * @return The array of text attribute keys.
     */
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (GillesTokenSets.KEYWORDS.contains(tokenType)) {
            return KEY_KEYS;
        } else if (tokenType.equals(GillesTypes.VARNAME)) {
            return EMPTY_KEYS;
        } else if (tokenType.equals(GillesTypes.NUMBER) || tokenType.equals(GillesTypes.REALNUMBER)) {
            return NUMBER_KEYS;
        } else if (GillesTokenSets.TYPES.contains(tokenType)) {
            return TYPE_KEYS;
        } else if (tokenType.equals(TokenType.WHITE_SPACE)) {
            return LINE_COMMENT_KEYS;
        } else if (tokenType.equals(GillesTypes.PROGNAME)) {
            return FUNCTION_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;

    }
}
