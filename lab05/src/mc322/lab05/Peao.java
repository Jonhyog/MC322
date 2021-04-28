package mc322.lab05;

import java.lang.Math;

public class Peao {
	private int x, y;
    boolean branco;

    Peao(boolean pBranco, int x, int y){
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
    
    /**
     * Valida o movimento de um peao entre 
     * a posicao source e target com um possivel
     * obstaculo do tipo peao. Se eh valido atualiza
     * o estado do peao
     * 
     * @param source Posicao de saida (x, y)
     * @param target Posicao de destino (x, y)
     * @param obstacle Obstaculo para o movimento do peao
     * (instancia de Peao ou null)
     * @return true se eh valido se nao false
     */
    boolean isValid(int []source, int []target, Peao obstacle){
        boolean valid = false;
        boolean compatible;
        int y, x;
        int obstaclePos[];
        
        y = target[1] - source[1];
        x = Math.abs(target[0] - source[0]);
        compatible = isCompatible(x, y);
        
        if (compatible == false)
        	return false;
        
        if (obstacle != null) {
        	if (branco == obstacle.getColor())
        		return false;
        	
        	obstaclePos = obstacle.getPosition();
    		if (target[0] == obstaclePos[0] && target[1] == obstaclePos[1])
        		valid = false;
    		else if ((branco) && (y == -2 && x == 2))
    			valid = true;
    		else if ((!branco) && (y == 2 && x == 2))
    			valid = true;
    	} else {
    		if ((branco) && (y == -1 && x == 1))
    			valid = true;
    		else if ((!branco) && (y == 1 && x == 1))
    			valid = true;
    	}
        
        if (valid)
        	updatePosition(target[0], target[1]);
        
        return valid;
    }
    
    /**
     * Valida o movimento de um peao entre 
     * a posicao source e target com um possivel
     * obstaculo do tipo dama. Se eh valido atualiza
     * o estado do peao
     * 
     * @param source Posicao de saida (x, y)
     * @param target Posicao de destino (x, y)
     * @param obstacle Obstaculo para o movimento do peao
     * (instancia de Dama ou null)
     * @return true se eh valido se nao false
     */
    boolean isValid(int []source, int []target, Dama obstacle){
        boolean valid = false;
        boolean compatible;
        int y, x;
        int obstaclePos[];
        
        y = target[1] - source[1];
        x = Math.abs(target[0] - source[0]);
        compatible = isCompatible(x, y);

        if (compatible == false)
        	return false;
        
        if (obstacle != null) {
        	if (branco == obstacle.getColor())
        		return false;
        	
        	obstaclePos = obstacle.getPosition();
    		if (target[0] == obstaclePos[0] && target[1] == obstaclePos[1])
    			valid = false;
    		if ((branco) && (y == -2 && x == 2))
    			valid = true;
    		if ((!branco) && (y == 2 && x == 2))
    			valid = true;
    	} else {
    		if ((branco) && (y == -1 && x == 1))
    			valid = true;
    		if ((!branco) && (y == 1 && x == 1))
    			valid = true;
    	}
        
        if (valid)
        	updatePosition(target[0], target[1]);
        
        return valid;
    }

    boolean isCompatible(int x, int y){
        boolean compatible = false;
        if (x == Math.abs(y)) {
            if ((branco) && ((y == -2 && x == 2) || (y == -1 && x == 1)))
                return true;
            else if ((!branco) && ((y == 2 && x == 2) || (y == 1 && x == 1)))
                return true;
        }        
        return compatible;
    }
}
