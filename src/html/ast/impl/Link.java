package html.ast.impl;

import css.visitor.Visitor;
import html.ast.AstHtml;

public class Link implements AstHtml {

    public String href;
    public String rel;
    public String type;


    @Override
    public Object accept(Visitor visitor, Object param) {
        return null;
    }
}
