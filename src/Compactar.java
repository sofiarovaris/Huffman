import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class Compactar {

	public ArvoreBinaria criaFilaArvore(Queue<ArvoreBinaria> fila) {
		// TODO Auto-generated method stub
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

	public void compactarArquivo(ArvoreBinaria r, String nomeArquivo) throws FileNotFoundException, IOException {
		File arq = new File(nomeArquivo);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arq));
		try {
	        arq.delete();
	        arq.createNewFile();
			o.writeObject(r);			
		}catch(IOException erro) {
			System.out.println("Erro: "+erro.getMessage());
	    }
		HashMap<Integer, CodigoBits> tabelaHash = new HashMap<Integer, CodigoBits>();
		//HashMap<int, CodigoBits> tabelaHash = new HashMap<int, CodigoBits>();
		ArrayList<CodigoBits> codigos = new ArrayList<>();
		r.gerarListaBits(codigos,"");
		//r.gerarTabelaHashCodigos(tabelaHash, "");
		BitSet b = new BitSet();
		try {
			int n = 0;
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo),"UTF-8"));
			int c = lerArq.read();
			Character aux = (char) c;
	        while (c != -1) {
	        	for (CodigoBits codigoBits : codigos) {
					if(codigoBits.getInfo().equals(aux.toString())){
						for(int i=0; i<codigoBits.getCodigo().length(); i++) {
							if(codigoBits.getCodigo().charAt(i) == '0') {
								b.set(n++,false);
							}else {
								b.set(n++,true);
							}
						}
					}
				}
	        	c = lerArq.read();
	        	aux = (char) c;
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
