package html.visitor;

import css.ast.AstCss;
import css.visitor.BuscaParamEnCssVisitor;
import html.ast.impl.*;

public class ImprimeHtmlConCssVisitor extends  ImprimeHtmlVisitor {

    BuscaParamEnCssVisitor buscaParamEnCssVisitor = new BuscaParamEnCssVisitor();



    @Override
    public Object visit(H1 p, Object param) {
        String resultado = "";
        resultado += "      <h1"+ buscarParametros("h1",param)+">";
        resultado +=String.join(" ", p.getTextos());
        resultado += " </h1> \n";
        return resultado;
    }



    @Override
    public Object visit(H2 h2, Object param){

        String resultado = "";
        resultado += "      <h2"+ buscarParametros("h2",param)+">";
        resultado +=String.join(" ", h2.getTextos());
        resultado += " </h2> \n";
        return resultado;
    }

    private String buscarParametros(String ident, Object param){
        String resultado = " style =\" ";
        resultado += "color: ";
        resultado += this.buscaParamEnCssVisitor.search(ident,"color",(AstCss) param);
        resultado += " ";
        resultado += "font-size: ";
        resultado += this.buscaParamEnCssVisitor.search(ident,"font-size",(AstCss) param);
        resultado += " ";
        resultado += "text-align: ";
        resultado += this.buscaParamEnCssVisitor.search(ident,"text-align",(AstCss) param);
        resultado += " ";
        resultado += "font-style: ";
        resultado += this.buscaParamEnCssVisitor.search(ident,"font-style",(AstCss) param);
        resultado += " ";
        resultado += " \"";

        return resultado;
    }



    @Override
    public Object visit(P p, Object param) {
        String resultado = "";

        resultado += "      <p"+ buscarParametros("p",param)+">";

        for ( Bloque bloque : p.getBloques()) {
            resultado += (String) bloque.accept(this, null);
        }
        resultado += "</p>\n";

        return resultado;
    }
}
