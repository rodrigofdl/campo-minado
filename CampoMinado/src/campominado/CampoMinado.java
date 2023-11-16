package campominado;

public class CampoMinado {

	public static void main(String[] args) {	
		// System.out.println(c);

		Campo c = new Campo();
		c.adicionarMinas();
		JFrameCampo f = new JFrameCampo(c);
	}
}
