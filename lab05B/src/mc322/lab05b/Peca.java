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

    boolean isValid(int []source, int []target, Peca []caminhoPecas){
        return true;
    }

    boolean isCompatible(int x, int y){
        return true;
    }
}