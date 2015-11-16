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
		T__17=18, T__18=19, T__19=20, T__20=21, INTEGER_STR=22, INTEGER=23, ESC=24, 
		GROUP_REF=25, RE_ESC_CHAR=26, WILDCARD=27;
	public static final int
		RULE_wildcard = 0, RULE_wildcard_no_lb = 1, RULE_wildcard_no_mb_to = 2, 
		RULE_s = 3, RULE_s_tag = 4, RULE_s_tag_name = 5, RULE_s_group = 6, RULE_s_group_l_margin = 7, 
		RULE_s_group_r_margin = 8, RULE_s_group_margin = 9, RULE_s_class = 10, 
		RULE_re_choice = 11, RULE_re_or = 12, RULE_re_seq = 13, RULE_re_seq_elem = 14, 
		RULE_re_factor = 15, RULE_re_char = 16, RULE_re_class = 17, RULE_re_class_char = 18, 
		RULE_re_group = 19, RULE_re_choice_no_lb = 20, RULE_re_seq_no_lb = 21, 
		RULE_re_seq_elem_no_lb = 22, RULE_re_factor_no_lb = 23, RULE_s_wild = 24, 
		RULE_re_quant = 25, RULE_int_seq = 26, RULE_esc = 27, RULE_group_ref = 28, 
		RULE_re_esc_char = 29;
	public static final String[] ruleNames = {
		"wildcard", "wildcard_no_lb", "wildcard_no_mb_to", "s", "s_tag", "s_tag_name", 
		"s_group", "s_group_l_margin", "s_group_r_margin", "s_group_margin", "s_class", 
		"re_choice", "re_or", "re_seq", "re_seq_elem", "re_factor", "re_char", 
		"re_class", "re_class_char", "re_group", "re_choice_no_lb", "re_seq_no_lb", 
		"re_seq_elem_no_lb", "re_factor_no_lb", "s_wild", "re_quant", "int_seq", 
		"esc", "group_ref", "re_esc_char"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'^'", "'?'", "'<'", "'>'", "'*'", "'+'", "'{'", "'}'", 
		"'['", "']'", "','", "'|'", "'-'", "'.'", "'time0'", "'money0'", "'money1'", 
		"'card0'", "'numeric0'", null, null, "'\\'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "INTEGER_STR", 
		"INTEGER", "ESC", "GROUP_REF", "RE_ESC_CHAR", "WILDCARD"
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
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60); 
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61); 
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62); 
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(63); 
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(64); 
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(65); 
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(66); 
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(67); 
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(68); 
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(69); 
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(70); 
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(71); 
				match(T__10);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(72); 
				match(T__11);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(73); 
				match(T__12);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(74); 
				match(T__13);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(75); 
				match(T__14);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(76); 
				match(T__15);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(77); 
				s_tag_name();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(78); 
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
			setState(98);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81); 
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(82); 
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(83); 
				match(T__4);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(84); 
				match(T__5);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(85); 
				match(T__3);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(86); 
				match(T__6);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(87); 
				match(T__7);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(88); 
				match(T__8);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(89); 
				match(T__9);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(90); 
				match(T__10);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(91); 
				match(T__11);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(92); 
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(93); 
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(94); 
				match(T__14);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(95); 
				match(T__15);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(96); 
				s_tag_name();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(97); 
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
			setState(116);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100); 
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(101); 
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(102); 
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(103); 
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(104); 
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(105); 
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(106); 
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(107); 
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(108); 
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(109); 
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(110); 
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(111); 
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(112); 
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(113); 
				match(T__15);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(114); 
				s_tag_name();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(115); 
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
		public List<S_wildContext> s_wild() {
			return getRuleContexts(S_wildContext.class);
		}
		public S_wildContext s_wild(int i) {
			return getRuleContext(S_wildContext.class,i);
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
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(123);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(118); 
					s_tag();
					}
					break;
				case 2:
					{
					setState(119); 
					s_group();
					}
					break;
				case 3:
					{
					setState(120); 
					s_class();
					}
					break;
				case 4:
					{
					setState(121); 
					s_wild();
					}
					break;
				case 5:
					{
					setState(122); 
					re_choice();
					}
					break;
				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(127); 
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
			setState(129); 
			match(T__0);
			setState(130); 
			match(T__3);
			setState(131); 
			match(T__4);
			setState(132); 
			s_tag_name();
			setState(133); 
			match(T__5);
			setState(134); 
			re_choice();
			setState(135); 
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
			setState(137);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
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

	public static class S_groupContext extends ParserRuleContext {
		public Re_groupContext re_group() {
			return getRuleContext(Re_groupContext.class,0);
		}
		public Re_quantContext re_quant() {
			return getRuleContext(Re_quantContext.class,0);
		}
		public S_group_l_marginContext s_group_l_margin() {
			return getRuleContext(S_group_l_marginContext.class,0);
		}
		public S_group_r_marginContext s_group_r_margin() {
			return getRuleContext(S_group_r_marginContext.class,0);
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
			setState(164);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139); 
				match(T__0);
				setState(141);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0)) {
					{
					setState(140); 
					s_group_l_margin();
					}
				}

				setState(143); 
				re_group();
				setState(145);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0)) {
					{
					setState(144); 
					s_group_r_margin();
					}
				}

				setState(147); 
				match(T__1);
				setState(148); 
				re_quant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(150); 
				match(T__0);
				setState(151); 
				re_seq_no_lb();
				setState(152); 
				re_or();
				setState(153); 
				re_seq_no_lb();
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(154); 
					re_or();
					setState(155); 
					re_seq_no_lb();
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162); 
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

	public static class S_group_l_marginContext extends ParserRuleContext {
		public S_group_marginContext s_group_margin() {
			return getRuleContext(S_group_marginContext.class,0);
		}
		public S_group_l_marginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_group_l_margin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_group_l_margin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_group_l_margin(this);
		}
	}

	public final S_group_l_marginContext s_group_l_margin() throws RecognitionException {
		S_group_l_marginContext _localctx = new S_group_l_marginContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_s_group_l_margin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); 
			s_group_margin();
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

	public static class S_group_r_marginContext extends ParserRuleContext {
		public S_group_marginContext s_group_margin() {
			return getRuleContext(S_group_marginContext.class,0);
		}
		public S_group_r_marginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_group_r_margin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_group_r_margin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_group_r_margin(this);
		}
	}

	public final S_group_r_marginContext s_group_r_margin() throws RecognitionException {
		S_group_r_marginContext _localctx = new S_group_r_marginContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_s_group_r_margin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); 
			s_group_margin();
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
		enterRule(_localctx, 18, RULE_s_group_margin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(173);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(170); 
					re_class();
					}
					break;
				case 2:
					{
					setState(171); 
					re_char();
					}
					break;
				case 3:
					{
					setState(172); 
					wildcard_no_lb();
					}
					break;
				}
				setState(176);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(175); 
					re_quant();
					}
					break;
				}
				}
				}
				setState(180); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
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
		enterRule(_localctx, 20, RULE_s_class);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); 
			re_class();
			setState(184);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(183); 
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
		enterRule(_localctx, 22, RULE_re_choice);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(186); 
			re_seq();
			setState(192);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(187); 
					re_or();
					setState(188); 
					re_seq();
					}
					} 
				}
				setState(194);
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
		enterRule(_localctx, 24, RULE_re_or);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); 
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
		enterRule(_localctx, 26, RULE_re_seq);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(198); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(197); 
					re_seq_elem();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(200); 
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
		enterRule(_localctx, 28, RULE_re_seq_elem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); 
			re_factor();
			setState(204);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(203); 
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
		enterRule(_localctx, 30, RULE_re_factor);
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(206); 
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207); 
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(208); 
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(209); 
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
		enterRule(_localctx, 32, RULE_re_char);
		try {
			setState(216);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); 
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(213); 
				int_seq();
				}
				break;
			case GROUP_REF:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); 
				group_ref();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 4);
				{
				setState(215); 
				match(T__15);
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
		enterRule(_localctx, 34, RULE_re_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218); 
			match(T__10);
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(219); 
				match(T__2);
				}
				break;
			}
			setState(227); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(227);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(222); 
					re_class_char();
					setState(223); 
					match(T__14);
					setState(224); 
					re_class_char();
					}
					break;
				case 2:
					{
					setState(226); 
					re_class_char();
					}
					break;
				}
				}
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << INTEGER_STR) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(231); 
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
		enterRule(_localctx, 36, RULE_re_class_char);
		try {
			setState(236);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(233); 
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(234); 
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
			case T__20:
			case WILDCARD:
				enterOuterAlt(_localctx, 3);
				{
				setState(235); 
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
		enterRule(_localctx, 38, RULE_re_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238); 
			match(T__0);
			setState(241);
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
			case T__20:
			case INTEGER_STR:
			case GROUP_REF:
			case RE_ESC_CHAR:
			case WILDCARD:
				{
				setState(239); 
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
			setState(243); 
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
		enterRule(_localctx, 40, RULE_re_choice_no_lb);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); 
			re_seq_no_lb();
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(246); 
				re_or();
				setState(247); 
				re_seq_no_lb();
				}
				}
				setState(253);
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
		enterRule(_localctx, 42, RULE_re_seq_no_lb);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(255); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(254); 
					re_seq_elem_no_lb();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(257); 
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
		enterRule(_localctx, 44, RULE_re_seq_elem_no_lb);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259); 
			re_factor_no_lb();
			setState(261);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(260); 
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
		enterRule(_localctx, 46, RULE_re_factor_no_lb);
		try {
			setState(267);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(263); 
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(264); 
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(265); 
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(266); 
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

	public static class S_wildContext extends ParserRuleContext {
		public Re_quantContext re_quant() {
			return getRuleContext(Re_quantContext.class,0);
		}
		public S_wildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_wild; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterS_wild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitS_wild(this);
		}
	}

	public final S_wildContext s_wild() throws RecognitionException {
		S_wildContext _localctx = new S_wildContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_s_wild);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); 
			match(T__15);
			setState(271);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(270); 
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
		enterRule(_localctx, 50, RULE_re_quant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(273); 
				match(T__3);
				}
				break;
			case T__6:
				{
				setState(274); 
				match(T__6);
				}
				break;
			case T__7:
				{
				setState(275); 
				match(T__7);
				}
				break;
			case T__8:
				{
				setState(276); 
				match(T__8);
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==INTEGER_STR) {
					{
					{
					setState(277); 
					match(INTEGER_STR);
					}
					}
					setState(282);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(292);
				_la = _input.LA(1);
				if (_la==T__12) {
					{
					setState(283); 
					match(T__12);
					setState(290);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						setState(287);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==INTEGER_STR) {
							{
							{
							setState(284); 
							match(INTEGER_STR);
							}
							}
							setState(289);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					}
					}
				}

				setState(294); 
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(298);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(297); 
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
		enterRule(_localctx, 52, RULE_int_seq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300); 
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
		enterRule(_localctx, 54, RULE_esc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302); 
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
		enterRule(_localctx, 56, RULE_group_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); 
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
		enterRule(_localctx, 58, RULE_re_esc_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u0137\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2"+
		"R\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3e\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4w\n\4\3\5\3\5\3\5\3\5\3\5\6\5~\n\5\r\5\16\5\177\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\5\b\u0090\n\b\3\b\3\b\5"+
		"\b\u0094\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a0\n\b\f\b"+
		"\16\b\u00a3\13\b\3\b\3\b\5\b\u00a7\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13"+
		"\5\13\u00b0\n\13\3\13\5\13\u00b3\n\13\6\13\u00b5\n\13\r\13\16\13\u00b6"+
		"\3\f\3\f\5\f\u00bb\n\f\3\r\3\r\3\r\3\r\7\r\u00c1\n\r\f\r\16\r\u00c4\13"+
		"\r\3\16\3\16\3\17\6\17\u00c9\n\17\r\17\16\17\u00ca\3\20\3\20\5\20\u00cf"+
		"\n\20\3\21\3\21\3\21\3\21\5\21\u00d5\n\21\3\22\3\22\3\22\3\22\5\22\u00db"+
		"\n\22\3\23\3\23\5\23\u00df\n\23\3\23\3\23\3\23\3\23\3\23\6\23\u00e6\n"+
		"\23\r\23\16\23\u00e7\3\23\3\23\3\24\3\24\3\24\5\24\u00ef\n\24\3\25\3\25"+
		"\3\25\5\25\u00f4\n\25\3\25\3\25\3\26\3\26\3\26\3\26\7\26\u00fc\n\26\f"+
		"\26\16\26\u00ff\13\26\3\27\6\27\u0102\n\27\r\27\16\27\u0103\3\30\3\30"+
		"\5\30\u0108\n\30\3\31\3\31\3\31\3\31\5\31\u010e\n\31\3\32\3\32\5\32\u0112"+
		"\n\32\3\33\3\33\3\33\3\33\3\33\7\33\u0119\n\33\f\33\16\33\u011c\13\33"+
		"\3\33\3\33\7\33\u0120\n\33\f\33\16\33\u0123\13\33\5\33\u0125\n\33\5\33"+
		"\u0127\n\33\3\33\5\33\u012a\n\33\3\33\5\33\u012d\n\33\3\34\3\34\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3\37\4\u00ca\u0103\2 \2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\3\3\2\23\27\u0175\2Q\3\2\2\2"+
		"\4d\3\2\2\2\6v\3\2\2\2\b}\3\2\2\2\n\u0083\3\2\2\2\f\u008b\3\2\2\2\16\u00a6"+
		"\3\2\2\2\20\u00a8\3\2\2\2\22\u00aa\3\2\2\2\24\u00b4\3\2\2\2\26\u00b8\3"+
		"\2\2\2\30\u00bc\3\2\2\2\32\u00c5\3\2\2\2\34\u00c8\3\2\2\2\36\u00cc\3\2"+
		"\2\2 \u00d4\3\2\2\2\"\u00da\3\2\2\2$\u00dc\3\2\2\2&\u00ee\3\2\2\2(\u00f0"+
		"\3\2\2\2*\u00f7\3\2\2\2,\u0101\3\2\2\2.\u0105\3\2\2\2\60\u010d\3\2\2\2"+
		"\62\u010f\3\2\2\2\64\u0129\3\2\2\2\66\u012e\3\2\2\28\u0130\3\2\2\2:\u0132"+
		"\3\2\2\2<\u0134\3\2\2\2>R\7\3\2\2?R\7\4\2\2@R\7\5\2\2AR\7\6\2\2BR\7\7"+
		"\2\2CR\7\b\2\2DR\7\6\2\2ER\7\t\2\2FR\7\n\2\2GR\7\13\2\2HR\7\f\2\2IR\7"+
		"\r\2\2JR\7\16\2\2KR\7\17\2\2LR\7\20\2\2MR\7\21\2\2NR\7\22\2\2OR\5\f\7"+
		"\2PR\7\35\2\2Q>\3\2\2\2Q?\3\2\2\2Q@\3\2\2\2QA\3\2\2\2QB\3\2\2\2QC\3\2"+
		"\2\2QD\3\2\2\2QE\3\2\2\2QF\3\2\2\2QG\3\2\2\2QH\3\2\2\2QI\3\2\2\2QJ\3\2"+
		"\2\2QK\3\2\2\2QL\3\2\2\2QM\3\2\2\2QN\3\2\2\2QO\3\2\2\2QP\3\2\2\2R\3\3"+
		"\2\2\2Se\7\5\2\2Te\7\6\2\2Ue\7\7\2\2Ve\7\b\2\2We\7\6\2\2Xe\7\t\2\2Ye\7"+
		"\n\2\2Ze\7\13\2\2[e\7\f\2\2\\e\7\r\2\2]e\7\16\2\2^e\7\17\2\2_e\7\20\2"+
		"\2`e\7\21\2\2ae\7\22\2\2be\5\f\7\2ce\7\35\2\2dS\3\2\2\2dT\3\2\2\2dU\3"+
		"\2\2\2dV\3\2\2\2dW\3\2\2\2dX\3\2\2\2dY\3\2\2\2dZ\3\2\2\2d[\3\2\2\2d\\"+
		"\3\2\2\2d]\3\2\2\2d^\3\2\2\2d_\3\2\2\2d`\3\2\2\2da\3\2\2\2db\3\2\2\2d"+
		"c\3\2\2\2e\5\3\2\2\2fw\7\3\2\2gw\7\4\2\2hw\7\5\2\2iw\7\6\2\2jw\7\7\2\2"+
		"kw\7\b\2\2lw\7\6\2\2mw\7\t\2\2nw\7\n\2\2ow\7\13\2\2pw\7\f\2\2qw\7\17\2"+
		"\2rw\7\20\2\2sw\7\22\2\2tw\5\f\7\2uw\7\35\2\2vf\3\2\2\2vg\3\2\2\2vh\3"+
		"\2\2\2vi\3\2\2\2vj\3\2\2\2vk\3\2\2\2vl\3\2\2\2vm\3\2\2\2vn\3\2\2\2vo\3"+
		"\2\2\2vp\3\2\2\2vq\3\2\2\2vr\3\2\2\2vs\3\2\2\2vt\3\2\2\2vu\3\2\2\2w\7"+
		"\3\2\2\2x~\5\n\6\2y~\5\16\b\2z~\5\26\f\2{~\5\62\32\2|~\5\30\r\2}x\3\2"+
		"\2\2}y\3\2\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2"+
		"\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\7\2\2\3\u0082\t\3\2"+
		"\2\2\u0083\u0084\7\3\2\2\u0084\u0085\7\6\2\2\u0085\u0086\7\7\2\2\u0086"+
		"\u0087\5\f\7\2\u0087\u0088\7\b\2\2\u0088\u0089\5\30\r\2\u0089\u008a\7"+
		"\4\2\2\u008a\13\3\2\2\2\u008b\u008c\t\2\2\2\u008c\r\3\2\2\2\u008d\u008f"+
		"\7\3\2\2\u008e\u0090\5\20\t\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2"+
		"\u0090\u0091\3\2\2\2\u0091\u0093\5(\25\2\u0092\u0094\5\22\n\2\u0093\u0092"+
		"\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\4\2\2\u0096"+
		"\u0097\5\64\33\2\u0097\u00a7\3\2\2\2\u0098\u0099\7\3\2\2\u0099\u009a\5"+
		",\27\2\u009a\u009b\5\32\16\2\u009b\u00a1\5,\27\2\u009c\u009d\5\32\16\2"+
		"\u009d\u009e\5,\27\2\u009e\u00a0\3\2\2\2\u009f\u009c\3\2\2\2\u00a0\u00a3"+
		"\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u00a5\7\4\2\2\u00a5\u00a7\3\2\2\2\u00a6\u008d\3\2"+
		"\2\2\u00a6\u0098\3\2\2\2\u00a7\17\3\2\2\2\u00a8\u00a9\5\24\13\2\u00a9"+
		"\21\3\2\2\2\u00aa\u00ab\5\24\13\2\u00ab\23\3\2\2\2\u00ac\u00b0\5$\23\2"+
		"\u00ad\u00b0\5\"\22\2\u00ae\u00b0\5\4\3\2\u00af\u00ac\3\2\2\2\u00af\u00ad"+
		"\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00b3\5\64\33\2"+
		"\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00af"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\25\3\2\2\2\u00b8\u00ba\5$\23\2\u00b9\u00bb\5\64\33\2\u00ba\u00b9\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\27\3\2\2\2\u00bc\u00c2\5\34\17\2\u00bd"+
		"\u00be\5\32\16\2\u00be\u00bf\5\34\17\2\u00bf\u00c1\3\2\2\2\u00c0\u00bd"+
		"\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"\31\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7\20\2\2\u00c6\33\3\2\2\2"+
		"\u00c7\u00c9\5\36\20\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb"+
		"\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\35\3\2\2\2\u00cc\u00ce\5 \21\2\u00cd"+
		"\u00cf\5\64\33\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\37\3\2"+
		"\2\2\u00d0\u00d5\5$\23\2\u00d1\u00d5\5(\25\2\u00d2\u00d5\5\"\22\2\u00d3"+
		"\u00d5\5\2\2\2\u00d4\u00d0\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d3\3\2\2\2\u00d5!\3\2\2\2\u00d6\u00db\5<\37\2\u00d7\u00db"+
		"\5\66\34\2\u00d8\u00db\5:\36\2\u00d9\u00db\7\22\2\2\u00da\u00d6\3\2\2"+
		"\2\u00da\u00d7\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00d9\3\2\2\2\u00db#"+
		"\3\2\2\2\u00dc\u00de\7\r\2\2\u00dd\u00df\7\5\2\2\u00de\u00dd\3\2\2\2\u00de"+
		"\u00df\3\2\2\2\u00df\u00e5\3\2\2\2\u00e0\u00e1\5&\24\2\u00e1\u00e2\7\21"+
		"\2\2\u00e2\u00e3\5&\24\2\u00e3\u00e6\3\2\2\2\u00e4\u00e6\5&\24\2\u00e5"+
		"\u00e0\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e5\3\2"+
		"\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\7\16\2\2\u00ea"+
		"%\3\2\2\2\u00eb\u00ef\5<\37\2\u00ec\u00ef\5\66\34\2\u00ed\u00ef\5\6\4"+
		"\2\u00ee\u00eb\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\'"+
		"\3\2\2\2\u00f0\u00f3\7\3\2\2\u00f1\u00f4\5*\26\2\u00f2\u00f4\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\7\4"+
		"\2\2\u00f6)\3\2\2\2\u00f7\u00fd\5,\27\2\u00f8\u00f9\5\32\16\2\u00f9\u00fa"+
		"\5,\27\2\u00fa\u00fc\3\2\2\2\u00fb\u00f8\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe+\3\2\2\2\u00ff\u00fd\3\2\2\2"+
		"\u0100\u0102\5.\30\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104"+
		"\3\2\2\2\u0103\u0101\3\2\2\2\u0104-\3\2\2\2\u0105\u0107\5\60\31\2\u0106"+
		"\u0108\5\64\33\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108/\3\2\2"+
		"\2\u0109\u010e\5$\23\2\u010a\u010e\5(\25\2\u010b\u010e\5\"\22\2\u010c"+
		"\u010e\5\4\3\2\u010d\u0109\3\2\2\2\u010d\u010a\3\2\2\2\u010d\u010b\3\2"+
		"\2\2\u010d\u010c\3\2\2\2\u010e\61\3\2\2\2\u010f\u0111\7\22\2\2\u0110\u0112"+
		"\5\64\33\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\63\3\2\2\2\u0113"+
		"\u012a\7\6\2\2\u0114\u012a\7\t\2\2\u0115\u012a\7\n\2\2\u0116\u011a\7\13"+
		"\2\2\u0117\u0119\7\30\2\2\u0118\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0126\3\2\2\2\u011c\u011a\3\2"+
		"\2\2\u011d\u0124\7\17\2\2\u011e\u0120\7\30\2\2\u011f\u011e\3\2\2\2\u0120"+
		"\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0125\3\2"+
		"\2\2\u0123\u0121\3\2\2\2\u0124\u0121\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0127\3\2\2\2\u0126\u011d\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\3\2"+
		"\2\2\u0128\u012a\7\f\2\2\u0129\u0113\3\2\2\2\u0129\u0114\3\2\2\2\u0129"+
		"\u0115\3\2\2\2\u0129\u0116\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u012d\7\6"+
		"\2\2\u012c\u012b\3\2\2\2\u012c\u012d\3\2\2\2\u012d\65\3\2\2\2\u012e\u012f"+
		"\7\30\2\2\u012f\67\3\2\2\2\u0130\u0131\7\32\2\2\u01319\3\2\2\2\u0132\u0133"+
		"\7\33\2\2\u0133;\3\2\2\2\u0134\u0135\7\34\2\2\u0135=\3\2\2\2$Qdv}\177"+
		"\u008f\u0093\u00a1\u00a6\u00af\u00b2\u00b6\u00ba\u00c2\u00ca\u00ce\u00d4"+
		"\u00da\u00de\u00e5\u00e7\u00ee\u00f3\u00fd\u0103\u0107\u010d\u0111\u011a"+
		"\u0121\u0124\u0126\u0129\u012c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}