package html.ast.impl;

import html.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Bold implements Bloque{

    public List<String> textos = new ArrayList<String>();

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this, param);
    }
}
