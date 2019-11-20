package html.ast.impl;

import html.visitor.Visitor;
import html.ast.AstHtml;

public class Head implements AstHtml {

    public Title title;
    public Link link;

    @Override
    public Object accept(Visitor visitor, Object param){
        return visitor.visit(this, param);
    }
}
