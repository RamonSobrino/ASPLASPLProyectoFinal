package html.ast.impl;

import html.visitor.Visitor;
import html.ast.AstHtml;

import java.util.List;

public class Program implements AstHtml {

    private  Body body;
    private Head head;

    @Override
    public Object accept(Visitor visitor, Object param){
        return visitor.visit(this, param);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }
}
