package html.ast.impl;

import html.visitor.Visitor;
import html.ast.AstHtml;

public class Link implements AstHtml {

    public String href;
    public String rel;
    public String type;


    @Override
    public Object accept(Visitor visitor, Object param){
        return visitor.visit(this, param);
    }
}
