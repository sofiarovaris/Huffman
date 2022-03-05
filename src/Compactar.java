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
import java.util.List;
import java.util.Queue;

public class Compactar {

	public ArvoreBinaria criarArvore(Queue<ArvoreBinaria> fila) {
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
		// TODO Auto-generated method stub
		File arq = new File("compactado.bin");
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arq));
		try {
	        arq.delete();
	        arq.createNewFile();
			o.writeObject(r);			
		}catch(IOException erro) {
				System.out.println("Erro: "+erro.getMessage());
	     }
		
		ArrayList<CodigoBits> codigos = new ArrayList<>();
		r.gerarListaBits(codigos,"");
		/*for (CodigoBits codigoBits : codigos) {
			System.out.println("caracter: "+codigoBits.getInfo()+" codigo: "+codigoBits.getCodigo());
		}*/
		BitSet b = new BitSet();
		try {
			int n = 0;
	        BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo),"UTF-8"));
			int c = lerArq.read();
			Character aux = (char) c;
	        while (c != -1) {
	        	//System.out.print(String.valueOf((char) c));
	        	for (CodigoBits codigoBits : codigos) {
	        		//System.out.println("getInfo: "+codigoBits.getInfo()+" aux: "+aux.toString());
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
	        /*for(int i=0; i<b.length(); i++) {
				if(b.get(i) == true) {
					System.out.print(1);
				}else {
					System.out.print(0);
				}
			}*/
	    }catch (IOException e) {}
		try {
			o.write(b.toByteArray());
			o.close();
		}catch(IOException erro) {
				System.out.println("Erro: "+erro.getMessage());
	     }
	}
	
	
}
