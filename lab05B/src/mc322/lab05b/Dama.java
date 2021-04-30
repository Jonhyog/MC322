package mc322.lab05b;

import java.lang.Math;

public class Dama extends Peca{

    public char presentPiece() {
    	if (branco)
    		return 'B';
    	else
    		return 'P';
    }

    boolean isValid(int []source, int []target, Peca []caminhoPecas){
        boolean valid = true;
        boolean compatible;
        int y, x;
        y = target[1] - source[1];
        x = Math.abs(target[0] - source[0]);
        compatible = isCompatible(x, y);

        if(compatible == false)
            return false;
        
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
        
        if (valid)
        	updatePosition(target[0], target[1]);
		
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