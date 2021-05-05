package mc322.lab05b;

public class Peao extends Peca{
	
	Peao(boolean pBranco, int x, int y) {
		super(pBranco, x, y);
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
        if (x == Math.abs(y)) {
            if ((branco) && ((y == -2 && x == 2) || (y == -1 && x == 1)))
                return true;
            else if ((!branco) && ((y == 2 && x == 2) || (y == 1 && x == 1)))
                return true;
        }        
        return compatible;
    }
}