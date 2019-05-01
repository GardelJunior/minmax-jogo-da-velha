package com.gardel.test;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gardel.tree.ArvoreMinMax;
import com.gardel.tree.JogoDaVelha;

/**
 * A classe Teste contém a main, nela uma janela é instanciada para fornecer ao usuário
 * uma interface gráfica para jogar o Jogo da Velha.
 * 
 * @author Gardel Júnior N 388691 UFC - Russas
 *
 */
public class TestePlayerVsBot {
	
	/* Variáveis estáticas */
	static JogoDaVelha jdv = new JogoDaVelha();
	static JButton[] buttons = new JButton[9];
	
	/* Main */
	public static void main(String[] args) {
		JFrame j = new JFrame("Jogo da Velha");
		j.setLayout(null);
		j.getContentPane().setPreferredSize(new Dimension(400, 400));
		j.getContentPane().setSize(new Dimension(400, 400));
		int width = j.getContentPane().getWidth()/3;
		int height = j.getContentPane().getHeight()/3;
		for(int i = 0 ; i < 9 ; i++) {
			JButton btn = new JButton(" ");
			buttons[i] = btn;
			btn.setFont(new Font("Arial",Font.BOLD,25));
			j.getContentPane().add(btn);
			btn.setBounds((i%3) * width, (i/3) * height, width, height);
			btn.setActionCommand(String.valueOf(i));
			btn.addActionListener(new ActionListener() {
				
				/* Ao clicar em um botão */
				public void actionPerformed(ActionEvent e) {
					
					/* Checa se o jogo foi ganho */
					int status = jdv.getStatus();
					if(status == 1 || status == -1 || jdv.isEmpate()) {
						showMessage(status, jdv.isEmpate());
					}else {
						/* Pega o id do botão, pasicamente sua posição no jogo da velha */
						int valor = Integer.parseInt(e.getActionCommand());
						
						/* Se o botão apertado já tem uma jogada X ou O então pare a execução, senão coloce um X no local */
						if(jdv.tabuleiro[valor] != ' ') {
							return;
						}else {
							jdv.setX(valor);
						}
						
						/* Apos a jogada checa se alguem ganhou novamente */
						status = jdv.getStatus();
						
						/* Se alguem ganhou, mostra uma mensagem para jogar novamente */
						if(showMessage(jdv.getStatus(), jdv.isEmpate())) return;
						
						/* Executa o minmax para o Bot */
						jdv = ArvoreMinMax.solve(jdv, false, 1);
						
						/* Atualiza a interface da jogada feita pelo Bot */
						for(int i = 0 ; i < 9 ; i++) {
							buttons[i].setText(String.valueOf(jdv.tabuleiro[i]));
						}
						
						/* Se alguem ganhou, mostra uma mensagem para jogar novamente */
						if(showMessage(jdv.getStatus(), jdv.isEmpate())) return;
					}
				}
			});
		}
		j.pack();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
	}
	
	/**
	 * Método responsável por mostrar a mensagem de vitória/empate e perguntar se o usuário deseja jogar novamente.
	 * @param status : [-1|1] Se o jogador 'X' ganhou (1) ou o jogador 'O' ganhou (-1)
	 * @param isEmpate : [True|False] Se houve um empate (True), se não (False)
	 * @return [True|False] Se houve condição de vitória (True), se não (False)
	 */
	public static boolean showMessage(int status, boolean isEmpate) {
		if(status == 1 || status == -1 || isEmpate) {
			String msg = "";
			if(isEmpate) {
				msg = "Empate! Deseja jogar novamente?";
			}else {
				msg = ((status==1)? "O Jogador [ X ] Ganhou! <br>" : "O Jogador [ O ] Ganhou! <br>")+" Deseja jogar novamente?";
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
