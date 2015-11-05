// Generated from /Users/DY/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/regex.g4 by ANTLR 4.5.1
package com.xiaomi.nlp.util.regular;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link regexParser}.
 */
public interface regexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link regexParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void enterWildcard(regexParser.WildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void exitWildcard(regexParser.WildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(regexParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(regexParser.SContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(regexParser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(regexParser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#s_tag}.
	 * @param ctx the parse tree
	 */
	void enterS_tag(regexParser.S_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#s_tag}.
	 * @param ctx the parse tree
	 */
	void exitS_tag(regexParser.S_tagContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#s_tag_name}.
	 * @param ctx the parse tree
	 */
	void enterS_tag_name(regexParser.S_tag_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#s_tag_name}.
	 * @param ctx the parse tree
	 */
	void exitS_tag_name(regexParser.S_tag_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_choice}.
	 * @param ctx the parse tree
	 */
	void enterRe_choice(regexParser.Re_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_choice}.
	 * @param ctx the parse tree
	 */
	void exitRe_choice(regexParser.Re_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_seq_elem}.
	 * @param ctx the parse tree
	 */
	void enterRe_seq_elem(regexParser.Re_seq_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_seq_elem}.
	 * @param ctx the parse tree
	 */
	void exitRe_seq_elem(regexParser.Re_seq_elemContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_seq}.
	 * @param ctx the parse tree
	 */
	void enterRe_seq(regexParser.Re_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_seq}.
	 * @param ctx the parse tree
	 */
	void exitRe_seq(regexParser.Re_seqContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_factor}.
	 * @param ctx the parse tree
	 */
	void enterRe_factor(regexParser.Re_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_factor}.
	 * @param ctx the parse tree
	 */
	void exitRe_factor(regexParser.Re_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_char}.
	 * @param ctx the parse tree
	 */
	void enterRe_char(regexParser.Re_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_char}.
	 * @param ctx the parse tree
	 */
	void exitRe_char(regexParser.Re_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_class}.
	 * @param ctx the parse tree
	 */
	void enterRe_class(regexParser.Re_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_class}.
	 * @param ctx the parse tree
	 */
	void exitRe_class(regexParser.Re_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_class_char}.
	 * @param ctx the parse tree
	 */
	void enterRe_class_char(regexParser.Re_class_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_class_char}.
	 * @param ctx the parse tree
	 */
	void exitRe_class_char(regexParser.Re_class_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_group}.
	 * @param ctx the parse tree
	 */
	void enterRe_group(regexParser.Re_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_group}.
	 * @param ctx the parse tree
	 */
	void exitRe_group(regexParser.Re_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_quant}.
	 * @param ctx the parse tree
	 */
	void enterRe_quant(regexParser.Re_quantContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_quant}.
	 * @param ctx the parse tree
	 */
	void exitRe_quant(regexParser.Re_quantContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#int_seq}.
	 * @param ctx the parse tree
	 */
	void enterInt_seq(regexParser.Int_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#int_seq}.
	 * @param ctx the parse tree
	 */
	void exitInt_seq(regexParser.Int_seqContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#esc}.
	 * @param ctx the parse tree
	 */
	void enterEsc(regexParser.EscContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#esc}.
	 * @param ctx the parse tree
	 */
	void exitEsc(regexParser.EscContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#group_ref}.
	 * @param ctx the parse tree
	 */
	void enterGroup_ref(regexParser.Group_refContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#group_ref}.
	 * @param ctx the parse tree
	 */
	void exitGroup_ref(regexParser.Group_refContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#re_esc_char}.
	 * @param ctx the parse tree
	 */
	void enterRe_esc_char(regexParser.Re_esc_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#re_esc_char}.
	 * @param ctx the parse tree
	 */
	void exitRe_esc_char(regexParser.Re_esc_charContext ctx);
}