package html.visitor;

import html.ast.impl.*;

import java.util.stream.Collectors;

public class ImprimeHtmlVisitor implements Visitor {


    @Override
    public Object visit(Program p, Object param) {
        String resultado = "";

        resultado += "<html> \n";
        resultado += (String) p.getHead().accept(this, param);

            resultado += (String) p.getBody().accept(this, param);

        resultado += "</html>";

        return resultado;
    }

    @Override
    public Object visit(H1 p, Object param) {
        String resultado = "";
        resultado += "      <h1>";
        resultado +=String.join(" ", p.getTextos());
        resultado += " </h1> \n";
        return resultado;
    }

    @Override
    public Object visit(Head p, Object param) {
        String resultado = "";

        resultado += "  <head>\n";

        resultado += p.getTitle().accept(this, param);
        resultado += p.getLink().accept(this,param);

        resultado += "  </head>\n";

        return resultado;
    }

    @Override
    public Object visit(Link p, Object param) {
        String resultado = "";

        resultado += "      <link ";

        resultado += " href=\""+ p.getHref()+"\" ";
        resultado += " rel=\""+ p.getRel()+"\" ";
        resultado += " type=\""+ p.getType()+"\" ";

        resultado += ">\n";

        return resultado;
    }

    @Override
    public Object visit(Title p, Object param) {
        String resultado = "";
        resultado += "      <title>";
        resultado +=String.join(" ", p.getTextos());
        resultado += " </title> \n";
        return resultado;
    }

    @Override
    public Object visit(Body p, Object param) {
        String resultado = "";

        resultado += " <body>\n";

        for ( Parrafo parrafo : p.getParrafos()) {
            resultado += (String) parrafo.accept(this, param);
        }
        resultado += "  </body>\n";

        return resultado;
    }

    @Override
    public Object visit(H2 h2, Object param){
        String resultado = "";
        resultado += "      <h2>";
        resultado +=String.join(" ", h2.getTextos());
        resultado += " </h2> \n";
        return resultado;
    }

    @Override
    public Object visit(AgrupTextos agrupTextos, Object param) {
        String resultado = "";
        resultado +=String.join(" ", agrupTextos.getTextos());
        return resultado;
    }

    @Override
    public Object visit(Bold bold, Object param) {
        String resultado = "";
        resultado += " <b>";
        resultado +=String.join(" ", bold.getTextos());
        resultado += "</b> ";
        return resultado;
    }

    @Override
    public Object visit(Italic italic, Object param) {
        String resultado = "";
        resultado += " <i>";
        resultado +=String.join(" ", italic.getTextos());
        resultado += "</i> ";
        return resultado;
    }

    @Override
    public Object visit(Underlined underlined, Object param) {
        String resultado = "";
        resultado += " <u>";
        resultado +=String.join(" ", underlined.getTextos());
        resultado += "</u> ";
        return resultado;
    }

    @Override
    public Object visit(P p, Object param) {
        String resultado = "";

        resultado += "      <p>";

        for ( Bloque bloque : p.getBloques()) {
            resultado += (String) bloque.accept(this, param);
        }
        resultado += "</p>\n";

        return resultado;
    }
}
