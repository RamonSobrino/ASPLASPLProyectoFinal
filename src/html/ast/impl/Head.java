package html.ast.impl;

import html.visitor.Visitor;
import html.ast.AstHtml;

public class Head implements AstHtml {

    private Title title;
    private Link link;

    @Override
    public Object accept(Visitor visitor, Object param){
        return visitor.visit(this, param);
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
