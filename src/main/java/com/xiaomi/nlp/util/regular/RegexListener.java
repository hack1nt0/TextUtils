// Generated from /Users/DY/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/Regex.g4 by ANTLR 4.5.1
package com.xiaomi.nlp.util.regular;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RegexParser}.
 */
public interface RegexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RegexParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void enterWildcard(RegexParser.WildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void exitWildcard(RegexParser.WildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#wildcard_no_lb}.
	 * @param ctx the parse tree
	 */
	void enterWildcard_no_lb(RegexParser.Wildcard_no_lbContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#wildcard_no_lb}.
	 * @param ctx the parse tree
	 */
	void exitWildcard_no_lb(RegexParser.Wildcard_no_lbContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#wildcard_no_mb_to}.
	 * @param ctx the parse tree
	 */
	void enterWildcard_no_mb_to(RegexParser.Wildcard_no_mb_toContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#wildcard_no_mb_to}.
	 * @param ctx the parse tree
	 */
	void exitWildcard_no_mb_to(RegexParser.Wildcard_no_mb_toContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(RegexParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(RegexParser.SContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#s_tag}.
	 * @param ctx the parse tree
	 */
	void enterS_tag(RegexParser.S_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#s_tag}.
	 * @param ctx the parse tree
	 */
	void exitS_tag(RegexParser.S_tagContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#s_tag_name}.
	 * @param ctx the parse tree
	 */
	void enterS_tag_name(RegexParser.S_tag_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#s_tag_name}.
	 * @param ctx the parse tree
	 */
	void exitS_tag_name(RegexParser.S_tag_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#s_group}.
	 * @param ctx the parse tree
	 */
	void enterS_group(RegexParser.S_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#s_group}.
	 * @param ctx the parse tree
	 */
	void exitS_group(RegexParser.S_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#s_group_margin}.
	 * @param ctx the parse tree
	 */
	void enterS_group_margin(RegexParser.S_group_marginContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#s_group_margin}.
	 * @param ctx the parse tree
	 */
	void exitS_group_margin(RegexParser.S_group_marginContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#s_class}.
	 * @param ctx the parse tree
	 */
	void enterS_class(RegexParser.S_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#s_class}.
	 * @param ctx the parse tree
	 */
	void exitS_class(RegexParser.S_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_choice}.
	 * @param ctx the parse tree
	 */
	void enterRe_choice(RegexParser.Re_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_choice}.
	 * @param ctx the parse tree
	 */
	void exitRe_choice(RegexParser.Re_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_or}.
	 * @param ctx the parse tree
	 */
	void enterRe_or(RegexParser.Re_orContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_or}.
	 * @param ctx the parse tree
	 */
	void exitRe_or(RegexParser.Re_orContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_seq}.
	 * @param ctx the parse tree
	 */
	void enterRe_seq(RegexParser.Re_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_seq}.
	 * @param ctx the parse tree
	 */
	void exitRe_seq(RegexParser.Re_seqContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_seq_elem}.
	 * @param ctx the parse tree
	 */
	void enterRe_seq_elem(RegexParser.Re_seq_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_seq_elem}.
	 * @param ctx the parse tree
	 */
	void exitRe_seq_elem(RegexParser.Re_seq_elemContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_factor}.
	 * @param ctx the parse tree
	 */
	void enterRe_factor(RegexParser.Re_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_factor}.
	 * @param ctx the parse tree
	 */
	void exitRe_factor(RegexParser.Re_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_char}.
	 * @param ctx the parse tree
	 */
	void enterRe_char(RegexParser.Re_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_char}.
	 * @param ctx the parse tree
	 */
	void exitRe_char(RegexParser.Re_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_class}.
	 * @param ctx the parse tree
	 */
	void enterRe_class(RegexParser.Re_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_class}.
	 * @param ctx the parse tree
	 */
	void exitRe_class(RegexParser.Re_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_class_char}.
	 * @param ctx the parse tree
	 */
	void enterRe_class_char(RegexParser.Re_class_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_class_char}.
	 * @param ctx the parse tree
	 */
	void exitRe_class_char(RegexParser.Re_class_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_group}.
	 * @param ctx the parse tree
	 */
	void enterRe_group(RegexParser.Re_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_group}.
	 * @param ctx the parse tree
	 */
	void exitRe_group(RegexParser.Re_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_choice_no_lb}.
	 * @param ctx the parse tree
	 */
	void enterRe_choice_no_lb(RegexParser.Re_choice_no_lbContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_choice_no_lb}.
	 * @param ctx the parse tree
	 */
	void exitRe_choice_no_lb(RegexParser.Re_choice_no_lbContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_seq_no_lb}.
	 * @param ctx the parse tree
	 */
	void enterRe_seq_no_lb(RegexParser.Re_seq_no_lbContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_seq_no_lb}.
	 * @param ctx the parse tree
	 */
	void exitRe_seq_no_lb(RegexParser.Re_seq_no_lbContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_seq_elem_no_lb}.
	 * @param ctx the parse tree
	 */
	void enterRe_seq_elem_no_lb(RegexParser.Re_seq_elem_no_lbContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_seq_elem_no_lb}.
	 * @param ctx the parse tree
	 */
	void exitRe_seq_elem_no_lb(RegexParser.Re_seq_elem_no_lbContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_factor_no_lb}.
	 * @param ctx the parse tree
	 */
	void enterRe_factor_no_lb(RegexParser.Re_factor_no_lbContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_factor_no_lb}.
	 * @param ctx the parse tree
	 */
	void exitRe_factor_no_lb(RegexParser.Re_factor_no_lbContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_quant}.
	 * @param ctx the parse tree
	 */
	void enterRe_quant(RegexParser.Re_quantContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_quant}.
	 * @param ctx the parse tree
	 */
	void exitRe_quant(RegexParser.Re_quantContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#int_seq}.
	 * @param ctx the parse tree
	 */
	void enterInt_seq(RegexParser.Int_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#int_seq}.
	 * @param ctx the parse tree
	 */
	void exitInt_seq(RegexParser.Int_seqContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#esc}.
	 * @param ctx the parse tree
	 */
	void enterEsc(RegexParser.EscContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#esc}.
	 * @param ctx the parse tree
	 */
	void exitEsc(RegexParser.EscContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#group_ref}.
	 * @param ctx the parse tree
	 */
	void enterGroup_ref(RegexParser.Group_refContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#group_ref}.
	 * @param ctx the parse tree
	 */
	void exitGroup_ref(RegexParser.Group_refContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#re_esc_char}.
	 * @param ctx the parse tree
	 */
	void enterRe_esc_char(RegexParser.Re_esc_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#re_esc_char}.
	 * @param ctx the parse tree
	 */
	void exitRe_esc_char(RegexParser.Re_esc_charContext ctx);
}