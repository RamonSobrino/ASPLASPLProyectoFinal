package css.parser;

import css.ast.*;
import css.ast.impl.Definicion;
import css.ast.impl.Program;
import css.ast.impl.Regla;

import java.util.List;

public class Parser {

    Lexicon lex;
    boolean errorSint = false;

    public Parser (Lexicon lex) {
        this.lex = lex;
    }

    public AstCss parse () {
        AstCss ast = new Program();
        int i = 0;
        int hasta = lex.tokens.size();
        while (i<hasta){
            Regla r = new Regla();
            i = clasificarRegla((Program) ast, i, r);
            i++;
        }

        return ast;
    }

    private int clasificarRegla(Program ast, int i, Regla r) {
        Token t = lex.tokens.get(i);
        if(t.getToken()== TokensId.Ident){
            r.ident = t.getLexeme();
            i++;
            t = lex.tokens.get(i);
            if(t.token==TokensId.ParentesisA) {
                i++;
                t = lex.tokens.get(i);

                while (t.token != TokensId.ParentesisB) {
                    Definicion d = new Definicion();
                    i = clasificarDefinicion(i, t, r, d);
                    i++;
                    t = lex.tokens.get(i);

                }
                ast.reglas.add(r);

            }else{
                this.errorSintactico(t.lexeme, t.line);
            }

        }else{
            this.errorSintactico(t.lexeme, t.line);
        }
        return i;
    }

    private int clasificarDefinicion(int i, Token t, Regla r, Definicion d) {
        switch (t.token) {
            case Color:
                d.label = t.lexeme;
                d.line = t.line;
                i++;
                t = lex.tokens.get(i);
                if (t.token == TokensId.Point) {
                    i++;
                    t = lex.tokens.get(i);
                    if (comprobarColor(t)) {
                        d.value = t.lexeme;
                        i++;
                        t = lex.tokens.get(i);
                        if (t.token == TokensId.ComaPoint) {
                            r.definiciones.add(d);
                        } else {
                            this.errorSintactico(t.lexeme, t.line);
                        }
                    } else {
                        this.errorSintactico(t.lexeme, t.line);
                    }

                } else {
                    this.errorSintactico(t.lexeme, t.line);
                }
                break;
            case FontSize:
                d.label = t.lexeme;
                d.line = t.line;
                i++;
                t = lex.tokens.get(i);
                if (t.token == TokensId.Point) {
                    i++;
                    t = lex.tokens.get(i);
                    if (t.token == TokensId.Size) {
                        d.value = t.lexeme;
                        i++;
                        t = lex.tokens.get(i);
                        if (t.token == TokensId.ComaPoint) {
                            r.definiciones.add(d);
                        } else {
                            this.errorSintactico(t.lexeme, t.line);
                        }
                    } else {
                        this.errorSintactico(t.lexeme, t.line);
                    }

                } else {
                    this.errorSintactico(t.lexeme, t.line);
                }
                break;
            case FontStyle:
                d.label = t.lexeme;
                d.line = t.line;
                i++;
                t = lex.tokens.get(i);
                if (t.token == TokensId.Point) {
                    i++;
                    t = lex.tokens.get(i);
                    if (comprobarStyle(t)) {
                        d.value = t.lexeme;
                        i++;
                        t = lex.tokens.get(i);
                        if (t.token == TokensId.ComaPoint) {
                            r.definiciones.add(d);
                        } else {
                            this.errorSintactico(t.lexeme, t.line);
                        }
                    } else {
                        this.errorSintactico(t.lexeme, t.line);
                    }

                } else {
                    this.errorSintactico(t.lexeme, t.line);
                }
                break;
            case TextAlign:
                d.label = t.lexeme;
                d.line = t.line;
                i++;
                t = lex.tokens.get(i);
                if (t.token == TokensId.Point) {
                    i++;
                    t = lex.tokens.get(i);
                    if (comprobarAlign(t)) {
                        d.value = t.lexeme;
                        i++;
                        t = lex.tokens.get(i);
                        if (t.token == TokensId.ComaPoint) {
                            r.definiciones.add(d);
                        } else {
                            this.errorSintactico(t.lexeme, t.line);
                        }
                    } else {
                        this.errorSintactico(t.lexeme, t.line);
                    }

                } else {
                    this.errorSintactico(t.lexeme, t.line);
                }
                break;
            default:
                this.errorSintactico(t.lexeme, t.line);
        }
        return i;
    }


    private boolean comprobarColor(Token t){
        switch(t.token){
            case Red:
            case Blue:
            case Black:
            case Green:
                return true;
            default:
                return false;
        }
    }

    private boolean comprobarStyle(Token t){
        switch(t.token){
            case Bold:
            case Italic:
            case Underlined:
            case Normal:
                return true;
            default:
                return false;
        }
    }


    private boolean comprobarAlign(Token t){
        switch(t.token){
            case Right:
            case Center:
            case Left:
                return true;
            default:
                return false;
        }
    }



    //Gesti�n de Errores Sint�ctico
    void errorSintactico (String e, int line) {
        errorSint = true;
        System.out.println("Error Sint�ctico : "+e+" en la l�nea "+line);
    }
}
