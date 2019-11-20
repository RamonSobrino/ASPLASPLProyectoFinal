package main;

import render.Render;

public class Main {

    public static void main(String[] args){

//		//Visitor print css
//
        Render render = new Render();
        System.out.println(  render.render("EX4.HTML"));
        System.out.println("Fin de todo");

    }
}
