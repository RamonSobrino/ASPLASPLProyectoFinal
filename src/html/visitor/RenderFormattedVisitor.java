package html.visitor;

import css.ast.AstCss;
import css.visitor.BuscaParamEnCssVisitor;
import html.ast.impl.*;
import render.FormattedLine;
import render.FormattedPage;
import render.FormattedText;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RenderFormattedVisitor implements Visitor {

    private BuscaParamEnCssVisitor buscaParamEnCssVisitor = new BuscaParamEnCssVisitor();
    private AstCss propio;
    private AstCss defecto;

    public RenderFormattedVisitor(AstCss propio, AstCss defecto){
        this.defecto=defecto;
        this.propio= propio;
    }

    @Override
    public Object visit(Program p, Object param) {
        FormattedPage resultado = new FormattedPage();

         resultado.setTitle( (String)p.getHead().accept(this, param));

          resultado.getLineas().addAll ((List<FormattedLine>)p.getBody().accept(this, param));

        return resultado;
    }

    @Override
    public Object visit(H1 p, Object param) {
        FormattedLine linea = new FormattedLine();
        asignarParametros("h1",linea);
        linea.getTextos().add(crearTexto("h1",String.join(" ", p.getTextos())));
        return linea;
    }

    private FormattedText crearTexto(String ident, String texto){
        FormattedText text = new FormattedText();
        text.setTexto(texto);
        asignarParametros(ident, text);
        return text;
    }

    private void asignarParametros(String ident, FormattedLine param){
        param.setTextAlign(buscaParametro(ident, "text-align"));
    }


    private void asignarParametros(String ident, FormattedText param){
        param.setColor(buscaParametro(ident, "color"));
        param.setFontSize(Float.parseFloat(buscaParametro(ident, "font-size")
                .replace("px", "")));
        param.setFontStyle(buscaParametro(ident, "font-style"));
    }

    private String buscaParametro(String ident, String label){
        String valor = this.buscaParamEnCssVisitor.search(ident,label,(AstCss) this.propio);
        if(valor !=null && !valor.equals("\'null\'")){
            return valor;
        }else{
            return this.buscaParamEnCssVisitor.search(ident,label,(AstCss) this.defecto);
        }

    }


    @Override
    public Object visit(Head p, Object param) {
      return p.getTitle().accept(this, param);
    }

    @Override
    public Object visit(Link p, Object param) {
        return null;
    }

    @Override
    public Object visit(Title p, Object param) {
        String resultado = "";
        resultado +=String.join(" ", p.getTextos());
        return resultado;
    }

    @Override
    public Object visit(Body p, Object param) {
       List<FormattedLine> lineas = new ArrayList<FormattedLine>();

        for ( Parrafo parrafo : p.getParrafos()) {
           lineas.add( (FormattedLine) parrafo.accept(this, param));
        }

        return lineas;
    }

    @Override
    public Object visit(H2 h2, Object param){
        FormattedLine linea = new FormattedLine();
        asignarParametros("h2",linea);
        linea.getTextos().add(crearTexto("h2",String.join(" ", h2.getTextos())));
        return linea;
    }

    @Override
    public Object visit(AgrupTextos agrupTextos, Object param) {

        return crearTexto("p", String.join(" ", agrupTextos.getTextos()));
    }

    @Override
    public Object visit(Bold bold, Object param) {
        return crearTextosFromatoOblig("p",
                String.join(" ", bold.getTextos()), "bold");
    }

    private FormattedText crearTextosFromatoOblig(String ident, String texto, String oblig){
        FormattedText forTexto = crearTexto(ident, texto);
        forTexto.setFontStyle(oblig);
        return forTexto;
    }

    @Override
    public Object visit(Italic italic, Object param) {
        return crearTextosFromatoOblig("p",
                String.join(" ", italic.getTextos()), "italic");
    }

    @Override
    public Object visit(Underlined underlined, Object param) {
        return crearTextosFromatoOblig("p",
                String.join(" ", underlined.getTextos()), "underlined");
    }

    @Override
    public Object visit(P p, Object param) {

        FormattedLine linea = new FormattedLine();
        asignarParametros("p",linea);


        for ( Bloque bloque : p.getBloques()) {
            linea.getTextos().add((FormattedText) bloque.accept(this, param));
        }

        return linea;
    }
}
