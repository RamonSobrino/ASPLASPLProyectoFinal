package css.visitor;

import css.ast.impl.Definicion;
import css.ast.impl.Program;
import css.ast.impl.Regla;

public class ImprimeCSSVisitor  implements Visitor{

    String sp ="    ";

    @Override
    public Object visit(Program p, Object param) {
        String resultado = "";

        for (Regla r : p.reglas) {
            resultado += r.ident;
            resultado += "{\n";
            resultado += (String) r.accept(this, sp);
            resultado += "}\n\n";
        }

        return resultado;
    }

    @Override
    public Object visit(Regla p, Object param) {
        String resultado = "";
        for (Definicion d : p.definiciones) {

            resultado += sp+ (String) d.accept(this,null);

        }
        return resultado;
    }

    @Override
    public Object visit(Definicion p, Object param) {
        return p.toString() + "\n";
    }
}
