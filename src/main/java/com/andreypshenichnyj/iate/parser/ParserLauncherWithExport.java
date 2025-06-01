package com.andreypshenichnyj.iate.parser;

import com.andreypshenichnyj.iate.parser.core.FastDataParser;
import com.andreypshenichnyj.iate.parser.core.FullDataParser;
import com.andreypshenichnyj.iate.parser.core.SmartDataParser;
import com.andreypshenichnyj.iate.parser.interfaces.DataParser;
import com.andreypshenichnyj.iate.visitor.DdlVisitor;
import com.andreypshenichnyj.iate.visitor.dto.Field;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.YaodlangLexer;
import parser.YaodlangParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ParserLauncherWithExport {

    public static void main(String[] args) throws IOException {
        URL ddlFile = ParserLauncherWithExport.class.getClassLoader().getResource("SnMar.ddl");
        URL datFile = ParserLauncherWithExport.class.getClassLoader().getResource("21802.dat");

        if (ddlFile == null || datFile == null) {
            throw new RuntimeException("Файл не найден: SnMar.ddl или 21802.dat");
        }

        Path ddlPath;
        Path datPath;
        try {
            ddlPath = Paths.get(ddlFile.toURI());
            datPath = Paths.get(datFile.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Ошибка преобразования пути к файлам", e);
        }

        List<String> lines = Files.readAllLines(datPath);
        List<Field> fields = parseDDL(ddlPath);

        runAndExport("FullDataParser", new FullDataParser(), lines, fields);
        runAndExport("SmartDataParser", new SmartDataParser(), lines, fields);
        runAndExport("FastDataParser", new FastDataParser(), lines, fields);
    }

    private static List<Field> parseDDL(Path ddlPath) throws IOException {
        YaodlangLexer lexer = new YaodlangLexer(CharStreams.fromFileName(ddlPath.toString()));
        TokenStream tokens = new CommonTokenStream(lexer);
        YaodlangParser parser = new YaodlangParser(tokens);
        ParseTree tree = parser.yaodfile();

        DdlVisitor visitor = new DdlVisitor();
        visitor.visit(tree);

        return visitor.getFields();
    }

    private static void runAndExport(String name, DataParser parser, List<String> lines, List<Field> fields) {
        System.out.printf("%s запущен...%n", name);

        try {
            long start = System.nanoTime();
            parser.parse(lines, fields);
            long end = System.nanoTime();
            double elapsedMillis = (end - start) / 1_000_000.0;
            System.out.printf("%s: %.2f мс%n", name, elapsedMillis);

            parser.saveAsJson(name + "_output.json");
            parser.saveAsCsv(name + "_output.csv");
            parser.saveAsTxt(name + "_output.txt");

            System.out.printf("%s: сохранены JSON, CSV, TXT%n%n", name);

        } catch (Exception e) {
            System.err.printf("Ошибка в %s: %s%n", name, e.getMessage());
        }
    }
}
