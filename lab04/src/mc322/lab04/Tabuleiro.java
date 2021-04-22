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
	 * Insere no tabuleiro uma peça na posição determinada
	 * e atualiza os vizinhos da peça.
	 * 
	 * @param pc Peça a ser inserida
	 * @param x posição absoluta no eixo x da peça no tabuleiro
	 * @param y posição absoluta no eixo y da peça no tabuleiro
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
	 * Verifica se o movimento de uma peça de source para
	 * target na direção do vetor vDir é valido.
	 * 
	 * @param source posição absoluta (x, y) da peça a ser movimentada
	 * @param target posição absoluta (x, y) de destino da peça
	 * @param vDir Vetor diretor do movimento da peça entre source e target
	 * @return true se o movimento é valido. Caso contrário false.
	 */
	public boolean isValidMove(int source[], int target[], int vDir[]) {
		// Movimento na diagonal
		if (vDir[0] != 0 && vDir[1] != 0)
			return false;
		
		// Movimento para fora do tabuleiro
		if (target[0] >= this.vPieces[0].length || target[0] < 0)
			return false;
		
		if (target[1] >= this.vPieces.length || target[1] < 0)
			return false;
		
		// Verifica se eh Peca em source esta vazia ou eh espaco nulo
		if (this.vPieces[target[1]][target[0]] == null)
			return false;
		
		if (this.vPieces[source[1]][source[0]].alive == false)
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
			
		return true;
	}
	
	/**
	 * Comanda o tabuleiro a mover a peça na posição source
	 * para a posição target se o movimento for válido.
	 * Se o movimento não é valido exibe na tela uma mensagem
	 * e não realiza o movimento.
	 * 
	 * @param source  posição do tabuleiro em forma de string (e.g. "f4") da peça a ser movimentada 
	 * @param target  posição do tabuleiro em forma de string (e.g. "d4") do destino da peça
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
			System.out.println("Movimento Inválido!");
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
	 * Apresenta o estado atual do tableiro.
	 * 
	 * @return string representando o estado do tabuleiro
	 */
	public String presentBoard() {
		String board = "";
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		Peca piece;
		
		for (int i = 0; i < vPieces.length; i++) {
			board += vPieces.length - i;						// Add indicador numerico a esquerda
			board += ' ';
			for (int j = 0; j < vPieces[0].length; j++) {
				piece = vPieces[i][j];
				board += ' ';
				if (piece == null)
					board += ' ';
				else
					board += piece.presentPiece();
			}
			board += '\n';
		}
		
		board += "  ";											// Add indicadores de letras em baixo
		for (int i = 0; i < vPieces.length; i++) {
			board += ' ';
			board += alphabet.charAt(i);
		}
		board += '\n';
		
		return board;
	}
}
