grammar Tiggrammar;

@header {
    package cloodsee.language.grammar;
}

// un programme est (pour l'instant) une expression
prog returns [cloodsee.language.ast.ASTprogram node] 
    : body=expr EOF
    ;

// Expressions enrichies
expr returns [cloodsee.language.ast.ASTexpression node]
    : op='-' arg=expr # Unary
    | arg1=expr op=('*' | '/' | '%') arg2=expr # Binary
    | arg1=expr op=('+' | '-') arg2=expr # Binary
    | arg1=expr op=('==' | '<' | '>' | '<=' | '>=') arg2=expr # Binary
    | arg1=expr op=('&&' | '||') arg2=expr # Binary
    | arg1=STR op='=' arg2=expr # Affectation
    | intConst=INT # ConstInteger
    | boolConst=BOOL # ConstBool
    | stringConst = STRING #ConstString
    | op='!' arg=expr # Unary
    | func=STR '(' args+=expr? (',' args+=expr)* ')' #Function
    | 'if' cond=expr 'then' branch1=expr 'else' branch2=expr #IfThenElse
    | '(' body+=expr (';'? body+=expr)* ';'? ')' # Sequence
    | 'let' name=STR '=' value=expr # Declaration
    | name=STR # Variable
    | 'while' cond=expr 'do' branch=expr #While
    ;

/*
 * Règles lexicales.
 * 
 * ANTLR impose que le nom des règles lexicales commencent par
 * une majuscule. 
 * Ces règles prnnent la forme d'expressions régulières.
 */

// Constantes entières
INT : [0-9]+ ;
BOOL : 'true' | 'false' ;
STR : ([a-zA-Z_])* ;
STRING : '"' (~[\r"])* '"';

// Commentaires
LINE_COMMENT : '//' (~[\r\n])* -> skip;
COMMENT : '/*' ('*' ~[/] | ~[*])* '*/' -> skip;

// Espaces
SPACE : [ \t\r\n]+ -> skip;
