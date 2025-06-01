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

public class ParserLauncher {

    public static void main(String[] args) throws IOException {
        URL ddlFile = ParserLauncher.class.getClassLoader().getResource("SnMar.ddl");
        URL datFile = ParserLauncher.class.getClassLoader().getResource("21802.dat");

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

        runWithTimer("FullDataParser", new FullDataParser(), lines, fields);
        runWithTimer("FastDataParser", new FastDataParser(), lines, fields);
        runWithTimer("SmartDataParser", new SmartDataParser(), lines, fields);
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

    private static void runWithTimer(String name, DataParser parser, List<String> lines, List<Field> fields) throws IOException {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("🔍 Запуск парсера: %s%n", name);
        long start = System.nanoTime();

        parser.parse(lines, fields);

        long end = System.nanoTime();
        double elapsedMillis = (end - start) / 1_000_000.0;
        System.out.printf("⏱ Время выполнения (%s): %.2f мс%n", name, elapsedMillis);
    }
}