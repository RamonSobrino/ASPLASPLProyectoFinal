package css.parser;

import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
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
		filereader = f;
		String lex;
		try{
			char valor=(char) 0;
			while(valor!=(char) -1){
				valor=nextChar();

				switch (valor){
                    case '{':
                        tokens.add(new Token(TokensId.ParentesisA,valor+"", line));
                        break;
                    case '}':
                        tokens.add(new Token(TokensId.ParentesisB,valor+"", line));
                        break;
                    case ':':
                        tokens.add(new Token(TokensId.Point,valor+"", line));
                        break;
                    case ';':
                        tokens.add(new Token(TokensId.ComaPoint,valor+"", line));
                        break;
                        //Para eliminar comentarios en general hay que comprobar que viene una barra y
                        //luego comprobar directamente
                    case '\n':
                        line++;
                    case '\r':
                    case '\t':
                    case (char) -1:
                    case ' ':
                        break;
                    default:
                        if(Character.isDigit(valor)){
                            String size = getSize(valor+"");
                            tokens.add(new Token(TokensId.Size, size, line));
                        }
                        if(Character.isAlphabetic(valor)){
                            String palabra = getText(valor+"");
                            switch (palabra){
                                case "color":
                                    tokens.add(new Token(TokensId.Color,palabra, line));
                                    break;
                                case "font-size":
                                    tokens.add(new Token(TokensId.FontSize,palabra, line));
                                    break;
                                case "font-style":
                                    tokens.add(new Token(TokensId.FontStyle,palabra, line));
                                    break;
                                case "text-align":
                                    tokens.add(new Token(TokensId.TextAlign,palabra, line));
                                    break;
                                case "black":
                                    tokens.add(new Token(TokensId.Black,palabra, line));
                                    break;
                                case "green":
                                    tokens.add(new Token(TokensId.Green,palabra, line));
                                    break;
                                case "blue":
                                    tokens.add(new Token(TokensId.Blue,palabra, line));
                                    break;
                                case "red":
                                    tokens.add(new Token(TokensId.Red,palabra, line));
                                    break;
                                case "center":
                                    tokens.add(new Token(TokensId.Center,palabra, line));
                                    break;
                                case "right":
                                    tokens.add(new Token(TokensId.Right,palabra, line));
                                    break;
                                case "left":
                                    tokens.add(new Token(TokensId.Left,palabra, line));
                                    break;
                                case "normal":
                                    tokens.add(new Token(TokensId.Normal,palabra, line));
                                    break;
                                case "italic":
                                    tokens.add(new Token(TokensId.Italic,palabra, line));
                                    break;
                                case "bold":
                                    tokens.add(new Token(TokensId.Bold,palabra, line));
                                    break;
                                case "underlined":
                                    tokens.add(new Token(TokensId.Underlined,palabra, line));
                                    break;
                                case "h1":
                                case "h2":
                                case "p":
                                default:
                                    tokens.add(new Token(TokensId.Ident,palabra, line));
                                    break;

                            }
                        }
                }

			//...
			}
			filereader.close();
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
		
	}
	
	// ++
	// ++ Operaciones para el Sintactico
	// ++
	// Devolver el �ltimo token
	public void returnLastToken () {
		i--;
	}
	
	// Get Token
	public Token getToken () {
		if (i < tokens.size()) {
			return tokens.get(i++);
		}
		return new Token (TokensId.EOF,"EOF", line);
	}	
	// ++
	// ++ Operaciones para el Sintactico
	// ++

	//Privadas
	String getSize (String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor;
		do {
			valor=nextChar();
			lexReturned = lexReturned+(valor);
		} while ((valor != 'p') && (valor != -1));
		//returnChar(valor);
		if (valor == 'p') {
			valor=nextChar();
			if (valor == 'x') {
				lexReturned = lexReturned+(valor);
			} else {
				errorLexico ("Encontrado "+lexReturned+". Se esperada un token SIZE.");
				return null;
			}
		}
		return lexReturned;
	}

	String getText (String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor = nextChar();
		while (Character.isDigit(valor) || Character.isAlphabetic(valor) || (valor == '-')) {
			lexReturned = lexReturned+(valor);
			valor=nextChar();
		}
		returnChar(valor);
		return lexReturned;
	}
	
	char nextChar() throws IOException{
		if (charBuffUsed) {
			charBuffUsed = false;
			return charBuff;
		} else {
		int valor=filereader.read();
		return ((char) valor);
		}
	}
	
	void returnChar (char r) {
		charBuffUsed = true;
		charBuff = r;
	}

	void errorLexico (String e) {
		System.out.println("Error l�xico en : "+e);
	}
}
