// Generated from Yaodlang.g4 by ANTLR 4.13.1

package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class YaodlangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, ID=12, FORMAT=13, NUMBER=14, LINE_COMMENT=15, NL=16, 
		WS=17;
	public static final int
		RULE_yaodfile = 0, RULE_yaodline = 1, RULE_formatList = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"yaodfile", "yaodline", "formatList"
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

	@Override
	public String getGrammarFileName() { return "Yaodlang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public YaodlangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class YaodfileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(YaodlangParser.EOF, 0); }
		public List<YaodlineContext> yaodline() {
			return getRuleContexts(YaodlineContext.class);
		}
		public YaodlineContext yaodline(int i) {
			return getRuleContext(YaodlineContext.class,i);
		}
		public List<TerminalNode> LINE_COMMENT() { return getTokens(YaodlangParser.LINE_COMMENT); }
		public TerminalNode LINE_COMMENT(int i) {
			return getToken(YaodlangParser.LINE_COMMENT, i);
		}
		public List<TerminalNode> NL() { return getTokens(YaodlangParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(YaodlangParser.NL, i);
		}
		public YaodfileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yaodfile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterYaodfile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitYaodfile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitYaodfile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YaodfileContext yaodfile() throws RecognitionException {
		YaodfileContext _localctx = new YaodfileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_yaodfile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 101946L) != 0)) {
				{
				setState(9);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case T__2:
				case T__3:
				case T__4:
				case T__8:
				case T__9:
				case T__10:
					{
					setState(6);
					yaodline();
					}
					break;
				case LINE_COMMENT:
					{
					setState(7);
					match(LINE_COMMENT);
					}
					break;
				case NL:
					{
					setState(8);
					match(NL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(14);
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

	@SuppressWarnings("CheckReturnValue")
	public static class YaodlineContext extends ParserRuleContext {
		public YaodlineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yaodline; }
	 
		public YaodlineContext() { }
		public void copyFrom(YaodlineContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FamilyHeaderContext extends YaodlineContext {
		public Token family;
		public Token format;
		public TerminalNode ID() { return getToken(YaodlangParser.ID, 0); }
		public TerminalNode FORMAT() { return getToken(YaodlangParser.FORMAT, 0); }
		public FamilyHeaderContext(YaodlineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterFamilyHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitFamilyHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitFamilyHeader(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KeyLineContext extends YaodlineContext {
		public Token keyid;
		public Token id;
		public FormatListContext formatList() {
			return getRuleContext(FormatListContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(YaodlangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(YaodlangParser.ID, i);
		}
		public KeyLineContext(YaodlineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterKeyLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitKeyLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitKeyLine(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RecordsContext extends YaodlineContext {
		public RecordsContext(YaodlineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterRecords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitRecords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitRecords(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RecordNameContext extends YaodlineContext {
		public Token recordname;
		public TerminalNode ID() { return getToken(YaodlangParser.ID, 0); }
		public RecordNameContext(YaodlineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterRecordName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitRecordName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitRecordName(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MitLineContext extends YaodlineContext {
		public Token id;
		public FormatListContext formatList() {
			return getRuleContext(FormatListContext.class,0);
		}
		public TerminalNode ID() { return getToken(YaodlangParser.ID, 0); }
		public MitLineContext(YaodlineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterMitLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitMitLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitMitLine(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EndRecordContext extends YaodlineContext {
		public TerminalNode ID() { return getToken(YaodlangParser.ID, 0); }
		public EndRecordContext(YaodlineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterEndRecord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitEndRecord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitEndRecord(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ChaLineContext extends YaodlineContext {
		public Token chid;
		public Token id;
		public Token na;
		public FormatListContext formatList() {
			return getRuleContext(FormatListContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(YaodlangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(YaodlangParser.ID, i);
		}
		public ChaLineContext(YaodlineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterChaLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitChaLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitChaLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YaodlineContext yaodline() throws RecognitionException {
		YaodlineContext _localctx = new YaodlineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_yaodline);
		int _la;
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new FamilyHeaderContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				match(T__0);
				setState(17);
				((FamilyHeaderContext)_localctx).family = match(ID);
				setState(18);
				((FamilyHeaderContext)_localctx).format = match(FORMAT);
				setState(19);
				match(T__1);
				}
				break;
			case T__2:
				_localctx = new RecordsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				match(T__2);
				}
				break;
			case T__3:
				_localctx = new RecordNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(21);
				match(T__3);
				setState(22);
				((RecordNameContext)_localctx).recordname = match(ID);
				setState(23);
				match(T__1);
				}
				break;
			case T__4:
				_localctx = new ChaLineContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(24);
				match(T__4);
				setState(25);
				match(T__5);
				setState(26);
				((ChaLineContext)_localctx).chid = match(ID);
				setState(27);
				match(T__6);
				setState(28);
				((ChaLineContext)_localctx).id = match(ID);
				setState(29);
				formatList();
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(30);
					((ChaLineContext)_localctx).na = match(T__7);
					}
				}

				setState(33);
				match(T__1);
				}
				break;
			case T__8:
				_localctx = new KeyLineContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(35);
				match(T__8);
				setState(36);
				match(T__5);
				setState(37);
				((KeyLineContext)_localctx).keyid = match(ID);
				setState(38);
				match(T__6);
				setState(39);
				((KeyLineContext)_localctx).id = match(ID);
				setState(40);
				formatList();
				setState(41);
				match(T__1);
				}
				break;
			case T__9:
				_localctx = new MitLineContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(43);
				match(T__9);
				setState(44);
				((MitLineContext)_localctx).id = match(ID);
				setState(45);
				formatList();
				setState(46);
				match(T__1);
				}
				break;
			case T__10:
				_localctx = new EndRecordContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(48);
				match(T__10);
				setState(49);
				match(ID);
				setState(50);
				match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FormatListContext extends ParserRuleContext {
		public List<TerminalNode> FORMAT() { return getTokens(YaodlangParser.FORMAT); }
		public TerminalNode FORMAT(int i) {
			return getToken(YaodlangParser.FORMAT, i);
		}
		public FormatListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).enterFormatList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YaodlangListener ) ((YaodlangListener)listener).exitFormatList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YaodlangVisitor ) return ((YaodlangVisitor<? extends T>)visitor).visitFormatList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormatListContext formatList() throws RecognitionException {
		FormatListContext _localctx = new FormatListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_formatList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(53);
				match(FORMAT);
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==FORMAT );
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
		"\u0004\u0001\u0011;\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000\n\b"+
		"\u0000\n\u0000\f\u0000\r\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001 \b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u00014\b\u0001\u0001"+
		"\u0002\u0004\u00027\b\u0002\u000b\u0002\f\u00028\u0001\u0002\u0000\u0000"+
		"\u0003\u0000\u0002\u0004\u0000\u0000B\u0000\u000b\u0001\u0000\u0000\u0000"+
		"\u00023\u0001\u0000\u0000\u0000\u00046\u0001\u0000\u0000\u0000\u0006\n"+
		"\u0003\u0002\u0001\u0000\u0007\n\u0005\u000f\u0000\u0000\b\n\u0005\u0010"+
		"\u0000\u0000\t\u0006\u0001\u0000\u0000\u0000\t\u0007\u0001\u0000\u0000"+
		"\u0000\t\b\u0001\u0000\u0000\u0000\n\r\u0001\u0000\u0000\u0000\u000b\t"+
		"\u0001\u0000\u0000\u0000\u000b\f\u0001\u0000\u0000\u0000\f\u000e\u0001"+
		"\u0000\u0000\u0000\r\u000b\u0001\u0000\u0000\u0000\u000e\u000f\u0005\u0000"+
		"\u0000\u0001\u000f\u0001\u0001\u0000\u0000\u0000\u0010\u0011\u0005\u0001"+
		"\u0000\u0000\u0011\u0012\u0005\f\u0000\u0000\u0012\u0013\u0005\r\u0000"+
		"\u0000\u00134\u0005\u0002\u0000\u0000\u00144\u0005\u0003\u0000\u0000\u0015"+
		"\u0016\u0005\u0004\u0000\u0000\u0016\u0017\u0005\f\u0000\u0000\u00174"+
		"\u0005\u0002\u0000\u0000\u0018\u0019\u0005\u0005\u0000\u0000\u0019\u001a"+
		"\u0005\u0006\u0000\u0000\u001a\u001b\u0005\f\u0000\u0000\u001b\u001c\u0005"+
		"\u0007\u0000\u0000\u001c\u001d\u0005\f\u0000\u0000\u001d\u001f\u0003\u0004"+
		"\u0002\u0000\u001e \u0005\b\u0000\u0000\u001f\u001e\u0001\u0000\u0000"+
		"\u0000\u001f \u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\"\u0005"+
		"\u0002\u0000\u0000\"4\u0001\u0000\u0000\u0000#$\u0005\t\u0000\u0000$%"+
		"\u0005\u0006\u0000\u0000%&\u0005\f\u0000\u0000&\'\u0005\u0007\u0000\u0000"+
		"\'(\u0005\f\u0000\u0000()\u0003\u0004\u0002\u0000)*\u0005\u0002\u0000"+
		"\u0000*4\u0001\u0000\u0000\u0000+,\u0005\n\u0000\u0000,-\u0005\f\u0000"+
		"\u0000-.\u0003\u0004\u0002\u0000./\u0005\u0002\u0000\u0000/4\u0001\u0000"+
		"\u0000\u000001\u0005\u000b\u0000\u000012\u0005\f\u0000\u000024\u0005\u0002"+
		"\u0000\u00003\u0010\u0001\u0000\u0000\u00003\u0014\u0001\u0000\u0000\u0000"+
		"3\u0015\u0001\u0000\u0000\u00003\u0018\u0001\u0000\u0000\u00003#\u0001"+
		"\u0000\u0000\u00003+\u0001\u0000\u0000\u000030\u0001\u0000\u0000\u0000"+
		"4\u0003\u0001\u0000\u0000\u000057\u0005\r\u0000\u000065\u0001\u0000\u0000"+
		"\u000078\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u000089\u0001\u0000"+
		"\u0000\u00009\u0005\u0001\u0000\u0000\u0000\u0005\t\u000b\u001f38";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}