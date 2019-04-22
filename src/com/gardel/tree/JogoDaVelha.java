package com.gardel.tree;

import java.awt.*;
import java.util.*;
import java.util.List;

public class JogoDaVelha {
	public char[] tabuleiro = {' ',' ',' ',
						' ',' ',' ',
						' ',' ',' '};
	public int getStatus() {
		char c1 = tabuleiro[0];
		char c2 = tabuleiro[4];
		char c3 = tabuleiro[8];
		
		//Horizontais
		if(c1!=' ' && tabuleiro[1]==c1 && tabuleiro[2]==c1) return c1=='X'? 1 : -1;
		if(c2!=' ' && tabuleiro[3]==c2 && tabuleiro[5]==c2) return c2=='X'? 1 : -1;
		if(c3!=' ' && tabuleiro[6]==c3 && tabuleiro[7]==c3) return c3=='X'? 1 : -1;
		
		//Verticais
		if(c1!=' ' && tabuleiro[3]==c1 && tabuleiro[6]==c1) return c1=='X'? 1 : -1;
		if(c2!=' ' && tabuleiro[1]==c2 && tabuleiro[7]==c2) return c2=='X'? 1 : -1;
		if(c3!=' ' && tabuleiro[2]==c3 && tabuleiro[8]==c3) return c3=='X'? 1 : -1;

		//Diagonais
		if(c1!=' ' && c1==c2 && c1==c3) 					return c1=='X'? 1 : -1;
		if(c2!=' ' && tabuleiro[2]==c2 && tabuleiro[6]==c2) return c2=='X'? 1 : -1;
		
		if(isEmpate()) return 0;
		
		return 0;
	}
	
	public boolean isEmpate() {
		for(int i = 0, c = 0; i < 9 ; i++) {
			if(tabuleiro[i] != ' ') c++;
			if(c==9) return true;
		}
		return false;
	}
	
	public JogoDaVelha() {};
	public JogoDaVelha(JogoDaVelha jdv) {
		for(int i = 0 ; i < 9 ; i++) 
			this.tabuleiro[i] = jdv.tabuleiro[i];
	};
	
	public void setX(int x, int y) {
		if(tabuleiro[x + y*3]!=' ') return;
		tabuleiro[x + y*3] = 'X';
	}
	public void setO(int x, int y) {
		if(tabuleiro[x + y*3]!=' ') return;
		tabuleiro[x + y*3] = 'O';
	}
	
	public List<Point> getEmptyPositions(){
		List<Point> positions = new ArrayList<Point>();
		for(int i = 0 ; i < 9 ; i++) {
			if(tabuleiro[i]==' ') positions.add(new Point(i % 3, i / 3));
		}
		return positions;
	}
	
	public String toString() {
		return String.format(" %c | %c | %c \n"+
							 "---+---+---\n"+
							 " %c | %c | %c \n"+
							 "---+---+---\n"+
							 " %c | %c | %c ", tabuleiro[0],tabuleiro[1],tabuleiro[2],tabuleiro[3],tabuleiro[4],tabuleiro[5],tabuleiro[6],tabuleiro[7],tabuleiro[8]);
	}
}