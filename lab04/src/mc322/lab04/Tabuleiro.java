package mc322.lab04;

public class Tabuleiro {
	String layout;
	Peca vPieces[][];
	MovementParser parser;
	
	Tabuleiro(String layout) {
		char item;
		int x = 0, y = 0;
		int pos_x, pos_y;
		this.layout = layout;
		this.parser = new MovementParser();
		
		// Determina tamanho do tabuleiro
		for (int i = 0; i < layout.length(); i++) {
			if (layout.charAt(i) == '\n')
				y += 1;
		}
		x = (layout.length() - y) / y;
		
		// Cria Matriz e configura parser
		vPieces = new Peca[y][x];
		parser.setDimensions(x, y);
		
		// Posiciona as pecas
		pos_x = 0; pos_y = 0;
		for (int i = 0; i < layout.length(); i++) {
			item = layout.charAt(i);
			
			switch (item) {
				case ('\n'):
					pos_x = 0;
					pos_y++;
					break;
				case ('-'):
					insertPiece(new Peca(pos_x, pos_y, false), pos_x, pos_y);
					pos_x++;
					break;
				case ('P'):
					insertPiece(new Peca(pos_x, pos_y, true), pos_x, pos_y);
					pos_x++;
					break;
				default:
					vPieces[pos_y][pos_x] = null;
					pos_x++;
			}
		}
	}
	
	/**
	 * Insere no tabuleiro uma pe�a na posi��o determinada
	 * e atualiza os vizinhos da pe�a.
	 * 
	 * @param pc Pe�a a ser inserida
	 * @param x posi��o absoluta no eixo x da pe�a no tabuleiro
	 * @param y posi��o absoluta no eixo y da pe�a no tabuleiro
	 */
	public void insertPiece(Peca pc, int x, int y) {
		
		vPieces[y][x] = pc;
		
		if (x - 1 >= 0) {
			pc.setLeftNeighbour(vPieces[y][x - 1]);
			if (vPieces[y][x - 1] != null) {
				vPieces[y][x - 1].setRightNeighbour(pc);
			}
		}
		
		if (x + 1 < vPieces[0].length) {
			pc.setRightNeighbour(vPieces[y][x + 1]);
			if (vPieces[y][x + 1] != null) {
				vPieces[y][x + 1].setLeftNeighbour(pc);
			}
		}
		
		if (y - 1 >= 0) {
			pc.setUpNeighbour(vPieces[y - 1][x]);
			if (vPieces[y - 1][x] != null) {
				vPieces[y - 1][x].setDownNeighbour(pc);
			}
		}
		
		if (y + 1 < vPieces.length) {
			pc.setDownNeighbour(vPieces[y + 1][x]);
			if (vPieces[y + 1][x] != null) {
				vPieces[y + 1][x].setUpNeighbour(pc);
			}
		}
	}
	
	public Peca getPiece(int x, int y) {
		return this.vPieces[y][x];
	}
	
	/**
	 * Verifica se o movimento de uma pe�a de source para
	 * target na dire��o do vetor vDir � valido.
	 * 
	 * @param source posi��o absoluta (x, y) da pe�a a ser movimentada
	 * @param target posi��o absoluta (x, y) de destino da pe�a
	 * @param vDir Vetor diretor do movimento da pe�a entre source e target
	 * @return true se o movimento � valido. Caso contr�rio false.
	 */
	public boolean isValidMove(int source[], int target[], int vDir[]) {
		Peca neighbour;
		
		// Movimento na diagonal
		if (vDir[0] != 0 && vDir[1] != 0)
			return false;
		
		// Source ou target fora do tabuleiro
		if ((source[0] < 0 || source[0] >= vPieces[0].length) || (source[1] < 0 || source[1] >= vPieces.length))
			return false;
		if (( target[0] < 0 || target[0] >= vPieces[0].length) || (target[1] < 0 || target[1] >= vPieces.length))
			return false;
		
		// Verifica se eh Peca em source esta vazia ou eh espaco nulo
		// ou se target nao eh vazio
		if (this.vPieces[source[1]][source[0]] == null || this.vPieces[target[1]][target[0]] == null)
			return false;
		if (this.vPieces[source[1]][source[0]].alive == false)
			return false;
		if (this.vPieces[target[1]][target[0]].alive == true)
			return false;
		
		// Movimento de alfa-x para alfa-x
		if (source[0] == target[0] && source[1] == target[1])
			return false;
		
		// Movimento mais longo que o permitido
		if (vDir[0]*vDir[0] != 4 && vDir[0] != 0)
			return false;
		else if (vDir[1]*vDir[1] != 4 && vDir[1] != 0)
			return false;
		// Sempre se pula uma peca. Logo, se espera norma^2 = 2
		
		// Verifica se tem peca intermediaria
		neighbour = getPiece(source[0], source[1]).getNeighbour(vDir);
		if (neighbour.alive == false)
			return false;
		
		return true;
	}
	
	/**
	 * Comanda o tabuleiro a mover a pe�a na posi��o source
	 * para a posi��o target se o movimento for v�lido.
	 * Se o movimento n�o � valido exibe na tela uma mensagem
	 * e n�o realiza o movimento.
	 * 
	 * @param source  posi��o do tabuleiro em forma de string (e.g. "f4") da pe�a a ser movimentada 
	 * @param target  posi��o do tabuleiro em forma de string (e.g. "d4") do destino da pe�a
	 */
	public void movePiece(String source, String target) {
		int pos[][];
		int vDir[];
		Peca pc, aux, neighbour;
		
		System.out.println("Source: " + source);
		System.out.println("Target: " + target);
		
		parser.setMovement(source, target);
		pos = parser.parsePosition();
		vDir = new int[] {pos[0][0] - pos[1][0], pos[0][1] - pos[1][1]};

		if (!isValidMove(pos[0], pos[1], vDir)) {
			System.out.println("Movimento Inv�lido!");
			return;
		}
		
		pc = getPiece(pos[0][0], pos[0][1]);
		aux = getPiece(pos[1][0], pos[1][1]);
		neighbour = pc.getNeighbour(vDir);
		
		// Alterar estados evita atualizar vizinhos
		pc.setState(false);
		neighbour.setState(false);
		aux.setState(true);
	}
	
	/**
	 * Apresenta na tela o estado atual do tabuleiro formatado
	 * com os eixos e retorna a String do estado do tabuleiro
	 * sem formata��o.
	 * 
	 * @return string representando o estado do tabuleiro
	 */
	public String presentBoard() {
		String board = "";
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		Peca piece;
		
		for (int i = 0; i < vPieces.length; i++) {
			System.out.print((vPieces.length - i) + " "); // Exibe eixo de n�meros
			for (int j = 0; j < vPieces[0].length; j++) {
				piece = vPieces[i][j];
				System.out.print(" ");
				if (piece == null) {
					System.out.print(" ");
					board += ' ';
				}		
				else {
					System.out.print(piece.presentPiece());
					board += piece.presentPiece();
				}	
			}
			System.out.print("\n");
			board += '\n';
		}
		
		// Exibe eixo de letras
		System.out.print("  ");
		for (int i = 0; i < vPieces.length; i++) {
			System.out.print(" " + alphabet.charAt(i));
		}
		System.out.print("\n");
		board += '\n';
		
		return board;
	}
}
