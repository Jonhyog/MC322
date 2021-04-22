package mc322.lab04;

public class MovementParser {
	String source, target;
	int x, y;
	
	MovementParser(){
		this.source = null;
		this.target = null;
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Prepara o parser para traduzir as string do movimento
	 * de source para target em coordenadas para o tabuleiro.
	 *  
	 * @param source
	 * @param target
	 */
	public void setMovement(String source, String target) {
		this.source = source;
		this.target = target;
	}
	
	/**
	 * Configura as dimensões do tabuleiro para permitir tradução
	 * de movimentos correta. Deve ser chamado antes de traduzir
	 * uma posição para resultados corretos.
	 * 
	 * @param x Tamanho x do tabuleiro
	 * @param y Ta,amho y do tabuleiro
	 */
	public void setDimensions(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Converte o sistemas de cordenadas alfa-número utilizado pelo
	 * jogador para coordenadas x, y do tabuleiro. Supõe que tabuleiro
	 * possui entre no mínimo 1 e no máximo 9 linhas.
	 * 
	 * @return Matriz 2x2 de posições com linha 0 correspondente a source e linha 1
	 * a target
	 */
	public int[][] parsePosition() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int pos[][] = new int[2][2];
		
		// Traduz posição de source
		for (int i = 0; i < alphabet.length(); i++) {
			if (alphabet.charAt(i) == source.charAt(0)) {
				pos[0][0] = i;
				break;
			}
		}
		pos[0][1] = y - Integer.parseInt(Character.toString(source.charAt(1)));
		
		// Traduz posição de target
		for (int i = 0; i < alphabet.length(); i++) {
			if (alphabet.charAt(i) == target.charAt(0)) {
				pos[1][0] = i;
				break;
			}
		}
		pos[1][1] = y - Integer.parseInt(Character.toString(target.charAt(1)));
		
		return pos;
	}
}
