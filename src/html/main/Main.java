package html.main;

import html.parser.Lexicon;
import html.parser.Token;
import html.parser.TokensId;

import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("EX4.HTML");
		Lexicon lex = new Lexicon(filereader);
		listaTokens(lex);
//		Parser parser = new Parser (lex);
//		AstCss ast = parser.parse();
//
//
//		//Visitor print css
//
//        Visitor visitor = new ImprimeCSSVisitor();
//        System.out.println(  ast.accept(visitor, null));

	}

	//Auxiliares
	//Lista de Tokens
	static void listaTokens (Lexicon lex) {
		Token t = lex.getToken();
		while (t.getToken() != TokensId.EOF) {
			System.out.println(t.toString());
			t = lex.getToken();
		}
		System.out.println ("\nFin de fichero. \n"+t.toString());	
	}
}
