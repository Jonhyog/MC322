package mc322.lab05;

public class Dama {
    boolean branco;

    Dama(boolean pBranco){
        branco = pBranco;
    }
    
    public boolean getColor() {
    	return this.branco;
    }
    
    public char presentPiece() {
    	if (branco)
    		return 'B';
    	else
    		return 'P';
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
