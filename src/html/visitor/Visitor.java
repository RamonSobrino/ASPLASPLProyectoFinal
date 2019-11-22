package html.visitor;

import html.ast.impl.*;

public interface Visitor {

    Object visit(Program p, Object param);
    Object visit(H1 p, Object param);
    Object visit(Head p, Object param);
    Object visit(Link p, Object param);
    Object visit(Title p, Object param);
    Object visit(Body p, Object param);
    Object visit(H2 h2, Object param);
    Object visit(AgrupTextos agrupTextos, Object param);
    Object visit(Bold bold, Object param);
    Object visit(Italic italic, Object param);
    Object visit(Underlined underlined, Object param);
    Object visit(P p, Object param);

    Object visit(H3 h3, Object param);
}
