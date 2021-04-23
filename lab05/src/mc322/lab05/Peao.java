package mc322.lab05;

public class Peao {
    boolean branco;

    Peao(boolean pBranco){
       branco = pBranco;
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

    boolean isValid(){
        boolean valid = false;

        return valid;
    }

    boolean isCompatible(){
        boolean compatible = false;

        return compatible;
    }
}
