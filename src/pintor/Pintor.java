package pintor;

import render.FormattedLine;
import render.FormattedPage;
import render.FormattedText;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Pintor {

    private FormattedPage page;

    public Pintor(FormattedPage page) {
        this.page = page;
    }

    public FormattedPage getPage() {
        return page;
    }

    public void setPage(FormattedPage page) {
        this.page = page;
    }

    public void paint(){
        if(page!=null){


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
                            int line = 0;
                            for (FormattedLine linea:page.getLineas()) {
                                int inicioLinea = start;
                                for (FormattedText text :linea.getTextos()) {
                                    Style styleP = sc.addStyle("StyleP"+line, null);
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
                                    doc.insertString(start, " ", styleP);
                                    start ++;

                                }
                                SimpleAttributeSet paraf = new SimpleAttributeSet();

                                switch (linea.getTextAlign()) {
                                    case "left":
                                        StyleConstants.setAlignment(paraf, StyleConstants.ALIGN_LEFT);
                                        break;
                                    case "right":
                                        StyleConstants.setAlignment(paraf, StyleConstants.ALIGN_RIGHT);
                                        break;
                                    case "center":
                                        StyleConstants.setAlignment(paraf, StyleConstants.ALIGN_CENTER);
                                        break;
                                }


                                doc.insertString(start, "\n", null );
                                start ++;
                                doc.setParagraphAttributes(inicioLinea, start, paraf, false);


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
}
