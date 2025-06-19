package com.andreypshenichnyj.iate.visitor;

import com.andreypshenichnyj.iate.visitor.dto.FieldDTO;
import com.andreypshenichnyj.iate.visitor.dto.Format;
import com.andreypshenichnyj.iate.visitor.enums.FormatType;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import parser.YaodlangBaseVisitor;
import parser.YaodlangParser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DdlVisitor extends YaodlangBaseVisitor<Void> {

    private final Map<String, FieldDTO> fieldsMap = new LinkedHashMap<>();

    private String lastComment = null;

    private String familyName;

    private String familyFormat;

    public List<FieldDTO> getFields() {
        return new ArrayList<>(fieldsMap.values());
    }

    @Override
    public Void visitFamilyHeader(YaodlangParser.FamilyHeaderContext ctx) {
        familyName = ctx.ID().getText();
        familyFormat = ctx.FORMAT().getText();
        return null;
    }

    @Override
    public Void visitRecords(YaodlangParser.RecordsContext ctx) {
        return null;
    }

    @Override
    public Void visitRecordName(YaodlangParser.RecordNameContext ctx) {
        return null;
    }

    @Override
    public Void visitChaLine(YaodlangParser.ChaLineContext ctx) {
        addField(ctx.id.getText(), ctx.formatList().FORMAT());
        return null;
    }

    @Override
    public Void visitKeyLine(YaodlangParser.KeyLineContext ctx) {
        addField(ctx.id.getText(), ctx.formatList().FORMAT());
        return null;
    }

    @Override
    public Void visitMitLine(YaodlangParser.MitLineContext ctx) {
        addField(ctx.id.getText(), ctx.formatList().FORMAT());
        return null;
    }

    @Override
    public Void visitEndRecord(YaodlangParser.EndRecordContext ctx) {
        return null;
    }

    @Override
    public Void visitYaodfile(YaodlangParser.YaodfileContext ctx) {
        for (int i = 0; i < ctx.children.size(); i++) {
            var child = ctx.children.get(i);

            if (child instanceof TerminalNode terminal && terminal.getSymbol().getType() == YaodlangParser.LINE_COMMENT) {
                lastComment = terminal.getText().replace("//", "").trim();

                if (i > 0) {
                    var prev = ctx.children.get(i - 1);
                    if (prev instanceof ParserRuleContext prevCtx) {
                        int commentLine = terminal.getSymbol().getLine();
                        int stmtLine = prevCtx.getStop().getLine();

                        if (commentLine == stmtLine) {
                            if (prevCtx instanceof YaodlangParser.ChaLineContext) {
                                addField(((YaodlangParser.ChaLineContext) prevCtx).id.getText(),
                                        ((YaodlangParser.ChaLineContext) prevCtx).formatList().FORMAT(),
                                        lastComment);
                                continue;
                            } else if (prevCtx instanceof YaodlangParser.KeyLineContext) {
                                addField(((YaodlangParser.KeyLineContext) prevCtx).id.getText(),
                                        ((YaodlangParser.KeyLineContext) prevCtx).formatList().FORMAT(),
                                        lastComment);
                                continue;
                            } else if (prevCtx instanceof YaodlangParser.MitLineContext) {
                                addField(((YaodlangParser.MitLineContext) prevCtx).id.getText(),
                                        ((YaodlangParser.MitLineContext) prevCtx).formatList().FORMAT(),
                                        lastComment);
                                continue;
                            }
                        }
                    }
                }
            } else if (child instanceof ParserRuleContext prc) {
                visit(child);
            }
            lastComment = null;
        }
        return null;
    }

    private void addField(String id, List<TerminalNode> formats, String comment) {
        FieldDTO fieldDTO = new FieldDTO(id, comment);
        for (TerminalNode fmt : formats) {
            String text = fmt.getText();
            Format format = parseFormat(text);
            fieldDTO.addFormat(format);
        }
        fieldsMap.put(id, fieldDTO);
    }

    private void addField(String id, List<TerminalNode> formats) {
        addField(id, formats, lastComment);
    }

    private Format parseFormat(String text) {
        String typePart = text.replaceAll("\\(.*?\\)", "");
        String[] params = text.contains("(") ? text.substring(text.indexOf('(') + 1, text.indexOf(')')).split(",") : new String[0];

        FormatType type = FormatType.fromString(typePart.trim());
        int width = params.length > 0 ? Integer.parseInt(params[0].trim()) : 0;
        int precision = params.length > 1 ? Integer.parseInt(params[1].trim()) : 0;

        return new Format(type, width, precision);
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyFormat() {
        return familyFormat;
    }

    public void setFamilyFormat(String familyFormat) {
        this.familyFormat = familyFormat;
    }
}