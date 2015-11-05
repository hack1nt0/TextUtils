// Generated from /home/dy/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/Regex.g4 by ANTLR 4.5
package com.xiaomi.nlp.util.regular;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, INTEGER_STR=19, INTEGER=20, ESC=21, GROUP_REF=22, RE_ESC_CHAR=23, 
		WILDCARD=24;
	public static final int
		RULE_wildcard = 0, RULE_s = 1, RULE_s_tag = 2, RULE_s_tag_name = 3, RULE_re_choice = 4, 
		RULE_re_seq_elem = 5, RULE_re_seq = 6, RULE_re_factor = 7, RULE_re_char = 8, 
		RULE_re_class = 9, RULE_re_class_char = 10, RULE_re_group = 11, RULE_re_quant = 12, 
		RULE_int_seq = 13, RULE_esc = 14, RULE_group_ref = 15, RULE_re_esc_char = 16;
	public static final String[] ruleNames = {
		"wildcard", "s", "s_tag", "s_tag_name", "re_choice", "re_seq_elem", "re_seq", 
		"re_factor", "re_char", "re_class", "re_class_char", "re_group", "re_quant", 
		"int_seq", "esc", "group_ref", "re_esc_char"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'^'", "'?'", "'<'", "'>'", "'*'", "'+'", "'}'", "'['", 
		"']'", "','", "'time0'", "'money0'", "'card0'", "'|'", "'-'", "'{'", null, 
		null, "'\\'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "INTEGER_STR", "INTEGER", "ESC", 
		"GROUP_REF", "RE_ESC_CHAR", "WILDCARD"
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
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Regex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RegexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class WildcardContext extends ParserRuleContext {
		public S_tag_nameContext s_tag_name() {
			return getRuleContext(S_tag_nameContext.class,0);
		}
		public TerminalNode WILDCARD() { return getToken(RegexParser.WILDCARD, 0); }
		public WildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitWildcard(this);
		}
	}

	public final WildcardContext wildcard() throws RecognitionException {
		WildcardContext _localctx = new WildcardContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_wildcard);
		try {
			setState(49);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(34); 
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(35); 
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(36); 
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(37); 
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(38); 
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(39); 
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(40); 
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(41); 
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(42); 
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(43); 
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(44); 
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(45); 
				match(T__10);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(46); 
				match(T__11);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(47); 
				s_tag_name();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(48); 
				match(WILDCARD);
				}
				break;
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

	public static class SContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RegexParser.EOF, 0); }
		public List<S_tagContext> s_tag() {
			return getRuleContexts(S_tagContext.class);
		}
		public S_tagContext s_tag(int i) {
			return getRuleContext(S_tagContext.class,i);
		}
		public List<Re_choiceContext> re_choice() {
			return getRuleContexts(Re_choiceContext.class);
		}
		public Re_choiceContext re_choice(int i) {
			return getRuleContext(Re_choiceContext.class,i);
		}
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_s);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(53);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(51); 
					s_tag();
					}
					break;
				case 2:
					{
					setState(52); 
					re_choice();
					}
					break;
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(57); 
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

	public static class S_tagContext extends ParserRuleContext {
		public S_tag_nameContext s_tag_name() {
			return getRuleContext(S_tag_nameContext.class,0);
		}
		public Re_choiceContext re_choice() {
			return getRuleContext(Re_choiceContext.class,0);
		}
		public S_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_tag(this);
		}
	}

	public final S_tagContext s_tag() throws RecognitionException {
		S_tagContext _localctx = new S_tagContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_s_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); 
			match(T__0);
			setState(60); 
			match(T__3);
			setState(61); 
			match(T__4);
			setState(62); 
			s_tag_name();
			setState(63); 
			match(T__5);
			setState(64); 
			re_choice();
			setState(65); 
			match(T__1);
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

	public static class S_tag_nameContext extends ParserRuleContext {
		public S_tag_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_tag_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_tag_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_tag_name(this);
		}
	}

	public final S_tag_nameContext s_tag_name() throws RecognitionException {
		S_tag_nameContext _localctx = new S_tag_nameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_s_tag_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class Re_choiceContext extends ParserRuleContext {
		public List<Re_seqContext> re_seq() {
			return getRuleContexts(Re_seqContext.class);
		}
		public Re_seqContext re_seq(int i) {
			return getRuleContext(Re_seqContext.class,i);
		}
		public Re_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_choice(this);
		}
	}

	public final Re_choiceContext re_choice() throws RecognitionException {
		Re_choiceContext _localctx = new Re_choiceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_re_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			re_seq();
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(70); 
				match(T__15);
				setState(71); 
				re_seq();
				}
				}
				setState(76);
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

	public static class Re_seq_elemContext extends ParserRuleContext {
		public Re_factorContext re_factor() {
			return getRuleContext(Re_factorContext.class,0);
		}
		public Re_quantContext re_quant() {
			return getRuleContext(Re_quantContext.class,0);
		}
		public Re_seq_elemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_seq_elem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_seq_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_seq_elem(this);
		}
	}

	public final Re_seq_elemContext re_seq_elem() throws RecognitionException {
		Re_seq_elemContext _localctx = new Re_seq_elemContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_re_seq_elem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); 
			re_factor();
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(78); 
				re_quant();
				}
				break;
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

	public static class Re_seqContext extends ParserRuleContext {
		public List<Re_seq_elemContext> re_seq_elem() {
			return getRuleContexts(Re_seq_elemContext.class);
		}
		public Re_seq_elemContext re_seq_elem(int i) {
			return getRuleContext(Re_seq_elemContext.class,i);
		}
		public Re_seqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_seq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_seq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_seq(this);
		}
	}

	public final Re_seqContext re_seq() throws RecognitionException {
		Re_seqContext _localctx = new Re_seqContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_re_seq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(81); 
					re_seq_elem();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(84); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class Re_factorContext extends ParserRuleContext {
		public Re_charContext re_char() {
			return getRuleContext(Re_charContext.class,0);
		}
		public Re_classContext re_class() {
			return getRuleContext(Re_classContext.class,0);
		}
		public Re_groupContext re_group() {
			return getRuleContext(Re_groupContext.class,0);
		}
		public WildcardContext wildcard() {
			return getRuleContext(WildcardContext.class,0);
		}
		public Re_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_factor(this);
		}
	}

	public final Re_factorContext re_factor() throws RecognitionException {
		Re_factorContext _localctx = new Re_factorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_re_factor);
		try {
			setState(90);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86); 
				re_char();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87); 
				re_class();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(88); 
				re_group();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(89); 
				wildcard();
				}
				break;
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

	public static class Re_charContext extends ParserRuleContext {
		public Re_esc_charContext re_esc_char() {
			return getRuleContext(Re_esc_charContext.class,0);
		}
		public Int_seqContext int_seq() {
			return getRuleContext(Int_seqContext.class,0);
		}
		public Group_refContext group_ref() {
			return getRuleContext(Group_refContext.class,0);
		}
		public Re_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_char(this);
		}
	}

	public final Re_charContext re_char() throws RecognitionException {
		Re_charContext _localctx = new Re_charContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_re_char);
		try {
			setState(95);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(92); 
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(93); 
				int_seq();
				}
				break;
			case GROUP_REF:
				enterOuterAlt(_localctx, 3);
				{
				setState(94); 
				group_ref();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Re_classContext extends ParserRuleContext {
		public List<Re_class_charContext> re_class_char() {
			return getRuleContexts(Re_class_charContext.class);
		}
		public Re_class_charContext re_class_char(int i) {
			return getRuleContext(Re_class_charContext.class,i);
		}
		public Re_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_class(this);
		}
	}

	public final Re_classContext re_class() throws RecognitionException {
		Re_classContext _localctx = new Re_classContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_re_class);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(97); 
			match(T__9);
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(98); 
				match(T__2);
				}
				break;
			}
			setState(106); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(106);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						setState(101); 
						re_class_char();
						setState(102); 
						match(T__16);
						setState(103); 
						re_class_char();
						}
						break;
					case 2:
						{
						setState(105); 
						re_class_char();
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(108); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(110); 
			match(T__10);
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

	public static class Re_class_charContext extends ParserRuleContext {
		public Re_esc_charContext re_esc_char() {
			return getRuleContext(Re_esc_charContext.class,0);
		}
		public Int_seqContext int_seq() {
			return getRuleContext(Int_seqContext.class,0);
		}
		public WildcardContext wildcard() {
			return getRuleContext(WildcardContext.class,0);
		}
		public Re_class_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_class_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_class_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_class_char(this);
		}
	}

	public final Re_class_charContext re_class_char() throws RecognitionException {
		Re_class_charContext _localctx = new Re_class_charContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_re_class_char);
		try {
			setState(115);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(112); 
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(113); 
				int_seq();
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case WILDCARD:
				enterOuterAlt(_localctx, 3);
				{
				setState(114); 
				wildcard();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Re_groupContext extends ParserRuleContext {
		public Re_choiceContext re_choice() {
			return getRuleContext(Re_choiceContext.class,0);
		}
		public Re_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_group(this);
		}
	}

	public final Re_groupContext re_group() throws RecognitionException {
		Re_groupContext _localctx = new Re_groupContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_re_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); 
			match(T__0);
			setState(120);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(118); 
				re_choice();
				}
				break;
			case 2:
				{
				}
				break;
			}
			setState(122); 
			match(T__1);
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

	public static class Re_quantContext extends ParserRuleContext {
		public List<TerminalNode> INTEGER_STR() { return getTokens(RegexParser.INTEGER_STR); }
		public TerminalNode INTEGER_STR(int i) {
			return getToken(RegexParser.INTEGER_STR, i);
		}
		public Re_quantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_quant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_quant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_quant(this);
		}
	}

	public final Re_quantContext re_quant() throws RecognitionException {
		Re_quantContext _localctx = new Re_quantContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_re_quant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(124); 
				match(T__3);
				}
				break;
			case T__6:
				{
				setState(125); 
				match(T__6);
				}
				break;
			case T__7:
				{
				setState(126); 
				match(T__7);
				}
				break;
			case T__17:
				{
				setState(127); 
				match(T__17);
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==INTEGER_STR) {
					{
					{
					setState(128); 
					match(INTEGER_STR);
					}
					}
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143);
				_la = _input.LA(1);
				if (_la==T__11) {
					{
					setState(134); 
					match(T__11);
					setState(141);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						setState(138);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==INTEGER_STR) {
							{
							{
							setState(135); 
							match(INTEGER_STR);
							}
							}
							setState(140);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					}
					}
				}

				setState(145); 
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(149);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(148); 
				match(T__3);
				}
				break;
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

	public static class Int_seqContext extends ParserRuleContext {
		public TerminalNode INTEGER_STR() { return getToken(RegexParser.INTEGER_STR, 0); }
		public Int_seqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_seq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterInt_seq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitInt_seq(this);
		}
	}

	public final Int_seqContext int_seq() throws RecognitionException {
		Int_seqContext _localctx = new Int_seqContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_int_seq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); 
			match(INTEGER_STR);
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

	public static class EscContext extends ParserRuleContext {
		public TerminalNode ESC() { return getToken(RegexParser.ESC, 0); }
		public EscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_esc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterEsc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitEsc(this);
		}
	}

	public final EscContext esc() throws RecognitionException {
		EscContext _localctx = new EscContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_esc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153); 
			match(ESC);
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

	public static class Group_refContext extends ParserRuleContext {
		public TerminalNode GROUP_REF() { return getToken(RegexParser.GROUP_REF, 0); }
		public Group_refContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group_ref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterGroup_ref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitGroup_ref(this);
		}
	}

	public final Group_refContext group_ref() throws RecognitionException {
		Group_refContext _localctx = new Group_refContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_group_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); 
			match(GROUP_REF);
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

	public static class Re_esc_charContext extends ParserRuleContext {
		public TerminalNode RE_ESC_CHAR() { return getToken(RegexParser.RE_ESC_CHAR, 0); }
		public Re_esc_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_esc_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_esc_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_esc_char(this);
		}
	}

	public final Re_esc_charContext re_esc_char() throws RecognitionException {
		Re_esc_charContext _localctx = new Re_esc_charContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_re_esc_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); 
			match(RE_ESC_CHAR);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\32\u00a2\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\64\n"+
		"\2\3\3\3\3\6\38\n\3\r\3\16\39\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\6\7\6K\n\6\f\6\16\6N\13\6\3\7\3\7\5\7R\n\7\3\b\6\b"+
		"U\n\b\r\b\16\bV\3\t\3\t\3\t\3\t\5\t]\n\t\3\n\3\n\3\n\5\nb\n\n\3\13\3\13"+
		"\5\13f\n\13\3\13\3\13\3\13\3\13\3\13\6\13m\n\13\r\13\16\13n\3\13\3\13"+
		"\3\f\3\f\3\f\5\fv\n\f\3\r\3\r\3\r\5\r{\n\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\7\16\u0084\n\16\f\16\16\16\u0087\13\16\3\16\3\16\7\16\u008b\n\16"+
		"\f\16\16\16\u008e\13\16\5\16\u0090\n\16\5\16\u0092\n\16\3\16\5\16\u0095"+
		"\n\16\3\16\5\16\u0098\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\3V\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\3\3\2\17\21\u00b6"+
		"\2\63\3\2\2\2\4\67\3\2\2\2\6=\3\2\2\2\bE\3\2\2\2\nG\3\2\2\2\fO\3\2\2\2"+
		"\16T\3\2\2\2\20\\\3\2\2\2\22a\3\2\2\2\24c\3\2\2\2\26u\3\2\2\2\30w\3\2"+
		"\2\2\32\u0094\3\2\2\2\34\u0099\3\2\2\2\36\u009b\3\2\2\2 \u009d\3\2\2\2"+
		"\"\u009f\3\2\2\2$\64\7\3\2\2%\64\7\4\2\2&\64\7\5\2\2\'\64\7\6\2\2(\64"+
		"\7\7\2\2)\64\7\b\2\2*\64\7\6\2\2+\64\7\t\2\2,\64\7\n\2\2-\64\7\13\2\2"+
		".\64\7\f\2\2/\64\7\r\2\2\60\64\7\16\2\2\61\64\5\b\5\2\62\64\7\32\2\2\63"+
		"$\3\2\2\2\63%\3\2\2\2\63&\3\2\2\2\63\'\3\2\2\2\63(\3\2\2\2\63)\3\2\2\2"+
		"\63*\3\2\2\2\63+\3\2\2\2\63,\3\2\2\2\63-\3\2\2\2\63.\3\2\2\2\63/\3\2\2"+
		"\2\63\60\3\2\2\2\63\61\3\2\2\2\63\62\3\2\2\2\64\3\3\2\2\2\658\5\6\4\2"+
		"\668\5\n\6\2\67\65\3\2\2\2\67\66\3\2\2\289\3\2\2\29\67\3\2\2\29:\3\2\2"+
		"\2:;\3\2\2\2;<\7\2\2\3<\5\3\2\2\2=>\7\3\2\2>?\7\6\2\2?@\7\7\2\2@A\5\b"+
		"\5\2AB\7\b\2\2BC\5\n\6\2CD\7\4\2\2D\7\3\2\2\2EF\t\2\2\2F\t\3\2\2\2GL\5"+
		"\16\b\2HI\7\22\2\2IK\5\16\b\2JH\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2"+
		"M\13\3\2\2\2NL\3\2\2\2OQ\5\20\t\2PR\5\32\16\2QP\3\2\2\2QR\3\2\2\2R\r\3"+
		"\2\2\2SU\5\f\7\2TS\3\2\2\2UV\3\2\2\2VW\3\2\2\2VT\3\2\2\2W\17\3\2\2\2X"+
		"]\5\22\n\2Y]\5\24\13\2Z]\5\30\r\2[]\5\2\2\2\\X\3\2\2\2\\Y\3\2\2\2\\Z\3"+
		"\2\2\2\\[\3\2\2\2]\21\3\2\2\2^b\5\"\22\2_b\5\34\17\2`b\5 \21\2a^\3\2\2"+
		"\2a_\3\2\2\2a`\3\2\2\2b\23\3\2\2\2ce\7\f\2\2df\7\5\2\2ed\3\2\2\2ef\3\2"+
		"\2\2fl\3\2\2\2gh\5\26\f\2hi\7\23\2\2ij\5\26\f\2jm\3\2\2\2km\5\26\f\2l"+
		"g\3\2\2\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2op\3\2\2\2pq\7\r\2\2"+
		"q\25\3\2\2\2rv\5\"\22\2sv\5\34\17\2tv\5\2\2\2ur\3\2\2\2us\3\2\2\2ut\3"+
		"\2\2\2v\27\3\2\2\2wz\7\3\2\2x{\5\n\6\2y{\3\2\2\2zx\3\2\2\2zy\3\2\2\2{"+
		"|\3\2\2\2|}\7\4\2\2}\31\3\2\2\2~\u0095\7\6\2\2\177\u0095\7\t\2\2\u0080"+
		"\u0095\7\n\2\2\u0081\u0085\7\24\2\2\u0082\u0084\7\25\2\2\u0083\u0082\3"+
		"\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"\u0091\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008f\7\16\2\2\u0089\u008b\7"+
		"\25\2\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u008c\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u0088\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\7\13\2\2\u0094~\3\2\2\2"+
		"\u0094\177\3\2\2\2\u0094\u0080\3\2\2\2\u0094\u0081\3\2\2\2\u0095\u0097"+
		"\3\2\2\2\u0096\u0098\7\6\2\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\33\3\2\2\2\u0099\u009a\7\25\2\2\u009a\35\3\2\2\2\u009b\u009c\7\27\2\2"+
		"\u009c\37\3\2\2\2\u009d\u009e\7\30\2\2\u009e!\3\2\2\2\u009f\u00a0\7\31"+
		"\2\2\u00a0#\3\2\2\2\25\63\679LQV\\aelnuz\u0085\u008c\u008f\u0091\u0094"+
		"\u0097";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}