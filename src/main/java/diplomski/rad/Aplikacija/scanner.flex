package diplomski.rad.Aplikacija;

import java.io.*;
import java_cup.runtime.*;

%%

%cup
%line
%column

%eofval{ 
    return new_symbol(sym.EOF);
%eofval}

%{
	private Symbol new_symbol(int type) {
			return new Symbol(type, yyline+1, yycolumn);
	}

	private Symbol new_symbol(int type, Object value) {
			return new Symbol(type, yyline+1, yycolumn, value);
	}
%}

text = [a-zA-Z_0-9]+
mesec = "JAN"|"FEB"|"MAR"|"APR"|"MAJ"|"JUN"|"JUL"|"AVG"|"SEP"|"OKT"|"NOV"|"DEC"
mesto = "basta"|"rasad"
broj = [1-9][0-9]*
svetlo = "senka"|"polusenka"|"sunce"
voda = "puno"|"srednje"|"malo"

%%

"dodaj"             { return new_symbol(sym.DODAJ); }
"pretrazi_raspon"	{ return new_symbol(sym.PRETRAZI_RASPON); }
"pretrazi"			{ return new_symbol(sym.PRETRAZI); }
"ukloni"            { return new_symbol(sym.UKLONI); }
"ime"				{ return new_symbol(sym.IME); }
"sadnja"			{ return new_symbol(sym.SADNJA); }
"berba"				{ return new_symbol(sym.BERBA); }
"mesto"				{ return new_symbol(sym.MESTO_KW);	}
"dubina"			{ return new_symbol(sym.DUBINA); }
"svetlo"			{ return new_symbol(sym.SVETLO_KW); }
"voda"				{ return new_symbol(sym.VODA_KW); }
"="					{ return new_symbol(sym.JEDNAKO); }
"\""                { return new_symbol(sym.NAVODNIK); }
"("                 { return new_symbol(sym.OZAGRADA); }
")"                 { return new_symbol(sym.ZZAGRADA); }
","                 { return new_symbol(sym.ZAREZ); }
{mesec}             { return new_symbol(sym.MESEC, yytext()); }
{mesto}             { return new_symbol(sym.MESTO, yytext()); }
{svetlo}            { return new_symbol(sym.SVETLO, yytext()); }
{voda}              { return new_symbol(sym.VODA, yytext()); }
{broj}              { return new_symbol(sym.BROJ, new Integer(yytext())); }
"//".*              { /* Ignorisi */ }
{text}              { return new_symbol(sym.TEXT, yytext()); } 
\t|\f|" "|\r|\n     { /* Ignorisi */}
.                   { System.out.println("Neispravan karakter: " + yytext()); }