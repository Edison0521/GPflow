// Generated from ca\waterloo\dsg\graphflow\grammar\Graphflow.g4 by ANTLR 4.7
package ca.waterloo.dsg.graphflow.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GraphflowParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LIMIT=1, QuotedCharacter=2, QuotedString=3, Comment=4, SPACE=5, TAB=6, 
		LINE_FEED=7, FORM_FEED=8, BACKSPACE=9, VERTICAL_TAB=10, CARRIAGE_RETURN=11, 
		DASH=12, UNDERSCORE=13, FORWARD_SLASH=14, BACKWARD_SLASH=15, SEMICOLON=16, 
		COMMA=17, COLON=18, SINGLE_QUOTE=19, DOUBLE_QUOTE=20, OPEN_ROUND_BRACKET=21, 
		CLOSE_ROUND_BRACKET=22, OPEN_SQUARE_BRACKET=23, CLOSE_SQUARE_BRACKET=24, 
		GREATER_THAN=25, Digits=26, Characters=27;
	public static final int
		RULE_graphflow = 0, RULE_matchPattern = 1, RULE_edge = 2, RULE_vertex = 3, 
		RULE_type = 4, RULE_label = 5, RULE_variable = 6, RULE_whitespace = 7;
	public static final String[] ruleNames = {
		"graphflow", "matchPattern", "edge", "vertex", "type", "label", "variable", 
		"whitespace"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"'-'", "'_'", "'/'", "'\\'", "';'", "','", "':'", "'''", "'\"'", "'('", 
		"')'", "'['", "']'", "'>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LIMIT", "QuotedCharacter", "QuotedString", "Comment", "SPACE", 
		"TAB", "LINE_FEED", "FORM_FEED", "BACKSPACE", "VERTICAL_TAB", "CARRIAGE_RETURN", 
		"DASH", "UNDERSCORE", "FORWARD_SLASH", "BACKWARD_SLASH", "SEMICOLON", 
		"COMMA", "COLON", "SINGLE_QUOTE", "DOUBLE_QUOTE", "OPEN_ROUND_BRACKET", 
		"CLOSE_ROUND_BRACKET", "OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", 
		"GREATER_THAN", "Digits", "Characters"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Graphflow.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GraphflowParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GraphflowContext extends ParserRuleContext {
		public MatchPatternContext matchPattern() {
			return getRuleContext(MatchPatternContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GraphflowParser.EOF, 0); }
		public List<WhitespaceContext> whitespace() {
			return getRuleContexts(WhitespaceContext.class);
		}
		public WhitespaceContext whitespace(int i) {
			return getRuleContext(WhitespaceContext.class,i);
		}
		public TerminalNode LIMIT() { return getToken(GraphflowParser.LIMIT, 0); }
		public TerminalNode Digits() { return getToken(GraphflowParser.Digits, 0); }
		public TerminalNode SEMICOLON() { return getToken(GraphflowParser.SEMICOLON, 0); }
		public GraphflowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphflow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterGraphflow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitGraphflow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitGraphflow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GraphflowContext graphflow() throws RecognitionException {
		GraphflowContext _localctx = new GraphflowContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_graphflow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
				{
				setState(16);
				whitespace();
				}
			}

			setState(19);
			matchPattern();
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
				{
				setState(20);
				whitespace();
				}
			}

			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(23);
				match(LIMIT);
				setState(24);
				whitespace();
				setState(25);
				match(Digits);
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
					{
					setState(26);
					whitespace();
					}
				}

				}
			}

			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(31);
				match(SEMICOLON);
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
					{
					setState(32);
					whitespace();
					}
				}

				}
			}

			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchPatternContext extends ParserRuleContext {
		public List<EdgeContext> edge() {
			return getRuleContexts(EdgeContext.class);
		}
		public EdgeContext edge(int i) {
			return getRuleContext(EdgeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GraphflowParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GraphflowParser.COMMA, i);
		}
		public List<WhitespaceContext> whitespace() {
			return getRuleContexts(WhitespaceContext.class);
		}
		public WhitespaceContext whitespace(int i) {
			return getRuleContext(WhitespaceContext.class,i);
		}
		public MatchPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterMatchPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitMatchPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitMatchPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchPatternContext matchPattern() throws RecognitionException {
		MatchPatternContext _localctx = new MatchPatternContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_matchPattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			edge();
			setState(50);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
						{
						setState(40);
						whitespace();
						}
					}

					setState(43);
					match(COMMA);
					setState(45);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
						{
						setState(44);
						whitespace();
						}
					}

					setState(47);
					edge();
					}
					} 
				}
				setState(52);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EdgeContext extends ParserRuleContext {
		public List<VertexContext> vertex() {
			return getRuleContexts(VertexContext.class);
		}
		public VertexContext vertex(int i) {
			return getRuleContext(VertexContext.class,i);
		}
		public TerminalNode DASH() { return getToken(GraphflowParser.DASH, 0); }
		public TerminalNode GREATER_THAN() { return getToken(GraphflowParser.GREATER_THAN, 0); }
		public WhitespaceContext whitespace() {
			return getRuleContext(WhitespaceContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public EdgeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edge; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterEdge(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitEdge(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitEdge(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EdgeContext edge() throws RecognitionException {
		EdgeContext _localctx = new EdgeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_edge);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			vertex();
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
				{
				setState(54);
				whitespace();
				}
			}

			setState(57);
			match(DASH);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_SQUARE_BRACKET) {
				{
				setState(58);
				label();
				}
			}

			setState(61);
			match(GREATER_THAN);
			setState(62);
			vertex();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VertexContext extends ParserRuleContext {
		public TerminalNode OPEN_ROUND_BRACKET() { return getToken(GraphflowParser.OPEN_ROUND_BRACKET, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode CLOSE_ROUND_BRACKET() { return getToken(GraphflowParser.CLOSE_ROUND_BRACKET, 0); }
		public List<WhitespaceContext> whitespace() {
			return getRuleContexts(WhitespaceContext.class);
		}
		public WhitespaceContext whitespace(int i) {
			return getRuleContext(WhitespaceContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VertexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vertex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterVertex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitVertex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitVertex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VertexContext vertex() throws RecognitionException {
		VertexContext _localctx = new VertexContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_vertex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(OPEN_ROUND_BRACKET);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
				{
				setState(65);
				whitespace();
				}
			}

			setState(68);
			variable();
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(69);
				type();
				}
				break;
			}
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
				{
				setState(72);
				whitespace();
				}
			}

			setState(75);
			match(CLOSE_ROUND_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(GraphflowParser.COLON, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<WhitespaceContext> whitespace() {
			return getRuleContexts(WhitespaceContext.class);
		}
		public WhitespaceContext whitespace(int i) {
			return getRuleContext(WhitespaceContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
				{
				setState(77);
				whitespace();
				}
			}

			setState(80);
			match(COLON);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) {
				{
				setState(81);
				whitespace();
				}
			}

			setState(84);
			variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode OPEN_SQUARE_BRACKET() { return getToken(GraphflowParser.OPEN_SQUARE_BRACKET, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode CLOSE_SQUARE_BRACKET() { return getToken(GraphflowParser.CLOSE_SQUARE_BRACKET, 0); }
		public TerminalNode DASH() { return getToken(GraphflowParser.DASH, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(OPEN_SQUARE_BRACKET);
			setState(87);
			variable();
			setState(88);
			match(CLOSE_SQUARE_BRACKET);
			setState(89);
			match(DASH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public List<TerminalNode> Digits() { return getTokens(GraphflowParser.Digits); }
		public TerminalNode Digits(int i) {
			return getToken(GraphflowParser.Digits, i);
		}
		public List<TerminalNode> Characters() { return getTokens(GraphflowParser.Characters); }
		public TerminalNode Characters(int i) {
			return getToken(GraphflowParser.Characters, i);
		}
		public List<TerminalNode> UNDERSCORE() { return getTokens(GraphflowParser.UNDERSCORE); }
		public TerminalNode UNDERSCORE(int i) {
			return getToken(GraphflowParser.UNDERSCORE, i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNDERSCORE) | (1L << Digits) | (1L << Characters))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNDERSCORE) | (1L << Digits) | (1L << Characters))) != 0)) {
				{
				{
				setState(92);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNDERSCORE) | (1L << Digits) | (1L << Characters))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhitespaceContext extends ParserRuleContext {
		public List<TerminalNode> SPACE() { return getTokens(GraphflowParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(GraphflowParser.SPACE, i);
		}
		public List<TerminalNode> TAB() { return getTokens(GraphflowParser.TAB); }
		public TerminalNode TAB(int i) {
			return getToken(GraphflowParser.TAB, i);
		}
		public List<TerminalNode> CARRIAGE_RETURN() { return getTokens(GraphflowParser.CARRIAGE_RETURN); }
		public TerminalNode CARRIAGE_RETURN(int i) {
			return getToken(GraphflowParser.CARRIAGE_RETURN, i);
		}
		public List<TerminalNode> LINE_FEED() { return getTokens(GraphflowParser.LINE_FEED); }
		public TerminalNode LINE_FEED(int i) {
			return getToken(GraphflowParser.LINE_FEED, i);
		}
		public List<TerminalNode> FORM_FEED() { return getTokens(GraphflowParser.FORM_FEED); }
		public TerminalNode FORM_FEED(int i) {
			return getToken(GraphflowParser.FORM_FEED, i);
		}
		public List<TerminalNode> Comment() { return getTokens(GraphflowParser.Comment); }
		public TerminalNode Comment(int i) {
			return getToken(GraphflowParser.Comment, i);
		}
		public WhitespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whitespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).enterWhitespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphflowListener ) ((GraphflowListener)listener).exitWhitespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphflowVisitor ) return ((GraphflowVisitor<? extends T>)visitor).visitWhitespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhitespaceContext whitespace() throws RecognitionException {
		WhitespaceContext _localctx = new WhitespaceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_whitespace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(98);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Comment) | (1L << SPACE) | (1L << TAB) | (1L << LINE_FEED) | (1L << FORM_FEED) | (1L << CARRIAGE_RETURN))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35j\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\5\2\24\n\2\3\2"+
		"\3\2\5\2\30\n\2\3\2\3\2\3\2\3\2\5\2\36\n\2\5\2 \n\2\3\2\3\2\5\2$\n\2\5"+
		"\2&\n\2\3\2\3\2\3\3\3\3\5\3,\n\3\3\3\3\3\5\3\60\n\3\3\3\7\3\63\n\3\f\3"+
		"\16\3\66\13\3\3\4\3\4\5\4:\n\4\3\4\3\4\5\4>\n\4\3\4\3\4\3\4\3\5\3\5\5"+
		"\5E\n\5\3\5\3\5\5\5I\n\5\3\5\5\5L\n\5\3\5\3\5\3\6\5\6Q\n\6\3\6\3\6\5\6"+
		"U\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\7\b`\n\b\f\b\16\bc\13\b\3\t"+
		"\6\tf\n\t\r\t\16\tg\3\t\2\2\n\2\4\6\b\n\f\16\20\2\4\4\2\17\17\34\35\4"+
		"\2\6\n\r\r\2s\2\23\3\2\2\2\4)\3\2\2\2\6\67\3\2\2\2\bB\3\2\2\2\nP\3\2\2"+
		"\2\fX\3\2\2\2\16]\3\2\2\2\20e\3\2\2\2\22\24\5\20\t\2\23\22\3\2\2\2\23"+
		"\24\3\2\2\2\24\25\3\2\2\2\25\27\5\4\3\2\26\30\5\20\t\2\27\26\3\2\2\2\27"+
		"\30\3\2\2\2\30\37\3\2\2\2\31\32\7\3\2\2\32\33\5\20\t\2\33\35\7\34\2\2"+
		"\34\36\5\20\t\2\35\34\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37\31\3\2\2\2"+
		"\37 \3\2\2\2 %\3\2\2\2!#\7\22\2\2\"$\5\20\t\2#\"\3\2\2\2#$\3\2\2\2$&\3"+
		"\2\2\2%!\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\7\2\2\3(\3\3\2\2\2)\64\5\6\4"+
		"\2*,\5\20\t\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2-/\7\23\2\2.\60\5\20\t\2/."+
		"\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\63\5\6\4\2\62+\3\2\2\2\63\66\3\2"+
		"\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\5\3\2\2\2\66\64\3\2\2\2\679\5\b\5"+
		"\28:\5\20\t\298\3\2\2\29:\3\2\2\2:;\3\2\2\2;=\7\16\2\2<>\5\f\7\2=<\3\2"+
		"\2\2=>\3\2\2\2>?\3\2\2\2?@\7\33\2\2@A\5\b\5\2A\7\3\2\2\2BD\7\27\2\2CE"+
		"\5\20\t\2DC\3\2\2\2DE\3\2\2\2EF\3\2\2\2FH\5\16\b\2GI\5\n\6\2HG\3\2\2\2"+
		"HI\3\2\2\2IK\3\2\2\2JL\5\20\t\2KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\30\2"+
		"\2N\t\3\2\2\2OQ\5\20\t\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RT\7\24\2\2SU\5"+
		"\20\t\2TS\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\5\16\b\2W\13\3\2\2\2XY\7\31\2"+
		"\2YZ\5\16\b\2Z[\7\32\2\2[\\\7\16\2\2\\\r\3\2\2\2]a\t\2\2\2^`\t\2\2\2_"+
		"^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\17\3\2\2\2ca\3\2\2\2df\t\3\2"+
		"\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\21\3\2\2\2\24\23\27\35\37"+
		"#%+/\649=DHKPTag";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}