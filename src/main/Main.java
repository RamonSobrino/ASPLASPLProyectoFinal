package main;

import pintor.Pintor;
import render.FormattedPage;
import render.Render;

public class Main {

    public static void main(String[] args){

//		//Visitor print css
//
        Render render = new Render();
        FormattedPage page = render.render("EX5.HTML");
        System.out.println(  page.toString());

        Pintor pintor= new Pintor(page);

        pintor.paint();
    }
}

