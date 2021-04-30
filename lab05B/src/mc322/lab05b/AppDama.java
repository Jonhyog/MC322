package mc322.lab05b;

public class AppDama {
	public static void executaJogo(String pathJogo) {
		String commands[], movement[];
		Tabuleiro board = new Tabuleiro();
		CSVHandling csv = new CSVHandling();
		
		csv.setDataSource(pathJogo);
		commands = csv.requestCommands();
		
		System.out.println("Tabuleiro Inicial:");
		board.presentBoard();
		for (int i = 0; i < commands.length; i++) {
			movement = commands[i].split(":");
			board.movePiece(movement[0], movement[1]);
			board.presentBoard();
		}
	}
	
	public static void exibirAjuda() {
		System.out.println("AppDama <-h> <caminhoJogo>");
		System.out.println("\t-h(opcional): null | Exibe tela de ajuda");
		System.out.println("\tcaminhoJogo(obrigatorio): caminho para csv | Executa jogo");
	}
	
	public static void main(String args[]) {
		String caminhoJogo = null;
		String verificaFormato[];
		
		if (args.length == 0) {
			System.out.println("Insira os argumentos obrigatorios.");
		}
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-h")) {
				exibirAjuda();
				return;
			}
			
			verificaFormato = args[i].split("\\.");
			if (verificaFormato.length == 2) {
				if (verificaFormato[1].equals("csv"))
					caminhoJogo = args[i];
			}
		}
		
		if (caminhoJogo != null) {
			executaJogo(caminhoJogo);			
		}
	}
}
