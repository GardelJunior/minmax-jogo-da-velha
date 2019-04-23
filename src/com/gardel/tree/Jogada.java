package com.gardel.tree;

public class Jogada {
	public JogoDaVelha tabuleiro;
	public int pontuacao;
	
	public Jogada(JogoDaVelha tabuleiro, int pontuacao) {
		this.tabuleiro = tabuleiro;
		this.pontuacao = pontuacao;
	}
	
	public Jogada(int pontuacao) {
		this.tabuleiro = null;
		this.pontuacao = pontuacao;
	}
}
