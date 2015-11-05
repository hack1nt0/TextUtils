// Generated from /home/dy/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/Regex.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RegexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RegexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RegexParser#wildcard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard(@NotNull RegexParser.WildcardContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(@NotNull RegexParser.SContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#s_tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS_tag(@NotNull RegexParser.S_tagContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#s_tag_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS_tag_name(@NotNull RegexParser.S_tag_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_choice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_choice(@NotNull RegexParser.Re_choiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_seq_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_seq_elem(@NotNull RegexParser.Re_seq_elemContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_seq(@NotNull RegexParser.Re_seqContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_factor(@NotNull RegexParser.Re_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_char(@NotNull RegexParser.Re_charContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_class(@NotNull RegexParser.Re_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_class_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_class_char(@NotNull RegexParser.Re_class_charContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_group(@NotNull RegexParser.Re_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_quant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_quant(@NotNull RegexParser.Re_quantContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#int_seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_seq(@NotNull RegexParser.Int_seqContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#esc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEsc(@NotNull RegexParser.EscContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#group_ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup_ref(@NotNull RegexParser.Group_refContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#re_esc_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRe_esc_char(@NotNull RegexParser.Re_esc_charContext ctx);
}