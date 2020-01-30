/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colores;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.PlainView;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;

/**
 *
 * @author Koka
 */
public class FSTokens extends PlainView{
      private static String IDS = "(\\w(\\w|'_')*)";
      private static String COMENTARIOLINEA = "(##([^\n]*))";
      private static String ENTERO = "\\d";
      private static String CADENA = "([\"]([^\"\n]+)?[\"|\n|\"\n])";
      private static HashMap<Pattern, Color> patternColors;
//    private static String GENERIC_XML_NAME = "[A-Za-z]+[A-Za-z0-9\\-_]*(:[A-Za-z]+[A-Za-z0-9\\-_]+)?";
//    private static String TAG_PATTERN = "(</?" + GENERIC_XML_NAME + ")";
//    private static String TAG_END_PATTERN = "(>|/>)";
//    private static String TAG_ATTRIBUTE_PATTERN = "(" + GENERIC_XML_NAME + ")\\w*\\=";
//    private static String TAG_ATTRIBUTE_VALUE = "\\w*\\=\\w*(\"[^\"]*\")";
//    private static String TAG_COMMENT = "(<\\!--[\\w ]*-->)";
//    private static String TAG_CDATA = "(<\\!\\[CDATA\\[.*\\]\\]>)";

    static {
        // NOTE: the order is important!
        patternColors = new LinkedHashMap<Pattern, Color>();
        patternColors.put(Pattern.compile(IDS), Color.BLACK);
        patternColors.put(Pattern.compile(COMENTARIOLINEA), Color.GRAY);
        patternColors.put(Pattern.compile(ENTERO), Color.MAGENTA);
        patternColors.put(Pattern.compile(CADENA), Color.ORANGE);
//        patternColors.put(Pattern.compile(TAG_PATTERN), Color.BLUE);
//        patternColors.put(Pattern.compile(TAG_CDATA), Color.GRAY);
//        patternColors.put(Pattern.compile(TAG_ATTRIBUTE_PATTERN), new Color(127, 0, 127));
//        patternColors.put(Pattern.compile(TAG_END_PATTERN), Color.BLUE);
//        patternColors.put(Pattern.compile(TAG_ATTRIBUTE_VALUE), new Color(42,0, 255));
//        patternColors.put(Pattern.compile(TAG_COMMENT), Color.BLUE);
    }

    public FSTokens(Element element) {
        super(element);
        // Set tabsize to 4 (instead of the default 8)
        getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);
    }

    @Override
    protected int drawUnselectedText(Graphics graphics, int x, int y, int p0,
            int p1) throws BadLocationException {

        Document doc = getDocument();
        String text = doc.getText(p0, p1 - p0);

        Segment segment = getLineBuffer();

        SortedMap<Integer, Integer> startMap = new TreeMap<>();
        SortedMap<Integer, Color> colorMap = new TreeMap<>();

        // Match all regexes on this snippet, store positions
        for (Map.Entry<Pattern, Color> entry : patternColors.entrySet()) {

            Matcher matcher = entry.getKey().matcher(text);

            while (matcher.find()) {
                startMap.put(matcher.start(0), matcher.end());
                colorMap.put(matcher.start(0), entry.getValue());
            }
        }

        // TODO: check the map for overlapping parts

        int i = 0;

        // Colour the parts
        for (Map.Entry<Integer, Integer> entry : startMap.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();

            if (i < start) {
                graphics.setColor(Color.black);
                doc.getText(p0 + i, start - i, segment);
                x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
            }

            graphics.setColor(colorMap.get(start));
            i = end;
            doc.getText(p0 + start, i - start, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, start);
        }

        // Paint possible remaining text black
        if (i < text.length()) {
            graphics.setColor(Color.black);
            doc.getText(p0 + i, text.length() - i, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
        }

        return x;
    }
}
