package html.parser;

import html.ast.AstHtml;
import html.ast.impl.*;

public class Parser {


    Lexicon lex;
    boolean errorSint = false;

    public Parser (Lexicon lex) {
        this.lex = lex;
    }


    public AstHtml parse () {
        Program ast = new Program();
        int i = 0;
        int hasta = lex.tokens.size();
        while (i<hasta){
            Token t = lex.tokens.get(i);
            if(t.token==TokensId.HTMLA){
                i++;
                t = lex.tokens.get(i);
                while(t.token!=TokensId.HTMLB) {
                    switch (t.token) {
                        case HEADA:
                            i = insertarHead(i, ast);
                            break;
                        case BODYA:
                            i = insertarBody(i, ast);
                            break;
                        default:
                            errorSintactico(t.lexeme, t.line);
                    }
                    i++;
                    t = lex.tokens.get(i);
                }
            }else{
                errorSintactico(t.lexeme, t.line);
            }
           i++;
        }
        return ast;
    }

    private int insertarHead(int i, Program ast){
        int hasta = lex.tokens.size();
        i++;
        Head head = new Head();
        while (lex.tokens.get(i).token!=TokensId.HEADB){
            Token token = lex.tokens.get(i);
           switch (token.token){
               case TITLEA:
                   i=insertarTitle(i, head);
                   break;
               case LINKA:
                   i=insertarLink(i, head);
                   break;
               default:
                   errorSintactico(token.lexeme, token.line);
           }
            i++;
        }
        ast.head = head;
        return i;
    }

    private int insertarBody(int i, Program ast){
        i++;
        Body body = new Body();
        while (lex.tokens.get(i).token!=TokensId.BODYB){
            Token token = lex.tokens.get(i);
            switch (token.token){
                case PA:
                    i= insertarP(i, body);
                    break;
                case H1A:
                    i= insertarH1(i, body);
                    break;
                case H2A:
                    i= insertarH2(i, body);
                    break;
            }
            i++;
        }
        ast.body=body;
        return i;
    }


    private int insertarP(int i, Body body){
        i++;
        P parrafo = new P();
        while (lex.tokens.get(i).token!=TokensId.PB){
            Token token = lex.tokens.get(i);
            switch (token.token){
                case TEXT:
                    i = insertarAgrupTextos(i,parrafo);
                    break;
                case BA:
                    i= insertarBold(i, parrafo);
                   break;
                case IA:
                    i = insertarItalic(i, parrafo);
                    break;
                case UA:
                    i= insertarUndelined(i, parrafo);
                    break;
                default:
                    errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        body.parrafos.add(parrafo);
        return i;
    }

    private int insertarAgrupTextos(int i, P parrafo){

        AgrupTextos agrupTextos = new AgrupTextos();
        while (lex.tokens.get(i).token==TokensId.TEXT){
            Token token = lex.tokens.get(i);
            if(token.token==TokensId.TEXT){
                agrupTextos.textos.add(token.lexeme);
            }else{
                errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        parrafo.bloques.add(agrupTextos);
        i--;
        return i;
    }

    private int insertarItalic(int i, P parrafo){
        i++;
        Italic italic = new Italic();
        while (lex.tokens.get(i).token!=TokensId.IB){
            Token token = lex.tokens.get(i);
            if(token.token==TokensId.TEXT){
                italic.textos.add(token.lexeme);
            }else{
                errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        parrafo.bloques.add(italic);
        return i;
    }

    private int insertarBold(int i, P parrafo){
        i++;
        Bold bold = new Bold();
        while (lex.tokens.get(i).token!=TokensId.BB){
            Token token = lex.tokens.get(i);
            if(token.token==TokensId.TEXT){
                bold.textos.add(token.lexeme);
            }else{
                errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        parrafo.bloques.add(bold);
        return i;
    }

    private int insertarUndelined(int i, P parrafo){
        i++;
        Underlined underlined = new Underlined();
        while (lex.tokens.get(i).token!=TokensId.UB){
            Token token = lex.tokens.get(i);
            if(token.token==TokensId.TEXT){
                underlined.textos.add(token.lexeme);
            }else{
                errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        parrafo.bloques.add(underlined);
        return i;
    }


    private int insertarH1(int i, Body body){
        i++;
        H1 h1 = new H1();
        while (lex.tokens.get(i).token!=TokensId.H1B){
            Token token = lex.tokens.get(i);
            if(token.token==TokensId.TEXT){
                h1.textos.add(token.lexeme);
            }else{
                errorSintactico(token.lexeme, token.line);
            }

            i++;
        }
        body.parrafos.add(h1);
        return i;
    }

    private int insertarH2(int i, Body body){
        i++;
        H2 h2 = new H2();
        while (lex.tokens.get(i).token!=TokensId.H2B){
            Token token = lex.tokens.get(i);
            if(token.token==TokensId.TEXT){
                h2.textos.add(token.lexeme);
            }else{
                errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        body.parrafos.add(h2);
        return i;
    }


    private int insertarTitle(int i, Head head){
        i++;
        Title title = new Title();
        while (lex.tokens.get(i).token!=TokensId.TITLEB){
            Token token = lex.tokens.get(i);
            if(token.token== TokensId.TEXT){
                title.textos.add(token.lexeme);
            }else{
                errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        head.title = title;
        return i;
    }

    private int insertarLink(int i, Head head){
        i++;
        Link link = new Link();
        while (lex.tokens.get(i).token!=TokensId.LINKB){
            Token token = lex.tokens.get(i);
            switch (token.token){
                case HREF:
                    i++;
                    token = lex.tokens.get(i);
                    if(token.token==TokensId.IGUAL){
                        i++;
                        token = lex.tokens.get(i);
                        if(token.token==TokensId.URL){
                            link.href = token.lexeme.substring(1, token.lexeme.length()-2);
                        }else{
                            errorSintactico(token.lexeme, token.line);
                        }
                    }else{
                        errorSintactico(token.lexeme, token.line);
                    }
                    break;
                case REL:
                    i++;
                    token = lex.tokens.get(i);
                    if(token.token==TokensId.IGUAL){
                        i++;
                        token = lex.tokens.get(i);
                        if(token.token==TokensId.URL){
                            link.rel = token.lexeme.substring(1, token.lexeme.length()-2);
                        }else{
                            errorSintactico(token.lexeme, token.line);
                        }
                    }else{
                        errorSintactico(token.lexeme, token.line);
                    }
                    break;
                case TYPE:
                    i++;
                    token = lex.tokens.get(i);
                    if(token.token==TokensId.IGUAL){
                        i++;
                        token = lex.tokens.get(i);
                        if(token.token==TokensId.URL){
                            link.type = token.lexeme.substring(1, token.lexeme.length()-2);
                        }else{
                            errorSintactico(token.lexeme, token.line);
                        }
                    }else{
                        errorSintactico(token.lexeme, token.line);
                    }
                    break;
                default:
                    errorSintactico(token.lexeme, token.line);
            }
            i++;
        }
        head.link= link;
        return i;
    }

    //Gesti�n de Errores Sint�ctico
    void errorSintactico (String e, int line) {
        errorSint = true;
        System.out.println("Error Sint�ctico : "+e+" en la l�nea "+line);
    }
}
