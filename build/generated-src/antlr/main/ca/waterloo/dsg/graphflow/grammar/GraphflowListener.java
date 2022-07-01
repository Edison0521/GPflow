// Generated from ca\waterloo\dsg\graphflow\grammar\Graphflow.g4 by ANTLR 4.7
package ca.waterloo.dsg.graphflow.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GraphflowParser}.
 */
public interface GraphflowListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#graphflow}.
	 * @param ctx the parse tree
	 */
	void enterGraphflow(GraphflowParser.GraphflowContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#graphflow}.
	 * @param ctx the parse tree
	 */
	void exitGraphflow(GraphflowParser.GraphflowContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#matchPattern}.
	 * @param ctx the parse tree
	 */
	void enterMatchPattern(GraphflowParser.MatchPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#matchPattern}.
	 * @param ctx the parse tree
	 */
	void exitMatchPattern(GraphflowParser.MatchPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#edge}.
	 * @param ctx the parse tree
	 */
	void enterEdge(GraphflowParser.EdgeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#edge}.
	 * @param ctx the parse tree
	 */
	void exitEdge(GraphflowParser.EdgeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#vertex}.
	 * @param ctx the parse tree
	 */
	void enterVertex(GraphflowParser.VertexContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#vertex}.
	 * @param ctx the parse tree
	 */
	void exitVertex(GraphflowParser.VertexContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GraphflowParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GraphflowParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(GraphflowParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(GraphflowParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(GraphflowParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(GraphflowParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphflowParser#whitespace}.
	 * @param ctx the parse tree
	 */
	void enterWhitespace(GraphflowParser.WhitespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphflowParser#whitespace}.
	 * @param ctx the parse tree
	 */
	void exitWhitespace(GraphflowParser.WhitespaceContext ctx);
}