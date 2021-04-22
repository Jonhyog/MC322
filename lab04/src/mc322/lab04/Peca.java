package mc322.lab04;

import java.util.Arrays;

public class Peca {
	boolean alive;
	Peca neighbours[]; // 0 == esquerda, 1 == direita, 2 == acima  e 3 == abaixo
	
	Peca(int x, int y, boolean alive) {
		this.neighbours = new Peca[4];
		this.alive = alive;
		Arrays.fill(this.neighbours, null);
	}
	
	public void setState(boolean state) {
		this.alive = state;
	}
	
	public void setLeftNeighbour(Peca pc) {
		neighbours[0] = pc;
	}
	
	public void setRightNeighbour(Peca pc) {
		neighbours[1] = pc;
	}
	
	public void setUpNeighbour(Peca pc) {
		neighbours[2] = pc;
	}
	
	public void setDownNeighbour(Peca pc) {
		neighbours[3] = pc;
	}
	
	/**
	 * Retorna o vizinho de uma peça na direção determinada.
	 * Caso a direção informada não corresponda com a de um vizinho
	 * (e.g. vetor apontando na diagonal) retorna null.
	 * 
	 * @param vDir Vetor (x, y) que aponta na direção do vizinho.
	 * @return Vizinho da peça
	 */
	public Peca getNeighbour(int vDir[]) {
		if (vDir[0] == 0) {
			if (vDir[1] < 0)
				return neighbours[3];
			else
				return neighbours[2];
		}
		
		if (vDir[1] == 0) {
			if (vDir[0] < 0)
				return neighbours[1];
			else
				return neighbours[0];
		}
		
		return null;
	}
	/**
	 * Retorna a "imagem" da peça.
	 * 
	 * @return char que representa peça.
	 */
	public char presentPiece() {
		if (alive)
			return 'P';
		else
			return '-';
	}
}
