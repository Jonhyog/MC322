package mc322.lab05b;

public class Dama extends Peca{
	
	Dama(boolean pBranco, int x, int y) {
		super(pBranco, x, y);
	}
	
    public char presentPiece() {
    	if (branco)
    		return 'B';
    	else
    		return 'P';
    }

    public boolean isValid(int []source, int []target, Peca []caminhoPecas){
        boolean valid = true;
        boolean compatible;
        int y, x;
        y = target[1] - source[1];
        x = Math.abs(target[0] - source[0]);
        compatible = isCompatible(x, y);

        if(compatible == false)
            return false;
        
        valid = super.isValid(source, target, caminhoPecas);
        
        if (valid)
        	updatePosition(target[0], target[1]);
		
        return valid;
    }

    private boolean isCompatible(int x, int y){
        boolean compatible = false;
        if(x == Math.abs(y)){
            compatible = true;
        }
        return compatible;
    }
}