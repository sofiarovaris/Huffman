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

	public Queue<ArvoreBinaria> lerArquivoPalavra() {
		// TODO Auto-generated method stub
		Queue<ArvoreBinaria> fila = new LinkedList<>();
	    try {
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo),"UTF-8"));
			int c = lerArq.read();
	        while (c != -1) {
	        	String palavra = "";
	        	while(Character.isLetterOrDigit(c)){
	        		palavra = palavra + String.valueOf((char) c);
	        		c = lerArq.read();
	        	}
	        	if(palavra.equals("")) {
	        		palavra = palavra + String.valueOf((char) c);
	        		//|| (c==128) || (c==130) || (c==131) || (c==133) || (c==135) || (c==136) || (c==144) || (c==147) || (c==160) || (c==161) || (c==162) || (c==163) || (c==181) || (c==182) || (c==183) || (c==198) || (c==199) || (c==210) || (c==214) || (c==224) || (c==228) || (c==233) || (c==234)
	        	}else if(!(Character.isLetterOrDigit(c))) {
	        		//System.out.println("Palavra: "+palavra);
		        	if(fila.isEmpty()) {
				        ArvoreBinaria r = new ArvoreBinaria(palavra, 1);
				        fila.add(r);
				    }else {
				        loop:{
				        	for (ArvoreBinaria arvoreBinaria : fila) {
								if(arvoreBinaria.getInfo().equals(palavra)){
									arvoreBinaria.setFreq(arvoreBinaria.getFreq()+1);
									break loop;
								}
							}
					       	ArvoreBinaria r = new ArvoreBinaria(palavra, 1);
					       	fila.add(r);
				        }
				    }
		        	palavra = String.valueOf((char) c);
	        	}
	        	//System.out.println("Palavra: "+palavra);
	        	if(fila.isEmpty()) {
			        ArvoreBinaria r = new ArvoreBinaria(palavra, 1);
			        fila.add(r);
			    }else {
			        loop:{
			        	for (ArvoreBinaria arvoreBinaria : fila) {
							if(arvoreBinaria.getInfo().equals(palavra)){
								arvoreBinaria.setFreq(arvoreBinaria.getFreq()+1);
								break loop;
							}
						}
				       	ArvoreBinaria r = new ArvoreBinaria(palavra, 1);
				       	fila.add(r);
			        }
			    }
	        	//System.out.print(String.valueOf((char) c));
	        	
	        	c = lerArq.read();
	        }
	        lerArq.close();
	    }catch (IOException e) {}
	    Collections.sort((List<ArvoreBinaria>) fila);
		return fila;
	}
	
}
