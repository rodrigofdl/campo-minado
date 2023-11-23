package campominado;

import java.util.Random;

// Lógica do campo

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
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				if (i > 0) {
					if (j > 0)
						matriz[i][j].adicionarVizinhos(matriz[i - 1][j - 1]);
					matriz[i][j].adicionarVizinhos(matriz[i - 1][j]);
					if (j < Constantes.NUMERO_COLUNAS - 1)
						matriz[i][j].adicionarVizinhos(matriz[i - 1][j + 1]);
				}

				if (j > 0)
					matriz[i][j].adicionarVizinhos(matriz[i][j - 1]);
				if (j < Constantes.NUMERO_COLUNAS - 1)
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
	// O void indica que a função não retorna nada e não tem parâmetros
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
		System.out.println(this);
	}

	public int clicar(int linha, int coluna) {
		return matriz[linha][coluna].clicar();
	}

	// Verifica se venceu
	public boolean isVencido() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				if (!matriz[i][j].isFinalizado())
					return false;
			}
		}
		return true;
	}

	// Verifica se perdeu
	public boolean isPerdido() {
		for (int i = 0; i < Constantes.NUMERO_LINHAS; i++) {
			for (int j = 0; j < Constantes.NUMERO_COLUNAS; j++) {
				if (matriz[i][j].clicado && matriz[i][j].minado)
					return true;
			}
		}
		return false;
	}

	// Ligação do espaço gráfico e espaço lógico
	public Espaco getEspaco(int linha, int coluna) {
		return matriz[linha][coluna];
	}

	// O Override serve para garantir que está sobreescrevendo
	// um método e não criando um novo
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
