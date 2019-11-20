package css.ast.impl;

import css.ast.AstCss;
import css.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Regla implements AstCss {

    public String ident;
    public int line;

    public List<Definicion> definiciones =  new ArrayList<>();


    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
