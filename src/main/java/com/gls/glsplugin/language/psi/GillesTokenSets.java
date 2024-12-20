package com.gls.glsplugin.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface GillesTokenSets {
    TokenSet ARITHMETIC_OPERATORS = TokenSet.create(GillesTypes.PLUS, GillesTypes.MINUS, GillesTypes.TIMES,
            GillesTypes.DIVIDE);

    TokenSet COMPARISON_OPERATORS = TokenSet.create(GillesTypes.SMALLER, GillesTypes.SMALEQ, GillesTypes.EQUAL);

    TokenSet DELIMITERS = TokenSet.create(GillesTypes.LBRACK, GillesTypes.RBRACK, GillesTypes.LPAREN, GillesTypes.RPAREN,
            GillesTypes.COLON, GillesTypes.PIPE);

    TokenSet KEYWORDS = TokenSet.create(GillesTypes.IF, GillesTypes.THEN, GillesTypes.ELSE,
            GillesTypes.WHILE, GillesTypes.REPEAT, GillesTypes.END, GillesTypes.LET, GillesTypes.BE, GillesTypes.FOR, GillesTypes.TO,
            GillesTypes.FUNCTION, GillesTypes.RETURNS, GillesTypes.RETURN, GillesTypes.IN, GillesTypes.OUT);

    TokenSet LITERALS = TokenSet.create(GillesTypes.NUMBER, GillesTypes.VARNAME, GillesTypes.PROGNAME);

    TokenSet TYPES = TokenSet.create(GillesTypes.INT, GillesTypes.FLOAT, GillesTypes.BOOL, GillesTypes.LIST, GillesTypes.OF);

    TokenSet BOOL_OPERATORS = TokenSet.create(GillesTypes.AND, GillesTypes.OR, GillesTypes.NOT);

}
