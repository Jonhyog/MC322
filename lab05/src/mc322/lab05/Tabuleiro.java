package mc322.lab05;

public class Tabuleiro {
	private int x, y;
	private boolean turno;
	private Peao vPeoes[][];
	private Dama vDamas[][];
	private MovementParser parser;
	
	Tabuleiro () {
		x = 8;
		y = 8;
		
		parser = new MovementParser();
		parser.setDimensions(x, y);
		
		turno = true;
		
		vPeoes = new Peao[y][x];
		vDamas = new Dama[y][x];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i < 3 && (j + i) % 2 != 0)
					vPeoes[i][j] = new Peao(false);
				else if (i > 4 && (j + i) % 2 != 0)
					vPeoes[i][j] = new Peao(true);
				else
					vPeoes[i][j] = null;
				vDamas[i][j] = null;
			}
		}
	}
	
	/**
	 * Move a dama pc de source para target se o movimento
	 * eh valido. Se valido passa o turno.
	 * 
	 * @param pc dama na posicao source
	 * @param source posicao de saida da dama no tabuleiro 
	 * @param target posicao destino da dama no tabuleiro
	 */
	private void move(Dama pc, int source[], int target[]) {
		if (pc.branco != turno) {
			System.out.println("Movimento Inválido. Turno das " + (turno ? "brancas." : "pretas"));
			return;
		}
		
		// FIX-ME: Verificar se eh valido antes de mover
			// Se movimento eh valido, determinar se ocorre captura
		vDamas[source[1]][source[0]] = null;
		vDamas[target[1]][target[0]] = pc;
		nextTurn();
	}
	
	/**
	 * Move o peao pc de source para target se o movimento
	 * eh valido. Se valido passa o turno.
	 * 
	 * @param pc peao na posicao source
	 * @param source posicao de saida do peao no tabuleiro 
	 * @param target posicao destino da peao no tabuleiro
	 */
	private void move(Peao pc, int source[], int target[]) {
		if (pc.branco != turno) {
			System.out.println("Movimento Inválido. Turno das " + (turno ? "brancas." : "pretas"));
			return;
		}
		// FIX-ME: Verificar se eh valido antes de mover
			// Se movimento eh valido, determinar se ocorre captura
		vPeoes[source[1]][source[0]] = null;
		vPeoes[target[1]][target[0]] = pc;
		
		if ((target[1] == 7 && !pc.branco) || (target[1] == 0 && pc.branco))
			upgrade(pc, target);
		nextTurn();
	}
	
	private void capture(Dama pc, int pos[]) {
		
	}
	
	private void capture(Peao pc, int pos[]) {
		
	}
	
	/**
	 * Realiza promocao de peao para dama. Supoe 
	 * que ja foi verificado se upgrade eh valido
	 * ou nao.
	 * 
	 * @param pc peao sera promovido 
	 * @param pos posicao do peao
	 */
	private void upgrade(Peao pc, int pos[]) {
		vPeoes[pos[1]][pos[0]] = null;
		vDamas[pos[1]][pos[0]] = new Dama(pc.getColor());
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
		Peao peao;
		Dama dama;
		
		parser.setMovement(source, target);
		pos = parser.parsePosition();
		
		sourceCord = pos[0];
		targetCord = pos[1];
		
		System.out.println("Source: " + source);
		System.out.println("Target: " + target);
		
		if (vPeoes[sourceCord[1]][sourceCord[0]] != null) {
			peao = vPeoes[sourceCord[1]][sourceCord[0]];
			move(peao, sourceCord, targetCord);
		}
		
		if (vDamas[sourceCord[1]][sourceCord[0]] != null) {
			dama = vDamas[sourceCord[1]][sourceCord[0]];
			move(dama, sourceCord, targetCord);
		}
		
	}
	
	public void presentBoard() {
		String boardState = "";
		
		for (int i = 0; i < y; i++) {
			boardState += (y - i);
			boardState += ' ';
			for (int j = 0; j < x; j++) {
				if (vPeoes[i][j] != null)
					boardState += vPeoes[i][j].presentPiece();
				else if (vDamas[i][j] != null)
					boardState += vDamas[i][j].presentPiece();
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
