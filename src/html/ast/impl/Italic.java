package html.ast.impl;

import html.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Italic implements Bloque {

    private  List<String> textos = new ArrayList<String>();

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this, param);
    }

    public List<String> getTextos() {
        return textos;
    }

    public void setTextos(List<String> textos) {
        this.textos = textos;
    }
}
