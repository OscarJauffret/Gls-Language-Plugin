package com.gls.glsplugin.language;

import com.gls.glsplugin.language.psi.GillesTokenType;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.gls.glsplugin.language.psi.GillesTypes.*;

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
  {WHITE_SPACE}       { return WHITE_SPACE; }
  "!"                 { yybegin(MULTILINECOMMENTSTART); return WHITE_SPACE; }
  {Comment}[^\r\n]*   { return WHITE_SPACE; }

  "LET"               { return LET; }
  "BE"                { return BE; }
  "END"               { return END; }
  ":"                 { return COLON; }
  "="                 { return ASSIGN_OP; }
  "("                 { return LPAREN; }
  ")"                 { return RPAREN; }
  "-"                 { return MINUS; }
  "+"                 { return PLUS; }
  "*"                 { return TIMES; }
  "/"                 { return DIVIDE; }
  "IF"                { return IF_INSTR; }
  "THEN"              { return THEN; }
  "ELSE"              { return ELSE; }
  "{"                 { return LBRACK; }
  "}"                 { return RBRACK; }
  "->"                { return IMPLIES; }
  "|"                 { return PIPE; }
  "=="                { return EQUAL; }
  "<="                { return SMALEQ; }
  "<"                 { return SMALLER; }
  "WHILE"             { return WHILE_INSTR; }
  "REPEAT"            { return REPEAT; }
  "OUT"               { return OUTPUT_OP; }
  "IN"                { return INPUT_OP; }

  {PROGNAME}          { return PROGNAME; }
  {VARNAME}           { return VARNAME; }
  {NUMBER}            { return NUMBER; }
  {INVALID_NAME}      { return BAD_CHARACTER; }

[^] { return BAD_CHARACTER; }
}

<MULTILINECOMMENTSTART> {
  "!"                 { yybegin(MULTILINECOMMENT); return WHITE_SPACE; }
  [^]                   { return BAD_CHARACTER; }
}

<MULTILINECOMMENT> {
  {MultilineComment} { yybegin(YYINITIAL); return WHITE_SPACE; }
  [^]                  { return WHITE_SPACE; }

}
