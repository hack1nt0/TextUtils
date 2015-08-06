// Generated from /home/dy/Desktop/newtech-smsunderstandoffline/src/main/java/com/xiaomi/dy/parser/sms/Sms.g4 by ANTLR 4.5
package com.xiaomi.nlp.util.sms;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SmsParser}.
 */
public interface SmsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SmsParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(@NotNull SmsParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(@NotNull SmsParser.SContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#smsLinear}.
	 * @param ctx the parse tree
	 */
	void enterSmsLinear(@NotNull SmsParser.SmsLinearContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#smsLinear}.
	 * @param ctx the parse tree
	 */
	void exitSmsLinear(@NotNull SmsParser.SmsLinearContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#colonSent}.
	 * @param ctx the parse tree
	 */
	void enterColonSent(@NotNull SmsParser.ColonSentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#colonSent}.
	 * @param ctx the parse tree
	 */
	void exitColonSent(@NotNull SmsParser.ColonSentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#bullet}.
	 * @param ctx the parse tree
	 */
	void enterBullet(@NotNull SmsParser.BulletContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#bullet}.
	 * @param ctx the parse tree
	 */
	void exitBullet(@NotNull SmsParser.BulletContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#delimiter}.
	 * @param ctx the parse tree
	 */
	void enterDelimiter(@NotNull SmsParser.DelimiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#delimiter}.
	 * @param ctx the parse tree
	 */
	void exitDelimiter(@NotNull SmsParser.DelimiterContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#bulletSent}.
	 * @param ctx the parse tree
	 */
	void enterBulletSent(@NotNull SmsParser.BulletSentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#bulletSent}.
	 * @param ctx the parse tree
	 */
	void exitBulletSent(@NotNull SmsParser.BulletSentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#ordSent}.
	 * @param ctx the parse tree
	 */
	void enterOrdSent(@NotNull SmsParser.OrdSentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#ordSent}.
	 * @param ctx the parse tree
	 */
	void exitOrdSent(@NotNull SmsParser.OrdSentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#colonToken}.
	 * @param ctx the parse tree
	 */
	void enterColonToken(@NotNull SmsParser.ColonTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#colonToken}.
	 * @param ctx the parse tree
	 */
	void exitColonToken(@NotNull SmsParser.ColonTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#tokens}.
	 * @param ctx the parse tree
	 */
	void enterTokens(@NotNull SmsParser.TokensContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#tokens}.
	 * @param ctx the parse tree
	 */
	void exitTokens(@NotNull SmsParser.TokensContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(@NotNull SmsParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(@NotNull SmsParser.TokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmsParser#bracket}.
	 * @param ctx the parse tree
	 */
	void enterBracket(@NotNull SmsParser.BracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmsParser#bracket}.
	 * @param ctx the parse tree
	 */
	void exitBracket(@NotNull SmsParser.BracketContext ctx);
}