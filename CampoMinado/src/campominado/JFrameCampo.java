package campominado;

import javax.swing.*;

// Gráfico do campo

// Interface do jogo
// O extends serve para aplicar uma herança na classe
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

	// Altera dificuldade
	// O void indica que a função não retorna nada e não tem parâmetros
	public void hardReset() {
		CampoMinado.hardReset();
		// O dispose serve para fechar a janela
		this.dispose();
	}

	// Configurações da janela
	// O private indica que este recurso só pode ser acessado por
	// objetos da mesma classe
	private void confIniciais() {
		// O this serve para chamar um atributo fora do método
		// O new serve para instânciar uma classe / criar um novo objeto do mesmo tipo da classe
		this.c = new Campo();
		this.panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		matrizBotao = new JButtonEspaco[Constantes.NUMERO_LINHAS][Constantes.NUMERO_COLUNAS];
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				matrizBotao[i][j] = new JButtonEspaco(this.c, this);
				c.getEspaco(i, j).setButton(matrizBotao[i][j]);
				// matrizBotao[i][j].linha = i;
				// matrizBotao[i][j].coluna = j;
				matrizBotao[i][j].setPosicao(i, j);
				matrizBotao[i][j].setSize(Constantes.TAMANHO_ESPACO, Constantes.TAMANHO_ESPACO);
				matrizBotao[i][j].setFocusable(false);
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

		// Botão de iniciar/reiniciar o jogo
		this.resetButton = new JButton("I/R");
		this.resetButton.addActionListener((java.awt.event.ActionEvent evt) -> {
			this.reset();
		});
		this.resetButton.setSize(Constantes.TAMANHO_ESPACO, Constantes.TAMANHO_ESPACO);
		this.resetButton.setLocation(
				Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS / 2 - Constantes.TAMANHO_ESPACO / 2,
				Constantes.OFFSET_SUPERIOR - Constantes.TAMANHO_ESPACO);
		this.panel.add(this.resetButton);

		// Botão de dificuldade fácil
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

		// Botão de dificuldade média
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

		// Botão de dificuldade difícil
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

		// Botão de customizar o jogo
		this.customButton = new JButton("C");
		this.customButton.addActionListener((java.awt.event.ActionEvent evt) -> {
			int l = Integer.parseInt(JOptionPane.showInputDialog("Insira #linha"));
			Constantes.NUMERO_LINHAS = l;

			int c = Integer.parseInt(JOptionPane.showInputDialog("Insira #coluna"));
			Constantes.NUMERO_COLUNAS = c;

			int m = Integer.parseInt(JOptionPane.showInputDialog("Insira #minas"));
			Constantes.NUMERO_MINAS = m;

			this.hardReset();
		});
		this.customButton.setSize((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4,
				Constantes.TAMANHO_ESPACO);
		this.customButton.setLocation((Constantes.TAMANHO_ESPACO * Constantes.NUMERO_COLUNAS) / 4 * 3, 0);
		this.panel.add(this.customButton);
	}

	// Inicia/Reinicia o jogo
	public void reset() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				matrizBotao[i][j].reset();
			}
		}
		this.c.adicionarMinas();
	}

	// Revela as minas
	public void revelarMinas() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				if (matrizBotao[i][j].espacoLogica.minado) {
					matrizBotao[i][j].revela("-1");
				}
			}
		}
	}

	// Desativa os botões
	public void desativaBotoes() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				matrizBotao[i][j].setEnabled(false);
			}
		}
	}

	// Checa se venceu ou perdeu
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
