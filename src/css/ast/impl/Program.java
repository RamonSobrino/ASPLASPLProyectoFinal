package css.ast.impl;

import css.ast.AstCss;
import css.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Program implements AstCss {

    public int line;

    public List<Regla> reglas =  new ArrayList<Regla>();


    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
