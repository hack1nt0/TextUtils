grammar Regex;

wildcard : '(' | ')' | '^' | '?' | '<' | '>' | '?' | '*' | '+'  | '{' | '}' | '[' | ']' | ',' | '|' | '-' | s_tag_name | WILDCARD;

wildcard_no_lb : '^' | '?' | '<' | '>' | '?' | '*' | '+'  | '{' | '}' | '[' | ']' | ',' | '|' | '-' | s_tag_name | WILDCARD;

wildcard_no_mb_to: '(' | ')' | '^' | '?' | '<' | '>' | '?' | '*' | '+'  | '{' | '}' | ',' | '|' | s_tag_name | WILDCARD;

s : (s_tag | re_choice)+ EOF;

//sentiment tag
s_tag : '(' '?' '<' s_tag_name '>' re_choice ')';

s_tag_name : 'time0' | 'money0' | 'card0';

//regex
re_choice : re_seq ( re_or re_seq )* ;

re_or : '|';

re_seq : re_seq_elem+? ; //'?' is necessary here, otherwise the re_factor may overwhelm the s_tag

re_seq_elem : re_factor re_quant?;

re_factor : re_class | re_group | re_char | wildcard;

re_char : re_esc_char | int_seq | group_ref ;

//class
re_class : '[' '^'? (re_class_char'-'re_class_char | re_class_char )+ ']';

re_class_char : re_esc_char | int_seq | wildcard_no_mb_to;

//group
re_group : '(' (re_choice_no_lb| ) ')'; // (xx(yy) -> (yy) is a group

re_choice_no_lb : re_seq_no_lb ( re_or re_seq_no_lb)* ;

re_seq_no_lb : re_seq_elem_no_lb+? ; //'?' is necessary here, otherwise the re_factor may overwhelm the s_tag

re_seq_elem_no_lb : re_factor_no_lb re_quant?;

re_factor_no_lb : re_class | re_group | re_char | wildcard_no_lb;

//quantifier
re_quant : ( '?' | '*' | '+' | '{' INTEGER_STR* (',' (INTEGER_STR*)?)? '}' ) '?'? ; //why not INTEGER?


//lex
//digital sequence
int_seq : INTEGER_STR;
INTEGER_STR : INTEGER+;
INTEGER : [0-9];

//escape slash
esc : ESC;
ESC : '\\';

//pre match group reference
group_ref : GROUP_REF;
GROUP_REF : ESC INTEGER_STR;

//escape char
re_esc_char : RE_ESC_CHAR;
RE_ESC_CHAR : ESC ESC | ESC [fnrtBbDdSsWw.] | ESC 'u' (INTEGER|[a-fA-F]) (INTEGER|[a-fA-F]) (INTEGER|[a-fA-F]) (INTEGER|[a-fA-F]) | ESC [\-\[\](){}?+*|.\^$] ;

//other char
WILDCARD : .;
