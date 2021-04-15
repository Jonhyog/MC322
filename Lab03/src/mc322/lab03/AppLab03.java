package mc322.lab03;

public class AppLab03 {
	public static void main(String[] args) {
		String chave = "080403MCMVM";
		String frame;
		Animacao animacao = new Animacao(chave);
		
		// Estado Inicial
		frame = animacao.apresenta();
		System.out.println(frame);
		
		// Animacao
		for (int i = 0; i < animacao.numFrames; i++) {
			animacao.passo();
			frame = animacao.apresenta();
			System.out.println(frame);
		}
	}
}
