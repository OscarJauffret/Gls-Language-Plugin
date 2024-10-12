package com.gls.glsplugin.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface GillesTokenSets {
    TokenSet ARITHMETIC_OPERATORS = TokenSet.create(GillesTypes.PLUS, GillesTypes.MINUS, GillesTypes.TIMES,
            GillesTypes.DIVIDE);

    TokenSet COMPARISON_OPERATORS = TokenSet.create(GillesTypes.SMALLER, GillesTypes.SMALEQ, GillesTypes.EQUAL);

    TokenSet DELIMITERS = TokenSet.create(GillesTypes.LBRACK, GillesTypes.RBRACK, GillesTypes.LPAREN, GillesTypes.RPAREN,
            GillesTypes.COLON, GillesTypes.PIPE);

    TokenSet KEYWORDS = TokenSet.create(GillesTypes.IF_INSTR, GillesTypes.THEN, GillesTypes.ELSE,
            GillesTypes.WHILE_INSTR, GillesTypes.REPEAT, GillesTypes.END, GillesTypes.LET, GillesTypes.BE, GillesTypes.IF);

    TokenSet LITERALS = TokenSet.create(GillesTypes.NUMBER, GillesTypes.VARNAME, GillesTypes.PROGNAME);

    TokenSet IO_OPERATORS = TokenSet.create(GillesTypes.INPUT_OP, GillesTypes.OUTPUT_OP);

}
