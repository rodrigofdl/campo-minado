package campominado;

import javax.swing.*;

// Interface do jogo
public class JFrameCampo extends JFrame {
	JPanel panel;
	JButtonEspaco[][] matrizBotao;
	Campo c;
	JButton resetButton;

	JButton facilButton;
	JButton medioButton;
	JButton dificilButton;
	JButton customButton;

	// Construtor da interface
	public JFrameCampo() {
		confIniciais();
	}

	public void hardReset() {
		CampoMinado.hardReset();
		this.dispose();
	}

	// Configurações da janela
	private void confIniciais() {
		this.c = new Campo();
		this.panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		matrizBotao = new JButtonEspaco[Constantes.NUMERO_LINHAS][Constantes.NUMERO_COLUNAS];
		int n = 0;
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				matrizBotao[i][j] = new JButtonEspaco(this.c, this);
				c.getEspaco(i, j).setButton(matrizBotao[i][j]);
				// matrizBotao[i][j].linha = i;
				// matrizBotao[i][j].coluna = j;
				matrizBotao[i][j].setPosicao(i, j);
				matrizBotao[i][j].setSize(Constantes.TAMANHO_ESPACO, Constantes.TAMANHO_ESPACO);
				matrizBotao[i][j].setLocation(Constantes.TAMANHO_ESPACO * j,
						Constantes.TAMANHO_ESPACO * i + Constantes.OFFSET_SUPERIOR);
				// matrizBotao[i][j].setText(Integer.toString(n++));
				panel.add(matrizBotao[i][j]);
			}
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Constantes.NUMERO_COLUNAS * Constantes.TAMANHO_ESPACO + 14,
				Constantes.NUMERO_LINHAS * Constantes.TAMANHO_ESPACO + Constantes.OFFSET_SUPERIOR
						+ Constantes.ALTURA_BARRA_SUPERIOR);
		this.setResizable(false);
		this.setVisible(true);

		this.resetButton = new JButton();
		this.resetButton.addActionListener((java.awt.event.ActionEvent evt) -> {
			this.reset();
		});
		this.resetButton.setSize(Constantes.TAMANHO_ESPACO, Constantes.TAMANHO_ESPACO);
		this.resetButton.setLocation(
				Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS / 2 - Constantes.TAMANHO_ESPACO / 2,
				Constantes.OFFSET_SUPERIOR - Constantes.TAMANHO_ESPACO);
		this.panel.add(this.resetButton);

		this.facilButton = new JButton("F");
		this.facilButton.addActionListener((java.awt.event.ActionEvent evt) -> {
			Constantes.NUMERO_COLUNAS = 6;
			Constantes.NUMERO_LINHAS = 6;
			Constantes.NUMERO_MINAS = 6;
			this.hardReset();
		});
		this.facilButton.setSize((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4,
				Constantes.TAMANHO_ESPACO);
		this.facilButton.setLocation(0, 0);
		this.panel.add(this.facilButton);

		this.medioButton = new JButton("M");
		this.medioButton.addActionListener((java.awt.event.ActionEvent evt) -> {
			Constantes.NUMERO_COLUNAS = 10;
			Constantes.NUMERO_LINHAS = 10;
			Constantes.NUMERO_MINAS = 15;
			this.hardReset();
		});
		this.medioButton.setSize((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4,
				Constantes.TAMANHO_ESPACO);
		this.medioButton.setLocation((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4, 0);
		this.panel.add(this.medioButton);

		this.dificilButton = new JButton("D");
		this.dificilButton.addActionListener((java.awt.event.ActionEvent evt) -> {
			Constantes.NUMERO_COLUNAS = 16;
			Constantes.NUMERO_LINHAS = 16;
			Constantes.NUMERO_MINAS = 40;
			this.hardReset();
		});
		this.dificilButton.setSize((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4,
				Constantes.TAMANHO_ESPACO);
		this.dificilButton.setLocation((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4 * 2, 0);
		this.panel.add(this.dificilButton);

		this.customButton = new JButton("C");
		this.customButton.addActionListener((java.awt.event.ActionEvent evt) -> {

		});
		this.customButton.setSize((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4,
				Constantes.TAMANHO_ESPACO);
		this.customButton.setLocation((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4 * 3, 0);
		this.panel.add(this.customButton);
	}

	public void reset() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				matrizBotao[i][j].reset();
			}
		}
		this.c.adicionarMinas();
	}

	public void revelarMinas() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				if (matrizBotao[i][j].espacoLogica.minado) {
					matrizBotao[i][j].revela("-1");
				}
			}
		}
	}

	public void desativaBotoes() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				matrizBotao[i][j].setEnabled(false);
			}
		}
	}

	public void checkEstado() {
		System.out.println("Verificando se venceu ou perdeu");
		if (this.c.isVencido()) {
			System.out.println("Venceu");
			this.desativaBotoes();
		}
		if (this.c.isPerdido()) {
			System.out.println("Perdeu");
			this.desativaBotoes();
		}
	}
}
