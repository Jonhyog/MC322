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
    
    /**
     * Valida o movimento de uma dama entre 
     * a posicao source e target com seguindo
     * um caminho que pode contar outras pecas. 
     * Se eh valido atualiza o estado da dama.
     * 
     * @param source Posicao de saida (x, y)
     * @param target Posicao de destino (x, y)
     * @param caminhoPeoes array de peoes na direcao do movimento entre source e target
     * @param caminhoDamas array de damas na direcao do movimento entre source e target
     * @return true se eh valido se nao false
     */
    boolean isValid(int []source, int []target, Peao []caminhoPeoes, Dama caminhoDamas[]){
        boolean valid = true;
        boolean compatible;
        int y, x;
        y = target[1] - source[1];
        x = Math.abs(target[0] - source[0]);
        compatible = isCompatible(x, y);

        if(compatible == false)
            return false;
        
        for (int i = 0; i < caminhoPeoes.length; i++) {
        	if (caminhoPeoes[i] != null || caminhoDamas[i] != null) {
        		// Pecas da mesma cor
        		if (caminhoPeoes[i] != null && caminhoPeoes[i].getColor() == branco)
        			return false;
        		if (caminhoDamas[i] != null && caminhoDamas[i].getColor() == branco)
        			return false;
        		
        		// Se tabuleiro acaba nao ha espaco para movimento
        		if (i + 1 >= caminhoPeoes.length)
        			return false;
        		
        		// Verifica se ha pelo menos um espaco vazio
        		if (caminhoPeoes[i+1] != null || caminhoDamas[i+1] != null)
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
