package html.ast.impl;

import html.visitor.Visitor;
import html.ast.AstHtml;

import java.util.ArrayList;
import java.util.List;

public class Body implements AstHtml {

    public List<Parrafo> parrafos =new ArrayList<Parrafo>();

    @Override
    public Object accept(Visitor visitor, Object param){
        return visitor.visit(this, param);
    }

}
