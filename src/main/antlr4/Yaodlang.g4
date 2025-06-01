grammar Yaodlang;

@header {
package parser;
}

yaodfile
    : (yaodline | LINE_COMMENT | NL)* EOF
    ;

yaodline
    : 'FAMILY' family=ID format=FORMAT ';'                                 # familyHeader
    | 'RECORDS;'                                                           # records
    | 'RECORD' recordname=ID ';'                                           # recordName
    | 'CHA' '(' chid=ID ')' id=ID formatList na='NA'? ';'                  # chaLine
    | 'KEY' '(' keyid=ID ')' id=ID formatList ';'                          # keyLine
    | 'MIT' id=ID formatList ';'                                           # mitLine
    | 'END' ID ';'                                                         # endRecord
    ;

formatList
    : FORMAT+
    ;

ID      : LETTER (LETTER | DIGIT)* ;
FORMAT  : ID '(' NUMBER (',' NUMBER)? ')' ;
NUMBER  : DIGIT+ ;

LINE_COMMENT : '//' ~[\r\n]* NL ;
NL           : '\r'? '\n' ;

fragment LETTER : [A-Za-z_абвгдеёжзиклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ] ;
fragment DIGIT  : [0-9] ;

WS : [ \t]+ -> skip ;