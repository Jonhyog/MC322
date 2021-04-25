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
        y = Math.abs(source[0] - target[0]);
        x = Math.abs(source[1] - target[1]);
        compatible = isCompatible(y, x);
        if(compatible == false){
            return false;
        }else{
            valid = true;
        }

            }
        }
        return valid;
    }

    boolean isCompatible(int x, int y){
        boolean compatible = false;
        if(y == x){
            compatible = true;
        }
        return compatible;
    }
}
