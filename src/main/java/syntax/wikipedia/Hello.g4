// Define a grammar called Hello
grammar Hello;
r  : 'hello' id;         // match keyword hello followed by an identifier
id : ID;
ID : [a-z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines