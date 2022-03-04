import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Arquivo {
	private String nomeArquivo;
	
	public Arquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public Fila lerArquivoCaracter() {
		Fila fila = new Fila();
	    try {
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo),"UTF-8"));
			int c = lerArq.read();
	        while (c != -1) {
	        	if(fila.vazia()) {
	        		ElementoFila e = new ElementoFila(String.valueOf((char) c), 1);
	        		fila.adicionaElemento(e);
	        	}else {
	        		if(!fila.elementoNaFila(String.valueOf((char) c))){ //nao ta na fila
	        			ElementoFila e = new ElementoFila(String.valueOf((char) c), 1);
		        		fila.adicionaElemento(e);
	        		}
	        	}
	        	c = lerArq.read();
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    fila.organizarFila();
		return fila;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
}
