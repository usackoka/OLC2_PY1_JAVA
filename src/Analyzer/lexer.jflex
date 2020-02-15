package Analyzer;
import java_cup.runtime.Symbol;
import Principal.Singleton;
%%
%class Lexer
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase //ignore mayusculas y minusculas
%line
%unicode

//---> Expresiones Regulares
numero = (\d)+ //[0-9]+
doble = [0-9]+"."[0-9]+
comentario_linea = "#"~"\n"
comentario_multilinea = "#*"~"*#"
id = [a-zA-Z]([a-zA-Z]|"_"|[0-9])*
caracter = "'"([^\"\n])?"'"
cadena = ([\"]([^\"\n]|(\\\"))*[\"])

%state COM

%%
<YYINITIAL>{
    //tipos de dato
    "integer"           {return new Symbol(sym.res_int,yyline,yychar,yytext());}
    "numeric"           {return new Symbol(sym.res_double,yyline,yychar,yytext());}
    "boolean"           {return new Symbol(sym.res_boolean,yyline,yychar,yytext());}
    "String"            {return new Symbol(sym.res_string,yyline,yychar,yytext());}
    
    //valores
    "null"              {return new Symbol(sym.res_null,yyline,yychar,yytext());}
    "true"              {return new Symbol(sym.res_false,yyline,yychar,yytext());}
    "false"             {return new Symbol(sym.res_true,yyline,yychar,yytext());}

    "if"                {return new Symbol(sym.res_if,yyline,yychar,yytext());}
    "else"              {return new Symbol(sym.res_else,yyline,yychar,yytext());}
    "while"             {return new Symbol(sym.res_while,yyline,yychar,yytext());}
    "for"               {return new Symbol(sym.res_for,yyline,yychar,yytext());}
    "repeat"            {return new Symbol(sym.res_repeat,yyline,yychar,yytext());}
    "switch"            {return new Symbol(sym.res_switch,yyline,yychar,yytext());}
    "case"              {return new Symbol(sym.res_case,yyline,yychar,yytext());}
    "default"           {return new Symbol(sym.res_default,yyline,yychar,yytext());}
    "break"             {return new Symbol(sym.res_break,yyline,yychar,yytext());}
    "continue"          {return new Symbol(sym.res_continue,yyline,yychar,yytext());}
    "final"             {return new Symbol(sym.res_final,yyline,yychar,yytext());}
    "import"            {return new Symbol(sym.res_import,yyline,yychar,yytext());}
    "return"            {return new Symbol(sym.res_return,yyline,yychar,yytext());}
    "do"                {return new Symbol(sym.res_do,yyline,yychar,yytext());}
    "main"              {return new Symbol(sym.res_main,yyline,yychar,yytext());}

    "print"             {return new Symbol(sym.res_print,yyline,yychar,yytext());}
    "println"           {return new Symbol(sym.res_println,yyline,yychar,yytext());}

    "++"            {return new Symbol(sym.masmas,yyline,yychar,yytext());}
    "--"            {return new Symbol(sym.menosmenos,yyline,yychar,yytext());}
    "["             {return new Symbol(sym.l_corchete,yyline,yychar, yytext());}
    "]"             {return new Symbol(sym.r_corchete,yyline,yychar, yytext());}
    "{"             {return new Symbol(sym.l_llave,yyline,yychar, yytext());}
    "}"             {return new Symbol(sym.r_llave,yyline,yychar, yytext());}
    "("             {return new Symbol(sym.l_parent,yyline,yychar, yytext());}
    ")"             {return new Symbol(sym.r_parent,yyline,yychar, yytext());}
    ","             {return new Symbol(sym.coma,yyline,yychar, yytext());}
    ";"             {return new Symbol(sym.puntocoma,yyline,yychar, yytext());}
    "=="            {return new Symbol(sym.igualigual,yyline,yychar, yytext());}
    "="             {return new Symbol(sym.igual,yyline,yychar, yytext());}
    "+"             {return new Symbol(sym.mas,yyline,yychar, yytext());}
    "-"             {return new Symbol(sym.menos,yyline,yychar, yytext());}
    "*"             {return new Symbol(sym.por,yyline,yychar, yytext());}
    "^"             {return new Symbol(sym.potencia,yyline,yychar, yytext());}
    "%%"             {return new Symbol(sym.modular,yyline,yychar, yytext());}
    "/"             {return new Symbol(sym.div,yyline,yychar, yytext());}
    ">="            {return new Symbol(sym.mayorigual,yyline,yychar, yytext());}
    ">"             {return new Symbol(sym.mayor,yyline,yychar, yytext());}
    "<="            {return new Symbol(sym.menorigual,yyline,yychar, yytext());}
    "<"             {return new Symbol(sym.menor,yyline,yychar, yytext());}
    "!="            {return new Symbol(sym.diferente,yyline,yychar, yytext());}
    "&"            {return new Symbol(sym.and,yyline,yychar, yytext());}
    "|"            {return new Symbol(sym.or,yyline,yychar, yytext());}
    "!"             {return new Symbol(sym.not,yyline,yychar, yytext());}
    "."             {return new Symbol(sym.punto,yyline,yychar, yytext());}
    ":"             {return new Symbol(sym.dospuntos,yyline,yychar, yytext());}

    {numero}            {return new Symbol(sym.numero,yyline,yychar,yytext());}
    {doble}             {return new Symbol(sym.doble,yyline,yychar,yytext());}
    {id}                {return new Symbol(sym.id,yyline,yychar,yytext());}
    {caracter}          {return new Symbol(sym.caracter,yyline,yychar,yytext());}
    {cadena}            {return new Symbol(sym.cadena,yyline,yychar,yytext());}
}

//------> INGORAR ESPACIOS EN BLANCO
{comentario_linea} {/*se ignoran*/}
{comentario_multilinea} {/*se ignoran*/}
[ \t\r\n\f]+                       {/* Espacios en blanco se ingnoran */}
.   { TablaErrores.add(new Token(yytext(),"Lexico","Caracter no pertenece al lenguaje",yycolumn+1,yyline+1)); }