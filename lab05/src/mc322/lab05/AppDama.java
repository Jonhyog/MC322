package mc322.lab05;

public class AppDama {
	public static void main(String args[]) {
		Tabuleiro board = new Tabuleiro();
		System.out.println("Tabuleiro Inicial:");
		board.presentBoard();
		
		board.movePiece("b6", "c5");
		board.presentBoard();
		
		board.movePiece("c3", "d4");
		board.presentBoard();
		
		board.movePiece("e3", "f4");
		board.presentBoard();
		
		board.movePiece("b6", "c5");
		board.presentBoard();
		
		// Movimento Invalido. Teste upgrade brancas.
		board.movePiece("a1", "a8");
		board.presentBoard();
		
		// Movimento Invalido. Teste upgrade pretas.
		board.movePiece("b8", "b1");
		board.presentBoard();
	}
}
