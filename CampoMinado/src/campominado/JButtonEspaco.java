package campominado;

import javax.swing.*;

// Configuração dos botões
public class JButtonEspaco extends JButton {
	int linha;
	int coluna;
	Campo c;
	String text;

	// Construtor do botão
	public JButtonEspaco(Campo c) {
		text = "";
		this.setText(text);
		this.c = c;
		this.addActionListener((java.awt.event.ActionEvent evt) -> {
			botaoPressionar(evt);
		});
	}
	
	// Botão ao pressionar
	private void botaoPressionar(java.awt.event.ActionEvent evt) {
		System.out.println("linha: " + linha + " coluna: " + coluna);
		int ret = c.clicar(linha, coluna);
		this.text = Integer.toString(ret);
		this.setText(text);
		this.setEnabled(false);
	}

	// Posição do botão
	public void setPosicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
}
