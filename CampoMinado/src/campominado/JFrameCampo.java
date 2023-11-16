package campominado;

import javax.swing.*;

// Interface do jogo
public class JFrameCampo extends JFrame {
	JPanel panel;
	JButtonEspaco[][] matrizBotao;
	Campo c;
	
	// Construtor da interface
	public JFrameCampo(Campo c) {
		this.c = c;
		this.panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		matrizBotao = new JButtonEspaco[Constantes.NUMERO_LINHAS][Constantes.NUMERO_COLUNAS];

		int n = 0;
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				matrizBotao[i][j] = new JButtonEspaco(this.c);
				matrizBotao[i][j].linha = i;
				matrizBotao[i][j].coluna = j;
				matrizBotao[i][j].setSize(30, 30);
				matrizBotao[i][j].setLocation(30 * j, 30 * i);
				// matrizBotao[i][j].setText(Integer.toString(n++));
				panel.add(matrizBotao[i][j]);
			}
		}

		confIniciais();
	}

	// Configurações da janela
	private void confIniciais() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Constantes.NUMERO_COLUNAS * Constantes.TAMANHO_ESPACO, Constantes.NUMERO_LINHAS * Constantes.TAMANHO_ESPACO + 100 + 20);
		this.setResizable(false);
		this.setVisible(true);
	}
}
