import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Collections;
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
	        	//System.out.print(String.valueOf((char) c));
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
	        	c = lerArq.read();
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    Collections.sort((List<ArvoreBinaria>) fila);
		return fila;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public void guardarArquivoArvore(ArvoreBinaria r){
		File arq = new File("compactado.bin");
		try {
	        arq.delete();
	        arq.createNewFile();
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arq));
			o.writeObject(r);
			o.close();
		}catch(IOException erro) {
			System.out.println("Erro: "+erro.getMessage());
	     }
	}
	
}
