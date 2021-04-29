package mc322.lab05;

public class AppDama {
	public static void executaJogo() {
		String pathPartida = "testes/teste07.csv", commands[], movement[];
		Tabuleiro board = new Tabuleiro();
		CSVReader csv = new CSVReader();
		
		csv.setDataSource(pathPartida);
		commands = csv.requestCommands();
		
		System.out.println("Tabuleiro Inicial:");
		board.presentBoard();
		for (int i = 0; i < commands.length; i++) {
			movement = commands[i].split(":");
			board.movePiece(movement[0], movement[1]);
			board.presentBoard();
		}
	}
	public static void main(String args[]) {
		executaJogo();
	}
}
