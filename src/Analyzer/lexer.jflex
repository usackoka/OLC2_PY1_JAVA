package Analyzer;
import java_cup.runtime.Symbol;
import Principal.Singleton;
%%
%class Lexer
%public
%line
%char
%cup
%unicode
%8bit
%full

%init{
    yyline = 0;
    yychar = 0;
%init}

BLANCOS=[\r\t]+
ENT_LIT = [0-9]+
DEC_LIT = [0-9]+"."[0-9]+
BOOL_LIT = true | false
ID = [A-Za-z"_"]+["_"0-9A-Za-z]*
STRING_LIT = [\"]([^\"\n]|(\\\"))*[\"]
CHAR_LIT = [']("\\")?([^\"\n]|(\\\"))[']
comentario_linea ="//".*\r\n|"//".*\n|"//".*\r|"//".*

%state COM

%%
<YYINITIAL>{
    "nlo"           {return new Symbol(sym.NLO,yyline,yychar,yytext());}
    "fusion"        {return new Symbol(sym.FUSIONR,yyline,yychar,yytext());}    
    "ent"           {return new Symbol(sym.ENTR,yyline,yychar,yytext());}
    "zro"           {return new Symbol(sym.ZROR,yyline,yychar,yytext());}
    "chr"           {return new Symbol(sym.CHRR,yyline,yychar,yytext());}
    "dec"           {return new Symbol(sym.DECR,yyline,yychar,yytext());}
    "bul"           {return new Symbol(sym.BULR,yyline,yychar,yytext());}
    "if"            {return new Symbol(sym.IFR,yyline,yychar,yytext());}
    "else"          {return new Symbol(sym.ELSER,yyline,yychar,yytext());}
    "while"         {return new Symbol(sym.WHILER,yyline,yychar,yytext());}
    "for"           {return new Symbol(sym.FORR,yyline,yychar,yytext());}
    "repeat"        {return new Symbol(sym.REPEATR,yyline,yychar,yytext());}
    "switch"        {return new Symbol(sym.SWITCHR,yyline,yychar,yytext());}
    "case"          {return new Symbol(sym.CASER,yyline,yychar,yytext());}
    "default"       {return new Symbol(sym.DEFAULTR,yyline,yychar,yytext());}
    "romper"        {return new Symbol(sym.ROMPERR,yyline,yychar,yytext());}
    "siga"          {return new Symbol(sym.SIGAR,yyline,yychar,yytext());}
    "definir"       {return new Symbol(sym.DEFINIRR,yyline,yychar,yytext());}
    "importar"      {return new Symbol(sym.IMPORTARR,yyline,yychar,yytext());}
    "regresar"      {return new Symbol(sym.REGRESARR,yyline,yychar,yytext());}
    "when"          {return new Symbol(sym.WHENR,yyline,yychar,yytext());}
    "Rstring"       {return new Symbol(sym.RSTRINGR,yyline,yychar,yytext());}
    "main"          {return new Symbol(sym.MAIN,yyline,yychar,yytext());}

    "Rlbl"          {return new Symbol(sym.RLBL,yyline,yychar,yytext());}
    "Rtxt"          {return new Symbol(sym.RTXT,yyline,yychar,yytext());}
    "RtxtA"         {return new Symbol(sym.RTXTA,yyline,yychar,yytext());}
    "RtxtP"         {return new Symbol(sym.RTXTP,yyline,yychar,yytext());}
    "RtxtN"         {return new Symbol(sym.RTXTN,yyline,yychar,yytext());}

    "Rbton"         {return new Symbol(sym.RBTON,yyline,yychar,yytext());}
    "Rmensaje"      {return new Symbol(sym.RMENSAGE,yyline,yychar,yytext());}

    "_imp"          {return new Symbol(sym.RIMP,yyline,yychar,yytext());}
    "_write"        {return new Symbol(sym.RWRITE,yyline,yychar,yytext());}
    "_apend"        {return new Symbol(sym.RAPEND,yyline,yychar,yytext());}
    "_wf"           {return new Symbol(sym.RWF,yyline,yychar,yytext());}
    "_close"        {return new Symbol(sym.RCLOSE,yyline,yychar,yytext());}
    "_read"         {return new Symbol(sym.RREAD,yyline,yychar,yytext());}

    "++"            {return new Symbol(sym.PLUSPLUS,yyline,yychar,yytext());}
    "--"            {return new Symbol(sym.MINUSMINUS,yyline,yychar,yytext());}
    "#"             {return new Symbol(sym.NUMERAL,yyline,yychar,yytext());}
    "["             {return new Symbol(sym.LEFT_BRACKET,yyline,yychar, yytext());}
    "]"             {return new Symbol(sym.RIGHT_BRACKET,yyline,yychar, yytext());}
    "{"             {return new Symbol(sym.LEFT_BRACES,yyline,yychar, yytext());}
    "}"             {return new Symbol(sym.RIGHT_BRACES,yyline,yychar, yytext());}
    "("             {return new Symbol(sym.LEFT_PARENT,yyline,yychar, yytext());}
    ")"             {return new Symbol(sym.RIGHT_PARENT,yyline,yychar, yytext());}
    ","             {return new Symbol(sym.COMMA,yyline,yychar, yytext());}
    ";"             {return new Symbol(sym.SEMI,yyline,yychar, yytext());}
    "=="            {return new Symbol(sym.EQ,yyline,yychar, yytext());}
    "="             {return new Symbol(sym.ASIG,yyline,yychar, yytext());}
    "+"             {return new Symbol(sym.PLUS,yyline,yychar, yytext());}
    "-"             {return new Symbol(sym.MINUS,yyline,yychar, yytext());}
    "*"             {return new Symbol(sym.TIMES,yyline,yychar, yytext());}
    "^"             {return new Symbol(sym.POT,yyline,yychar, yytext());}
    "%"             {return new Symbol(sym.MOD,yyline,yychar, yytext());}
    "/"             {return new Symbol(sym.DIV,yyline,yychar, yytext());}
    ">="            {return new Symbol(sym.GTR_EQ,yyline,yychar, yytext());}
    ">"             {return new Symbol(sym.GTR,yyline,yychar, yytext());}
    "<="            {return new Symbol(sym.LSS_EQ,yyline,yychar, yytext());}
    "<"             {return new Symbol(sym.LSS,yyline,yychar, yytext());}
    "<>"            {return new Symbol(sym.NOT_EQ,yyline,yychar, yytext());}
    "&&"            {return new Symbol(sym.AND,yyline,yychar, yytext());}
    "||"            {return new Symbol(sym.OR,yyline,yychar, yytext());}
    "!"             {return new Symbol(sym.NOT,yyline,yychar, yytext());}

    "."             {return new Symbol(sym.DOT,yyline,yychar, yytext());}
    ":"             {return new Symbol(sym.TWO_POINTS,yyline,yychar, yytext());}

    " " {}
    \n {yychar=0;}
    "/*" {yybegin(COM);}
    {BLANCOS} {}
    {comentario_linea} {}
    {ENT_LIT} {return new Symbol(sym.ENT_LIT,yyline,yychar,yytext());}
    {DEC_LIT} {return new Symbol(sym.DEC_LIT,yyline,yychar,yytext());}
    {BOOL_LIT} {return new Symbol(sym.BOOL_LIT,yyline,yychar,yytext());}
    {STRING_LIT} {return new Symbol(sym.STRING_LIT,yyline,yychar,yytext());}
    {CHAR_LIT} {return new Symbol(sym.CHAR_LIT,yyline,yychar,yytext());}
    {ID} {return new Symbol(sym.ID,yyline,yychar,yytext());}
    . {
            Singleton.getInstance().addError(yyline,yychar,"Lexico : Caracter: " + yytext() + " no reconocido");
           
    }
}

<COM>{
    "*/" {yybegin(YYINITIAL);}
    {BLANCOS} { }
    "\n" {  }
    . { }
}