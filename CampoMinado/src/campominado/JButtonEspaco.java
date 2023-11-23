package campominado;

import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

// Gráfico dos botões

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

		// Botão esquerdo pressionado
		this.addActionListener((java.awt.event.ActionEvent evt) -> {
			botaoPressionado(false);
		});
		// Botão direito pressionado
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					botaoPressionado(true);
				}
			}
		});
	}

	// Botão Iniciar/Reiniciar
	public void reset() {
		this.espacoLogica.reset();
		this.text = "";
		this.setText(text);
		this.setEnabled(true);
		this.setIcon(null);
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

	// Espaço clicado
	public void clicar() {
		System.out.println("linha: " + linha + " coluna: " + coluna);
		// e.clicado = true;

		// Número de vizinhos minados se o espaço atual não possui mina
		int numeroVizinhosMinados = espacoLogica.clicar();

		if (this.espacoLogica.minado) {
			this.campoGrafico.revelarMinas();
		}

		if (numeroVizinhosMinados == 0) {
			for (Espaco vizinho : espacoLogica.vizinhos) {
				if (!vizinho.clicado)
					vizinho.button.clicar();
			}
		}

		this.text = Integer.toString(numeroVizinhosMinados);
		this.revela(this.text);
	}

	// Marca o espaço que supostamente tem uma mina
	public void marcar() {
		if (this.espacoLogica.clicado)
			return;
		boolean estaMarcado = this.espacoLogica.marcar();
		if (this.espacoLogica.marcado) {
			// Imagem da bandeira
			try {
                Image img = ImageIO.read(getClass().getResource("bandeira.png"));
                img = img.getScaledInstance(Constantes.TAMANHO_ESPACO, Constantes.TAMANHO_ESPACO, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
			} catch (Exception ex) {
                this.setText("M");
                System.out.println("ERRO!");
            }
		} else {
			this.setIcon(null);
			this.setText("");
		}
	}

	// Posição do botão
	public void setPosicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
		this.espacoLogica = campoLogica.getEspaco(linha, coluna);
	}

	// Revela o que tem no espaço
	public void revela(String cod) {
		if (cod.equals("-1")) {
			// Imagem da bomba
			try {
                Image img = ImageIO.read(getClass().getResource("mina.png"));
                img = img.getScaledInstance(Constantes.TAMANHO_ESPACO, Constantes.TAMANHO_ESPACO, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
			} catch (Exception ex) {
                this.setText("-1");
                System.out.println("ERRO!");
            }
		} else {
			this.setText(cod);
		}
		
		this.setEnabled(false);
	}
}
