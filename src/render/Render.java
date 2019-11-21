package render;

import css.ast.AstCss;
import css.parser.Lexicon;
import css.parser.Parser;
import css.visitor.BuscaParamEnCssVisitor;
import css.visitor.ImprimeCSSVisitor;
import html.ast.AstHtml;
import html.visitor.BuscaCssVisitor;
import html.visitor.ImprimeHtmlConCssVisitor;
import html.visitor.RenderFormattedVisitor;

import java.io.FileReader;
import java.io.IOException;

public class Render {

    public Render(){

    }

    public String renderPruebas(String fichero){


        try {
            AstHtml html = obtenerArbolHtml(fichero);

            BuscaCssVisitor buscaCssVisitor = new BuscaCssVisitor();
            String ficheroCss =(String) html.accept(buscaCssVisitor,null);

            AstCss css = obtenerArbolCss(ficheroCss);

            ImprimeHtmlConCssVisitor imprimeHtmlConCssVisitor = new ImprimeHtmlConCssVisitor();

            return (String) html.accept(imprimeHtmlConCssVisitor, css);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }


    public FormattedPage render(String fichero) {


        try {
            AstHtml html = obtenerArbolHtml(fichero);

            BuscaCssVisitor buscaCssVisitor = new BuscaCssVisitor();
            String ficheroCss = (String) html.accept(buscaCssVisitor, null);

            AstCss cssPropio = obtenerArbolCss(ficheroCss);
            AstCss cssDefault = obtenerArbolCss("DEFAULT.CSS");

            RenderFormattedVisitor renderFormattedVisitor = new RenderFormattedVisitor(cssPropio, cssDefault);

            return ((FormattedPage) html.accept(renderFormattedVisitor, null));


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private AstHtml obtenerArbolHtml(String  fichero) throws IOException {
        FileReader filereader = new FileReader (fichero);
        html.parser.Lexicon lex = new html.parser.Lexicon(filereader);
        html.parser.Parser parser = new html.parser.Parser(lex);
        AstHtml ast = parser.parse();
        return ast;
    }


    private AstCss obtenerArbolCss(String fichero) throws IOException {
        FileReader filereader = new FileReader(fichero);
        Lexicon lex = new Lexicon(filereader);
        Parser parser = new Parser (lex);
        AstCss ast = parser.parse();
        return ast;
    }


}
