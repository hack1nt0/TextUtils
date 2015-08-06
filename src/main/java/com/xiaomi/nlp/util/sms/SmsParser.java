// Generated from /home/dy/Desktop/newtech-smsunderstandoffline/src/main/java/com/xiaomi/dy/parser/sms/Sms.g4 by ANTLR 4.5
package com.xiaomi.nlp.util.sms;

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
public class SmsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, DELIMITER=2, TAG=3, FULLSUCHAS=4, SUCHAS=5, COLON=6, NUMBER=7, DIGIT=8, 
		BULLET=9, DIGITINBRACKET=10, LEFTBRACKET=11, RIGHTBRACKET=12, WILDCARD=13;
	public static final int
		RULE_s = 0, RULE_smsLinear = 1, RULE_colonSent = 2, RULE_bullet = 3, RULE_delimiter = 4, 
		RULE_bulletSent = 5, RULE_ordSent = 6, RULE_colonToken = 7, RULE_tokens = 8, 
		RULE_token = 9, RULE_bracket = 10;
	public static final String[] ruleNames = {
		"s", "smsLinear", "colonSent", "bullet", "delimiter", "bulletSent", "ordSent", 
		"colonToken", "tokens", "token", "bracket"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'情况分别为'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "DELIMITER", "TAG", "FULLSUCHAS", "SUCHAS", "COLON", "NUMBER", 
		"DIGIT", "BULLET", "DIGITINBRACKET", "LEFTBRACKET", "RIGHTBRACKET", "WILDCARD"
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
	public String getGrammarFileName() { return "Sms.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	    int preBulletNum;
	    int curBulletNum;

	    static int bullet2Int(String bullet) {
	        int p = 0, q = bullet.length() - 1;
	        while (!Character.isDigit(bullet.charAt(p))) ++p;
	        while (!Character.isDigit(bullet.charAt(q))) --q;
	        int res = 0;
	        for (int i = p; i <= q; ++i) res = res * 10 + bullet.charAt(i) - '0';
	        return res;
	    }

	    static boolean isameBulletStyle(String a, String b) {
	        a = a.trim(); b = b.trim();
	        if (a.length() != b.length()) return false;
	        for (int i = 0; i < a.length(); ++i)
	            if (a.charAt(i) != b.charAt(i) && !Character.isDigit(a.charAt(i)))
	                return false;
	        return true;
	    }

	public SmsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SContext extends ParserRuleContext {
		public SmsLinearContext smsLinear() {
			return getRuleContext(SmsLinearContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SmsParser.EOF, 0); }
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitS(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); 
			smsLinear();
			setState(23); 
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

	public static class SmsLinearContext extends ParserRuleContext {
		public List<BulletSentContext> bulletSent() {
			return getRuleContexts(BulletSentContext.class);
		}
		public BulletSentContext bulletSent(int i) {
			return getRuleContext(BulletSentContext.class,i);
		}
		public List<ColonSentContext> colonSent() {
			return getRuleContexts(ColonSentContext.class);
		}
		public ColonSentContext colonSent(int i) {
			return getRuleContext(ColonSentContext.class,i);
		}
		public List<OrdSentContext> ordSent() {
			return getRuleContexts(OrdSentContext.class);
		}
		public OrdSentContext ordSent(int i) {
			return getRuleContext(OrdSentContext.class,i);
		}
		public SmsLinearContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_smsLinear; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterSmsLinear(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitSmsLinear(this);
		}
	}

	public final SmsLinearContext smsLinear() throws RecognitionException {
		SmsLinearContext _localctx = new SmsLinearContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_smsLinear);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					setState(28);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(25);
						bulletSent();
						}
						break;
					case 2:
						{
						setState(26);
						colonSent();
						}
						break;
					case 3:
						{
						setState(27);
						ordSent();
						}
						break;
					}
					}
				}
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class ColonSentContext extends ParserRuleContext {
		public ColonTokenContext colonToken() {
			return getRuleContext(ColonTokenContext.class,0);
		}
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public ColonSentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colonSent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterColonSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitColonSent(this);
		}
	}

	public final ColonSentContext colonSent() throws RecognitionException {
		ColonSentContext _localctx = new ColonSentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_colonSent);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(33);
					token();
					}
					}
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(39);
			colonToken();
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

	public static class BulletContext extends ParserRuleContext {
		public TerminalNode BULLET() { return getToken(SmsParser.BULLET, 0); }
		public BulletContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bullet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterBullet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitBullet(this);
		}
	}

	public final BulletContext bullet() throws RecognitionException {
		BulletContext _localctx = new BulletContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bullet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(BULLET);
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

	public static class DelimiterContext extends ParserRuleContext {
		public TerminalNode DELIMITER() { return getToken(SmsParser.DELIMITER, 0); }
		public DelimiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delimiter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterDelimiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitDelimiter(this);
		}
	}

	public final DelimiterContext delimiter() throws RecognitionException {
		DelimiterContext _localctx = new DelimiterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_delimiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(DELIMITER);
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

	public static class BulletSentContext extends ParserRuleContext {
		public BulletContext bullet() {
			return getRuleContext(BulletContext.class,0);
		}
		public DelimiterContext delimiter() {
			return getRuleContext(DelimiterContext.class,0);
		}
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public BulletSentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bulletSent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterBulletSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitBulletSent(this);
		}
	}

	public final BulletSentContext bulletSent() throws RecognitionException {
		BulletSentContext _localctx = new BulletSentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bulletSent);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			bullet();
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(46);
					token();
					}
					}
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(52);
			delimiter();
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

	public static class OrdSentContext extends ParserRuleContext {
		public DelimiterContext delimiter() {
			return getRuleContext(DelimiterContext.class,0);
		}
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public OrdSentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordSent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterOrdSent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitOrdSent(this);
		}
	}

	public final OrdSentContext ordSent() throws RecognitionException {
		OrdSentContext _localctx = new OrdSentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ordSent);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(54); 
					token();
					}
					} 
				}
				setState(59);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(60); 
			delimiter();
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

	public static class ColonTokenContext extends ParserRuleContext {
		public TerminalNode SUCHAS() { return getToken(SmsParser.SUCHAS, 0); }
		public TerminalNode COLON() { return getToken(SmsParser.COLON, 0); }
		public TerminalNode FULLSUCHAS() { return getToken(SmsParser.FULLSUCHAS, 0); }
		public ColonTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colonToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterColonToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitColonToken(this);
		}
	}

	public final ColonTokenContext colonToken() throws RecognitionException {
		ColonTokenContext _localctx = new ColonTokenContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_colonToken);
		try {
			setState(66);
			switch (_input.LA(1)) {
			case SUCHAS:
				enterOuterAlt(_localctx, 1);
				{
				setState(62); 
				match(SUCHAS);
				setState(63); 
				match(COLON);
				}
				break;
			case FULLSUCHAS:
				enterOuterAlt(_localctx, 2);
				{
				setState(64); 
				match(FULLSUCHAS);
				}
				break;
			case COLON:
				enterOuterAlt(_localctx, 3);
				{
				setState(65); 
				match(COLON);
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

	public static class TokensContext extends ParserRuleContext {
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public TokensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tokens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterTokens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitTokens(this);
		}
	}

	public final TokensContext tokens() throws RecognitionException {
		TokensContext _localctx = new TokensContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tokens);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68); 
				token();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WS) | (1L << TAG) | (1L << SUCHAS) | (1L << NUMBER) | (1L << BULLET) | (1L << LEFTBRACKET) | (1L << RIGHTBRACKET) | (1L << WILDCARD))) != 0) );
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

	public static class TokenContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(SmsParser.WS, 0); }
		public TerminalNode TAG() { return getToken(SmsParser.TAG, 0); }
		public TerminalNode SUCHAS() { return getToken(SmsParser.SUCHAS, 0); }
		public TerminalNode BULLET() { return getToken(SmsParser.BULLET, 0); }
		public TerminalNode NUMBER() { return getToken(SmsParser.NUMBER, 0); }
		public BracketContext bracket() {
			return getRuleContext(BracketContext.class,0);
		}
		public TerminalNode WILDCARD() { return getToken(SmsParser.WILDCARD, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitToken(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_token);
		try {
			setState(80);
			switch (_input.LA(1)) {
			case WS:
				enterOuterAlt(_localctx, 1);
				{
				setState(73); 
				match(WS);
				}
				break;
			case TAG:
				enterOuterAlt(_localctx, 2);
				{
				setState(74); 
				match(TAG);
				}
				break;
			case SUCHAS:
				enterOuterAlt(_localctx, 3);
				{
				setState(75); 
				match(SUCHAS);
				}
				break;
			case BULLET:
				enterOuterAlt(_localctx, 4);
				{
				setState(76); 
				match(BULLET);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 5);
				{
				setState(77); 
				match(NUMBER);
				}
				break;
			case LEFTBRACKET:
			case RIGHTBRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(78); 
				bracket();
				}
				break;
			case WILDCARD:
				enterOuterAlt(_localctx, 7);
				{
				setState(79); 
				match(WILDCARD);
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

	public static class BracketContext extends ParserRuleContext {
		public TerminalNode LEFTBRACKET() { return getToken(SmsParser.LEFTBRACKET, 0); }
		public TerminalNode RIGHTBRACKET() { return getToken(SmsParser.RIGHTBRACKET, 0); }
		public BracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).enterBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmsListener ) ((SmsListener)listener).exitBracket(this);
		}
	}

	public final BracketContext bracket() throws RecognitionException {
		BracketContext _localctx = new BracketContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bracket);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_la = _input.LA(1);
			if ( !(_la==LEFTBRACKET || _la==RIGHTBRACKET) ) {
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17W\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\3\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3\3\4\7\4%\n\4"+
		"\f\4\16\4(\13\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\7\7\62\n\7\f\7\16\7\65"+
		"\13\7\3\7\3\7\3\b\7\b:\n\b\f\b\16\b=\13\b\3\b\3\b\3\t\3\t\3\t\3\t\5\t"+
		"E\n\t\3\n\6\nH\n\n\r\n\16\nI\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13S"+
		"\n\13\3\f\3\f\3\f\6 &\63;\2\r\2\4\6\b\n\f\16\20\22\24\26\2\3\3\2\r\16"+
		"Z\2\30\3\2\2\2\4 \3\2\2\2\6&\3\2\2\2\b+\3\2\2\2\n-\3\2\2\2\f/\3\2\2\2"+
		"\16;\3\2\2\2\20D\3\2\2\2\22G\3\2\2\2\24R\3\2\2\2\26T\3\2\2\2\30\31\5\4"+
		"\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33\37\5\f\7\2\34\37\5\6\4\2\35\37\5\16"+
		"\b\2\36\33\3\2\2\2\36\34\3\2\2\2\36\35\3\2\2\2\37\"\3\2\2\2 !\3\2\2\2"+
		" \36\3\2\2\2!\5\3\2\2\2\" \3\2\2\2#%\5\24\13\2$#\3\2\2\2%(\3\2\2\2&\'"+
		"\3\2\2\2&$\3\2\2\2\')\3\2\2\2(&\3\2\2\2)*\5\20\t\2*\7\3\2\2\2+,\7\13\2"+
		"\2,\t\3\2\2\2-.\7\4\2\2.\13\3\2\2\2/\63\5\b\5\2\60\62\5\24\13\2\61\60"+
		"\3\2\2\2\62\65\3\2\2\2\63\64\3\2\2\2\63\61\3\2\2\2\64\66\3\2\2\2\65\63"+
		"\3\2\2\2\66\67\5\n\6\2\67\r\3\2\2\28:\5\24\13\298\3\2\2\2:=\3\2\2\2;<"+
		"\3\2\2\2;9\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\5\n\6\2?\17\3\2\2\2@A\7\7\2\2"+
		"AE\7\b\2\2BE\7\6\2\2CE\7\b\2\2D@\3\2\2\2DB\3\2\2\2DC\3\2\2\2E\21\3\2\2"+
		"\2FH\5\24\13\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\23\3\2\2\2KS\7"+
		"\3\2\2LS\7\5\2\2MS\7\7\2\2NS\7\13\2\2OS\7\t\2\2PS\5\26\f\2QS\7\17\2\2"+
		"RK\3\2\2\2RL\3\2\2\2RM\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2"+
		"S\25\3\2\2\2TU\t\2\2\2U\27\3\2\2\2\n\36 &\63;DIR";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}