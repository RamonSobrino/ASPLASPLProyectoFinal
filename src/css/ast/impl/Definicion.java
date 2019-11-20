package css.ast.impl;

import css.ast.AstCss;
import css.visitor.Visitor;

public class Definicion implements AstCss {

    public String label;
    public String value;

    public int line;

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this, param);
    }


    @Override
    public String toString() {
        return label+" --> "+value;
    }
}
