package com.gls.glsplugin.language;

import com.intellij.lexer.FlexAdapter;

public class GillesLexerAdapter extends FlexAdapter {
    public GillesLexerAdapter() {
        super(new GillesLexer(null));
    }
}
