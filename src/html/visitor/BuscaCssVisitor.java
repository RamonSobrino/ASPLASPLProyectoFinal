package html.visitor;

import html.ast.impl.*;

public class BuscaCssVisitor implements Visitor {
    @Override
    public Object visit(Program p, Object param) {
        return p.getHead().accept(this, param);
    }

    @Override
    public Object visit(H1 p, Object param) {
        return null;
    }

    @Override
    public Object visit(Head p, Object param) {
        return p.getLink().accept(this,param);
    }

    @Override
    public Object visit(Link p, Object param) {
        return p.getHref();
    }

    @Override
    public Object visit(Title p, Object param) {
        return null;
    }

    @Override
    public Object visit(Body p, Object param) {
        return null;
    }

    @Override
    public Object visit(H2 h2, Object param) {
        return null;
    }

    @Override
    public Object visit(AgrupTextos agrupTextos, Object param) {
        return null;
    }

    @Override
    public Object visit(Bold bold, Object param) {
        return null;
    }

    @Override
    public Object visit(Italic italic, Object param) {
        return null;
    }

    @Override
    public Object visit(Underlined underlined, Object param) {
        return null;
    }

    @Override
    public Object visit(P p, Object param) {
        return null;
    }
}
