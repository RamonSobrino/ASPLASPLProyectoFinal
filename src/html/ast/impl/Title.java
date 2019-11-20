package html.ast.impl;

import css.visitor.Visitor;
import html.ast.AstHtml;

import java.util.ArrayList;
import java.util.List;

public class Title implements AstHtml {

    public List<String> textos = new ArrayList<>();

    @Override
    public Object accept(Visitor visitor, Object param) {
        return null;
    }
}
