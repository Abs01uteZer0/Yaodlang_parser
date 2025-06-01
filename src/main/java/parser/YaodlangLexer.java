// Generated from Yaodlang.g4 by ANTLR 4.13.1

package parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class YaodlangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, ID=12, FORMAT=13, NUMBER=14, LINE_COMMENT=15, NL=16, 
		WS=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "ID", "FORMAT", "NUMBER", "LINE_COMMENT", "NL", "LETTER", 
			"DIGIT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'FAMILY'", "';'", "'RECORDS;'", "'RECORD'", "'CHA'", "'('", "')'", 
			"'NA'", "'KEY'", "'MIT'", "'END'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"ID", "FORMAT", "NUMBER", "LINE_COMMENT", "NL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public YaodlangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Yaodlang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0011\u0088\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0005\u000b[\b\u000b\n\u000b\f\u000b^\t\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\fe\b\f\u0001\f\u0001\f\u0001\r\u0004"+
		"\rj\b\r\u000b\r\f\rk\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005"+
		"\u000er\b\u000e\n\u000e\f\u000eu\t\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0003\u000fz\b\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0004\u0012\u0083\b\u0012\u000b"+
		"\u0012\f\u0012\u0084\u0001\u0012\u0001\u0012\u0000\u0000\u0013\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010!\u0000#\u0000%\u0011\u0001\u0000\u0004\u0002\u0000\n\n\r"+
		"\r\b\u0000AZ__az\u0401\u0401\u0410\u0418\u041a\u0438\u043a\u044f\u0451"+
		"\u0451\u0001\u000009\u0002\u0000\t\t  \u008c\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0001\'"+
		"\u0001\u0000\u0000\u0000\u0003.\u0001\u0000\u0000\u0000\u00050\u0001\u0000"+
		"\u0000\u0000\u00079\u0001\u0000\u0000\u0000\t@\u0001\u0000\u0000\u0000"+
		"\u000bD\u0001\u0000\u0000\u0000\rF\u0001\u0000\u0000\u0000\u000fH\u0001"+
		"\u0000\u0000\u0000\u0011K\u0001\u0000\u0000\u0000\u0013O\u0001\u0000\u0000"+
		"\u0000\u0015S\u0001\u0000\u0000\u0000\u0017W\u0001\u0000\u0000\u0000\u0019"+
		"_\u0001\u0000\u0000\u0000\u001bi\u0001\u0000\u0000\u0000\u001dm\u0001"+
		"\u0000\u0000\u0000\u001fy\u0001\u0000\u0000\u0000!}\u0001\u0000\u0000"+
		"\u0000#\u007f\u0001\u0000\u0000\u0000%\u0082\u0001\u0000\u0000\u0000\'"+
		"(\u0005F\u0000\u0000()\u0005A\u0000\u0000)*\u0005M\u0000\u0000*+\u0005"+
		"I\u0000\u0000+,\u0005L\u0000\u0000,-\u0005Y\u0000\u0000-\u0002\u0001\u0000"+
		"\u0000\u0000./\u0005;\u0000\u0000/\u0004\u0001\u0000\u0000\u000001\u0005"+
		"R\u0000\u000012\u0005E\u0000\u000023\u0005C\u0000\u000034\u0005O\u0000"+
		"\u000045\u0005R\u0000\u000056\u0005D\u0000\u000067\u0005S\u0000\u0000"+
		"78\u0005;\u0000\u00008\u0006\u0001\u0000\u0000\u00009:\u0005R\u0000\u0000"+
		":;\u0005E\u0000\u0000;<\u0005C\u0000\u0000<=\u0005O\u0000\u0000=>\u0005"+
		"R\u0000\u0000>?\u0005D\u0000\u0000?\b\u0001\u0000\u0000\u0000@A\u0005"+
		"C\u0000\u0000AB\u0005H\u0000\u0000BC\u0005A\u0000\u0000C\n\u0001\u0000"+
		"\u0000\u0000DE\u0005(\u0000\u0000E\f\u0001\u0000\u0000\u0000FG\u0005)"+
		"\u0000\u0000G\u000e\u0001\u0000\u0000\u0000HI\u0005N\u0000\u0000IJ\u0005"+
		"A\u0000\u0000J\u0010\u0001\u0000\u0000\u0000KL\u0005K\u0000\u0000LM\u0005"+
		"E\u0000\u0000MN\u0005Y\u0000\u0000N\u0012\u0001\u0000\u0000\u0000OP\u0005"+
		"M\u0000\u0000PQ\u0005I\u0000\u0000QR\u0005T\u0000\u0000R\u0014\u0001\u0000"+
		"\u0000\u0000ST\u0005E\u0000\u0000TU\u0005N\u0000\u0000UV\u0005D\u0000"+
		"\u0000V\u0016\u0001\u0000\u0000\u0000W\\\u0003!\u0010\u0000X[\u0003!\u0010"+
		"\u0000Y[\u0003#\u0011\u0000ZX\u0001\u0000\u0000\u0000ZY\u0001\u0000\u0000"+
		"\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]\u0018\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000"+
		"_`\u0003\u0017\u000b\u0000`a\u0005(\u0000\u0000ad\u0003\u001b\r\u0000"+
		"bc\u0005,\u0000\u0000ce\u0003\u001b\r\u0000db\u0001\u0000\u0000\u0000"+
		"de\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0005)\u0000\u0000"+
		"g\u001a\u0001\u0000\u0000\u0000hj\u0003#\u0011\u0000ih\u0001\u0000\u0000"+
		"\u0000jk\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000"+
		"\u0000\u0000l\u001c\u0001\u0000\u0000\u0000mn\u0005/\u0000\u0000no\u0005"+
		"/\u0000\u0000os\u0001\u0000\u0000\u0000pr\b\u0000\u0000\u0000qp\u0001"+
		"\u0000\u0000\u0000ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000"+
		"st\u0001\u0000\u0000\u0000tv\u0001\u0000\u0000\u0000us\u0001\u0000\u0000"+
		"\u0000vw\u0003\u001f\u000f\u0000w\u001e\u0001\u0000\u0000\u0000xz\u0005"+
		"\r\u0000\u0000yx\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0001"+
		"\u0000\u0000\u0000{|\u0005\n\u0000\u0000| \u0001\u0000\u0000\u0000}~\u0007"+
		"\u0001\u0000\u0000~\"\u0001\u0000\u0000\u0000\u007f\u0080\u0007\u0002"+
		"\u0000\u0000\u0080$\u0001\u0000\u0000\u0000\u0081\u0083\u0007\u0003\u0000"+
		"\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000"+
		"\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0006\u0012\u0000"+
		"\u0000\u0087&\u0001\u0000\u0000\u0000\b\u0000Z\\dksy\u0084\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}