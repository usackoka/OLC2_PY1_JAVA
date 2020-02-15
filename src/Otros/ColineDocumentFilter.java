/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public final class ColineDocumentFilter extends DocumentFilter {
    
    private final StyledDocument styledDocument;
    private final StyleContext styleContext;
    private final AttributeSet purpleAttributeSet;
    private final AttributeSet blueAttributeSet;
    private final AttributeSet orangeAttributeSet;
    private final AttributeSet blackAttributeSet;
    private final AttributeSet grayAttributeSet;
    private final JTextPane myTextPane;
    public List<String> palabras_reservadas = new ArrayList<>();
    public boolean primero = true;

    public ColineDocumentFilter(JTextPane myTextPane) {
        this.myTextPane = myTextPane;
        styledDocument = myTextPane.getStyledDocument();

        styleContext = StyleContext.getDefaultStyleContext();
        purpleAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, new Color(102, 0, 153));
        blueAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
        orangeAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.ORANGE);
        grayAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.GRAY);
        blackAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLACK);

    }

// Use a regular expression to find the words you are looking for
    Pattern pattern_reservadas = buildPattern();
    Pattern pattern_numeros = buildPattern1();

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attributeSet) throws BadLocationException {
        if ("{".equals(text)) {
            text = addWhiteSpace(fb.getDocument(), offset);
            super.insertString(fb, offset, text, attributeSet);
        } else if ("\n".equals(text)) {
            text = keepWhiteSpace(fb.getDocument(), offset);
            super.insertString(fb, offset, text, attributeSet);
        } else if ("}".equals(text)) {
            text = removeWhiteSpace(fb.getDocument(), offset);

            if (offset > 0) {
                super.insertString(fb, offset - 1, text, attributeSet);
            } else {
                super.insertString(fb, offset, text, attributeSet);
            }

        } else {
            super.insertString(fb, offset, text, attributeSet);

        }
        handleTextChanged();
    }

    private String keepWhiteSpace(Document doc, int offset)
            throws BadLocationException {
        StringBuilder whiteSpace = new StringBuilder("\n");
        Element rootElement = doc.getDefaultRootElement();
        int line = rootElement.getElementIndex(offset);
        int i = rootElement.getElement(line).getStartOffset();

        while (true) {
            String temp = doc.getText(i, 1);

            if (temp.equals(" ") || temp.equals("\t")) {
                whiteSpace.append(temp);
                i++;
            } else {
                break;
            }
        }

        return whiteSpace.toString();
    }

    private String addWhiteSpace(Document doc, int offset)
            throws BadLocationException {
        StringBuilder whiteSpace = new StringBuilder("{\n");
        Element rootElement = doc.getDefaultRootElement();
        int line = rootElement.getElementIndex(offset);
        int i = rootElement.getElement(line).getStartOffset();

        while (true) {
            String temp = doc.getText(i, 1);

            if (temp.equals(" ") || temp.equals("\t")) {
                whiteSpace.append(temp);
                i++;
            } else {
                whiteSpace.append("\t");
                break;
            }
        }

        return whiteSpace.toString();
    }

    private String removeWhiteSpace(Document doc, int offset)
            throws BadLocationException {
        StringBuilder whiteSpace = new StringBuilder("");
        Element rootElement = doc.getDefaultRootElement();
        int line = rootElement.getElementIndex(offset);
        int i = rootElement.getElement(line).getStartOffset();

        while (true) {
            String temp = doc.getText(i, 1);

            if (temp.equals(" ") || temp.equals("\t")) {
                whiteSpace.append(temp);
                i++;
            } else {
                break;
            }
        }
        String espaciado = whiteSpace.toString();
        String espaciado2 = whiteSpace.toString();
        try {
            espaciado2 = espaciado2.substring(0, espaciado2.length() - 1);
        } catch (Exception e) {
            //System.out.println(":C era: " + espaciado.length());
        }
        try {
            espaciado2 = espaciado2.substring(0, espaciado2.length() - 1);
        } catch (Exception e) {
            //System.out.println(":C era: " + espaciado.length());
        }

        espaciado = espaciado + espaciado2;
        return espaciado;
    }

    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);

        handleTextChanged();
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException {

        if ("\n".equals(text)) {
            text = keepWhiteSpace(fb.getDocument(), offset);
            super.replace(fb, offset, length, text, attributeSet);

        }else {
            super.replace(fb, offset, length, text, attributeSet);

        }

        handleTextChanged();
    }

    /**
     * Runs your updates later, not during the event notification.
     */
    private void handleTextChanged() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updateTextStyles();
            }
        });
    }

    /**
     * Build the regular expression that looks for the whole word of each word
     * that you wish to find. The "\\b" is the beginning or end of a word
     * boundary. The "|" is a regex "or" operator.
     *
     * @return
     */
    private Pattern buildPattern() {
        StringBuilder sb = new StringBuilder();
        palabras_reservadas.add("null");
        palabras_reservadas.add("true");
        palabras_reservadas.add("false");
        palabras_reservadas.add("function");
        palabras_reservadas.add("in");
        //================ SENTENCIAS =======================
        palabras_reservadas.add("break");
        palabras_reservadas.add("continue");
        palabras_reservadas.add("return");
        palabras_reservadas.add("switch");
        palabras_reservadas.add("case");
        palabras_reservadas.add("if");
        palabras_reservadas.add("else");
        palabras_reservadas.add("while");
        palabras_reservadas.add("do");
        palabras_reservadas.add("default");
        palabras_reservadas.add("for");
        palabras_reservadas.add("print");

        for (String token : palabras_reservadas) {
            sb.append("(?i)\\b"); // Start of word boundary
            sb.append(token);
            sb.append("\\b|"); // End of word boundary and an or for the next word
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove the trailing "|"
        }

        Pattern p = Pattern.compile(sb.toString());

        return p;
    }

    private Pattern buildPattern1() {
        StringBuilder sb = new StringBuilder();
        palabras_reservadas = new ArrayList<>();
        palabras_reservadas.add("([0-9])+(.([0-9])+)?");

        for (String token : palabras_reservadas) {
            sb.append("\\b"); // Start of word boundary
            sb.append(token);
            sb.append("\\b|"); // End of word boundary and an or for the next word
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove the trailing "|"
        }

        Pattern p = Pattern.compile(sb.toString());

        return p;
    }

    private void updateTextStyles() {
        // Clear existing styles  
        styledDocument.setCharacterAttributes(0, myTextPane.getText().length(), blackAttributeSet, true);

        // Look for tokens and highlight them
        Matcher matcher;
        if (primero) {
            matcher = pattern_reservadas.matcher(myTextPane.getText().replaceAll("\n", ""));
        } else {
            matcher = pattern_reservadas.matcher(myTextPane.getText().replaceAll("\n", " "));
        }
        while (matcher.find()) {
            // Change the color of recognized tokens
            styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), this.blueAttributeSet, false);
        }
        if (primero) {
            matcher = this.pattern_numeros.matcher(myTextPane.getText().replaceAll("\n", ""));
        } else {
            matcher = this.pattern_numeros.matcher(myTextPane.getText().replaceAll("\n", " "));

        }
        while (matcher.find()) {
            // Change the color of recognized tokens
            styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), this.purpleAttributeSet, false);
        }
        boolean comilla = false;
        boolean apostrofe = false;
        boolean multilinea = false;
        char[] caracteres = null;
        if (primero) {
            caracteres = this.myTextPane.getText().replace("\n", " ").toCharArray();
        } else {
            caracteres = this.myTextPane.getText().replace("\n", " ").toCharArray();

        }
        for (int i = 0; i < caracteres.length; i++) {
            if (caracteres[i] == '\"') {
                if (comilla) {
                    if (i - 1 >= 0) {
                        if (caracteres[i - 1] == '\\') {
                            styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);
                        } else {
                            styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);
                            comilla = false;
                        }
                    } else {
                        comilla = false;
                    }
                } else {
                    styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);
                    comilla = true;
                }

            } else if (caracteres[i] == '\'') {
                if (apostrofe) {
                    if (i - 1 >= 0) {
                        if (caracteres[i - 1] == '\\') {
                            styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);
                        } else {
                            styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);
                            apostrofe = false;
                        }
                    } else {
                        apostrofe = false;
                    }
                } else {
                    styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);
                    apostrofe = true;
                }
            } else if (caracteres[i] == '/') {
                if (i + 1 < caracteres.length) {
                    if (caracteres[i + 1] == '*') {
                        styledDocument.setCharacterAttributes(i, 1, this.grayAttributeSet, false);
                        multilinea = true;
                    }
                }
            } else if (caracteres[i] == '*') {
                if (multilinea) {
                    styledDocument.setCharacterAttributes(i, 1, this.grayAttributeSet, false);
                }
                if (i + 1 < caracteres.length) {
                    if (caracteres[i + 1] == '/') {
                        styledDocument.setCharacterAttributes(i + 1, 1, this.grayAttributeSet, false);
                        multilinea = false;
                    }
                }
            } else if (multilinea) {
                styledDocument.setCharacterAttributes(i, 1, this.grayAttributeSet, false);

            } else if (comilla) {
                styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);
            } else if (apostrofe) {
                styledDocument.setCharacterAttributes(i, 1, this.orangeAttributeSet, false);

            }
        }
        boolean unilinea = false;
        char[] caracteres_saltos = myTextPane.getText().toCharArray();
        int saltos = 0;
        for (int i = 0; i < caracteres_saltos.length; i++) {
            if (caracteres_saltos[i] == '/') {
                if (i + 1 < caracteres_saltos.length) {
                    if (caracteres_saltos[i + 1] == '/') {
                        styledDocument.setCharacterAttributes(i, 2, this.grayAttributeSet, false);
                        unilinea = true;
                    }
                }
            } else if (caracteres_saltos[i] == '\n') {
                unilinea = false;
                saltos++;
            } else if (unilinea) {
                styledDocument.setCharacterAttributes(i, 1, this.grayAttributeSet, false);

            }
        }
    }
}
