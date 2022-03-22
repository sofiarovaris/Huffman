import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
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
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
	    try {
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo),"UTF-8"));
			int c = lerArq.read();
	        while (c != -1) {
	        	contaFrequencia(hash, String.valueOf((char) c));
	        	c = lerArq.read();
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    adicionaNoFila(hash, fila);
	    Collections.sort((List<ArvoreBinaria>) fila);
		return fila;
	}
	
	public void adicionaNoFila(HashMap<String, Integer> hash, Queue<ArvoreBinaria> fila) {
		hash.forEach((key, value)->{
	    	ArvoreBinaria r = new ArvoreBinaria(key, value);
       		fila.add(r);
	    });
	}
	
	public void contaFrequencia(HashMap<String, Integer> hash, String palavra) {
		if(hash.get(palavra)!=null) {
    		hash.replace(palavra, hash.get(palavra)+1);
    	}else {
    		hash.put(palavra, 1);
    	}
	}	

	public Queue<ArvoreBinaria> lerArquivoPalavra() {
		Queue<ArvoreBinaria> fila = new LinkedList<>();
		StringBuilder palavra = new StringBuilder();
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
	    try {
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo),"UTF-8"));
			int c = lerArq.read();
	        while (c != -1) {
	        	if(Character.isLetterOrDigit(c)) {
	        		palavra.append((char) c);
	        	}else {
	        		contaFrequencia(hash, palavra.toString());
		        	palavra.delete(0, palavra.length());
		        	
		        	if(c != -1) {
		        		palavra.append((char) c);
		        		contaFrequencia(hash, palavra.toString());
			        	palavra.delete(0, palavra.length());
		        	}
	        	}
	        	
		        c = lerArq.read();
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    adicionaNoFila(hash, fila);
	    Collections.sort((List<ArvoreBinaria>) fila);
		return fila;
	}
	
}
