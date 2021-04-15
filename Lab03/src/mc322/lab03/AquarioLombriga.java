package mc322.lab03;

public class AquarioLombriga {
	int sizeAquario, sizeLombriga;
    int posLombriga;
    boolean direita;
    
    public AquarioLombriga(int sizeAquario, int sizeLombriga, int posLombriga) {
        this.sizeAquario = sizeAquario;
        this.sizeLombriga = sizeLombriga;
        this.posLombriga = posLombriga;
        this.direita = true;
        
        // Lombriga maior que aquario
        if (this.sizeLombriga > this.sizeAquario) {
        	this.sizeAquario = this.sizeLombriga * 2;
        }
        
        // Trata caso em que a lombriga nao cabe na pos espeficicada
        if (this.posLombriga + this.sizeLombriga - 1 > this.sizeAquario) {
        	this.posLombriga = 1;
        }
    }
    
    void crescer() {
    	if (direita) {
    		if (posLombriga > 1) {
    			posLombriga--;
    			sizeLombriga++;
    		}
    	} else {
    		if (posLombriga < sizeAquario) {
    			posLombriga++;
    			sizeLombriga++;
    		}
    	}
    }
    
    void virar() {
    	if (direita)
    		posLombriga = posLombriga + sizeLombriga - 1;
    	else
    		posLombriga = posLombriga - sizeLombriga + 1;
    	direita = !direita;
    	
    }
    
    void mover() {
    	int posCabeca;
    	
    	if (direita) {
    		posCabeca = posLombriga + sizeLombriga - 1;
    		
    		if (posCabeca == sizeAquario)
    			virar();
    		else
    			posLombriga++;
    		
    	} else {
    		posCabeca = posLombriga - sizeLombriga + 1;
    		
    		if (posCabeca == 1)
    			virar();
    		else
    			posLombriga--;
    	}
    }
    
    String apresenta() { 
        String lombriga = "";
        char pixel;
        int numPartes = 0; // Conta numero de partes renderizadas da lombriga
        
        for (int i = 1; i <= sizeAquario; i++) {
        	pixel = '#';
        	
        	if (numPartes < sizeLombriga) {
        		
        		if (direita && posLombriga + numPartes == i) {
        			pixel = numPartes == sizeLombriga - 1 ? 'O':'@';
        			numPartes++;
        		}
        		
        		if (!direita && posLombriga - (sizeLombriga - numPartes - 1) == i) {
        			pixel = numPartes == 0 ? 'O':'@';
        			numPartes++;
        		}
        		
        	}
        	
        	lombriga = lombriga + pixel;
        	
        }
        
        return lombriga;
    }
}
