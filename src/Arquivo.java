import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Arquivo {
	private String nomeArquivo;
	
	public Arquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public Queue<ArvoreBinaria> leArquivoCaracter() {
		Queue<ArvoreBinaria> fila = new LinkedList<>();
	    try {
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo),"UTF-8"));
			int c = lerArq.read();
	        while (c != -1) {
	        	adicionaNoFila(fila, String.valueOf((char) c));
	        	c = lerArq.read();
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    Collections.sort((List<ArvoreBinaria>) fila);
		return fila;
	}
	
	public void adicionaNoFila(Queue<ArvoreBinaria> fila, String palavra) {
		if(fila.isEmpty() || !buscaPalavraLista(fila, palavra)) {
    		ArvoreBinaria r = new ArvoreBinaria(palavra, 1);
       		fila.add(r);
    	}
	}

	public boolean buscaPalavraLista(Queue<ArvoreBinaria> fila, String palavra) {
		for (ArvoreBinaria arvoreBinaria : fila) {
			if(arvoreBinaria.getInfo().equals(palavra)){
				arvoreBinaria.setFreq(arvoreBinaria.getFreq()+1);
				return true;
			}
		}
		return false;
	}
	
	

	public Queue<ArvoreBinaria> lerArquivoPalavra() {
		Queue<ArvoreBinaria> fila = new LinkedList<>();
		StringBuilder palavra = new StringBuilder();
	    try {
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo),"UTF-8"));
			int c = lerArq.read();
	        while (c != -1) {
	        	while(Character.isLetterOrDigit(c)){
	        		palavra.append((char) c);
	        		c = lerArq.read();
	        	}
	        	if(!palavra.toString().equals("")) {
	        		adicionaNoFila(fila, palavra.toString());
		        	palavra.delete(0, palavra.length());
	        	}
	        	
	        	if(c != -1) {
	        		palavra.append((char) c);
		        	adicionaNoFila(fila, palavra.toString());
		        	palavra.delete(0, palavra.length());
		        	
		        	c = lerArq.read();	
	        	}
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    Collections.sort((List<ArvoreBinaria>) fila);
		return fila;
	}
	
}
