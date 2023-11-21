package campominado;

import java.awt.event.MouseEvent;

import javax.swing.*;

// Configuração dos botões
public final class JButtonEspaco extends JButton {
	int linha;
	int coluna;
	Campo campoLogica;
	Espaco espacoLogica;
	JFrameCampo campoGrafico;
	String text;

	// Construtor do botão
	public JButtonEspaco(Campo c, JFrameCampo cg) {
		this.campoGrafico = cg;
		this.text = "";
		this.setText(text);
		this.campoLogica = c;
		this.addActionListener((java.awt.event.ActionEvent evt) -> {
			botaoPressionado(false);
		});
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					botaoPressionado(true);
				}
			}
		});
	}

	public void reset() {
		this.espacoLogica.reset();
		this.text = "";
		this.setText(text);
		this.setEnabled(true);
	}

	// Botão ao pressionar
	private void botaoPressionado(boolean mouseBotaoDireito) {
		if (!mouseBotaoDireito) {
			if (!this.espacoLogica.marcado)
				this.clicar();
		} else {
			this.marcar();
		}
		this.campoGrafico.checkEstado();
	}

	public void clicar() {
		System.out.println("linha: " + linha + " coluna: " + coluna);
		// e.clicado = true;

		// Retorna numeroVizinhosMinados se o Espaco atual não possui mina
		int numeroVizinhosMinados = espacoLogica.clicar();

		if (this.espacoLogica.minado) {
			this.campoGrafico.revelarMinas();
		}

		if (numeroVizinhosMinados == 0) {
			for (Espaco vizinho : espacoLogica.vizinhos) {
				if (!vizinho.clicado)
					vizinho.button.clicar();
			}
			// return;
		}

		this.text = Integer.toString(numeroVizinhosMinados);
		this.revela(this.text);
	}

	public void marcar() {
		if (this.espacoLogica.clicado)
			return;
		boolean estaMarcado = this.espacoLogica.marcar();
		if (this.espacoLogica.marcado) {
			this.setText("M");
		} else {
			this.setText("");
		}
	}

	// Posição do botão
	public void setPosicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
		this.espacoLogica = campoLogica.getEspaco(linha, coluna);
	}

	public void revela(String cod) {
		this.setText(cod);
		this.setEnabled(false);
	}
}
