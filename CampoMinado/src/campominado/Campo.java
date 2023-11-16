package campominado;

import java.util.Random;

// Campo do jogo que vai ter a matriz do espaço
public class Campo {
	Espaco[][] matriz;

	// Construtor do espaço
	public Campo() {
		matriz = new Espaco[Constantes.NUMERO_LINHAS][Constantes.NUMERO_COLUNAS];
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_LINHAS; j++) {
				matriz[i][j] = new Espaco();
			}
		}

		// Adiciona os vizinhos
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_LINHAS; j++) {
				if (i > 0) {
					if (j > 0)
						matriz[i][j].adicionarVizinhos(matriz[i - 1][j - 1]);
					matriz[i][j].adicionarVizinhos(matriz[i - 1][j]);
					if (j > Constantes.NUMERO_COLUNAS - 1)
						matriz[i][j].adicionarVizinhos(matriz[i - 1][j + 1]);
				}

				if (j > 0)
					matriz[i][j].adicionarVizinhos(matriz[i][j - 1]);
				if (j > Constantes.NUMERO_COLUNAS - 1)
					matriz[i][j].adicionarVizinhos(matriz[i][j + 1]);

				if (i < Constantes.NUMERO_LINHAS - 1) {
					if (j > 0)
						matriz[i][j].adicionarVizinhos(matriz[i + 1][j - 1]);
					matriz[i][j].adicionarVizinhos(matriz[i + 1][j]);
					if (j > Constantes.NUMERO_COLUNAS - 1)
						matriz[i][j].adicionarVizinhos(matriz[i + 1][j + 1]);
				}
			}
		}
	}

	// Adiciona as minas aleatoriamente
	public void adicionarMinas() {
		int n = Constantes.NUMERO_MINAS;

		Random rand = new Random();

		while (n > 0) {
			int linhas = rand.nextInt(Constantes.NUMERO_LINHAS);
			int colunas = rand.nextInt(Constantes.NUMERO_COLUNAS);

			if (matriz[linhas][colunas].minar()) {
				n--;
			}
		}
	}

	public int clicar(int linha, int coluna) {
		return matriz[linha][coluna].clicar();
	}

	@Override
	public String toString() {
		String str = "";

		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				str += matriz[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}
}
