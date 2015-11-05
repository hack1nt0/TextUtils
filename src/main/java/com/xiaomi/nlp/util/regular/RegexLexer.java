// Generated from /home/dy/Desktop/TextUtils/src/main/java/com/xiaomi/nlp/util/regular/Regex.g4 by ANTLR 4.5
package com.xiaomi.nlp.util.regular;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegexLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, INTEGER_STR=19, INTEGER=20, ESC=21, GROUP_REF=22, RE_ESC_CHAR=23, 
		WILDCARD=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "INTEGER_STR", "INTEGER", "ESC", "GROUP_REF", "RE_ESC_CHAR", 
		"WILDCARD"
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


	public RegexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Regex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\u008f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\6\24f\n\24\r\24\16\24g\3\25\3\25\3\26\3\26\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30{"+
		"\n\30\3\30\3\30\5\30\177\n\30\3\30\3\30\5\30\u0083\n\30\3\30\3\30\5\30"+
		"\u0087\n\30\3\30\3\30\3\30\5\30\u008c\n\30\3\31\3\31\2\2\32\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\6\3\2\62;\r\2\60\60DDFFUUYYdd"+
		"ffhhpptvyy\4\2CHch\b\2&&*-/\60AA]`}\177\u0096\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2"+
		"\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3\2\2\2\17?\3"+
		"\2\2\2\21A\3\2\2\2\23C\3\2\2\2\25E\3\2\2\2\27G\3\2\2\2\31I\3\2\2\2\33"+
		"K\3\2\2\2\35Q\3\2\2\2\37X\3\2\2\2!^\3\2\2\2#`\3\2\2\2%b\3\2\2\2\'e\3\2"+
		"\2\2)i\3\2\2\2+k\3\2\2\2-m\3\2\2\2/\u008b\3\2\2\2\61\u008d\3\2\2\2\63"+
		"\64\7*\2\2\64\4\3\2\2\2\65\66\7+\2\2\66\6\3\2\2\2\678\7`\2\28\b\3\2\2"+
		"\29:\7A\2\2:\n\3\2\2\2;<\7>\2\2<\f\3\2\2\2=>\7@\2\2>\16\3\2\2\2?@\7,\2"+
		"\2@\20\3\2\2\2AB\7-\2\2B\22\3\2\2\2CD\7\177\2\2D\24\3\2\2\2EF\7]\2\2F"+
		"\26\3\2\2\2GH\7_\2\2H\30\3\2\2\2IJ\7.\2\2J\32\3\2\2\2KL\7v\2\2LM\7k\2"+
		"\2MN\7o\2\2NO\7g\2\2OP\7\62\2\2P\34\3\2\2\2QR\7o\2\2RS\7q\2\2ST\7p\2\2"+
		"TU\7g\2\2UV\7{\2\2VW\7\62\2\2W\36\3\2\2\2XY\7e\2\2YZ\7c\2\2Z[\7t\2\2["+
		"\\\7f\2\2\\]\7\62\2\2] \3\2\2\2^_\7~\2\2_\"\3\2\2\2`a\7/\2\2a$\3\2\2\2"+
		"bc\7}\2\2c&\3\2\2\2df\5)\25\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2"+
		"h(\3\2\2\2ij\t\2\2\2j*\3\2\2\2kl\7^\2\2l,\3\2\2\2mn\5+\26\2no\5\'\24\2"+
		"o.\3\2\2\2pq\5+\26\2qr\5+\26\2r\u008c\3\2\2\2st\5+\26\2tu\t\3\2\2u\u008c"+
		"\3\2\2\2vw\5+\26\2wz\7w\2\2x{\5)\25\2y{\t\4\2\2zx\3\2\2\2zy\3\2\2\2{~"+
		"\3\2\2\2|\177\5)\25\2}\177\t\4\2\2~|\3\2\2\2~}\3\2\2\2\177\u0082\3\2\2"+
		"\2\u0080\u0083\5)\25\2\u0081\u0083\t\4\2\2\u0082\u0080\3\2\2\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0087\5)\25\2\u0085\u0087\t\4\2\2\u0086"+
		"\u0084\3\2\2\2\u0086\u0085\3\2\2\2\u0087\u008c\3\2\2\2\u0088\u0089\5+"+
		"\26\2\u0089\u008a\t\5\2\2\u008a\u008c\3\2\2\2\u008bp\3\2\2\2\u008bs\3"+
		"\2\2\2\u008bv\3\2\2\2\u008b\u0088\3\2\2\2\u008c\60\3\2\2\2\u008d\u008e"+
		"\13\2\2\2\u008e\62\3\2\2\2\t\2gz~\u0082\u0086\u008b\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}