package com.gardel.tree;

import java.awt.Point;
import java.util.*;

public class No {
	
	private List<No> filhos = new ArrayList<No>();
	private JogoDaVelha valor;
	private boolean max;
	private int status;
	
	public No(JogoDaVelha valor, boolean max, int vez) {
		this.valor = valor;
		this.max = max;
		List<Point> free = valor.getEmptyPositions();
		for(Point p : free) {
			JogoDaVelha temp = new JogoDaVelha(valor);
			if(vez==0) temp.setX(p.x, p.y); else temp.setO(p.x, p.y);
			addFilho(new No(temp, !max, (vez+1)%2));
		}
	}
	
	public int solve() {
		if(filhos.size() == 0) {
			return valor.getStatus();
		}
		if(max) {
			Integer max = Integer.MIN_VALUE;
			for(No filho : filhos) {
				max = Math.max(max, filho.solve());
			}
			this.status = max;
			return max;
		}else {
			Integer min = Integer.MAX_VALUE;
			for(No filho : filhos) {
				min = Math.min(min, filho.solve());
			}
			this.status = min;
			return min;
		}
	}
	
	public int getStatus() {
		return status;
	}

	public List<No> getFilhos() {
		return filhos;
	}

	public No(JogoDaVelha valor) {
		this.valor = valor;
	}
	
	public boolean isMax() {
		return max;
	}

	public void setMax(boolean max) {
		this.max = max;
	}

	public void addFilho(No filho) {
		this.filhos.add(filho);
	}

	public JogoDaVelha getValor() {
		return valor;
	}

	public void setValor(JogoDaVelha valor) {
		this.valor = valor;
	}
}
