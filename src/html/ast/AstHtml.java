package html.ast;

import css.visitor.Visitor;

public interface AstHtml {

    Object accept(Visitor visitor, Object param);

}
