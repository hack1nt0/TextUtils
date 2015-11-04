grammar regex;

wildcard : ')' | '^' | '?' | '<' | '>' | '?' | '*' | '+'  | '}' | '[' | ']' | ',' | s_tag_name | WILDCARD;

s : (s_tag | re_choice)+ EOF;

test : 'cat' | 'ca';

s_tag : '(' '?' '<' s_tag_name '>' re_choice ')';

s_tag_name : 'time0' | 'money0' | 'card0';

re_choice : re_seq ( '|' re_seq )* ;

re_seq : ( re_factor re_quant? )+ ;

re_factor : re_char | re_class | re_group | wildcard;


re_char : re_esc_char | group_ref;

re_class : '[' '^'? (re_class_char'-'re_class_char | re_class_char )+ ']';

re_class_char : re_esc_char | int_seq | wildcard;

re_group : '(' (re_choice| ) ')';

re_quant : ( '?' | '*' | '+' | '{' INTEGER_STR* (',' (INTEGER_STR*)?)? '}' ) '?'? ; //why not INTEGER?

int_seq : INTEGER_STR;
INTEGER_STR : INTEGER+;
INTEGER : [0-9];

esc : ESC;
ESC : '\\';

group_ref : GROUP_REF;
GROUP_REF : ESC INTEGER_STR;

re_esc_char : RE_ESC_CHAR;
RE_ESC_CHAR : ESC ESC | ESC [fnrtBbDdSsWw.] | ESC 'u' (INTEGER|[a-fA-F]) (INTEGER|[a-fA-F]) (INTEGER|[a-fA-F]) (INTEGER|[a-fA-F]) | ESC [\-\[\](){}?+*|.\^$] ;


WILDCARD : '(' | .;
//wildcard : ~(ESC | GROUP_REF | RE_ESC_CHAR);

//| GROUP_REF | RE_ESC_CHAR | INTEGER_STR) ;
