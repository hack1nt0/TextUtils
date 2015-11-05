// Generated from /Users/DY/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/regex.g4 by ANTLR 4.5.1
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
public class regexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, INTEGER_STR=21, INTEGER=22, ESC=23, GROUP_REF=24, 
		RE_ESC_CHAR=25, WILDCARD=26;
	public static final int
		RULE_wildcard = 0, RULE_s = 1, RULE_test = 2, RULE_s_tag = 3, RULE_s_tag_name = 4, 
		RULE_re_choice = 5, RULE_re_seq_elem = 6, RULE_re_seq = 7, RULE_re_factor = 8, 
		RULE_re_char = 9, RULE_re_class = 10, RULE_re_class_char = 11, RULE_re_group = 12, 
		RULE_re_quant = 13, RULE_int_seq = 14, RULE_esc = 15, RULE_group_ref = 16, 
		RULE_re_esc_char = 17;
	public static final String[] ruleNames = {
		"wildcard", "s", "test", "s_tag", "s_tag_name", "re_choice", "re_seq_elem", 
		"re_seq", "re_factor", "re_char", "re_class", "re_class_char", "re_group", 
		"re_quant", "int_seq", "esc", "group_ref", "re_esc_char"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "')'", "'^'", "'?'", "'<'", "'>'", "'*'", "'+'", "'}'", "'['", "']'", 
		"','", "'cat'", "'ca'", "'('", "'time0'", "'money0'", "'card0'", "'|'", 
		"'-'", "'{'", null, null, "'\\'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "INTEGER_STR", "INTEGER", 
		"ESC", "GROUP_REF", "RE_ESC_CHAR", "WILDCARD"
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
	public String getGrammarFileName() { return "regex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public regexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class WildcardContext extends ParserRuleContext {
		public S_tag_nameContext s_tag_name() {
			return getRuleContext(S_tag_nameContext.class,0);
		}
		public TerminalNode WILDCARD() { return getToken(regexParser.WILDCARD, 0); }
		public WildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitWildcard(this);
		}
	}

	public final WildcardContext wildcard() throws RecognitionException {
		WildcardContext _localctx = new WildcardContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_wildcard);
		try {
			setState(50);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(39);
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(40);
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(41);
				match(T__2);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(42);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(43);
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(44);
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(45);
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(46);
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(47);
				match(T__10);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(48);
				s_tag_name();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(49);
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
		public TerminalNode EOF() { return getToken(regexParser.EOF, 0); }
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitS(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_s);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(54);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(52);
					s_tag();
					}
					break;
				case 2:
					{
					setState(53);
					re_choice();
					}
					break;
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(58);
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

	public static class TestContext extends ParserRuleContext {
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitTest(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_la = _input.LA(1);
			if ( !(_la==T__11 || _la==T__12) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterS_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitS_tag(this);
		}
	}

	public final S_tagContext s_tag() throws RecognitionException {
		S_tagContext _localctx = new S_tagContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_s_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__13);
			setState(63);
			match(T__2);
			setState(64);
			match(T__3);
			setState(65);
			s_tag_name();
			setState(66);
			match(T__4);
			setState(67);
			re_choice();
			setState(68);
			match(T__0);
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterS_tag_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitS_tag_name(this);
		}
	}

	public final S_tag_nameContext s_tag_name() throws RecognitionException {
		S_tag_nameContext _localctx = new S_tag_nameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_s_tag_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_choice(this);
		}
	}

	public final Re_choiceContext re_choice() throws RecognitionException {
		Re_choiceContext _localctx = new Re_choiceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_re_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			re_seq();
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(73);
				match(T__17);
				setState(74);
				re_seq();
				}
				}
				setState(79);
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_seq_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_seq_elem(this);
		}
	}

	public final Re_seq_elemContext re_seq_elem() throws RecognitionException {
		Re_seq_elemContext _localctx = new Re_seq_elemContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_re_seq_elem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			re_factor();
			setState(82);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(81);
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_seq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_seq(this);
		}
	}

	public final Re_seqContext re_seq() throws RecognitionException {
		Re_seqContext _localctx = new Re_seqContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_re_seq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(84);
					re_seq_elem();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(87); 
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_factor(this);
		}
	}

	public final Re_factorContext re_factor() throws RecognitionException {
		Re_factorContext _localctx = new Re_factorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_re_factor);
		try {
			setState(93);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				re_char();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				re_class();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				re_group();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(92);
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
		public Group_refContext group_ref() {
			return getRuleContext(Group_refContext.class,0);
		}
		public Re_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_char(this);
		}
	}

	public final Re_charContext re_char() throws RecognitionException {
		Re_charContext _localctx = new Re_charContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_re_char);
		try {
			setState(97);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				re_esc_char();
				}
				break;
			case GROUP_REF:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_class(this);
		}
	}

	public final Re_classContext re_class() throws RecognitionException {
		Re_classContext _localctx = new Re_classContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_re_class);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__8);
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(100);
				match(T__1);
				}
				break;
			}
			setState(108); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(108);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						setState(103);
						re_class_char();
						setState(104);
						match(T__18);
						setState(105);
						re_class_char();
						}
						break;
					case 2:
						{
						setState(107);
						re_class_char();
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(110); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(112);
			match(T__9);
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_class_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_class_char(this);
		}
	}

	public final Re_class_charContext re_class_char() throws RecognitionException {
		Re_class_charContext _localctx = new Re_class_charContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_re_class_char);
		try {
			setState(117);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
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
			case T__14:
			case T__15:
			case T__16:
			case WILDCARD:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
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
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_group(this);
		}
	}

	public final Re_groupContext re_group() throws RecognitionException {
		Re_groupContext _localctx = new Re_groupContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_re_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__13);
			setState(122);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(120);
				re_choice();
				}
				break;
			case 2:
				{
				}
				break;
			}
			setState(124);
			match(T__0);
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
		public List<TerminalNode> INTEGER_STR() { return getTokens(regexParser.INTEGER_STR); }
		public TerminalNode INTEGER_STR(int i) {
			return getToken(regexParser.INTEGER_STR, i);
		}
		public Re_quantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_quant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_quant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_quant(this);
		}
	}

	public final Re_quantContext re_quant() throws RecognitionException {
		Re_quantContext _localctx = new Re_quantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_re_quant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(126);
				match(T__2);
				}
				break;
			case T__5:
				{
				setState(127);
				match(T__5);
				}
				break;
			case T__6:
				{
				setState(128);
				match(T__6);
				}
				break;
			case T__19:
				{
				setState(129);
				match(T__19);
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==INTEGER_STR) {
					{
					{
					setState(130);
					match(INTEGER_STR);
					}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(136);
					match(T__10);
					setState(143);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						setState(140);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==INTEGER_STR) {
							{
							{
							setState(137);
							match(INTEGER_STR);
							}
							}
							setState(142);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					}
					}
				}

				setState(147);
				match(T__7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(151);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(150);
				match(T__2);
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
		public TerminalNode INTEGER_STR() { return getToken(regexParser.INTEGER_STR, 0); }
		public Int_seqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_seq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterInt_seq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitInt_seq(this);
		}
	}

	public final Int_seqContext int_seq() throws RecognitionException {
		Int_seqContext _localctx = new Int_seqContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_int_seq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
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
		public TerminalNode ESC() { return getToken(regexParser.ESC, 0); }
		public EscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_esc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterEsc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitEsc(this);
		}
	}

	public final EscContext esc() throws RecognitionException {
		EscContext _localctx = new EscContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_esc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
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
		public TerminalNode GROUP_REF() { return getToken(regexParser.GROUP_REF, 0); }
		public Group_refContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group_ref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterGroup_ref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitGroup_ref(this);
		}
	}

	public final Group_refContext group_ref() throws RecognitionException {
		Group_refContext _localctx = new Group_refContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_group_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
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
		public TerminalNode RE_ESC_CHAR() { return getToken(regexParser.RE_ESC_CHAR, 0); }
		public Re_esc_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_esc_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).enterRe_esc_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexListener ) ((regexListener)listener).exitRe_esc_char(this);
		}
	}

	public final Re_esc_charContext re_esc_char() throws RecognitionException {
		Re_esc_charContext _localctx = new Re_esc_charContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_re_esc_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\34\u00a4\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2"+
		"\65\n\2\3\3\3\3\6\39\n\3\r\3\16\3:\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\7\7N\n\7\f\7\16\7Q\13\7\3\b\3\b\5\bU"+
		"\n\b\3\t\6\tX\n\t\r\t\16\tY\3\n\3\n\3\n\3\n\5\n`\n\n\3\13\3\13\5\13d\n"+
		"\13\3\f\3\f\5\fh\n\f\3\f\3\f\3\f\3\f\3\f\6\fo\n\f\r\f\16\fp\3\f\3\f\3"+
		"\r\3\r\3\r\5\rx\n\r\3\16\3\16\3\16\5\16}\n\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\7\17\u0086\n\17\f\17\16\17\u0089\13\17\3\17\3\17\7\17\u008d"+
		"\n\17\f\17\16\17\u0090\13\17\5\17\u0092\n\17\5\17\u0094\n\17\3\17\5\17"+
		"\u0097\n\17\3\17\5\17\u009a\n\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\23\3Y\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\4\3\2\16"+
		"\17\3\2\21\23\u00b5\2\64\3\2\2\2\48\3\2\2\2\6>\3\2\2\2\b@\3\2\2\2\nH\3"+
		"\2\2\2\fJ\3\2\2\2\16R\3\2\2\2\20W\3\2\2\2\22_\3\2\2\2\24c\3\2\2\2\26e"+
		"\3\2\2\2\30w\3\2\2\2\32y\3\2\2\2\34\u0096\3\2\2\2\36\u009b\3\2\2\2 \u009d"+
		"\3\2\2\2\"\u009f\3\2\2\2$\u00a1\3\2\2\2&\65\7\3\2\2\'\65\7\4\2\2(\65\7"+
		"\5\2\2)\65\7\6\2\2*\65\7\7\2\2+\65\7\5\2\2,\65\7\b\2\2-\65\7\t\2\2.\65"+
		"\7\n\2\2/\65\7\13\2\2\60\65\7\f\2\2\61\65\7\r\2\2\62\65\5\n\6\2\63\65"+
		"\7\34\2\2\64&\3\2\2\2\64\'\3\2\2\2\64(\3\2\2\2\64)\3\2\2\2\64*\3\2\2\2"+
		"\64+\3\2\2\2\64,\3\2\2\2\64-\3\2\2\2\64.\3\2\2\2\64/\3\2\2\2\64\60\3\2"+
		"\2\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63\3\2\2\2\65\3\3\2\2\2\669\5\b\5"+
		"\2\679\5\f\7\28\66\3\2\2\28\67\3\2\2\29:\3\2\2\2:8\3\2\2\2:;\3\2\2\2;"+
		"<\3\2\2\2<=\7\2\2\3=\5\3\2\2\2>?\t\2\2\2?\7\3\2\2\2@A\7\20\2\2AB\7\5\2"+
		"\2BC\7\6\2\2CD\5\n\6\2DE\7\7\2\2EF\5\f\7\2FG\7\3\2\2G\t\3\2\2\2HI\t\3"+
		"\2\2I\13\3\2\2\2JO\5\20\t\2KL\7\24\2\2LN\5\20\t\2MK\3\2\2\2NQ\3\2\2\2"+
		"OM\3\2\2\2OP\3\2\2\2P\r\3\2\2\2QO\3\2\2\2RT\5\22\n\2SU\5\34\17\2TS\3\2"+
		"\2\2TU\3\2\2\2U\17\3\2\2\2VX\5\16\b\2WV\3\2\2\2XY\3\2\2\2YZ\3\2\2\2YW"+
		"\3\2\2\2Z\21\3\2\2\2[`\5\24\13\2\\`\5\26\f\2]`\5\32\16\2^`\5\2\2\2_[\3"+
		"\2\2\2_\\\3\2\2\2_]\3\2\2\2_^\3\2\2\2`\23\3\2\2\2ad\5$\23\2bd\5\"\22\2"+
		"ca\3\2\2\2cb\3\2\2\2d\25\3\2\2\2eg\7\13\2\2fh\7\4\2\2gf\3\2\2\2gh\3\2"+
		"\2\2hn\3\2\2\2ij\5\30\r\2jk\7\25\2\2kl\5\30\r\2lo\3\2\2\2mo\5\30\r\2n"+
		"i\3\2\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\7\f\2\2"+
		"s\27\3\2\2\2tx\5$\23\2ux\5\36\20\2vx\5\2\2\2wt\3\2\2\2wu\3\2\2\2wv\3\2"+
		"\2\2x\31\3\2\2\2y|\7\20\2\2z}\5\f\7\2{}\3\2\2\2|z\3\2\2\2|{\3\2\2\2}~"+
		"\3\2\2\2~\177\7\3\2\2\177\33\3\2\2\2\u0080\u0097\7\5\2\2\u0081\u0097\7"+
		"\b\2\2\u0082\u0097\7\t\2\2\u0083\u0087\7\26\2\2\u0084\u0086\7\27\2\2\u0085"+
		"\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0093\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u0091\7\r\2\2\u008b"+
		"\u008d\7\27\2\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3"+
		"\2\2\2\u008e\u008f\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0091"+
		"\u008e\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u008a\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\7\n\2\2\u0096"+
		"\u0080\3\2\2\2\u0096\u0081\3\2\2\2\u0096\u0082\3\2\2\2\u0096\u0083\3\2"+
		"\2\2\u0097\u0099\3\2\2\2\u0098\u009a\7\5\2\2\u0099\u0098\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\35\3\2\2\2\u009b\u009c\7\27\2\2\u009c\37\3\2\2\2"+
		"\u009d\u009e\7\31\2\2\u009e!\3\2\2\2\u009f\u00a0\7\32\2\2\u00a0#\3\2\2"+
		"\2\u00a1\u00a2\7\33\2\2\u00a2%\3\2\2\2\25\648:OTY_cgnpw|\u0087\u008e\u0091"+
		"\u0093\u0096\u0099";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}