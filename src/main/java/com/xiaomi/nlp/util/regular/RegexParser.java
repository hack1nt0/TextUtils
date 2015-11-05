// Generated from /Users/DY/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/Regex.g4 by ANTLR 4.5.1
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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, INTEGER_STR=19, INTEGER=20, ESC=21, GROUP_REF=22, RE_ESC_CHAR=23, 
		WILDCARD=24;
	public static final int
		RULE_wildcard = 0, RULE_wildcard_no_lb = 1, RULE_wildcard_no_mb_to = 2, 
		RULE_s = 3, RULE_s_tag = 4, RULE_s_tag_name = 5, RULE_re_choice = 6, RULE_re_or = 7, 
		RULE_re_seq = 8, RULE_re_seq_elem = 9, RULE_re_factor = 10, RULE_re_char = 11, 
		RULE_re_class = 12, RULE_re_class_char = 13, RULE_re_group = 14, RULE_re_choice_no_lb = 15, 
		RULE_re_seq_no_lb = 16, RULE_re_seq_elem_no_lb = 17, RULE_re_factor_no_lb = 18, 
		RULE_re_quant = 19, RULE_int_seq = 20, RULE_esc = 21, RULE_group_ref = 22, 
		RULE_re_esc_char = 23;
	public static final String[] ruleNames = {
		"wildcard", "wildcard_no_lb", "wildcard_no_mb_to", "s", "s_tag", "s_tag_name", 
		"re_choice", "re_or", "re_seq", "re_seq_elem", "re_factor", "re_char", 
		"re_class", "re_class_char", "re_group", "re_choice_no_lb", "re_seq_no_lb", 
		"re_seq_elem_no_lb", "re_factor_no_lb", "re_quant", "int_seq", "esc", 
		"group_ref", "re_esc_char"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'^'", "'?'", "'<'", "'>'", "'*'", "'+'", "'{'", "'}'", 
		"'['", "']'", "','", "'|'", "'-'", "'time0'", "'money0'", "'card0'", null, 
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
			setState(66);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(53);
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(54);
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(55);
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(56);
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(57);
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(58);
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(59);
				match(T__10);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(60);
				match(T__11);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(61);
				match(T__12);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(62);
				match(T__13);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(63);
				match(T__14);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(64);
				s_tag_name();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(65);
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

	public static class Wildcard_no_lbContext extends ParserRuleContext {
		public S_tag_nameContext s_tag_name() {
			return getRuleContext(S_tag_nameContext.class,0);
		}
		public TerminalNode WILDCARD() { return getToken(RegexParser.WILDCARD, 0); }
		public Wildcard_no_lbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard_no_lb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterWildcard_no_lb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitWildcard_no_lb(this);
		}
	}

	public final Wildcard_no_lbContext wildcard_no_lb() throws RecognitionException {
		Wildcard_no_lbContext _localctx = new Wildcard_no_lbContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_wildcard_no_lb);
		try {
			setState(84);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				match(T__4);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				match(T__5);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(72);
				match(T__3);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
				match(T__6);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(74);
				match(T__7);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(75);
				match(T__8);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(76);
				match(T__9);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(77);
				match(T__10);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(78);
				match(T__11);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(79);
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(80);
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(81);
				match(T__14);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(82);
				s_tag_name();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(83);
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

	public static class Wildcard_no_mb_toContext extends ParserRuleContext {
		public S_tag_nameContext s_tag_name() {
			return getRuleContext(S_tag_nameContext.class,0);
		}
		public TerminalNode WILDCARD() { return getToken(RegexParser.WILDCARD, 0); }
		public Wildcard_no_mb_toContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard_no_mb_to; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterWildcard_no_mb_to(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitWildcard_no_mb_to(this);
		}
	}

	public final Wildcard_no_mb_toContext wildcard_no_mb_to() throws RecognitionException {
		Wildcard_no_mb_toContext _localctx = new Wildcard_no_mb_toContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_wildcard_no_mb_to);
		try {
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(88);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(89);
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(90);
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(91);
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(92);
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(93);
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(94);
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(95);
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(96);
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(97);
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(98);
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(99);
				s_tag_name();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(100);
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
		enterRule(_localctx, 6, RULE_s);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(105);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(103);
					s_tag();
					}
					break;
				case 2:
					{
					setState(104);
					re_choice();
					}
					break;
				}
				}
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(109);
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
		enterRule(_localctx, 8, RULE_s_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__0);
			setState(112);
			match(T__3);
			setState(113);
			match(T__4);
			setState(114);
			s_tag_name();
			setState(115);
			match(T__5);
			setState(116);
			re_choice();
			setState(117);
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
		enterRule(_localctx, 10, RULE_s_tag_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17))) != 0)) ) {
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
		public List<Re_orContext> re_or() {
			return getRuleContexts(Re_orContext.class);
		}
		public Re_orContext re_or(int i) {
			return getRuleContext(Re_orContext.class,i);
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
		enterRule(_localctx, 12, RULE_re_choice);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			re_seq();
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(122);
					re_or();
					setState(123);
					re_seq();
					}
					} 
				}
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class Re_orContext extends ParserRuleContext {
		public Re_orContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_or(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_or(this);
		}
	}

	public final Re_orContext re_or() throws RecognitionException {
		Re_orContext _localctx = new Re_orContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_re_or);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__13);
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
		enterRule(_localctx, 16, RULE_re_seq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(132);
					re_seq_elem();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(135); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		enterRule(_localctx, 18, RULE_re_seq_elem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			re_factor();
			setState(139);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(138);
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

	public static class Re_factorContext extends ParserRuleContext {
		public Re_classContext re_class() {
			return getRuleContext(Re_classContext.class,0);
		}
		public Re_groupContext re_group() {
			return getRuleContext(Re_groupContext.class,0);
		}
		public Re_charContext re_char() {
			return getRuleContext(Re_charContext.class,0);
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
		enterRule(_localctx, 20, RULE_re_factor);
		try {
			setState(145);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
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
		enterRule(_localctx, 22, RULE_re_char);
		try {
			setState(150);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				int_seq();
				}
				break;
			case GROUP_REF:
				enterOuterAlt(_localctx, 3);
				{
				setState(149);
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
		enterRule(_localctx, 24, RULE_re_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__10);
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(153);
				match(T__2);
				}
				break;
			}
			setState(161); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(161);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(156);
					re_class_char();
					setState(157);
					match(T__14);
					setState(158);
					re_class_char();
					}
					break;
				case 2:
					{
					setState(160);
					re_class_char();
					}
					break;
				}
				}
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << INTEGER_STR) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(165);
			match(T__11);
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
		public Wildcard_no_mb_toContext wildcard_no_mb_to() {
			return getRuleContext(Wildcard_no_mb_toContext.class,0);
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
		enterRule(_localctx, 26, RULE_re_class_char);
		try {
			setState(170);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
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
			case T__12:
			case T__13:
			case T__15:
			case T__16:
			case T__17:
			case WILDCARD:
				enterOuterAlt(_localctx, 3);
				{
				setState(169);
				wildcard_no_mb_to();
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
		public Re_choice_no_lbContext re_choice_no_lb() {
			return getRuleContext(Re_choice_no_lbContext.class,0);
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
		enterRule(_localctx, 28, RULE_re_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(T__0);
			setState(175);
			switch (_input.LA(1)) {
			case T__0:
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
			case T__15:
			case T__16:
			case T__17:
			case INTEGER_STR:
			case GROUP_REF:
			case RE_ESC_CHAR:
			case WILDCARD:
				{
				setState(173);
				re_choice_no_lb();
				}
				break;
			case T__1:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(177);
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

	public static class Re_choice_no_lbContext extends ParserRuleContext {
		public List<Re_seq_no_lbContext> re_seq_no_lb() {
			return getRuleContexts(Re_seq_no_lbContext.class);
		}
		public Re_seq_no_lbContext re_seq_no_lb(int i) {
			return getRuleContext(Re_seq_no_lbContext.class,i);
		}
		public List<Re_orContext> re_or() {
			return getRuleContexts(Re_orContext.class);
		}
		public Re_orContext re_or(int i) {
			return getRuleContext(Re_orContext.class,i);
		}
		public Re_choice_no_lbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_choice_no_lb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_choice_no_lb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_choice_no_lb(this);
		}
	}

	public final Re_choice_no_lbContext re_choice_no_lb() throws RecognitionException {
		Re_choice_no_lbContext _localctx = new Re_choice_no_lbContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_re_choice_no_lb);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			re_seq_no_lb();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(180);
				re_or();
				setState(181);
				re_seq_no_lb();
				}
				}
				setState(187);
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

	public static class Re_seq_no_lbContext extends ParserRuleContext {
		public List<Re_seq_elem_no_lbContext> re_seq_elem_no_lb() {
			return getRuleContexts(Re_seq_elem_no_lbContext.class);
		}
		public Re_seq_elem_no_lbContext re_seq_elem_no_lb(int i) {
			return getRuleContext(Re_seq_elem_no_lbContext.class,i);
		}
		public Re_seq_no_lbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_seq_no_lb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_seq_no_lb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_seq_no_lb(this);
		}
	}

	public final Re_seq_no_lbContext re_seq_no_lb() throws RecognitionException {
		Re_seq_no_lbContext _localctx = new Re_seq_no_lbContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_re_seq_no_lb);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(188);
					re_seq_elem_no_lb();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(191); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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

	public static class Re_seq_elem_no_lbContext extends ParserRuleContext {
		public Re_factor_no_lbContext re_factor_no_lb() {
			return getRuleContext(Re_factor_no_lbContext.class,0);
		}
		public Re_quantContext re_quant() {
			return getRuleContext(Re_quantContext.class,0);
		}
		public Re_seq_elem_no_lbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_seq_elem_no_lb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_seq_elem_no_lb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_seq_elem_no_lb(this);
		}
	}

	public final Re_seq_elem_no_lbContext re_seq_elem_no_lb() throws RecognitionException {
		Re_seq_elem_no_lbContext _localctx = new Re_seq_elem_no_lbContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_re_seq_elem_no_lb);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			re_factor_no_lb();
			setState(195);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(194);
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

	public static class Re_factor_no_lbContext extends ParserRuleContext {
		public Re_classContext re_class() {
			return getRuleContext(Re_classContext.class,0);
		}
		public Re_groupContext re_group() {
			return getRuleContext(Re_groupContext.class,0);
		}
		public Re_charContext re_char() {
			return getRuleContext(Re_charContext.class,0);
		}
		public Wildcard_no_lbContext wildcard_no_lb() {
			return getRuleContext(Wildcard_no_lbContext.class,0);
		}
		public Re_factor_no_lbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_re_factor_no_lb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRe_factor_no_lb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRe_factor_no_lb(this);
		}
	}

	public final Re_factor_no_lbContext re_factor_no_lb() throws RecognitionException {
		Re_factor_no_lbContext _localctx = new Re_factor_no_lbContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_re_factor_no_lb);
		try {
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(197);
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(199);
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(200);
				wildcard_no_lb();
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
		enterRule(_localctx, 38, RULE_re_quant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(203);
				match(T__3);
				}
				break;
			case T__6:
				{
				setState(204);
				match(T__6);
				}
				break;
			case T__7:
				{
				setState(205);
				match(T__7);
				}
				break;
			case T__8:
				{
				setState(206);
				match(T__8);
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==INTEGER_STR) {
					{
					{
					setState(207);
					match(INTEGER_STR);
					}
					}
					setState(212);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(222);
				_la = _input.LA(1);
				if (_la==T__12) {
					{
					setState(213);
					match(T__12);
					setState(220);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						setState(217);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==INTEGER_STR) {
							{
							{
							setState(214);
							match(INTEGER_STR);
							}
							}
							setState(219);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					}
					}
				}

				setState(224);
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(228);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(227);
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
		enterRule(_localctx, 40, RULE_int_seq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
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
		enterRule(_localctx, 42, RULE_esc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
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
		enterRule(_localctx, 44, RULE_group_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
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
		enterRule(_localctx, 46, RULE_re_esc_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\32\u00f1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\5\2E\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\3W\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4h\n\4\3\5\3\5\6\5l\n\5\r\5\16\5m\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u0080\n\b\f\b\16\b\u0083\13\b"+
		"\3\t\3\t\3\n\6\n\u0088\n\n\r\n\16\n\u0089\3\13\3\13\5\13\u008e\n\13\3"+
		"\f\3\f\3\f\3\f\5\f\u0094\n\f\3\r\3\r\3\r\5\r\u0099\n\r\3\16\3\16\5\16"+
		"\u009d\n\16\3\16\3\16\3\16\3\16\3\16\6\16\u00a4\n\16\r\16\16\16\u00a5"+
		"\3\16\3\16\3\17\3\17\3\17\5\17\u00ad\n\17\3\20\3\20\3\20\5\20\u00b2\n"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u00ba\n\21\f\21\16\21\u00bd\13"+
		"\21\3\22\6\22\u00c0\n\22\r\22\16\22\u00c1\3\23\3\23\5\23\u00c6\n\23\3"+
		"\24\3\24\3\24\3\24\5\24\u00cc\n\24\3\25\3\25\3\25\3\25\3\25\7\25\u00d3"+
		"\n\25\f\25\16\25\u00d6\13\25\3\25\3\25\7\25\u00da\n\25\f\25\16\25\u00dd"+
		"\13\25\5\25\u00df\n\25\5\25\u00e1\n\25\3\25\5\25\u00e4\n\25\3\25\5\25"+
		"\u00e7\n\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\4\u0089\u00c1"+
		"\2\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\2\3\3\2\22\24"+
		"\u0124\2D\3\2\2\2\4V\3\2\2\2\6g\3\2\2\2\bk\3\2\2\2\nq\3\2\2\2\fy\3\2\2"+
		"\2\16{\3\2\2\2\20\u0084\3\2\2\2\22\u0087\3\2\2\2\24\u008b\3\2\2\2\26\u0093"+
		"\3\2\2\2\30\u0098\3\2\2\2\32\u009a\3\2\2\2\34\u00ac\3\2\2\2\36\u00ae\3"+
		"\2\2\2 \u00b5\3\2\2\2\"\u00bf\3\2\2\2$\u00c3\3\2\2\2&\u00cb\3\2\2\2(\u00e3"+
		"\3\2\2\2*\u00e8\3\2\2\2,\u00ea\3\2\2\2.\u00ec\3\2\2\2\60\u00ee\3\2\2\2"+
		"\62E\7\3\2\2\63E\7\4\2\2\64E\7\5\2\2\65E\7\6\2\2\66E\7\7\2\2\67E\7\b\2"+
		"\28E\7\6\2\29E\7\t\2\2:E\7\n\2\2;E\7\13\2\2<E\7\f\2\2=E\7\r\2\2>E\7\16"+
		"\2\2?E\7\17\2\2@E\7\20\2\2AE\7\21\2\2BE\5\f\7\2CE\7\32\2\2D\62\3\2\2\2"+
		"D\63\3\2\2\2D\64\3\2\2\2D\65\3\2\2\2D\66\3\2\2\2D\67\3\2\2\2D8\3\2\2\2"+
		"D9\3\2\2\2D:\3\2\2\2D;\3\2\2\2D<\3\2\2\2D=\3\2\2\2D>\3\2\2\2D?\3\2\2\2"+
		"D@\3\2\2\2DA\3\2\2\2DB\3\2\2\2DC\3\2\2\2E\3\3\2\2\2FW\7\5\2\2GW\7\6\2"+
		"\2HW\7\7\2\2IW\7\b\2\2JW\7\6\2\2KW\7\t\2\2LW\7\n\2\2MW\7\13\2\2NW\7\f"+
		"\2\2OW\7\r\2\2PW\7\16\2\2QW\7\17\2\2RW\7\20\2\2SW\7\21\2\2TW\5\f\7\2U"+
		"W\7\32\2\2VF\3\2\2\2VG\3\2\2\2VH\3\2\2\2VI\3\2\2\2VJ\3\2\2\2VK\3\2\2\2"+
		"VL\3\2\2\2VM\3\2\2\2VN\3\2\2\2VO\3\2\2\2VP\3\2\2\2VQ\3\2\2\2VR\3\2\2\2"+
		"VS\3\2\2\2VT\3\2\2\2VU\3\2\2\2W\5\3\2\2\2Xh\7\3\2\2Yh\7\4\2\2Zh\7\5\2"+
		"\2[h\7\6\2\2\\h\7\7\2\2]h\7\b\2\2^h\7\6\2\2_h\7\t\2\2`h\7\n\2\2ah\7\13"+
		"\2\2bh\7\f\2\2ch\7\17\2\2dh\7\20\2\2eh\5\f\7\2fh\7\32\2\2gX\3\2\2\2gY"+
		"\3\2\2\2gZ\3\2\2\2g[\3\2\2\2g\\\3\2\2\2g]\3\2\2\2g^\3\2\2\2g_\3\2\2\2"+
		"g`\3\2\2\2ga\3\2\2\2gb\3\2\2\2gc\3\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2"+
		"h\7\3\2\2\2il\5\n\6\2jl\5\16\b\2ki\3\2\2\2kj\3\2\2\2lm\3\2\2\2mk\3\2\2"+
		"\2mn\3\2\2\2no\3\2\2\2op\7\2\2\3p\t\3\2\2\2qr\7\3\2\2rs\7\6\2\2st\7\7"+
		"\2\2tu\5\f\7\2uv\7\b\2\2vw\5\16\b\2wx\7\4\2\2x\13\3\2\2\2yz\t\2\2\2z\r"+
		"\3\2\2\2{\u0081\5\22\n\2|}\5\20\t\2}~\5\22\n\2~\u0080\3\2\2\2\177|\3\2"+
		"\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\17"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7\20\2\2\u0085\21\3\2\2\2\u0086"+
		"\u0088\5\24\13\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3"+
		"\2\2\2\u0089\u0087\3\2\2\2\u008a\23\3\2\2\2\u008b\u008d\5\26\f\2\u008c"+
		"\u008e\5(\25\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\25\3\2\2"+
		"\2\u008f\u0094\5\32\16\2\u0090\u0094\5\36\20\2\u0091\u0094\5\30\r\2\u0092"+
		"\u0094\5\2\2\2\u0093\u008f\3\2\2\2\u0093\u0090\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0093\u0092\3\2\2\2\u0094\27\3\2\2\2\u0095\u0099\5\60\31\2\u0096"+
		"\u0099\5*\26\2\u0097\u0099\5.\30\2\u0098\u0095\3\2\2\2\u0098\u0096\3\2"+
		"\2\2\u0098\u0097\3\2\2\2\u0099\31\3\2\2\2\u009a\u009c\7\r\2\2\u009b\u009d"+
		"\7\5\2\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u00a3\3\2\2\2\u009e"+
		"\u009f\5\34\17\2\u009f\u00a0\7\21\2\2\u00a0\u00a1\5\34\17\2\u00a1\u00a4"+
		"\3\2\2\2\u00a2\u00a4\5\34\17\2\u00a3\u009e\3\2\2\2\u00a3\u00a2\3\2\2\2"+
		"\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7\u00a8\7\16\2\2\u00a8\33\3\2\2\2\u00a9\u00ad\5\60\31\2\u00aa"+
		"\u00ad\5*\26\2\u00ab\u00ad\5\6\4\2\u00ac\u00a9\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ab\3\2\2\2\u00ad\35\3\2\2\2\u00ae\u00b1\7\3\2\2\u00af\u00b2"+
		"\5 \21\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b0\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\u00b4\7\4\2\2\u00b4\37\3\2\2\2\u00b5\u00bb\5\"\22"+
		"\2\u00b6\u00b7\5\20\t\2\u00b7\u00b8\5\"\22\2\u00b8\u00ba\3\2\2\2\u00b9"+
		"\u00b6\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2"+
		"\2\2\u00bc!\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c0\5$\23\2\u00bf\u00be"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2"+
		"#\3\2\2\2\u00c3\u00c5\5&\24\2\u00c4\u00c6\5(\25\2\u00c5\u00c4\3\2\2\2"+
		"\u00c5\u00c6\3\2\2\2\u00c6%\3\2\2\2\u00c7\u00cc\5\32\16\2\u00c8\u00cc"+
		"\5\36\20\2\u00c9\u00cc\5\30\r\2\u00ca\u00cc\5\4\3\2\u00cb\u00c7\3\2\2"+
		"\2\u00cb\u00c8\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\'"+
		"\3\2\2\2\u00cd\u00e4\7\6\2\2\u00ce\u00e4\7\t\2\2\u00cf\u00e4\7\n\2\2\u00d0"+
		"\u00d4\7\13\2\2\u00d1\u00d3\7\25\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3"+
		"\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00e0\3\2\2\2\u00d6"+
		"\u00d4\3\2\2\2\u00d7\u00de\7\17\2\2\u00d8\u00da\7\25\2\2\u00d9\u00d8\3"+
		"\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00db\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df\u00e1\3\2\2\2\u00e0\u00d7\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2\u00e4\7\f\2\2\u00e3\u00cd\3\2\2\2\u00e3\u00ce\3\2"+
		"\2\2\u00e3\u00cf\3\2\2\2\u00e3\u00d0\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5"+
		"\u00e7\7\6\2\2\u00e6\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7)\3\2\2\2"+
		"\u00e8\u00e9\7\25\2\2\u00e9+\3\2\2\2\u00ea\u00eb\7\27\2\2\u00eb-\3\2\2"+
		"\2\u00ec\u00ed\7\30\2\2\u00ed/\3\2\2\2\u00ee\u00ef\7\31\2\2\u00ef\61\3"+
		"\2\2\2\33DVgkm\u0081\u0089\u008d\u0093\u0098\u009c\u00a3\u00a5\u00ac\u00b1"+
		"\u00bb\u00c1\u00c5\u00cb\u00d4\u00db\u00de\u00e0\u00e3\u00e6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}