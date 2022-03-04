import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Arquivo {
	private String nomeArquivo;
	
	public Arquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public Queue<ArvoreBinaria> lerArquivoCaracter() {
		Queue<ArvoreBinaria> fila = new LinkedList<>();
	    try {
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo),"UTF-8"));
			int c = lerArq.read();
	        while (c != -1) {
	        	if(c!=13) {
	        		if(fila.isEmpty()) {
		        		ArvoreBinaria r = new ArvoreBinaria(String.valueOf((char) c), 1);
		        		fila.add(r);
		        	}else {
		        		loop:{
		        			for (ArvoreBinaria arvoreBinaria : fila) {
								if(arvoreBinaria.getInfo().equals(String.valueOf((char) c))){
									arvoreBinaria.setFreq(arvoreBinaria.getFreq()+1);
									break loop;
								}
							}
			        		ArvoreBinaria r = new ArvoreBinaria(String.valueOf((char) c), 1);
			        		fila.add(r);
		        		}
		        	}
	        	}
	        	c = lerArq.read();
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    Collections.sort((List<ArvoreBinaria>) fila);
		return fila;
	}
	
	/*
	
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
	}*/
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
}
