package mc322.lab04;

public class AppRestaUm {
	static String layout = "  PPP  \n  PPP  \nPPPPPPP\nPPP-PPP\nPPPPPPP\n  PPP  \n  PPP  \n";
	static CSVReader csv = new CSVReader();
	
	public static String[] executaJogo(String jogoCSV) {
		int i = 0;
		String boardState, jogo[], commands[], movement[];
		Tabuleiro board = new Tabuleiro(layout);
		
		csv.setDataSource(jogoCSV);
		commands = csv.requestCommands();
		jogo = new String[commands.length + 1];
		
		System.out.println("Tabuleiro inicial:");
		boardState = board.presentBoard();
		System.out.println(boardState);
		jogo[i] = boardState;
		i++;
		
		while (i < commands.length + 1) {
			movement = commands[i - 1].split(":");
			board.movePiece(movement[0], movement[1]);
			boardState = board.presentBoard();
			System.out.println(boardState);
			jogo[i] = boardState;
			i++;
		}
		
		return jogo;
	}
	
	public static void main(String[] args) {
		executaJogo("testes/teste08.csv");
	}
}
