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

public class Compactar {

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

	/*public void compactarArquivo(ArvoreBinaria r, String nomeArquivo) throws FileNotFoundException, IOException {
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
		
		r.gerarTabelaHashCodigos(tabelaHash, "");
		
		try {
			int n = 0;
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo),"UTF-8"));
			int c = lerArq.read();
			
	        while (c != -1) {
	        	codigo = tabelaHash.get(String.valueOf((char) c));
	        	for(int i=0; i<codigo.length(); i++) {
					if(codigo.charAt(i) == '0') {
						b.set(n++,false);
					}else {
						b.set(n++,true);
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
	}*/
	
	public void compactarArquivo(ArvoreBinaria r, String nomeArquivo) throws FileNotFoundException, IOException {
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
	        	for(int i=0; i<codigo.length(); i++) {
					if(codigo.charAt(i) == '0') {
						b.set(n++,false);
					}else {
						b.set(n++,true);
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
