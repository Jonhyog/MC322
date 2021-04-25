package mc322.lab05;

import java.lang.Math;

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
            if((branco) && comer == true && y == 2 && x == 2){
                valid = true;
            }else if((!branco) && comer == true && y == -2 && x == 2){
                valid = true;
            }else if((branco) && comer == false && y == 1 && x == 1){
                valid = true;
            }else if((!branco) && comer == false && y == -1 && x == 1){
                valid = true;
            }
        }
        return valid;
    }

    boolean isCompatible(int x, int y){
        boolean compatible = false;
        if (x == Math.abs(y)) {
            if ((branco) && (y == -2 || y == -1) && (x == 2 || x == 1))
                return true;
            else if ((!branco) && (y == 2 || y == 1) && (x == 2 || x == 1))
                return true;
        }        
        return compatible;
    }
}
