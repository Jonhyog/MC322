package mc322.lab05b;

public class AppDama {
	private static CSVHandling csv = new CSVHandling();
	
	public static String[][] executaJogo(String pathJogo, String pathSaida) {
		String commands[], movement[], estados[][];
		Tabuleiro board = new Tabuleiro();
		
		csv.setDataSource(pathJogo);
		commands = csv.requestCommands();
		
		System.out.println("Tabuleiro Inicial:");
		board.imprimirTabuleiro();
		
		estados = new String[commands.length][1];
		for (int i = 0; i < commands.length; i++) {
			movement = commands[i].split(":");
			board.solicitaMovimento(movement[0], movement[1]);
			board.imprimirTabuleiro();
			estados[i] = board.exportarArquivo();
		}
		
		csv.setDataExport(pathSaida);
		csv.exportState(estados[estados.length - 1]);
		
		return estados;
	}
	
	public static void main(String args[]) {
		String caminhoJogo = null, caminhoSaida = null;
		
		if (args.length == 0) {
			System.out.println("Insira os argumentos obrigatorios.");
			return;
		}
		
		caminhoJogo = args[0];
		caminhoSaida = args[1];
		
		if (caminhoJogo != null) {
			executaJogo(caminhoJogo, caminhoSaida);			
		}
	}
}
