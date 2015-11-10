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
		T__17=18, T__18=19, T__19=20, INTEGER_STR=21, INTEGER=22, ESC=23, GROUP_REF=24, 
		RE_ESC_CHAR=25, WILDCARD=26;
	public static final int
		RULE_wildcard = 0, RULE_wildcard_no_lb = 1, RULE_wildcard_no_mb_to = 2, 
		RULE_s = 3, RULE_s_tag = 4, RULE_s_tag_name = 5, RULE_s_group = 6, RULE_s_group_margin = 7, 
		RULE_s_class = 8, RULE_re_choice = 9, RULE_re_or = 10, RULE_re_seq = 11, 
		RULE_re_seq_elem = 12, RULE_re_factor = 13, RULE_re_char = 14, RULE_re_class = 15, 
		RULE_re_class_char = 16, RULE_re_group = 17, RULE_re_choice_no_lb = 18, 
		RULE_re_seq_no_lb = 19, RULE_re_seq_elem_no_lb = 20, RULE_re_factor_no_lb = 21, 
		RULE_re_quant = 22, RULE_int_seq = 23, RULE_esc = 24, RULE_group_ref = 25, 
		RULE_re_esc_char = 26;
	public static final String[] ruleNames = {
		"wildcard", "wildcard_no_lb", "wildcard_no_mb_to", "s", "s_tag", "s_tag_name", 
		"s_group", "s_group_margin", "s_class", "re_choice", "re_or", "re_seq", 
		"re_seq_elem", "re_factor", "re_char", "re_class", "re_class_char", "re_group", 
		"re_choice_no_lb", "re_seq_no_lb", "re_seq_elem_no_lb", "re_factor_no_lb", 
		"re_quant", "int_seq", "esc", "group_ref", "re_esc_char"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'^'", "'?'", "'<'", "'>'", "'*'", "'+'", "'{'", "'}'", 
		"'['", "']'", "','", "'|'", "'-'", "'time0'", "'money0'", "'money1'", 
		"'card0'", "'numeric0'", null, null, "'\\'"
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
			setState(72);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(57);
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(58);
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(59);
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(60);
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(61);
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(62);
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(63);
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(64);
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(65);
				match(T__10);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(66);
				match(T__11);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(67);
				match(T__12);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(68);
				match(T__13);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(69);
				match(T__14);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(70);
				s_tag_name();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(71);
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
			setState(90);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				match(T__4);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				match(T__5);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				match(T__3);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				match(T__6);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(80);
				match(T__7);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(81);
				match(T__8);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(82);
				match(T__9);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(83);
				match(T__10);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(84);
				match(T__11);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(85);
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(86);
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(87);
				match(T__14);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(88);
				s_tag_name();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(89);
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
			setState(107);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(95);
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(96);
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(97);
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(98);
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(99);
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(100);
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(101);
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(102);
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(103);
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(104);
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(105);
				s_tag_name();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(106);
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
		public List<S_groupContext> s_group() {
			return getRuleContexts(S_groupContext.class);
		}
		public S_groupContext s_group(int i) {
			return getRuleContext(S_groupContext.class,i);
		}
		public List<S_classContext> s_class() {
			return getRuleContexts(S_classContext.class);
		}
		public S_classContext s_class(int i) {
			return getRuleContext(S_classContext.class,i);
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
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(113);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(109);
					s_tag();
					}
					break;
				case 2:
					{
					setState(110);
					s_group();
					}
					break;
				case 3:
					{
					setState(111);
					s_class();
					}
					break;
				case 4:
					{
					setState(112);
					re_choice();
					}
					break;
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(117);
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
			setState(119);
			match(T__0);
			setState(120);
			match(T__3);
			setState(121);
			match(T__4);
			setState(122);
			s_tag_name();
			setState(123);
			match(T__5);
			setState(124);
			re_choice();
			setState(125);
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
			setState(127);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
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

	public static class S_groupContext extends ParserRuleContext {
		public Re_groupContext re_group() {
			return getRuleContext(Re_groupContext.class,0);
		}
		public List<S_group_marginContext> s_group_margin() {
			return getRuleContexts(S_group_marginContext.class);
		}
		public S_group_marginContext s_group_margin(int i) {
			return getRuleContext(S_group_marginContext.class,i);
		}
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
		public S_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_group(this);
		}
	}

	public final S_groupContext s_group() throws RecognitionException {
		S_groupContext _localctx = new S_groupContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_s_group);
		int _la;
		try {
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				match(T__0);
				setState(131);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0)) {
					{
					setState(130);
					s_group_margin();
					}
				}

				setState(133);
				re_group();
				setState(135);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0)) {
					{
					setState(134);
					s_group_margin();
					}
				}

				setState(137);
				match(T__1);
				setState(138);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				match(T__0);
				setState(141);
				re_seq_no_lb();
				setState(142);
				re_or();
				setState(143);
				re_seq_no_lb();
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(144);
					re_or();
					setState(145);
					re_seq_no_lb();
					}
					}
					setState(151);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(152);
				match(T__1);
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

	public static class S_group_marginContext extends ParserRuleContext {
		public List<Re_classContext> re_class() {
			return getRuleContexts(Re_classContext.class);
		}
		public Re_classContext re_class(int i) {
			return getRuleContext(Re_classContext.class,i);
		}
		public List<Re_charContext> re_char() {
			return getRuleContexts(Re_charContext.class);
		}
		public Re_charContext re_char(int i) {
			return getRuleContext(Re_charContext.class,i);
		}
		public List<Wildcard_no_lbContext> wildcard_no_lb() {
			return getRuleContexts(Wildcard_no_lbContext.class);
		}
		public Wildcard_no_lbContext wildcard_no_lb(int i) {
			return getRuleContext(Wildcard_no_lbContext.class,i);
		}
		public List<Re_quantContext> re_quant() {
			return getRuleContexts(Re_quantContext.class);
		}
		public Re_quantContext re_quant(int i) {
			return getRuleContext(Re_quantContext.class,i);
		}
		public S_group_marginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_group_margin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_group_margin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_group_margin(this);
		}
	}

	public final S_group_marginContext s_group_margin() throws RecognitionException {
		S_group_marginContext _localctx = new S_group_marginContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_s_group_margin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(159);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(156);
					re_class();
					}
					break;
				case 2:
					{
					setState(157);
					re_char();
					}
					break;
				case 3:
					{
					setState(158);
					wildcard_no_lb();
					}
					break;
				}
				setState(162);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(161);
					re_quant();
					}
					break;
				}
				}
				}
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
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

	public static class S_classContext extends ParserRuleContext {
		public Re_classContext re_class() {
			return getRuleContext(Re_classContext.class,0);
		}
		public Re_quantContext re_quant() {
			return getRuleContext(Re_quantContext.class,0);
		}
		public S_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_class(this);
		}
	}

	public final S_classContext s_class() throws RecognitionException {
		S_classContext _localctx = new S_classContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_s_class);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			re_class();
			setState(170);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(169);
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
		enterRule(_localctx, 18, RULE_re_choice);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			re_seq();
			setState(178);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(173);
					re_or();
					setState(174);
					re_seq();
					}
					} 
				}
				setState(180);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		enterRule(_localctx, 20, RULE_re_or);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
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
		enterRule(_localctx, 22, RULE_re_seq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(183);
					re_seq_elem();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(186); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
		enterRule(_localctx, 24, RULE_re_seq_elem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			re_factor();
			setState(190);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(189);
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
		enterRule(_localctx, 26, RULE_re_factor);
		try {
			setState(196);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
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
		enterRule(_localctx, 28, RULE_re_char);
		try {
			setState(201);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				int_seq();
				}
				break;
			case GROUP_REF:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
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
		enterRule(_localctx, 30, RULE_re_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(T__10);
			setState(205);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(204);
				match(T__2);
				}
				break;
			}
			setState(212); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(212);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(207);
					re_class_char();
					setState(208);
					match(T__14);
					setState(209);
					re_class_char();
					}
					break;
				case 2:
					{
					setState(211);
					re_class_char();
					}
					break;
				}
				}
				setState(214); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(216);
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
		enterRule(_localctx, 32, RULE_re_class_char);
		try {
			setState(221);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
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
			case T__18:
			case T__19:
			case WILDCARD:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
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
		enterRule(_localctx, 34, RULE_re_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(T__0);
			setState(226);
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
			case T__18:
			case T__19:
			case INTEGER_STR:
			case GROUP_REF:
			case RE_ESC_CHAR:
			case WILDCARD:
				{
				setState(224);
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
			setState(228);
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
		enterRule(_localctx, 36, RULE_re_choice_no_lb);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			re_seq_no_lb();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(231);
				re_or();
				setState(232);
				re_seq_no_lb();
				}
				}
				setState(238);
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
		enterRule(_localctx, 38, RULE_re_seq_no_lb);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(240); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(239);
					re_seq_elem_no_lb();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(242); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
		enterRule(_localctx, 40, RULE_re_seq_elem_no_lb);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			re_factor_no_lb();
			setState(246);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(245);
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
		enterRule(_localctx, 42, RULE_re_factor_no_lb);
		try {
			setState(252);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(251);
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
		enterRule(_localctx, 44, RULE_re_quant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(254);
				match(T__3);
				}
				break;
			case T__6:
				{
				setState(255);
				match(T__6);
				}
				break;
			case T__7:
				{
				setState(256);
				match(T__7);
				}
				break;
			case T__8:
				{
				setState(257);
				match(T__8);
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==INTEGER_STR) {
					{
					{
					setState(258);
					match(INTEGER_STR);
					}
					}
					setState(263);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(273);
				_la = _input.LA(1);
				if (_la==T__12) {
					{
					setState(264);
					match(T__12);
					setState(271);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						setState(268);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==INTEGER_STR) {
							{
							{
							setState(265);
							match(INTEGER_STR);
							}
							}
							setState(270);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					}
					}
				}

				setState(275);
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(279);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(278);
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
		enterRule(_localctx, 46, RULE_int_seq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
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
		enterRule(_localctx, 48, RULE_esc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
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
		enterRule(_localctx, 50, RULE_group_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
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
		enterRule(_localctx, 52, RULE_re_esc_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\34\u0124\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2K\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3]\n\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4n\n\4\3\5\3\5\3\5\3\5\6\5t\n\5"+
		"\r\5\16\5u\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\5\b"+
		"\u0086\n\b\3\b\3\b\5\b\u008a\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\7\b\u0096\n\b\f\b\16\b\u0099\13\b\3\b\3\b\5\b\u009d\n\b\3\t\3\t\3\t"+
		"\5\t\u00a2\n\t\3\t\5\t\u00a5\n\t\6\t\u00a7\n\t\r\t\16\t\u00a8\3\n\3\n"+
		"\5\n\u00ad\n\n\3\13\3\13\3\13\3\13\7\13\u00b3\n\13\f\13\16\13\u00b6\13"+
		"\13\3\f\3\f\3\r\6\r\u00bb\n\r\r\r\16\r\u00bc\3\16\3\16\5\16\u00c1\n\16"+
		"\3\17\3\17\3\17\3\17\5\17\u00c7\n\17\3\20\3\20\3\20\5\20\u00cc\n\20\3"+
		"\21\3\21\5\21\u00d0\n\21\3\21\3\21\3\21\3\21\3\21\6\21\u00d7\n\21\r\21"+
		"\16\21\u00d8\3\21\3\21\3\22\3\22\3\22\5\22\u00e0\n\22\3\23\3\23\3\23\5"+
		"\23\u00e5\n\23\3\23\3\23\3\24\3\24\3\24\3\24\7\24\u00ed\n\24\f\24\16\24"+
		"\u00f0\13\24\3\25\6\25\u00f3\n\25\r\25\16\25\u00f4\3\26\3\26\5\26\u00f9"+
		"\n\26\3\27\3\27\3\27\3\27\5\27\u00ff\n\27\3\30\3\30\3\30\3\30\3\30\7\30"+
		"\u0106\n\30\f\30\16\30\u0109\13\30\3\30\3\30\7\30\u010d\n\30\f\30\16\30"+
		"\u0110\13\30\5\30\u0112\n\30\5\30\u0114\n\30\3\30\5\30\u0117\n\30\3\30"+
		"\5\30\u011a\n\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\4\u00bc"+
		"\u00f4\2\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"\2\3\3\2\22\26\u015f\2J\3\2\2\2\4\\\3\2\2\2\6m\3\2\2\2\bs\3\2\2\2\ny\3"+
		"\2\2\2\f\u0081\3\2\2\2\16\u009c\3\2\2\2\20\u00a6\3\2\2\2\22\u00aa\3\2"+
		"\2\2\24\u00ae\3\2\2\2\26\u00b7\3\2\2\2\30\u00ba\3\2\2\2\32\u00be\3\2\2"+
		"\2\34\u00c6\3\2\2\2\36\u00cb\3\2\2\2 \u00cd\3\2\2\2\"\u00df\3\2\2\2$\u00e1"+
		"\3\2\2\2&\u00e8\3\2\2\2(\u00f2\3\2\2\2*\u00f6\3\2\2\2,\u00fe\3\2\2\2."+
		"\u0116\3\2\2\2\60\u011b\3\2\2\2\62\u011d\3\2\2\2\64\u011f\3\2\2\2\66\u0121"+
		"\3\2\2\28K\7\3\2\29K\7\4\2\2:K\7\5\2\2;K\7\6\2\2<K\7\7\2\2=K\7\b\2\2>"+
		"K\7\6\2\2?K\7\t\2\2@K\7\n\2\2AK\7\13\2\2BK\7\f\2\2CK\7\r\2\2DK\7\16\2"+
		"\2EK\7\17\2\2FK\7\20\2\2GK\7\21\2\2HK\5\f\7\2IK\7\34\2\2J8\3\2\2\2J9\3"+
		"\2\2\2J:\3\2\2\2J;\3\2\2\2J<\3\2\2\2J=\3\2\2\2J>\3\2\2\2J?\3\2\2\2J@\3"+
		"\2\2\2JA\3\2\2\2JB\3\2\2\2JC\3\2\2\2JD\3\2\2\2JE\3\2\2\2JF\3\2\2\2JG\3"+
		"\2\2\2JH\3\2\2\2JI\3\2\2\2K\3\3\2\2\2L]\7\5\2\2M]\7\6\2\2N]\7\7\2\2O]"+
		"\7\b\2\2P]\7\6\2\2Q]\7\t\2\2R]\7\n\2\2S]\7\13\2\2T]\7\f\2\2U]\7\r\2\2"+
		"V]\7\16\2\2W]\7\17\2\2X]\7\20\2\2Y]\7\21\2\2Z]\5\f\7\2[]\7\34\2\2\\L\3"+
		"\2\2\2\\M\3\2\2\2\\N\3\2\2\2\\O\3\2\2\2\\P\3\2\2\2\\Q\3\2\2\2\\R\3\2\2"+
		"\2\\S\3\2\2\2\\T\3\2\2\2\\U\3\2\2\2\\V\3\2\2\2\\W\3\2\2\2\\X\3\2\2\2\\"+
		"Y\3\2\2\2\\Z\3\2\2\2\\[\3\2\2\2]\5\3\2\2\2^n\7\3\2\2_n\7\4\2\2`n\7\5\2"+
		"\2an\7\6\2\2bn\7\7\2\2cn\7\b\2\2dn\7\6\2\2en\7\t\2\2fn\7\n\2\2gn\7\13"+
		"\2\2hn\7\f\2\2in\7\17\2\2jn\7\20\2\2kn\5\f\7\2ln\7\34\2\2m^\3\2\2\2m_"+
		"\3\2\2\2m`\3\2\2\2ma\3\2\2\2mb\3\2\2\2mc\3\2\2\2md\3\2\2\2me\3\2\2\2m"+
		"f\3\2\2\2mg\3\2\2\2mh\3\2\2\2mi\3\2\2\2mj\3\2\2\2mk\3\2\2\2ml\3\2\2\2"+
		"n\7\3\2\2\2ot\5\n\6\2pt\5\16\b\2qt\5\22\n\2rt\5\24\13\2so\3\2\2\2sp\3"+
		"\2\2\2sq\3\2\2\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2vw\3\2\2\2wx\7"+
		"\2\2\3x\t\3\2\2\2yz\7\3\2\2z{\7\6\2\2{|\7\7\2\2|}\5\f\7\2}~\7\b\2\2~\177"+
		"\5\24\13\2\177\u0080\7\4\2\2\u0080\13\3\2\2\2\u0081\u0082\t\2\2\2\u0082"+
		"\r\3\2\2\2\u0083\u0085\7\3\2\2\u0084\u0086\5\20\t\2\u0085\u0084\3\2\2"+
		"\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\5$\23\2\u0088\u008a"+
		"\5\20\t\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2"+
		"\u008b\u008c\7\4\2\2\u008c\u008d\7\6\2\2\u008d\u009d\3\2\2\2\u008e\u008f"+
		"\7\3\2\2\u008f\u0090\5(\25\2\u0090\u0091\5\26\f\2\u0091\u0097\5(\25\2"+
		"\u0092\u0093\5\26\f\2\u0093\u0094\5(\25\2\u0094\u0096\3\2\2\2\u0095\u0092"+
		"\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7\4\2\2\u009b\u009d\3\2"+
		"\2\2\u009c\u0083\3\2\2\2\u009c\u008e\3\2\2\2\u009d\17\3\2\2\2\u009e\u00a2"+
		"\5 \21\2\u009f\u00a2\5\36\20\2\u00a0\u00a2\5\4\3\2\u00a1\u009e\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a5"+
		"\5.\30\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6"+
		"\u00a1\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\21\3\2\2\2\u00aa\u00ac\5 \21\2\u00ab\u00ad\5.\30\2\u00ac\u00ab"+
		"\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\23\3\2\2\2\u00ae\u00b4\5\30\r\2\u00af"+
		"\u00b0\5\26\f\2\u00b0\u00b1\5\30\r\2\u00b1\u00b3\3\2\2\2\u00b2\u00af\3"+
		"\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\25\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\20\2\2\u00b8\27\3\2\2\2"+
		"\u00b9\u00bb\5\32\16\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd"+
		"\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\31\3\2\2\2\u00be\u00c0\5\34\17\2\u00bf"+
		"\u00c1\5.\30\2\u00c0\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\33\3\2\2"+
		"\2\u00c2\u00c7\5 \21\2\u00c3\u00c7\5$\23\2\u00c4\u00c7\5\36\20\2\u00c5"+
		"\u00c7\5\2\2\2\u00c6\u00c2\3\2\2\2\u00c6\u00c3\3\2\2\2\u00c6\u00c4\3\2"+
		"\2\2\u00c6\u00c5\3\2\2\2\u00c7\35\3\2\2\2\u00c8\u00cc\5\66\34\2\u00c9"+
		"\u00cc\5\60\31\2\u00ca\u00cc\5\64\33\2\u00cb\u00c8\3\2\2\2\u00cb\u00c9"+
		"\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\37\3\2\2\2\u00cd\u00cf\7\r\2\2\u00ce"+
		"\u00d0\7\5\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d6\3\2"+
		"\2\2\u00d1\u00d2\5\"\22\2\u00d2\u00d3\7\21\2\2\u00d3\u00d4\5\"\22\2\u00d4"+
		"\u00d7\3\2\2\2\u00d5\u00d7\5\"\22\2\u00d6\u00d1\3\2\2\2\u00d6\u00d5\3"+
		"\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00db\7\16\2\2\u00db!\3\2\2\2\u00dc\u00e0\5\66\34"+
		"\2\u00dd\u00e0\5\60\31\2\u00de\u00e0\5\6\4\2\u00df\u00dc\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00de\3\2\2\2\u00e0#\3\2\2\2\u00e1\u00e4\7\3\2\2"+
		"\u00e2\u00e5\5&\24\2\u00e3\u00e5\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e3"+
		"\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\7\4\2\2\u00e7%\3\2\2\2\u00e8"+
		"\u00ee\5(\25\2\u00e9\u00ea\5\26\f\2\u00ea\u00eb\5(\25\2\u00eb\u00ed\3"+
		"\2\2\2\u00ec\u00e9\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\'\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f3\5*\26\2"+
		"\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f4\u00f2"+
		"\3\2\2\2\u00f5)\3\2\2\2\u00f6\u00f8\5,\27\2\u00f7\u00f9\5.\30\2\u00f8"+
		"\u00f7\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9+\3\2\2\2\u00fa\u00ff\5 \21\2"+
		"\u00fb\u00ff\5$\23\2\u00fc\u00ff\5\36\20\2\u00fd\u00ff\5\4\3\2\u00fe\u00fa"+
		"\3\2\2\2\u00fe\u00fb\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff"+
		"-\3\2\2\2\u0100\u0117\7\6\2\2\u0101\u0117\7\t\2\2\u0102\u0117\7\n\2\2"+
		"\u0103\u0107\7\13\2\2\u0104\u0106\7\27\2\2\u0105\u0104\3\2\2\2\u0106\u0109"+
		"\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0113\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u010a\u0111\7\17\2\2\u010b\u010d\7\27\2\2\u010c\u010b\3"+
		"\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u010e\3\2\2\2\u0111\u0112\3\2"+
		"\2\2\u0112\u0114\3\2\2\2\u0113\u010a\3\2\2\2\u0113\u0114\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0117\7\f\2\2\u0116\u0100\3\2\2\2\u0116\u0101\3\2"+
		"\2\2\u0116\u0102\3\2\2\2\u0116\u0103\3\2\2\2\u0117\u0119\3\2\2\2\u0118"+
		"\u011a\7\6\2\2\u0119\u0118\3\2\2\2\u0119\u011a\3\2\2\2\u011a/\3\2\2\2"+
		"\u011b\u011c\7\27\2\2\u011c\61\3\2\2\2\u011d\u011e\7\31\2\2\u011e\63\3"+
		"\2\2\2\u011f\u0120\7\32\2\2\u0120\65\3\2\2\2\u0121\u0122\7\33\2\2\u0122"+
		"\67\3\2\2\2#J\\msu\u0085\u0089\u0097\u009c\u00a1\u00a4\u00a8\u00ac\u00b4"+
		"\u00bc\u00c0\u00c6\u00cb\u00cf\u00d6\u00d8\u00df\u00e4\u00ee\u00f4\u00f8"+
		"\u00fe\u0107\u010e\u0111\u0113\u0116\u0119";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}