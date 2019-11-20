package html.main;

import html.ast.AstHtml;
import html.parser.Lexicon;
import html.parser.Parser;
import html.parser.Token;
import html.parser.TokensId;
import html.visitor.ImprimeHtmlVisitor;
import html.visitor.Visitor;

import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		FileReader filereader = new FileReader ("EX4.HTML");
		Lexicon lex = new Lexicon(filereader);
		listaTokens(lex);
		Parser parser = new Parser (lex);
		AstHtml ast = parser.parse();

//		//Visitor print css
//
        Visitor visitor = new ImprimeHtmlVisitor();
       System.out.println(  ast.accept(visitor, null));
		System.out.println("Fin de todo");

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
