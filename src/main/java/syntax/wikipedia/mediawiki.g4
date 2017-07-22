grammar mediawiki;


r : infobox? text  category* ;

infobox : '{{Infobox' title ('|' key '=' value)* '}}' ;

title : (~'|')+ ;

key : (~'=')+ ;

value : text ;

category : CATEGORY;

text : (headline | entity | format_tag | unknown | string )*;

headline : '==' CHAR+ '==';

string : CHAR+;

entity : ENTITY;

format_tag : FORMAT_TAG;

unknown : '{{' ('\\}' | ~('}'))+? '}}';


FORMAT_TAG : ('<br />' | '&lt;br /&gt;')  -> skip ;

REFERENCE : (('&lt;ref&gt;' (CHAR|WS)+ '&lt;/ref&gt;') | ('<ref>' (CHAR|WS)+ '</ref>')) -> skip ;

IMAGE_RADIO : '[[File:' ('\\]' | ~']')+ ']]' -> skip;

CATEGORY: '[[Category:' ('\\]' | ~(']'))+ ']]' ;

ENTITY : '[[' ('\\]' | ~(']'))+ ']]';

//STRING : (CHAR)*;

CHAR : [\p{Letter}\p{Number}\p{Symbol}\p{Punctuation}];

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

