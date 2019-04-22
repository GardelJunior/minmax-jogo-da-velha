package com.gardel.tree;

public class ArvoreMinMax {
	
	public static JogoDaVelha solve(JogoDaVelha jogo, boolean max, int vez) {
		No raiz = new No(jogo,max,vez);
		int valor = raiz.solve();
		No best = null;
		int score = Integer.MAX_VALUE;
		for(No filho : raiz.getFilhos()) {
			if(filho.getStatus() == valor) {
				if(filho.getFilhos().size() < score) {
					score = filho.getFilhos().size();
					best = filho;
				}
			}
		}
		if(best==null) return jogo;
		return best.getValor();
	}
	
}
