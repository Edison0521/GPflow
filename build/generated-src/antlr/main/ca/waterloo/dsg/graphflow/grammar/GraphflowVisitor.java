// Generated from ca\waterloo\dsg\graphflow\grammar\Graphflow.g4 by ANTLR 4.7
package ca.waterloo.dsg.graphflow.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GraphflowParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GraphflowVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#graphflow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGraphflow(GraphflowParser.GraphflowContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#matchPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchPattern(GraphflowParser.MatchPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#edge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdge(GraphflowParser.EdgeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#vertex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVertex(GraphflowParser.VertexContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GraphflowParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(GraphflowParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(GraphflowParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphflowParser#whitespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhitespace(GraphflowParser.WhitespaceContext ctx);
}