package mc322.lab05b;

public class Tabuleiro {
	private int x, y;
	private boolean turno;
	private Peca vPecas[][];
	private MovementParser parser;
	
	Tabuleiro () {
		x = 8;
		y = 8;
		
		parser = new MovementParser();
		parser.setDimensions(x, y);
		
		turno = true;
		
		vPecas = new Peca[y][x];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i < 3 && (j + i) % 2 != 0)
					vPecas[i][j] = new Peao(false, j, i);
				else if (i > 4 && (j + i) % 2 != 0)
					vPecas[i][j] = new Peao(true, j, i);
				else
					vPecas[i][j] = null;
			}
		}
	}
	
	private Peca getPiece(int x, int y) {
		if ((x >= 0 && x < 8) && (y >= 0 && y < 8))
			return vPecas[y][x];
		return null;
	}
	
	/**
	 * Move a dama pc de source para target se o movimento
	 * eh valido. Se valido passa o turno.
	 * 
	 * @param pc dama na posicao source
	 * @param source posicao de saida da dama no tabuleiro 
	 * @param target posicao destino da dama no tabuleiro
	 */
	private void move(Peca pc, int source[], int target[]) {
		int fatorX, fatorY, i;
		Peca caminho[];
		
		if (pc.branco != turno) {
			System.out.println("Movimento Inválido. Turno das " + (turno ? "brancas." : "pretas"));
			return;
		}
				
		fatorY = target[1] - source[1] > 0 ? 1 : -1;
		fatorX = target[0] - source[0] > 0 ? 1 : -1;
		
		// Gera caminho. Multiplicar pelo fator garante que tamanho > 0
		caminho = new Peca[(target[0] - source[0]) * fatorX];
		
		i = 1;
		while (source[0] + i * fatorX != target[0] + 1 * fatorX && source[1] + i * fatorY != target[1] + 1 * fatorY) {
			caminho[i - 1] = getPiece(source[0] + i * fatorX, source[1] + i * fatorY);
			i++;
		}
		
		if (!pc.isValid(source, target, caminho)) {
			System.out.println("Movimento Invalido.");
			return;
		}
		
		for (int j = 0; j < caminho.length; j++) {
			if (caminho[j] != null)
				capture(caminho[j], caminho[j].getPosition());
		}
		
		vPecas[source[1]][source[0]] = null;
		vPecas[target[1]][target[0]] = pc;
		
		// FIX-ME: Utilizar instanceOf para evitar dar upgrade em dama
		if ((target[1] == 7 && !pc.branco) || (target[1] == 0 && pc.branco))
			upgrade(pc, target);
		nextTurn();
	}
	
	private void capture(Peca pc, int pos[]) {
		this.vPecas[pos[1]][pos[0]] = null;
	}
	
	/**
	 * Realiza promocao de peao para dama. Supoe 
	 * que ja foi verificado se upgrade eh valido
	 * ou nao.
	 * 
	 * @param pc peao sera promovido 
	 * @param pos posicao do peao
	 */
	private void upgrade(Peca pc, int pos[]) {
		vPecas[pos[1]][pos[0]] = new Dama(pc.getColor(), pos[0], pos[1]);
	}
	
	/**
	 * Atualiza estado turno. Deve ser chamado
	 * apos cada jogada valida.
	 */
	private void nextTurn() {
		turno = !turno;
	}
	
	/**
	 * Move a peca da posicao source para target no tabuleiro,
	 * se o movimento eh valido.
	 * As Strings de posicao devem ser do tipo alfa-num com 
	 * alfa entre a e h e num entre 1 e 8.
	 * 
	 * @param source posicao de saida da peca
	 * @param target posicao de destino da peca
	 */
	public void movePiece(String source, String target) {
		int pos[][], sourceCord[], targetCord[];
		Peca pc;
		
		parser.setMovement(source, target);
		pos = parser.parsePosition();
		
		sourceCord = pos[0];
		targetCord = pos[1];
		
		System.out.println("Source: " + source);
		System.out.println("Target: " + target);
		
		if (vPecas[sourceCord[1]][sourceCord[0]] != null) {
			pc = vPecas[sourceCord[1]][sourceCord[0]];
			move(pc, sourceCord, targetCord);
		} else {
			System.out.println("Movimento Invalido. Selecione uma peca valida");
		}
	}
	
	public void presentBoard() {
		String boardState = "";
		
		for (int i = 0; i < y; i++) {
			boardState += (y - i);
			boardState += ' ';
			for (int j = 0; j < x; j++) {
				if (vPecas[i][j] != null)
					boardState += vPecas[i][j].presentPiece();
				else
					boardState += '-';
				boardState += ' ';
			}
			boardState += '\n';
		}
		
		// Adiciona eixo das letras. 97 == a (ASCII)
		boardState += "  ";
		for (int i = 0; i < x; i++) {
			boardState += (char) (97 + i);
			boardState += ' ';
		}
		boardState += '\n';
		
		System.out.println(boardState);
	}
}
