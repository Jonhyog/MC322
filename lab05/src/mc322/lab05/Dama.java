package mc322.lab05;

public class Dama {
	private int x, y;
    boolean branco;

    Dama(boolean pBranco, int x, int y){
        branco = pBranco;
        this.x = x;
        this.y = y;
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
    
    public void updatePosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public int[] getPosition() {
    	return new int[] {x, y};
    }

    boolean isValid(int []source, int []target, boolean comer){
        boolean valid = false;
        boolean compatible;
        int y, x;
        y = target[1] - source[1];
        x = Math.abs(target[0] - source[0]);
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
