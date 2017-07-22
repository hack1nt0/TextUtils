// Generated from /Users/dy/AC/src/main/java/wikipedia/mediawiki.g4 by ANTLR 4.7
package syntax.wikipedia;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link mediawikiParser}.
 */
public interface mediawikiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(mediawikiParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(mediawikiParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#infobox}.
	 * @param ctx the parse tree
	 */
	void enterInfobox(mediawikiParser.InfoboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#infobox}.
	 * @param ctx the parse tree
	 */
	void exitInfobox(mediawikiParser.InfoboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(mediawikiParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(mediawikiParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(mediawikiParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(mediawikiParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(mediawikiParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(mediawikiParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(mediawikiParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(mediawikiParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(mediawikiParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(mediawikiParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#headline}.
	 * @param ctx the parse tree
	 */
	void enterHeadline(mediawikiParser.HeadlineContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#headline}.
	 * @param ctx the parse tree
	 */
	void exitHeadline(mediawikiParser.HeadlineContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(mediawikiParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(mediawikiParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#entity}.
	 * @param ctx the parse tree
	 */
	void enterEntity(mediawikiParser.EntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#entity}.
	 * @param ctx the parse tree
	 */
	void exitEntity(mediawikiParser.EntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#format_tag}.
	 * @param ctx the parse tree
	 */
	void enterFormat_tag(mediawikiParser.Format_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#format_tag}.
	 * @param ctx the parse tree
	 */
	void exitFormat_tag(mediawikiParser.Format_tagContext ctx);
	/**
	 * Enter a parse tree produced by {@link mediawikiParser#unknown}.
	 * @param ctx the parse tree
	 */
	void enterUnknown(mediawikiParser.UnknownContext ctx);
	/**
	 * Exit a parse tree produced by {@link mediawikiParser#unknown}.
	 * @param ctx the parse tree
	 */
	void exitUnknown(mediawikiParser.UnknownContext ctx);
}