package css.ast.impl;

import css.ast.AstCss;
import css.visitor.Visitor;

public class Definicion implements AstCss {

    private String label;
    private String value;

    private  int line;

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this, param);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return label+" --> "+value;
    }
}
