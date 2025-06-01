// Generated from Yaodlang.g4 by ANTLR 4.13.1

package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link YaodlangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface YaodlangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link YaodlangParser#yaodfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYaodfile(YaodlangParser.YaodfileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code familyHeader}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFamilyHeader(YaodlangParser.FamilyHeaderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code records}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecords(YaodlangParser.RecordsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code recordName}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordName(YaodlangParser.RecordNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code chaLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChaLine(YaodlangParser.ChaLineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code keyLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyLine(YaodlangParser.KeyLineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mitLine}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMitLine(YaodlangParser.MitLineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code endRecord}
	 * labeled alternative in {@link YaodlangParser#yaodline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndRecord(YaodlangParser.EndRecordContext ctx);
	/**
	 * Visit a parse tree produced by {@link YaodlangParser#formatList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatList(YaodlangParser.FormatListContext ctx);
}