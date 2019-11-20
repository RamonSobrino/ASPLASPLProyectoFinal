package html.ast.impl;

import html.visitor.Visitor;
import html.ast.AstHtml;

public class Link implements AstHtml {

    private String href;
    private  String rel;
    private String type;


    @Override
    public Object accept(Visitor visitor, Object param){
        return visitor.visit(this, param);
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
