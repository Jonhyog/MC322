package mc322.lab05;

public class Tabuleiro {
	private int x, y;
	private int turno;
	private Peao vPeoes[][];
	private Dama vDamas[][];
	private MovementParser parser;
	
	Tabuleiro () {
		x = 8;
		y = 8;
		
		parser = new MovementParser();
		parser.setDimensions(x, y);
		
		turno = 0;
		
		// FIX-ME: Inicializar corretamente Peoes com cor adequada
		vPeoes = new Peao[y][x];
		vDamas = new Dama[y][x];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i < 3 && (j + i) % 2 != 0)
					vPeoes[i][j] = new Peao();
				else if (i > 4 && (j + i) % 2 != 0)
					vPeoes[i][j] = new Peao();
				else
					vPeoes[i][j] = null;
				vDamas[i][j] = null;
			}
		}
	}
	
	private void move(Dama pc, int source[], int target[]) {
		// FIX-ME: Verificar se eh valido antes de mover
			// Se movimento eh valido, determinar se ocorre captura
		vDamas[target[1]][target[0]] = pc;
		nextTurn();
	}
	
	private void move(Peao pc, int source[], int target[]) {
		// FIX-ME: Verificar se eh valido antes de mover
			// Se movimento eh valido, determinar se ocorre captura
		vPeoes[target[1]][target[0]] = pc;
		
		if (target[1] == 7)
			upgrade(pc, target);
		nextTurn();
	}
	
	private void capture(Dama pc, int pos[]) {
		
	}
	
	private void capture(Peao pc, int pos[]) {
		
	}
	
	private void upgrade(Peao pc, int pos[]) {
		vPeoes[pos[1]][pos[0]] = null;
		vDamas[pos[1]][pos[0]] = new Dama(/*Inserir parametros para cor*/);
	}
	
	private void nextTurn() {
		turno++;
	}
	
	public void movePiece(String source, String target) {
		int pos[][], sourceCord[], targetCord[];
		Peao peao;
		Dama dama;
		
		parser.setMovement(source, target);
		pos = parser.parsePosition();
		
		sourceCord = pos[0];
		targetCord = pos[1];
		
		if (vPeoes[sourceCord[1]][sourceCord[0]] != null) {
			peao = vPeoes[sourceCord[1]][sourceCord[0]];
			move(peao, sourceCord, targetCord);
		}
		
		if (vDamas[sourceCord[1]][sourceCord[0]] != null) {
			dama = vDamas[sourceCord[1]][sourceCord[0]];
			move(dama, sourceCord, targetCord);
		}
		
	}
}
