package css.visitor;

import css.ast.impl.Definicion;
import css.ast.impl.Program;
import css.ast.impl.Regla;

public interface Visitor {

    Object visit(Program p, Object param);

    Object visit(Regla r, Object param);

    Object visit(Definicion d, Object param);

}
