import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.BitSet;
import java.util.HashMap;

public class Descompactar {

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
				FileWriter fw = new FileWriter(desc);
				
				ArvoreBinaria r = (ArvoreBinaria) o.readObject();
				BitSet b = BitSet.valueOf(o.readAllBytes());

				r.gerarTabelaHashCodigos(tabelaHash, "", 2);
	
				String codigo="";
				for(int i=0; i<b.length(); i++) {
					if(b.get(i) == true) {
						codigo = codigo + "1";
					}else {
						codigo = codigo + "0";
					}
					if(tabelaHash.get(codigo) != null) {
						fw.write(tabelaHash.get(codigo));
						codigo = "";
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
