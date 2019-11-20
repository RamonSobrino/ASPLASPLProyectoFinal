package css.visitor;

import css.ast.AstCss;
import css.ast.impl.*;

public class BuscaParamEnCssVisitor implements Visitor {

    String label = null;
    String ident = null;


    @Override
    public Object visit(Program p, Object param) {
        for(Regla r : p.getReglas()) {
            if(r.getIdent().equals(ident)){
                return (String) r.accept(this, null);
            }
        }
        return null;
    }

    @Override
    public Object visit(Regla r, Object param) {
        for(Definicion d : r.getDefiniciones()){
            if(d.getLabel().equals(label)){
                return (String) d.accept(this, null);
            }
        }

        return null;
    }

    @Override
    public Object visit(Definicion f, Object param) {
        return f.getValue();
    }

    public String search(String ident, String label, AstCss program) {
        this.ident = ident;
        this.label = label;

        if(ident == null || label == null || program == null){
            return null;
        }

        return (String) program.accept(this, null);
    }
}