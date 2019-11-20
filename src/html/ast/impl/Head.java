package html.ast.impl;

import css.visitor.Visitor;
import html.ast.AstHtml;

public class Head implements AstHtml {

    public Head head;
    public Link link;

    @Override
    public Object accept(Visitor visitor, Object param) {
        return null;
    }
}
