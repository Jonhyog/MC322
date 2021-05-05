package mc322.lab05b;

public class Peca {
	private int x, y;
    boolean branco;

    Peca(boolean pBranco, int x, int y){
       branco = pBranco;
       this.x = x;
       this.y = y;
    }
    
    public boolean getColor() {
    	return this.branco;
    }
    
    public char presentPiece() {
    	if (branco)
    		return 'b';
    	else
    		return 'p';
    }
    
    public void updatePosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public int[] getPosition() {
    	return new int[] {x, y};
    }

    public boolean isValid(int []source, int []target, Peca []caminhoPecas){
    	for (int i = 0; i < caminhoPecas.length; i++) {
        	if (caminhoPecas[i] != null) {
        		// Pecas da mesma cor
        		if (caminhoPecas[i] != null && caminhoPecas[i].getColor() == branco)
        			return false;
        		
        		// Se tabuleiro acaba nao ha espaco para movimento
        		if (i + 1 >= caminhoPecas.length)
        			return false;
        		
        		// Verifica se ha pelo menos um espaco vazio
        		if (caminhoPecas[i+1] != null)
        			return false;
        	}
        }
        return true;
    }

    private boolean isCompatible(int x, int y){
        return true;
    }
}