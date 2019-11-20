package html.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lexicon {


    // Gesti�n de tokens
    List<Token> tokens = new ArrayList<Token>();
    int i = 0; //�ltimo token entregado en getToken()
    //Gesti�n de lectura del fichero
    FileReader filereader;
    boolean charBuffUsed = false;
    char charBuff;
    int line = 1; // indica la l�nea del fichero fuente

    HashSet<Character> charText = new HashSet<Character>();


    public Lexicon (FileReader f) {
        this.loadSet();
        filereader = f;
        String lex;
        try{
            char valor=(char) 0;
            while(valor!=(char) -1){
                valor=nextChar();
                switch (valor) {
                    case '<':
                        valor=nextChar();
                        switch (valor){
                            case 'h':
                                insertarTokensAperturaH();
                                break;
                            case 'b':
                                insertarTokensAperturaB();
                                break;
                            case 'i':
                                insertarTokenLexemaSimple("i",'>',"i>",TokensId.IA, false);
                                break;
                            case 'u':
                                insertarTokenLexemaSimple("u",'>',"u>",TokensId.UA, false);
                                break;
                            case 't':
                                insertarTokenLexemaSimple("t",'>',"title>",TokensId.TITLEA, false);
                                break;
                            case 'p':
                                insertarTokenLexemaSimple("p",'>',"p>",TokensId.PA, false);
                                break;
                            case 'l':
                                valor = insertarTokenLink(valor);
                                break;
                            case '/':
                                valor=nextChar();
                                insertarTokensCierre(valor);
                                break;
                            default:
                                errorLexico(valor+"");
                        }
                        break;
                    case '"':
                        lex = getLexeme("\"", '"');
                        tokens.add(new Token(TokensId.URL, lex, line));
                        break;
                    case '\n':
                        line++;
                    case '\r':
                    case '\t':
                    case (char) -1:
                    case ' ':
                        break;
                    default:
                        lex = getLexemeTEXT(valor+"");
                        tokens.add(new Token(TokensId.TEXT, lex, line) );
                }
            }
            filereader.close();
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }

    }

    private void insertarTokensAperturaB() throws IOException {
        String lex;
        lex= getLexeme("b",'>');
        if(lex.equals("body>")){
            tokens.add(new Token(TokensId.BODYA,"<"+lex, line));
        }else if(lex.equals("b>")){
            tokens.add(new Token(TokensId.BA,"<"+lex, line));
        }else{
            errorLexico(lex);
        }
    }

    private char insertarTokenLink(char valor) throws IOException {
        String lex;
        lex= getLexeme("l", 'k');
        if(lex.equals("link")){
            tokens.add(new Token(TokensId.LINKA,"<"+lex, line));
            do {
                valor = nextChar();
                switch (valor) {
                    case 'h':
                        lex = getLexeme("h", 'f');
                        if (lex.equals("href")) {
                            tokens.add(new Token(TokensId.HREF, lex, line));
                            valor = nextChar();
                            valor = insertarTokensIgualyURL(valor);
                        } else {
                            errorLexico(lex);
                        }
                        break;

                    case 'r':
                        lex = getLexeme("r", 'l');
                        if (lex.equals("rel")) {
                            tokens.add(new Token(TokensId.REL, lex, line));
                            valor = nextChar();
                            valor = insertarTokensIgualyURL(valor);
                        } else {
                            errorLexico(lex);
                        }
                        break;
                    case 't':
                        lex = getLexeme("t", 'e');
                        if (lex.equals("type")) {
                            tokens.add(new Token(TokensId.TYPE, lex, line));
                            valor = nextChar();
                            valor = insertarTokensIgualyURL(valor);
                        } else {
                            errorLexico(lex);
                        }
                        break;
                }
            }while(valor!='>');
            tokens.add(new Token(TokensId.LINKB, valor+"", line));

        }else{
            errorLexico(lex);
        }
        return valor;
    }

    private char insertarTokensIgualyURL(char valor) throws IOException {
        String lex;
        if (valor == '=') {
            tokens.add(new Token(TokensId.IGUAL, valor + "", line));
            valor = nextChar();
            lex = getLexeme("\"", '"');
            tokens.add(new Token(TokensId.URL, lex, line));
        }else{
            errorLexico(valor+"");
        }
        return valor;
    }

    private void insertarTokensCierre(char valor) throws IOException {
        String lex;
        switch (valor) {
            case 'h':
                insertarTokensCierreH();
                break;
            case 'b':
                lex = getLexeme("b", '>');
                if (lex.equals("body>")) {
                    tokens.add(new Token(TokensId.BODYB, "</" + lex, line));
                } else if (lex.equals("b>")) {
                    tokens.add(new Token(TokensId.BB, "</" + lex, line));
                } else {
                    errorLexico(lex);
                }
                break;
            case 'i':
                insertarTokenLexemaSimple("i",'>',"i>",TokensId.IB, true);
                break;
            case 'u':
                insertarTokenLexemaSimple("u",'>',"u>",TokensId.UB, true);
                break;
            case 't':
                insertarTokenLexemaSimple("t",'>',"title>",TokensId.TITLEB, true);
                break;
            case 'p':
                insertarTokenLexemaSimple("p",'>',"p>",TokensId.PB, true);
                break;
        }
    }

    private void insertarTokenLexemaSimple(String lexStart, Character finChar,String lexComparacion, TokensId token, boolean cierre )throws IOException{
        String lex;
        lex = getLexeme(lexStart, finChar);
        if (lex.equals(lexComparacion)) {
            if(cierre) {
                tokens.add(new Token(token, "</" + lex, line));
            }else{
                tokens.add(new Token(token, "<" + lex, line));
            }
        } else {
            errorLexico(lex);
        }
    }


    private void insertarTokensCierreH() throws IOException {
        String lex;
        lex = getLexeme("h", '>');
        switch (lex) {
            case "html>":
                tokens.add(new Token(TokensId.HTMLB, "</" + lex, line));
                break;
            case "h1>":
                tokens.add(new Token(TokensId.H1B, "</" + lex, line));
                break;
            case "h2>":
                tokens.add(new Token(TokensId.H2B, "</" + lex, line));
                break;
            case "head>":
                tokens.add(new Token(TokensId.HEADB, "</" + lex, line));
                break;
            default:
                errorLexico(lex);
        }
    }

    private void insertarTokensAperturaH() throws IOException {
        String lex;
        lex =getLexeme("h",'>');
        switch (lex){
            case "html>":
                tokens.add(new Token(TokensId.HTMLA,"<"+lex, line));
                break;
            case "h1>":
                tokens.add(new Token(TokensId.H1A,"<"+lex, line));
                break;
            case "h2>":
                tokens.add(new Token(TokensId.H2A,"<"+lex, line));
                break;
            case "head>":
                tokens.add(new Token(TokensId.HEADA,"<"+lex, line));
                break;
            default:
                errorLexico(lex);
        }
    }


    // ++
    // ++ Operaciones para el Léxio
    // ++

    //Privadas
    // Saca del fichero fuente un lexema que comienza por la cadena lexStart y termina en el caracter finChar
    String getLexeme (String lexStart, char finChar) throws IOException {
        String lexReturned = lexStart;
        char valor;
        do {
            valor=nextChar();
            lexReturned = lexReturned+((char) valor);
        } while (((char) valor != finChar) && ((char) valor != -1));
        //returnChar(valor); Consume hasta el último caracter, no hay cnada que devolver
        return lexReturned;
    }

    // Saca del fichero un lexema de texto que termina con cualquier caracter que esté contenido en charText.
    // Necesita devolver el último caracter porque la condición del es con un caracter que no
    // pertenece al lexema
    String getLexemeTEXT (String lexStart) throws IOException {
        String lexReturned = lexStart;
        char valor = nextChar();
        while (charText.contains(((char) valor)) && ((char) valor != -1)) {
            lexReturned = lexReturned+((char) valor);
            valor=nextChar();
        }
        returnChar(valor);
        return lexReturned;
    }

    // Crea un conjunto con los caracteres permitidos para los lexemas de texto (TEXT)
    void loadSet () {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.,;:+-*/()[]!?";
        int i=0;
        Character a = new Character('a');
        while (i < s.length()) {
            a = s.charAt(i);
            charText.add(a);
            i++;
        }
        //System.out.println(charText);
    }

    // Devuelve el siguiente caracter, si se ha devuelto previamente uno lo devuelve del buffer, si no lo devuelve del fichero
    char nextChar() throws IOException{
        if (charBuffUsed) {
            charBuffUsed = false;
            return charBuff;
        } else {
            int valor=filereader.read();
            return ((char) valor);
        }
    }

    // Devuelde un caracter al buffer
    void returnChar (char r) {
        charBuffUsed = true;
        charBuff = r;
    }

    void errorLexico (String e) {
        System.out.println("Error léxico en : "+e);
    }

    // Get Token
    public Token getToken () {
        if (i < tokens.size()) {
            return tokens.get(i++);
        }
        return new Token (TokensId.EOF,"EOF", line);
    }
}
