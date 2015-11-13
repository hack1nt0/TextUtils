// Generated from /home/dy/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/Regex.g4 by ANTLR 4.5
package com.xiaomi.nlp.util.regular;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

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
		RULE_s = 3, RULE_s_tag = 4, RULE_s_tag_name = 5, RULE_s_group = 6, RULE_s_group_l_margin = 7, 
		RULE_s_group_r_margin = 8, RULE_s_group_margin = 9, RULE_s_class = 10, 
		RULE_re_choice = 11, RULE_re_or = 12, RULE_re_seq = 13, RULE_re_seq_elem = 14, 
		RULE_re_factor = 15, RULE_re_char = 16, RULE_re_class = 17, RULE_re_class_char = 18, 
		RULE_re_group = 19, RULE_re_choice_no_lb = 20, RULE_re_seq_no_lb = 21, 
		RULE_re_seq_elem_no_lb = 22, RULE_re_factor_no_lb = 23, RULE_re_quant = 24, 
		RULE_int_seq = 25, RULE_esc = 26, RULE_group_ref = 27, RULE_re_esc_char = 28;
	public static final String[] ruleNames = {
		"wildcard", "wildcard_no_lb", "wildcard_no_mb_to", "s", "s_tag", "s_tag_name", 
		"s_group", "s_group_l_margin", "s_group_r_margin", "s_group_margin", "s_class", 
		"re_choice", "re_or", "re_seq", "re_seq_elem", "re_factor", "re_char", 
		"re_class", "re_class_char", "re_group", "re_choice_no_lb", "re_seq_no_lb", 
		"re_seq_elem_no_lb", "re_factor_no_lb", "re_quant", "int_seq", "esc", 
		"group_ref", "re_esc_char"
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
			setState(76);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58); 
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59); 
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(60); 
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(61); 
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(62); 
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(63); 
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(64); 
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(65); 
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(66); 
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(67); 
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(68); 
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(69); 
				match(T__10);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(70); 
				match(T__11);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(71); 
				match(T__12);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(72); 
				match(T__13);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(73); 
				match(T__14);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(74); 
				s_tag_name();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(75); 
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
			setState(94);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78); 
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79); 
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(80); 
				match(T__4);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(81); 
				match(T__5);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(82); 
				match(T__3);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(83); 
				match(T__6);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(84); 
				match(T__7);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(85); 
				match(T__8);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(86); 
				match(T__9);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(87); 
				match(T__10);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(88); 
				match(T__11);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(89); 
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(90); 
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(91); 
				match(T__14);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(92); 
				s_tag_name();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(93); 
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
			setState(111);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(96); 
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97); 
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(98); 
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(99); 
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(100); 
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(101); 
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(102); 
				match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(103); 
				match(T__6);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(104); 
				match(T__7);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(105); 
				match(T__8);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(106); 
				match(T__9);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(107); 
				match(T__12);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(108); 
				match(T__13);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(109); 
				s_tag_name();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(110); 
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
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(117);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(113); 
					s_tag();
					}
					break;
				case 2:
					{
					setState(114); 
					s_group();
					}
					break;
				case 3:
					{
					setState(115); 
					s_class();
					}
					break;
				case 4:
					{
					setState(116); 
					re_choice();
					}
					break;
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(121); 
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
			setState(123); 
			match(T__0);
			setState(124); 
			match(T__3);
			setState(125); 
			match(T__4);
			setState(126); 
			s_tag_name();
			setState(127); 
			match(T__5);
			setState(128); 
			re_choice();
			setState(129); 
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
			setState(131);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
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
			setState(158);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133); 
				match(T__0);
				setState(135);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0)) {
					{
					setState(134); 
					s_group_l_margin();
					}
				}

				setState(137); 
				re_group();
				setState(139);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << GROUP_REF) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0)) {
					{
					setState(138); 
					s_group_r_margin();
					}
				}

				setState(141); 
				match(T__1);
				setState(142); 
				re_quant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144); 
				match(T__0);
				setState(145); 
				re_seq_no_lb();
				setState(146); 
				re_or();
				setState(147); 
				re_seq_no_lb();
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(148); 
					re_or();
					setState(149); 
					re_seq_no_lb();
					}
					}
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(156); 
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
			setState(160); 
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
			setState(162); 
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
			setState(172); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(167);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(164); 
					re_class();
					}
					break;
				case 2:
					{
					setState(165); 
					re_char();
					}
					break;
				case 3:
					{
					setState(166); 
					wildcard_no_lb();
					}
					break;
				}
				setState(170);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(169); 
					re_quant();
					}
					break;
				}
				}
				}
				setState(174); 
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
		enterRule(_localctx, 20, RULE_s_class);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); 
			re_class();
			setState(178);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(177); 
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
			setState(180); 
			re_seq();
			setState(186);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(181); 
					re_or();
					setState(182); 
					re_seq();
					}
					} 
				}
				setState(188);
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
			setState(189); 
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
			setState(192); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(191); 
					re_seq_elem();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(194); 
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
			setState(196); 
			re_factor();
			setState(198);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(197); 
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
			setState(204);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200); 
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201); 
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(202); 
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(203); 
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
			setState(209);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(206); 
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(207); 
				int_seq();
				}
				break;
			case GROUP_REF:
				enterOuterAlt(_localctx, 3);
				{
				setState(208); 
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
		enterRule(_localctx, 34, RULE_re_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); 
			match(T__10);
			setState(213);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(212); 
				match(T__2);
				}
				break;
			}
			setState(220); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(220);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(215); 
					re_class_char();
					setState(216); 
					match(T__14);
					setState(217); 
					re_class_char();
					}
					break;
				case 2:
					{
					setState(219); 
					re_class_char();
					}
					break;
				}
				}
				setState(222); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << INTEGER_STR) | (1L << RE_ESC_CHAR) | (1L << WILDCARD))) != 0) );
			setState(224); 
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
			setState(229);
			switch (_input.LA(1)) {
			case RE_ESC_CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(226); 
				re_esc_char();
				}
				break;
			case INTEGER_STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(227); 
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
				setState(228); 
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
			setState(231); 
			match(T__0);
			setState(234);
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
				setState(232); 
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
			setState(236); 
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
			setState(238); 
			re_seq_no_lb();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(239); 
				re_or();
				setState(240); 
				re_seq_no_lb();
				}
				}
				setState(246);
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
			setState(248); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(247); 
					re_seq_elem_no_lb();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(250); 
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
			setState(252); 
			re_factor_no_lb();
			setState(254);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(253); 
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
			setState(260);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256); 
				re_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257); 
				re_group();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(258); 
				re_char();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(259); 
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
		enterRule(_localctx, 48, RULE_re_quant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(262); 
				match(T__3);
				}
				break;
			case T__6:
				{
				setState(263); 
				match(T__6);
				}
				break;
			case T__7:
				{
				setState(264); 
				match(T__7);
				}
				break;
			case T__8:
				{
				setState(265); 
				match(T__8);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==INTEGER_STR) {
					{
					{
					setState(266); 
					match(INTEGER_STR);
					}
					}
					setState(271);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(281);
				_la = _input.LA(1);
				if (_la==T__12) {
					{
					setState(272); 
					match(T__12);
					setState(279);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						setState(276);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==INTEGER_STR) {
							{
							{
							setState(273); 
							match(INTEGER_STR);
							}
							}
							setState(278);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
						break;
					}
					}
				}

				setState(283); 
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(287);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(286); 
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
		enterRule(_localctx, 50, RULE_int_seq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289); 
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
		enterRule(_localctx, 52, RULE_esc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291); 
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
		enterRule(_localctx, 54, RULE_group_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); 
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
		enterRule(_localctx, 56, RULE_re_esc_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\34\u012c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2O\n\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3a\n\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4r\n\4\3\5"+
		"\3\5\3\5\3\5\6\5x\n\5\r\5\16\5y\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\5\b\u008a\n\b\3\b\3\b\5\b\u008e\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u009a\n\b\f\b\16\b\u009d\13\b\3\b\3\b\5\b"+
		"\u00a1\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\5\13\u00aa\n\13\3\13\5\13\u00ad"+
		"\n\13\6\13\u00af\n\13\r\13\16\13\u00b0\3\f\3\f\5\f\u00b5\n\f\3\r\3\r\3"+
		"\r\3\r\7\r\u00bb\n\r\f\r\16\r\u00be\13\r\3\16\3\16\3\17\6\17\u00c3\n\17"+
		"\r\17\16\17\u00c4\3\20\3\20\5\20\u00c9\n\20\3\21\3\21\3\21\3\21\5\21\u00cf"+
		"\n\21\3\22\3\22\3\22\5\22\u00d4\n\22\3\23\3\23\5\23\u00d8\n\23\3\23\3"+
		"\23\3\23\3\23\3\23\6\23\u00df\n\23\r\23\16\23\u00e0\3\23\3\23\3\24\3\24"+
		"\3\24\5\24\u00e8\n\24\3\25\3\25\3\25\5\25\u00ed\n\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\7\26\u00f5\n\26\f\26\16\26\u00f8\13\26\3\27\6\27\u00fb\n"+
		"\27\r\27\16\27\u00fc\3\30\3\30\5\30\u0101\n\30\3\31\3\31\3\31\3\31\5\31"+
		"\u0107\n\31\3\32\3\32\3\32\3\32\3\32\7\32\u010e\n\32\f\32\16\32\u0111"+
		"\13\32\3\32\3\32\7\32\u0115\n\32\f\32\16\32\u0118\13\32\5\32\u011a\n\32"+
		"\5\32\u011c\n\32\3\32\5\32\u011f\n\32\3\32\5\32\u0122\n\32\3\33\3\33\3"+
		"\34\3\34\3\35\3\35\3\36\3\36\3\36\4\u00c4\u00fc\2\37\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\3\3\2\22\26\u0165\2N\3"+
		"\2\2\2\4`\3\2\2\2\6q\3\2\2\2\bw\3\2\2\2\n}\3\2\2\2\f\u0085\3\2\2\2\16"+
		"\u00a0\3\2\2\2\20\u00a2\3\2\2\2\22\u00a4\3\2\2\2\24\u00ae\3\2\2\2\26\u00b2"+
		"\3\2\2\2\30\u00b6\3\2\2\2\32\u00bf\3\2\2\2\34\u00c2\3\2\2\2\36\u00c6\3"+
		"\2\2\2 \u00ce\3\2\2\2\"\u00d3\3\2\2\2$\u00d5\3\2\2\2&\u00e7\3\2\2\2(\u00e9"+
		"\3\2\2\2*\u00f0\3\2\2\2,\u00fa\3\2\2\2.\u00fe\3\2\2\2\60\u0106\3\2\2\2"+
		"\62\u011e\3\2\2\2\64\u0123\3\2\2\2\66\u0125\3\2\2\28\u0127\3\2\2\2:\u0129"+
		"\3\2\2\2<O\7\3\2\2=O\7\4\2\2>O\7\5\2\2?O\7\6\2\2@O\7\7\2\2AO\7\b\2\2B"+
		"O\7\6\2\2CO\7\t\2\2DO\7\n\2\2EO\7\13\2\2FO\7\f\2\2GO\7\r\2\2HO\7\16\2"+
		"\2IO\7\17\2\2JO\7\20\2\2KO\7\21\2\2LO\5\f\7\2MO\7\34\2\2N<\3\2\2\2N=\3"+
		"\2\2\2N>\3\2\2\2N?\3\2\2\2N@\3\2\2\2NA\3\2\2\2NB\3\2\2\2NC\3\2\2\2ND\3"+
		"\2\2\2NE\3\2\2\2NF\3\2\2\2NG\3\2\2\2NH\3\2\2\2NI\3\2\2\2NJ\3\2\2\2NK\3"+
		"\2\2\2NL\3\2\2\2NM\3\2\2\2O\3\3\2\2\2Pa\7\5\2\2Qa\7\6\2\2Ra\7\7\2\2Sa"+
		"\7\b\2\2Ta\7\6\2\2Ua\7\t\2\2Va\7\n\2\2Wa\7\13\2\2Xa\7\f\2\2Ya\7\r\2\2"+
		"Za\7\16\2\2[a\7\17\2\2\\a\7\20\2\2]a\7\21\2\2^a\5\f\7\2_a\7\34\2\2`P\3"+
		"\2\2\2`Q\3\2\2\2`R\3\2\2\2`S\3\2\2\2`T\3\2\2\2`U\3\2\2\2`V\3\2\2\2`W\3"+
		"\2\2\2`X\3\2\2\2`Y\3\2\2\2`Z\3\2\2\2`[\3\2\2\2`\\\3\2\2\2`]\3\2\2\2`^"+
		"\3\2\2\2`_\3\2\2\2a\5\3\2\2\2br\7\3\2\2cr\7\4\2\2dr\7\5\2\2er\7\6\2\2"+
		"fr\7\7\2\2gr\7\b\2\2hr\7\6\2\2ir\7\t\2\2jr\7\n\2\2kr\7\13\2\2lr\7\f\2"+
		"\2mr\7\17\2\2nr\7\20\2\2or\5\f\7\2pr\7\34\2\2qb\3\2\2\2qc\3\2\2\2qd\3"+
		"\2\2\2qe\3\2\2\2qf\3\2\2\2qg\3\2\2\2qh\3\2\2\2qi\3\2\2\2qj\3\2\2\2qk\3"+
		"\2\2\2ql\3\2\2\2qm\3\2\2\2qn\3\2\2\2qo\3\2\2\2qp\3\2\2\2r\7\3\2\2\2sx"+
		"\5\n\6\2tx\5\16\b\2ux\5\26\f\2vx\5\30\r\2ws\3\2\2\2wt\3\2\2\2wu\3\2\2"+
		"\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\2\2\3|\t\3\2"+
		"\2\2}~\7\3\2\2~\177\7\6\2\2\177\u0080\7\7\2\2\u0080\u0081\5\f\7\2\u0081"+
		"\u0082\7\b\2\2\u0082\u0083\5\30\r\2\u0083\u0084\7\4\2\2\u0084\13\3\2\2"+
		"\2\u0085\u0086\t\2\2\2\u0086\r\3\2\2\2\u0087\u0089\7\3\2\2\u0088\u008a"+
		"\5\20\t\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2"+
		"\u008b\u008d\5(\25\2\u008c\u008e\5\22\n\2\u008d\u008c\3\2\2\2\u008d\u008e"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\7\4\2\2\u0090\u0091\5\62\32\2"+
		"\u0091\u00a1\3\2\2\2\u0092\u0093\7\3\2\2\u0093\u0094\5,\27\2\u0094\u0095"+
		"\5\32\16\2\u0095\u009b\5,\27\2\u0096\u0097\5\32\16\2\u0097\u0098\5,\27"+
		"\2\u0098\u009a\3\2\2\2\u0099\u0096\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099"+
		"\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u009f\7\4\2\2\u009f\u00a1\3\2\2\2\u00a0\u0087\3\2\2\2\u00a0\u0092\3\2"+
		"\2\2\u00a1\17\3\2\2\2\u00a2\u00a3\5\24\13\2\u00a3\21\3\2\2\2\u00a4\u00a5"+
		"\5\24\13\2\u00a5\23\3\2\2\2\u00a6\u00aa\5$\23\2\u00a7\u00aa\5\"\22\2\u00a8"+
		"\u00aa\5\4\3\2\u00a9\u00a6\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2"+
		"\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00ad\5\62\32\2\u00ac\u00ab\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00a9\3\2\2\2\u00af\u00b0\3\2"+
		"\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\25\3\2\2\2\u00b2\u00b4"+
		"\5$\23\2\u00b3\u00b5\5\62\32\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2"+
		"\u00b5\27\3\2\2\2\u00b6\u00bc\5\34\17\2\u00b7\u00b8\5\32\16\2\u00b8\u00b9"+
		"\5\34\17\2\u00b9\u00bb\3\2\2\2\u00ba\u00b7\3\2\2\2\u00bb\u00be\3\2\2\2"+
		"\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\31\3\2\2\2\u00be\u00bc"+
		"\3\2\2\2\u00bf\u00c0\7\20\2\2\u00c0\33\3\2\2\2\u00c1\u00c3\5\36\20\2\u00c2"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c4\u00c2\3\2"+
		"\2\2\u00c5\35\3\2\2\2\u00c6\u00c8\5 \21\2\u00c7\u00c9\5\62\32\2\u00c8"+
		"\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\37\3\2\2\2\u00ca\u00cf\5$\23"+
		"\2\u00cb\u00cf\5(\25\2\u00cc\u00cf\5\"\22\2\u00cd\u00cf\5\2\2\2\u00ce"+
		"\u00ca\3\2\2\2\u00ce\u00cb\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd\3\2"+
		"\2\2\u00cf!\3\2\2\2\u00d0\u00d4\5:\36\2\u00d1\u00d4\5\64\33\2\u00d2\u00d4"+
		"\58\35\2\u00d3\u00d0\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4"+
		"#\3\2\2\2\u00d5\u00d7\7\r\2\2\u00d6\u00d8\7\5\2\2\u00d7\u00d6\3\2\2\2"+
		"\u00d7\u00d8\3\2\2\2\u00d8\u00de\3\2\2\2\u00d9\u00da\5&\24\2\u00da\u00db"+
		"\7\21\2\2\u00db\u00dc\5&\24\2\u00dc\u00df\3\2\2\2\u00dd\u00df\5&\24\2"+
		"\u00de\u00d9\3\2\2\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de"+
		"\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\7\16\2\2"+
		"\u00e3%\3\2\2\2\u00e4\u00e8\5:\36\2\u00e5\u00e8\5\64\33\2\u00e6\u00e8"+
		"\5\6\4\2\u00e7\u00e4\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8"+
		"\'\3\2\2\2\u00e9\u00ec\7\3\2\2\u00ea\u00ed\5*\26\2\u00eb\u00ed\3\2\2\2"+
		"\u00ec\u00ea\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef"+
		"\7\4\2\2\u00ef)\3\2\2\2\u00f0\u00f6\5,\27\2\u00f1\u00f2\5\32\16\2\u00f2"+
		"\u00f3\5,\27\2\u00f3\u00f5\3\2\2\2\u00f4\u00f1\3\2\2\2\u00f5\u00f8\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7+\3\2\2\2\u00f8\u00f6"+
		"\3\2\2\2\u00f9\u00fb\5.\30\2\u00fa\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd-\3\2\2\2\u00fe\u0100\5\60\31"+
		"\2\u00ff\u0101\5\62\32\2\u0100\u00ff\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"/\3\2\2\2\u0102\u0107\5$\23\2\u0103\u0107\5(\25\2\u0104\u0107\5\"\22\2"+
		"\u0105\u0107\5\4\3\2\u0106\u0102\3\2\2\2\u0106\u0103\3\2\2\2\u0106\u0104"+
		"\3\2\2\2\u0106\u0105\3\2\2\2\u0107\61\3\2\2\2\u0108\u011f\7\6\2\2\u0109"+
		"\u011f\7\t\2\2\u010a\u011f\7\n\2\2\u010b\u010f\7\13\2\2\u010c\u010e\7"+
		"\27\2\2\u010d\u010c\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u011b\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0119\7\17"+
		"\2\2\u0113\u0115\7\27\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116"+
		"\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u011a\3\2\2\2\u0118\u0116\3\2"+
		"\2\2\u0119\u0116\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b"+
		"\u0112\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\7\f"+
		"\2\2\u011e\u0108\3\2\2\2\u011e\u0109\3\2\2\2\u011e\u010a\3\2\2\2\u011e"+
		"\u010b\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u0122\7\6\2\2\u0121\u0120\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\63\3\2\2\2\u0123\u0124\7\27\2\2\u0124\65"+
		"\3\2\2\2\u0125\u0126\7\31\2\2\u0126\67\3\2\2\2\u0127\u0128\7\32\2\2\u0128"+
		"9\3\2\2\2\u0129\u012a\7\33\2\2\u012a;\3\2\2\2#N`qwy\u0089\u008d\u009b"+
		"\u00a0\u00a9\u00ac\u00b0\u00b4\u00bc\u00c4\u00c8\u00ce\u00d3\u00d7\u00de"+
		"\u00e0\u00e7\u00ec\u00f6\u00fc\u0100\u0106\u010f\u0116\u0119\u011b\u011e"+
		"\u0121";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}