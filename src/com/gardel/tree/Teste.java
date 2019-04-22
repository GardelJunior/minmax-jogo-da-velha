package com.gardel.tree;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Teste {
	
	static JogoDaVelha jdv = new JogoDaVelha();
	static JButton[] buttons = new JButton[9];
	public static void main(String[] args) {
		JFrame j = new JFrame("Jogo da Velha");
		j.setLayout(null);
		j.setMinimumSize(new Dimension(400, 400));
		j.setPreferredSize(new Dimension(400, 400));
		j.setSize(new Dimension(400, 400));
		int width = j.getWidth()/3;
		int height = j.getHeight()/3;
		for(int i = 0 ; i < 9 ; i++) {
			JButton btn = new JButton(" ");
			buttons[i] = btn;
			btn.setFont(new Font("Arial",Font.BOLD,25));
			j.add(btn);
			btn.setBounds((i%3) * width, (i/3) * height, width, height);
			btn.setActionCommand(String.valueOf(i));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int status = jdv.getStatus();
					if(status == 1 || status == -1 || jdv.isEmpate()) {
						showMessage(status, jdv.isEmpate());
					}else {
						int valor = Integer.parseInt(e.getActionCommand());
						if(jdv.tabuleiro[valor] != ' ') return;
						jdv.setX(valor % 3, valor / 3);
						status = jdv.getStatus();
						showMessage(jdv.getStatus(), jdv.isEmpate());
						jdv = ArvoreMinMax.solve(jdv, false, 1);
						for(int i = 0 ; i < 9 ; i++) {
							buttons[i].setText(String.valueOf(jdv.tabuleiro[i]));
						}
						showMessage(jdv.getStatus(), jdv.isEmpate());
					}
				}
			});
		}
		j.pack();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
	}
	
	public static boolean showMessage(int status, boolean isEmpate) {
		if(status == 1 || status == -1 || isEmpate) {
			String msg = "";
			if(jdv.isEmpate()) {
				msg = "Empate! Deseja jogar denovo?";
			}else {
				msg = ((status==1)? "O Jogador X Ganhou! <br>" : "O Jogador O Ganhou! <br>")+" Deseja jogar denovo?";
			}
			int r = JOptionPane.showConfirmDialog(null, msg.replace("<br>", "\n"),"Empate!",JOptionPane.YES_NO_OPTION);
			if(r == JOptionPane.OK_OPTION) {
				jdv = new JogoDaVelha();
				for(int i = 0 ; i < 9 ; i++) {
					buttons[i].setText(String.valueOf(jdv.tabuleiro[i]));
				}
			}
			return true;
		}
		return false;
	}
}
