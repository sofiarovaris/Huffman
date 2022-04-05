import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*Finalidade: classe que l� os arquivos de texto*/

public class Arquivo {
	private String nomeArquivo;
	
	/*Finalidade: construtor da classe
	 * Pr� condi��o: nome do arquivo texto
	 * P�s condi��o: nenhuma */
	public Arquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	// getters e setters dos atributos da classe
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	//-------------------------------------------
	
	/*Finalidade: le caractere por caractere do arquivo de texto, adiciona na fila e conta
	 * a frequ�ncia dos dados
	 * Pr� condi��o: ter um arquivo de texto
	 * P�s condi��o: fila de n�s da �rvore bin�ria com suas respectivas informa��es*/
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
	
	/*Finalidade: adiciona na fila todos os itens contidos na tabela hash
	 * Pr� condi��o: hash map com os dados
	 * P�s condi��o: fila com os n�s da �rvore bin�ria e suas respectivas frequ�ncias*/
	public void adicionaNoFila(HashMap<String, Integer> hash, Queue<ArvoreBinaria> fila) {
		hash.forEach((key, value)->{
	    	ArvoreBinaria r = new ArvoreBinaria(key, value);
       		fila.add(r);
	    });
	}
	
	/*Finalidade: adiciona 1 a frequencia se aquele caractere ou palavra j� existe na tabela hash,
	 * caso nao exista, o dado � adicionado na tabela hash com frequ�ncia igual a 1
	 * a frequ�ncia dos dados
	 * Pr� condi��o: tabela hash
	 * P�s condi��o: tabela hash atualizada*/
	public void contaFrequencia(HashMap<String, Integer> hash, String palavra) {
		if(hash.get(palavra)!=null) {
    		hash.replace(palavra, hash.get(palavra)+1);
    	}else {
    		hash.put(palavra, 1);
    	}
	}	

	/*Finalidade: le caractere por caractere do arquivo de texto at� encontrar uma palavra, 
	 * adiciona na fila e conta a frequ�ncia dos dados
	 * Pr� condi��o: ter um arquivo de texto
	 * P�s condi��o: fila de n�s da �rvore bin�ria com suas respectivas informa��es*/
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
