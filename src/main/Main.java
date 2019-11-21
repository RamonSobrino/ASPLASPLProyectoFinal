package main;

import render.FormattedLine;
import render.FormattedPage;
import render.FormattedText;
import render.Render;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){

//		//Visitor print css
//
        Render render = new Render();
        FormattedPage page = render.render("EX4.HTML");
        System.out.println(  page.toString());



        JFrame f = new JFrame(page.getTitle());

        // Create the StyleContext, the document and the pane
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane pane = new JTextPane(doc);


        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        // Add the text to the document
                        int start = 0;
                        int line = 1;
                        for (FormattedLine linea:page.getLineas()) {
                            Style styleP = sc.addStyle("StyleP"+line, null);
                            switch (linea.getTextAlign()) {
                                case "left":
                                    styleP.addAttribute(StyleConstants.ALIGN_LEFT,true);
                                    break;
                                case "right":
                                    styleP.addAttribute(StyleConstants.ALIGN_RIGHT,true);
                                    break;
                                case "center":
                                    styleP.addAttribute(StyleConstants.ALIGN_CENTER,true);
                                    break;
                            }

                            for (FormattedText text :linea.getTextos()) {
                                Style style = sc.addStyle("Style"+text, null);

                                style.addAttribute(StyleConstants.FontSize, text.getFontSize().intValue());
                                switch (text.getColor()) {
                                    case "black":
                                        style.addAttribute(StyleConstants.Foreground, Color.BLACK);
                                        break;
                                    case "blue":
                                        style.addAttribute(StyleConstants.Foreground, Color.BLUE);
                                        break;
                                    case "green":
                                        style.addAttribute(StyleConstants.Foreground, Color.GREEN);
                                        break;
                                    case "red":
                                        style.addAttribute(StyleConstants.Foreground, Color.RED);
                                        break;
                                }

                                switch (text.getFontStyle()) {
                                    case "bold":
                                        style.addAttribute(StyleConstants.Bold,true);
                                        break;
                                    case "italic":
                                        style.addAttribute(StyleConstants.Italic,true);
                                        break;
                                    case "normal":
                                        break;
                                    case "underlined":
                                        style.addAttribute(StyleConstants.Underline,true);
                                        break;
                                }


                                doc.insertString(start, text.getTexto(), style);
                                start += text.getTexto().length();
                                doc.insertString(start, " ", null);
                                start ++;

                            }

                            doc.insertString(start, "\n", null );
                            start ++;


                        }

                    } catch (BadLocationException e) {
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Exception when constructing document: " + e);
            System.exit(1);
        }

        f.getContentPane().add(new JScrollPane(pane));
        f.setSize(400, 300);
        f.setVisible(true);
    }
}

