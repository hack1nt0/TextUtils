// Generated from /Users/DY/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/regex.g4 by ANTLR 4.5.1
package com.xiaomi.nlp.util.regular;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class regexLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, INTEGER_STR=21, INTEGER=22, ESC=23, GROUP_REF=24, 
		RE_ESC_CHAR=25, WILDCARD=26;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "INTEGER_STR", "INTEGER", "ESC", "GROUP_REF", 
		"RE_ESC_CHAR", "WILDCARD"
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


	public regexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "regex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\34\u009c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\26\6\26q\n\26\r\26\16\26r\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0086\n\32\3\32\3\32"+
		"\5\32\u008a\n\32\3\32\3\32\5\32\u008e\n\32\3\32\3\32\5\32\u0092\n\32\3"+
		"\32\3\32\3\32\5\32\u0097\n\32\3\33\3\33\5\33\u009b\n\33\2\2\34\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\6\3\2\62;\r\2\60\60"+
		"DDFFUUYYddffhhpptvyy\4\2CHch\b\2&&*-/\60AA]`}\177\u00a4\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\59\3\2\2\2\7;\3\2\2\2\t=\3\2\2"+
		"\2\13?\3\2\2\2\rA\3\2\2\2\17C\3\2\2\2\21E\3\2\2\2\23G\3\2\2\2\25I\3\2"+
		"\2\2\27K\3\2\2\2\31M\3\2\2\2\33Q\3\2\2\2\35T\3\2\2\2\37V\3\2\2\2!\\\3"+
		"\2\2\2#c\3\2\2\2%i\3\2\2\2\'k\3\2\2\2)m\3\2\2\2+p\3\2\2\2-t\3\2\2\2/v"+
		"\3\2\2\2\61x\3\2\2\2\63\u0096\3\2\2\2\65\u009a\3\2\2\2\678\7+\2\28\4\3"+
		"\2\2\29:\7`\2\2:\6\3\2\2\2;<\7A\2\2<\b\3\2\2\2=>\7>\2\2>\n\3\2\2\2?@\7"+
		"@\2\2@\f\3\2\2\2AB\7,\2\2B\16\3\2\2\2CD\7-\2\2D\20\3\2\2\2EF\7\177\2\2"+
		"F\22\3\2\2\2GH\7]\2\2H\24\3\2\2\2IJ\7_\2\2J\26\3\2\2\2KL\7.\2\2L\30\3"+
		"\2\2\2MN\7e\2\2NO\7c\2\2OP\7v\2\2P\32\3\2\2\2QR\7e\2\2RS\7c\2\2S\34\3"+
		"\2\2\2TU\7*\2\2U\36\3\2\2\2VW\7v\2\2WX\7k\2\2XY\7o\2\2YZ\7g\2\2Z[\7\62"+
		"\2\2[ \3\2\2\2\\]\7o\2\2]^\7q\2\2^_\7p\2\2_`\7g\2\2`a\7{\2\2ab\7\62\2"+
		"\2b\"\3\2\2\2cd\7e\2\2de\7c\2\2ef\7t\2\2fg\7f\2\2gh\7\62\2\2h$\3\2\2\2"+
		"ij\7~\2\2j&\3\2\2\2kl\7/\2\2l(\3\2\2\2mn\7}\2\2n*\3\2\2\2oq\5-\27\2po"+
		"\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s,\3\2\2\2tu\t\2\2\2u.\3\2\2\2v"+
		"w\7^\2\2w\60\3\2\2\2xy\5/\30\2yz\5+\26\2z\62\3\2\2\2{|\5/\30\2|}\5/\30"+
		"\2}\u0097\3\2\2\2~\177\5/\30\2\177\u0080\t\3\2\2\u0080\u0097\3\2\2\2\u0081"+
		"\u0082\5/\30\2\u0082\u0085\7w\2\2\u0083\u0086\5-\27\2\u0084\u0086\t\4"+
		"\2\2\u0085\u0083\3\2\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u008a\5-\27\2\u0088\u008a\t\4\2\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2"+
		"\2\2\u008a\u008d\3\2\2\2\u008b\u008e\5-\27\2\u008c\u008e\t\4\2\2\u008d"+
		"\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u0092\5-"+
		"\27\2\u0090\u0092\t\4\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0097\3\2\2\2\u0093\u0094\5/\30\2\u0094\u0095\t\5\2\2\u0095\u0097\3\2"+
		"\2\2\u0096{\3\2\2\2\u0096~\3\2\2\2\u0096\u0081\3\2\2\2\u0096\u0093\3\2"+
		"\2\2\u0097\64\3\2\2\2\u0098\u009b\7*\2\2\u0099\u009b\13\2\2\2\u009a\u0098"+
		"\3\2\2\2\u009a\u0099\3\2\2\2\u009b\66\3\2\2\2\n\2r\u0085\u0089\u008d\u0091"+
		"\u0096\u009a\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}