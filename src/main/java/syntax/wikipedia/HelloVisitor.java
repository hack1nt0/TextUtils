// Generated from /Users/dy/AC/src/main/java/wikipedia/Hello.g4 by ANTLR 4.7
package syntax.wikipedia;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(HelloParser.RContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(HelloParser.IdContext ctx);
}