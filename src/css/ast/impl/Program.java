package css.ast.impl;

import css.ast.AstCss;
import css.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Program implements AstCss {

    private int line;

    private List<Regla> reglas =  new ArrayList<Regla>();


    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public List<Regla> getReglas() {
        return reglas;
    }

    public void setReglas(List<Regla> reglas) {
        this.reglas = reglas;
    }
}
