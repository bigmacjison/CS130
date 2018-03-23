/**
CS 130: Theory of Computation Project Part 1

Submitted by: Mark Daniel M. Jison
Date: March 23, 2018
**/

import java.util.*;
import java.io.*;

public class Lex{
	
	String text = ""; // stores the text from the input text file
	String finalA =  // output of the program showing the tokens and lexemes
	"TOKEN          LEXEME\n" +
	"---------------------------------\n";
	private int currentState = 0;
	private int nextState[][] = { 
		
		{ 1, 0, 17, 12, 10, 20, 13, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 0}, //q0
		{ 0, 2, 0, 9, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q1
		{ 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q2
		{ 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q3
		{ 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, //q4
		{ 0, 0, 6, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, //q5
		{ 0, 0, 7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, //q6
		{ 0, 0, 0, 5, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}, //q7
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q8
		{ 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q9
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  //q10
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q11
		{ 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q12
		{ 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0}, //q13
		{ 0, 0, 0, 0, 0, 15, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q14
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q15
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q16
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q17
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q18
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q19
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q20
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q21
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q22
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q23
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q24
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q25
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q26
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q27
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q28
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q29
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //q30
	};
	
	public Lex(){
		
	}
	
	public static void main(String args[]){
		Lex l = new Lex();
		System.out.println(l.run(args[0]));
	}
	
	public String run(String arg){
		try{
			Scanner scn = new Scanner(new File(arg));
			do{
				text += scn.nextLine();
				//text += "\n";
			}while(scn.hasNext());
		}catch(FileNotFoundException ex){
			System.out.println("File does not exists.");
		}
		System.out.println(text);
		
		int prevState = currentState;
		int charIndex = 0;
		String tempLexeme = "";
		
		for(int i = 0; i < text.length(); i++){
			prevState = currentState;
			charIndex = charClass(text.charAt(i));
			//System.out.println(currentState + " " + charIndex + " " + text.charAt(i));
			currentState = nextState[currentState][charIndex]; // this is the matrix where currentState is the state and charIndex depends on the current char
			//System.out.println(currentState);
			//System.out.println(text.charAt(i));
			switch(currentState){
				
				case 0:
					//currentState = 0;
					break;
				case 1:
					if(text.charAt(i+1) == ' ' || Character.isDigit(text.charAt(i+1))){
						finalA += "LTHAN       " + text.charAt(i) + "\n";
						currentState = 0;
					}
					break;
				case 2:
					//finalA += "! read\n";
					break;
				case 3:
					//finalA += "- read\n";
					break;
				case 4:
					//finalA += "2nd - read\n";
					break;
				case 5:
					//finalA += "part of comment read\n";
					break;
				case 6:
					//finalA += "3rd - read\n";
					break;
				case 7:
					//finalA += "last - read\n";
					break;
				case 8:
					//finalA += "> read\n";
					currentState = 0;
					//reset state
					break;
				case 9:
					//System.out.println("Im in 9");
					//System.out.println(text.charAt(i));
					//finalA += "read a letter after <     \n";
					if(prevState == 5){
						break;
					}else{
						tempLexeme += text.charAt(i);
						//finalA += tempLexeme + "\n";
						if(text.charAt(i+1) == '>'){
							finalA += "TAGIDENT    ";
							finalA += "<" + tempLexeme + "\n";
							tempLexeme = "";
							currentState = 0;
						}
					}
					break;
				case 10:
					//System.out.println("Im in 10");
					finalA += "GTHAN       " + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 11:
					tempLexeme += text.charAt(i);
					finalA += "ENDTAGHEAD  <" + tempLexeme + "\n";
					tempLexeme = "";
					currentState = 0;
					break;
				case 12:
					tempLexeme += text.charAt(i);
					if(!(Character.isLetter(text.charAt(i+1))) || (text.charAt(i+1) == ' ')){
						finalA += "IDENT       ";
						finalA += tempLexeme + "\n";
						tempLexeme = "";
						currentState = 0;
					}
					break;
				case 13:
					tempLexeme += text.charAt(i);
					if("+-*/% ".indexOf(Character.toString(text.charAt(i+1))) != -1){
						finalA += "NUMBER      ";
						finalA += tempLexeme + "\n";
						tempLexeme = "";
						currentState = 0;
					}
					break;
				case 14:
					tempLexeme += text.charAt(i);
					if("+-*/% ".indexOf(Character.toString(text.charAt(i+1))) != -1){
						finalA += "NUMBER      ";
						finalA += tempLexeme + "\n";
						tempLexeme = "";
						currentState = 0;
					}
					break;
				case 15:
					finalA += "Badly formed number\n";
					currentState = 0;
					break;
				case 16:
					finalA += "PLUS        "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 17:
					finalA += "MINUS       "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 18:
					if(text.charAt(i+1) == '*'){
						finalA += "EXP        "    + text.charAt(i) + "*\n";
						currentState = 0;
						break;
					}
					finalA += "MULT        "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 20:
					finalA += "DIVIDE      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 21:
					finalA += "MODULO      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 22:
					finalA += "LPAREN      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 23:
					finalA += "RPAREN      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 24:
					finalA += "EQUALS      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 25:
					finalA += "COLON       "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 26:
					finalA += "COMMA       "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 27:
					finalA += "SCOLON      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 28:
					finalA += "PERIOD      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 29:
					finalA += "QUOTE       "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
				case 30:
					finalA += "DQUOTE      "    + text.charAt(i) + "\n";
					currentState = 0;
					break;
			}
		}
		finalA += "EOF";
		return finalA;
	}
	
	private int charClass(char c){
		if(c == 60){ // <
			return 0;
		}else if(c == 32){ //space
			return 21;
		}else if(c == 33){ //!
			return 1;
		}else if(c == '-'){ // 45
			return 2;
		}else if(c == 43){ //+
			return 7;
		}else if(c == '-'){
			return 8; //or 2
		}else if(c == 42){ //*
			return 9;
		}else if(c == 47){ // /
			return 5; //or 10
		}else if(c == 37){ //%
			return 11;
		}else if(c == 40){ // (
			return 12;
		}else if(c == 41){ // )
			return 13;
		}else if(c == 61){ // =
			return 14;
		}else if(c == 58){ // :
			return 15;
		}else if(c == 44){ // comma
			return 16;
		}else if(c == 59){ // ;
			return 17;
		}else if(c == 46){ // period
			return 18;
		}else if(c == 39){
			return 19;
		}else if(c == 34){
			return 20;
		}else if(Character.isLetter(c)){
			return 3;
		}else if(Character.isDigit(c)){
			return 6;
		}else if(!("0123456789".indexOf(Character.toString(c)) == -1)){
			return 5;
		}else if(c == 62){ // >
			return 4;
		}else if("<!->".indexOf(Character.toString(c)) == -1){
			return 3;
		}else{
			return -1;
		}
	}
}