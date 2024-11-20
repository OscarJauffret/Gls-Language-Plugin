package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesTokenType;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import com.gls.glsplugin.language.psi.GillesTypes;import groovyjarjarantlr.Token;

%%

%{
  public GillesLexer() {
    this((java.io.Reader) null);
  }
%}

%public
%class GillesLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%eof{
    return;
%eof}

EOL=\R
WHITE_SPACE=\s+

PROGNAME=[A-Z]([a-z]|_)*
VARNAME=[a-z]([a-z]|\[0-9])*
NUMBER=[0-9]+

INVALID_NAME=([A-Z]|[a-z]|[0-9]|_)+

MultilineComment = "!!"
Comment = "$"

%xstate YYINITIAL, MULTILINECOMMENTSTART, MULTILINECOMMENT
%%
<YYINITIAL> {
  {WHITE_SPACE}       { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
  "!"                 { yybegin(MULTILINECOMMENTSTART); return TokenType.WHITE_SPACE; }
  {Comment}[^\r\n]*   { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

  "LET"               { yybegin(YYINITIAL); return GillesTypes.LET; }
  "BE"                { yybegin(YYINITIAL); return GillesTypes.BE; }
  "END"               { yybegin(YYINITIAL); return GillesTypes.END; }
  ":"                 { yybegin(YYINITIAL); return GillesTypes.COLON; }
  "="                 { yybegin(YYINITIAL); return GillesTypes.ASSIGN; }
  "("                 { yybegin(YYINITIAL); return GillesTypes.LPAREN; }
  ")"                 { yybegin(YYINITIAL); return GillesTypes.RPAREN; }
  "-"                 { yybegin(YYINITIAL); return GillesTypes.MINUS; }
  "+"                 { yybegin(YYINITIAL); return GillesTypes.PLUS; }
  "*"                 { yybegin(YYINITIAL); return GillesTypes.TIMES; }
  "/"                 { yybegin(YYINITIAL); return GillesTypes.DIVIDE; }
  "%"                 { yybegin(YYINITIAL); return GillesTypes.MODULO; }
  "IF"                { yybegin(YYINITIAL); return GillesTypes.IF; }
  "THEN"              { yybegin(YYINITIAL); return GillesTypes.THEN; }
  "ELSE"              { yybegin(YYINITIAL); return GillesTypes.ELSE; }
  "{"                 { yybegin(YYINITIAL); return GillesTypes.LBRACK; }
  "}"                 { yybegin(YYINITIAL); return GillesTypes.RBRACK; }
  "->"                { yybegin(YYINITIAL); return GillesTypes.IMPLIES; }
  "|"                 { yybegin(YYINITIAL); return GillesTypes.PIPE; }
  "=="                { yybegin(YYINITIAL); return GillesTypes.EQUAL; }
  "!="                { yybegin(YYINITIAL); return GillesTypes.NEQUAL; }
  "<="                { yybegin(YYINITIAL); return GillesTypes.SMALEQ; }
  ">="                { yybegin(YYINITIAL); return GillesTypes.BIGEQ; }
  "<"                 { yybegin(YYINITIAL); return GillesTypes.SMALLER; }
  ">"                 { yybegin(YYINITIAL); return GillesTypes.BIGGER; }
  "WHILE"             { yybegin(YYINITIAL); return GillesTypes.WHILE; }
  "REPEAT"            { yybegin(YYINITIAL); return GillesTypes.REPEAT; }
  "FOR"               { yybegin(YYINITIAL); return GillesTypes.FOR; }
  "TO"                { yybegin(YYINITIAL); return GillesTypes.TO; }
  "OUT"               { yybegin(YYINITIAL); return GillesTypes.OUT; }
  "IN"                { yybegin(YYINITIAL); return GillesTypes.IN; }

  {PROGNAME}          { yybegin(YYINITIAL); return GillesTypes.PROGNAME; }
  {VARNAME}           { yybegin(YYINITIAL); return GillesTypes.VARNAME; }
  {NUMBER}            { yybegin(YYINITIAL); return GillesTypes.NUMBER; }
  {INVALID_NAME}      { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

[^] { return TokenType.BAD_CHARACTER; }
}

<MULTILINECOMMENTSTART> {
  "!"                 { yybegin(MULTILINECOMMENT); return TokenType.WHITE_SPACE; }
  [^]                   { return TokenType.BAD_CHARACTER; }
}

<MULTILINECOMMENT> {
  {MultilineComment} { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
  [^]                  { return TokenType.WHITE_SPACE; }

}
