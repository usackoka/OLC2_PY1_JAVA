package Analyzer;
import java_cup.runtime.Symbol;
import AST.Principal;

%%

%{
    Principal principal;

    public void setPrincipal(Principal principal){
        this.principal = principal;
    }

    public Principal getPrincipal(){
        return this.principal;
    }
%}

%class Lexer
%public
%cupsym sym
%cup
%char
%column
%full
%ignorecase //ignore mayusculas y minusculas
%line
%unicode

%init{
    yyline = 0;
    yycolumn = 0;
%init}

//---> Expresiones Regulares
numero = (\d)+ //[0-9]+
doble = [0-9]+"."[0-9]+
comentario_linea = "#"~"\n"
comentario_multilinea = "#*"~"*#"
//id = [a-zA-Z]([a-zA-Z]|"_"|[0-9])*
id = ([a-zA-Z]|"."([a-zA-Z]|"_")|".")([a-zA-Z]|"_"|[0-9]|".")*
//caracter = "'"([^\"\n])?"'"
cadena = ([\"]([^\"\n]|(\\\"))*[\"])

%state COM

%%
<YYINITIAL>{
    //tipos de dato
    /*"integer"           {return new Symbol(sym.res_int,yyline,yycolumn,yytext());}
    "numeric"           {return new Symbol(sym.res_double,yyline,yycolumn,yytext());}
    "boolean"           {return new Symbol(sym.res_boolean,yyline,yycolumn,yytext());}
    "String"            {return new Symbol(sym.res_string,yyline,yycolumn,yytext());}
    */
    
    //valores
    "null"                          {return new Symbol(sym.res_null,yyline,yycolumn,yytext());}
    "false"                         {return new Symbol(sym.res_false,yyline,yycolumn,yytext());}
    "true"                          {return new Symbol(sym.res_true,yyline,yycolumn,yytext());}

    "function"                      {return new Symbol(sym.res_function,yyline,yycolumn,yytext());}
    "in"                            {return new Symbol(sym.res_in,yyline,yycolumn,yytext());}

    "if"                            {return new Symbol(sym.res_if,yyline,yycolumn,yytext());}
    "else"                          {return new Symbol(sym.res_else,yyline,yycolumn,yytext());}
    "while"                         {return new Symbol(sym.res_while,yyline,yycolumn,yytext());}
    "for"                           {return new Symbol(sym.res_for,yyline,yycolumn,yytext());}
    "switch"                        {return new Symbol(sym.res_switch,yyline,yycolumn,yytext());}
    "case"                          {return new Symbol(sym.res_case,yyline,yycolumn,yytext());}
    "default"                       {return new Symbol(sym.res_default,yyline,yycolumn,yytext());}
    "break"                         {return new Symbol(sym.res_break,yyline,yycolumn,yytext());}
    "continue"                      {return new Symbol(sym.res_continue,yyline,yycolumn,yytext());}
    "return"                        {return new Symbol(sym.res_return,yyline,yycolumn,yytext());}
    "do"                            {return new Symbol(sym.res_do,yyline,yycolumn,yytext());}

    "["                             {return new Symbol(sym.l_corchete,yyline,yycolumn, yytext());}
    "]"                             {return new Symbol(sym.r_corchete,yyline,yycolumn, yytext());}
    "{"                             {return new Symbol(sym.l_llave,yyline,yycolumn, yytext());}
    "}"                             {return new Symbol(sym.r_llave,yyline,yycolumn, yytext());}
    "("                             {return new Symbol(sym.l_parent,yyline,yycolumn, yytext());}
    ")"                             {return new Symbol(sym.r_parent,yyline,yycolumn, yytext());}
    ","                             {return new Symbol(sym.coma,yyline,yycolumn, yytext());}
    ";"                             {return new Symbol(sym.puntocoma,yyline,yycolumn, yytext());}
    "=="                            {return new Symbol(sym.igualigual,yyline,yycolumn, yytext());}
    "="                             {return new Symbol(sym.igual,yyline,yycolumn, yytext());}
    "+"                             {return new Symbol(sym.mas,yyline,yycolumn, yytext());}
    "-"                             {return new Symbol(sym.menos,yyline,yycolumn, yytext());}
    "*"                             {return new Symbol(sym.por,yyline,yycolumn, yytext());}
    "^"                             {return new Symbol(sym.potencia,yyline,yycolumn, yytext());}
    "%%"                            {return new Symbol(sym.modular,yyline,yycolumn, yytext());}
    "/"                             {return new Symbol(sym.div,yyline,yycolumn, yytext());}
    "?"                             {return new Symbol(sym.ternario,yyline,yycolumn, yytext());}
    ">="                            {return new Symbol(sym.mayorigual,yyline,yycolumn, yytext());}
    ">"                             {return new Symbol(sym.mayor,yyline,yycolumn, yytext());}
    "<="                            {return new Symbol(sym.menorigual,yyline,yycolumn, yytext());}
    "<"                             {return new Symbol(sym.menor,yyline,yycolumn, yytext());}
    "!="                            {return new Symbol(sym.diferente,yyline,yycolumn, yytext());}
    "&"                             {return new Symbol(sym.and,yyline,yycolumn, yytext());}
    "|"                             {return new Symbol(sym.or,yyline,yycolumn, yytext());}
    "!"                             {return new Symbol(sym.not,yyline,yycolumn, yytext());}
    ":"                             {return new Symbol(sym.dospuntos,yyline,yycolumn, yytext());}

    {numero}                        {return new Symbol(sym.numero,yyline,yycolumn,yytext());}
    {doble}                         {return new Symbol(sym.doble,yyline,yycolumn,yytext());}
    {id}                            {return new Symbol(sym.id,yyline,yycolumn,yytext());}
    //{caracter}          {return new Symbol(sym.caracter,yyline,yycolumn,yytext());}
    {cadena}                        {return new Symbol(sym.cadena,yyline,yycolumn,yytext());}
}

//------> INGORAR ESPACIOS EN BLANCO
{comentario_linea} {/*se ignoran*/}
{comentario_multilinea} {/*se ignoran*/}
[ \t\r\n\f]+                       {/* Espacios en blanco se ingnoran */}
.   {
        Token token = new Token();
        token.lexema = yytext();
        token.componenteLexico = "Léxico";
        token.descripcion = "El caracter no pertenece al lenguaje";
        token.columna = yycolumn+1+"";
        token.fila = yyline+1+"";
        this.principal.addError(token);
    }