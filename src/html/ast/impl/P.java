package html.ast.impl;

import html.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class P implements Parrafo {

    private  List<Bloque> bloques = new ArrayList();

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this, param);
    }

    public List<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(List<Bloque> bloques) {
        this.bloques = bloques;
    }
}
