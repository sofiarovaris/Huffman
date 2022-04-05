import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/*Finalidade: classe onde é criada a árvore de frequencia e realizada a compactação dos arquivos por 
 * caractere e por palavra*/
public class Compactar {

	/*Finalidade: criar ávore binária de acordo com a fila de frequências (Código de Huffman)
	 * Pré condição: fila de nós da árvore ordenada por frequência
	 * Pós condição: retorna a cabeça da árvore criada */
	public ArvoreBinaria criaFilaArvore(Queue<ArvoreBinaria> fila) {
		while(fila.size()>1) {
			ArvoreBinaria x = fila.element();
			fila.remove();
			ArvoreBinaria y = fila.element();
			fila.remove();
			ArvoreBinaria z = new ArvoreBinaria(x.getFreq()+y.getFreq(), x, y);
			fila.add(z);
			Collections.sort((List<ArvoreBinaria>) fila);
		}
		return fila.element();
	}
	
	/*Finalidade: transforma todos os caracteres do arquivo de texto em seus códigos
	 * binários correspondentes
	 * Pré condição: árvore binária com os dados e arquivo de texto
	 * Pós condição: cria o arquivo 'compactado.bin' com os dados do arquivo de texto compactados */
	public void compactarArquivoCaracter(ArvoreBinaria r, String nomeArquivo) throws FileNotFoundException, IOException {
		BitSet b = new BitSet();
		HashMap<String, String> tabelaHash = new HashMap<String, String>();
		File arq = new File("compactado.bin");
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arq));
		String codigo;
		
		try {
	        arq.delete();
	        arq.createNewFile();
			o.writeObject(r);			
		}catch(IOException erro) {
			System.out.println("Erro: "+erro.getMessage());
	    }
		
		r.gerarTabelaHashCodigos(tabelaHash, "", 1);
		
		try {
			int n = 0;
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo),"UTF-8"));
			int c = lerArq.read();
			
	        while (c != -1) {
	        	codigo = tabelaHash.get(String.valueOf((char) c));
	        	n = adicionarSequenciaBits(codigo, b, n);
	        	c = lerArq.read();
	        }
	        b.set(n, true);
	        lerArq.close();
	    }catch (IOException e) {}
		try {
			o.write(b.toByteArray());
			o.close();
		}catch(IOException erro) {
			System.out.println("Erro: "+erro.getMessage());
		}
	}
	
	/*Finalidade: adiciona ao BitSet o código binário de determinado caractere ou palavra
	 * Pré condição: bitset e o código que será transformado em bits
	 * Pós condição: código binário adicionado ao BitSet*/
	private int adicionarSequenciaBits(String codigo, BitSet b, int n) {
		for(int i=0; i<codigo.length(); i++) {
			if(codigo.charAt(i) == '0') {
				b.set(n++,false);
			}else {
				b.set(n++,true);
			}
		}
		return n;
	}
	
	/*Finalidade: transforma todas as palavras do arquivo de texto em seus códigos
	 * binários correspondentes
	 * Pré condição: árvore binária com os dados e arquivo de texto
	 * Pós condição: cria o arquivo 'compactado.bin' com os dados do arquivo de texto compactados */
	public void compactarArquivoPalavra(ArvoreBinaria r, String nomeArquivo) throws FileNotFoundException, IOException {
		BitSet b = new BitSet();
		HashMap<String, String> tabelaHash = new HashMap<String, String>();
		File arq = new File("compactado.bin");
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arq));
		String codigo;
		StringBuilder palavra = new StringBuilder();
		
		try {
	        arq.delete();
	        arq.createNewFile();
			o.writeObject(r);			
		}catch(IOException erro) {
			System.out.println("Erro: "+erro.getMessage());
	    }
		
		r.gerarTabelaHashCodigos(tabelaHash, "", 1);
		
		try {
			int n = 0;
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo),"UTF-8"));
			int c = lerArq.read();
			
	        while (c != -1) {
	        	
	        	if(Character.isLetterOrDigit(c)) {
	        		palavra.append((char) c);
	        	}else {
	        		codigo = tabelaHash.get(palavra.toString());
		        	n = adicionarSequenciaBits(codigo, b, n);
		        	palavra.delete(0, palavra.length());
		        	
		        	if(c != -1) {
		        		palavra.append((char) c);
			        	codigo = tabelaHash.get(palavra.toString());
			        	n = adicionarSequenciaBits(codigo, b, n);
			        	palavra.delete(0, palavra.length());
		        	}
	        	}
	        	c = lerArq.read();
	        }
	        b.set(n, true);
	        lerArq.close();
	    }catch (IOException e) {}
		try {
			o.write(b.toByteArray());
			o.close();
		}catch(IOException erro) {
			System.out.println("Erro: "+erro.getMessage());
		}
	}
}
