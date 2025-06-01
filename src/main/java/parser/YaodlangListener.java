// Generated from Yaodlang.g4 by ANTLR 4.13.1

package parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YaodlangParser}.
 */
public interface YaodlangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YaodlangParser#yaodfile}.
	 * @param ctx the parse tree
	 */
	void enterYaodfile(YaodlangParser.YaodfileContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaodlangParser#yaodfile}.
	 * @param ctx the parse tree
	 */
	void exitYaodfile(YaodlangParser.YaodfileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code familyHeader}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void enterFamilyHeader(YaodlangParser.FamilyHeaderContext ctx);
	/**
	 * Exit a parse tree produced by the {@code familyHeader}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void exitFamilyHeader(YaodlangParser.FamilyHeaderContext ctx);
	/**
	 * Enter a parse tree produced by the {@code records}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void enterRecords(YaodlangParser.RecordsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code records}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void exitRecords(YaodlangParser.RecordsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code recordName}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void enterRecordName(YaodlangParser.RecordNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code recordName}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void exitRecordName(YaodlangParser.RecordNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code chaLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void enterChaLine(YaodlangParser.ChaLineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code chaLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void exitChaLine(YaodlangParser.ChaLineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code keyLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void enterKeyLine(YaodlangParser.KeyLineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code keyLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void exitKeyLine(YaodlangParser.KeyLineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mitLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void enterMitLine(YaodlangParser.MitLineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mitLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void exitMitLine(YaodlangParser.MitLineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code endRecord}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void enterEndRecord(YaodlangParser.EndRecordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code endRecord}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 */
	void exitEndRecord(YaodlangParser.EndRecordContext ctx);
	/**
	 * Enter a parse tree produced by {@link YaodlangParser#formatList}.
	 * @param ctx the parse tree
	 */
	void enterFormatList(YaodlangParser.FormatListContext ctx);
	/**
	 * Exit a parse tree produced by {@link YaodlangParser#formatList}.
	 * @param ctx the parse tree
	 */
	void exitFormatList(YaodlangParser.FormatListContext ctx);
}