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

    boolean isValid(int []source, int []target, boolean comer){
        boolean valid = false;
        boolean compatible;
        int y, x;
        y = target[0] - source[0];
        x = Math.abs(target[1] - source[1]);
        compatible = isCompatible(x, y);

        if(compatible == false){
            return false;
        }else{
            valid = true;
        }
        return valid;
    }

    boolean isCompatible(int x, int y){
        boolean compatible = false;
        if(x == Math.abs(y)){
            compatible = true;
        }
        return compatible;
    }
}
