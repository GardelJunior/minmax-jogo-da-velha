package com.gardel.tree;

import java.util.List;

public class ArvoreMinMax {
	
	/**
	 * Método que calcula a melhor jogada a ser tomada dado um tabuleiro.
	 * @param jogo : O tabuleiro
	 * @param max : [True|False] Se é maximizador(True) ou minimizador(False)
	 * @param vez : [0|1] Se é a vez do jogador 'X' (0) ou a vez do jogador 'O' (1)
	 * @return Intância do JogoDaVelha com a melhor jogada inserida.
	 */
	public static JogoDaVelha solve(JogoDaVelha jogo, boolean max, int vez) {
		return solve(jogo, 0, false, 1).tabuleiro;
	}
	
	/**
	 * Função recursiva que cria a árvore de decisão usando o algoritmo do minMax
	 * @param jogo : O tabuleiro
	 * @param profundidade : A profundidade do nó, ela influencia no valor do nó.
	 * @param max : [True|False] Se é maximizador(True) ou minimizador(False)
	 * @param vez : [0|1] Se é a vez do jogador 'X' (0) ou a vez do jogador 'O' (1)
	 * @return Jogo
	 */
	private static Jogada solve(JogoDaVelha jogo, int profundidade, boolean max, int vez) {
		if(jogo.isTerminal()) {
			return new Jogada(jogo,jogo.getStatus()*100 - profundidade);
		}
		if(max) {
			int maximo = Integer.MIN_VALUE;
			Jogada melhorJogada = null;
			List<Integer> jogadas = jogo.getPosicoesVazias();
			
			for(Integer pos : jogadas) {
				JogoDaVelha copia = new JogoDaVelha(jogo);
				
				if(vez==0) copia.setX(pos); else copia.setO(pos);
				
				Jogada jog = solve(copia,profundidade+1,false,(vez+1)%2);
				if(jog.pontuacao > maximo) {
					maximo = jog.pontuacao;
					melhorJogada = jog;
					melhorJogada.tabuleiro = copia;
				}
			}
			return melhorJogada;
			
		}else {
			
			int minimo = Integer.MAX_VALUE;
			Jogada melhorJogada = null;
			List<Integer> jogadas = jogo.getPosicoesVazias();
			
			for(Integer pos : jogadas) {
				JogoDaVelha copia = new JogoDaVelha(jogo);
				
				if(vez==0) copia.setX(pos); else copia.setO(pos);
				
				Jogada jog = solve(copia,profundidade+1,true,(vez+1)%2);
				if(jog.pontuacao < minimo) {
					minimo = jog.pontuacao;
					melhorJogada = jog;
					melhorJogada.tabuleiro = copia;
				}
			}
			return melhorJogada;
		}
	}
}
