package mc322.lab03;

public class Animacao {
	int frame, numFrames;
	String chave;
	char[] sequencia;
	AquarioLombriga aquario;
	
	Animacao(String sequencia) {
		String aux;
		int sizeAquario, sizeLombriga;
		int posLombrigaInicial;
		
		// Extrai dados e cria a Lobriga
		aux = new String("");
		aux = aux + sequencia.charAt(0) + sequencia.charAt(1);
		sizeAquario = Integer.parseInt(aux);
		
		aux = new String("");
		aux = aux + sequencia.charAt(2) + sequencia.charAt(3);
		sizeLombriga = Integer.parseInt(aux);
		
		aux = new String("");
		aux = aux + sequencia.charAt(4) + sequencia.charAt(5);
		posLombrigaInicial = Integer.parseInt(aux);
		
		this.aquario = new AquarioLombriga(sizeAquario,
				sizeLombriga, posLombrigaInicial);
		
		// Armazena chave e sequencia de movimentos
		this.frame = 0;
		this.chave = sequencia;
		this.sequencia = new char[sequencia.length() - 6];
		for (int i = 6; i < sequencia.length(); i++) {
			this.sequencia[i - 6] = sequencia.charAt(i);
		}
		this.numFrames = this.sequencia.length;
		
	}
	
	String apresenta() {
		return aquario.apresenta();
	}
	
	void passo() {
		switch (sequencia[frame]) {
			case 'C':
				aquario.crescer();
				break;
			case 'M':
				aquario.mover();
				break;
			case 'V':
				aquario.virar();
				break;
		}
		frame++;
	}
}
