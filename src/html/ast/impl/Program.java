package html.ast.impl;

import html.visitor.Visitor;
import html.ast.AstHtml;

import java.util.List;

public class Program implements AstHtml {

    public Body body;
    public Head head;

    @Override
    public Object accept(Visitor visitor, Object param){
        return visitor.visit(this, param);
    }
}
