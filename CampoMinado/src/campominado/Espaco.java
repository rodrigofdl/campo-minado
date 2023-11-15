package campominado;

import java.util.ArrayList;

// Espaço do jogo
public class Espaco {
	boolean minado;
	boolean revelado;
	boolean marcado;
	boolean clicado;
	
	ArrayList<Espaco> vizinhos;
	
	// Cria todos os atributos falsos para depois sortear se vai ser verdadeiro ou falso
	public Espaco() {
		this.minado = false;
		this.revelado = false;
		this.marcado = false;
		this.clicado = false;
		
		this.vizinhos = new ArrayList<Espaco>();
	}
	
	// Adiciona vizinho
	public void adicionarVizinhos(Espaco e) {
		this.vizinhos.add(e);
	}
	
	// Método para indicar que o espaço tem uma mina
	// Se o espaço não tinha uma mina, ele retorna verdadeiro
	// Se o espaço já tem uma mina, ele retorna falso
	public boolean minar() {
		if (!this.minado) {
			this.minado = true;
			return true;
		} else {
			return false;
		}
	}
	
	// Marca e desmarca um box que supostamente tem uma mina
	public boolean marcar() {
		this.marcado = !this.marcado;
		return this.marcado;
	}
	
	// -1 não possui minas nos vizinhos
	// 0 não possui minas nos vizinhos
	// n possui n minas nos vizinhos
	public int clicar() {
		if (this.minado) {
			return -1;
		} else {
			return numeroMinasNosVizinhos();
		}
	}
	
	// Retorna a quantidade de minas que tem nos vizinhos
	public int numeroMinasNosVizinhos() {
		int n = 0;
		for (Espaco e : this.vizinhos) {
			if (e.minado) n++;
		}
		return n;
	}
	
	// Resetar o espaço do jogo
	public void reset() {
		this.minado = false;
		this.revelado = false;
		this.marcado = false;
		this.clicado = false;
	}
	
	@Override
	public String toString() {
		if (this.minado)
			return "-1";
		return "+" + this.numeroMinasNosVizinhos();
	}
}













