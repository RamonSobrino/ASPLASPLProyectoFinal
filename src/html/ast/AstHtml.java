package html.ast;

import html.visitor.Visitor;

public interface AstHtml {

    Object accept(Visitor visitor, Object param);

}
