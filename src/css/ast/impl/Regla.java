package css.ast.impl;

import css.ast.AstCss;
import css.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Regla implements AstCss {

    private String ident;
    private  int line;



    private List<Definicion> definiciones =  new ArrayList<>();

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public List<Definicion> getDefiniciones() {
        return definiciones;
    }

    public void setDefiniciones(List<Definicion> definiciones) {
        this.definiciones = definiciones;
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
