import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.BitSet;
import java.util.HashMap;

/*Finalidade: classe para descompactar os arquivos*/
public class Descompactar {

	/*Finalidade: le a árvore binária e os bits do arquivo, e decodifica os códigos binários para
	 * seus caracteres ou suas palavras correspondentes
	 * Pré condição: arquivo contendo a árvore binária necessária para descompactação
	 * Pós condição: arquivo descompactado */
	public void descompactarArquivo(String nomeArquivo) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			File arq = new File(nomeArquivo);
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(arq));
			
			HashMap<String, String> tabelaHash = new HashMap<String, String>();
			
			if(arq.exists()){
				File desc = new File("descompactado.txt");
				if(desc.exists()) {
					desc.createNewFile();
				}
				
				OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(desc),"UTF-8");
				
				ArvoreBinaria r = (ArvoreBinaria) o.readObject();
				BitSet b = BitSet.valueOf(o.readAllBytes());

				r.gerarTabelaHashCodigos(tabelaHash, "", 2);
	
				StringBuilder codigo = new StringBuilder();
				for(int i=0; i<b.length(); i++) {
					if(b.get(i) == true) {
						codigo.append(1);
					}else {
						codigo.append(0);
					}
					if(tabelaHash.get(codigo.toString()) != null) {
						fw.write(tabelaHash.get(codigo.toString()));
						codigo.delete(0, codigo.length());
					}
				}
				fw.close();
				o.close();
			}
		}catch(IOException e) {
			System.out.printf("Erro: %s", e.getMessage());
		}
	}
}
