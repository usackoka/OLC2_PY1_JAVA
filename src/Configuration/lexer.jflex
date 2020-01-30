package Configuration;
import java_cup.runtime.Symbol;

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
STRING_LIT = [\"]([^\"\n]|(\\\"))*[\"]

%%
<YYINITIAL>{

    "proyecto"      {return new Symbol(sym.PROYECTO,yyline,yychar,yytext());}
    "archivo"       {return new Symbol(sym.ARCHIVO,yyline,yychar,yytext());}
    "carpeta"       {return new Symbol(sym.CARPETA,yyline,yychar,yytext());}
    "ruta"          {return new Symbol(sym.RUTA,yyline,yychar,yytext());}
    "nombre"        {return new Symbol(sym.NOMBRE,yyline,yychar,yytext());}
    "correr"        {return new Symbol(sym.CORRER,yyline,yychar,yytext());}
    "configuracion" {return new Symbol(sym.CONFIGURACION,yyline,yychar,yytext());}
    "fecha_mod"     {return new Symbol(sym.FECHAMOD,yyline,yychar,yytext());}

    "{"             {return new Symbol(sym.LEFT_BRACES,yyline,yychar, yytext());}
    "}"             {return new Symbol(sym.RIGHT_BRACES,yyline,yychar, yytext());}
    ","             {return new Symbol(sym.COMMA,yyline,yychar, yytext());}
    ":"             {return new Symbol(sym.TWO_POINTS,yyline,yychar, yytext());}

    " " {}
    \n {yychar=0;}

    {STRING_LIT} {return new Symbol(sym.CADENA,yyline,yychar,yytext());}
    . {
        //Generator.GetInstance().AddError("Lexio","Caracter: " + yytext() + " no reconocido",yyline,yychar);
    }
}
