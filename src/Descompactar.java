import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.BitSet;

public class Descompactar {

	public void descompactarArquivo(String nomeArquivo) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			File arq = new File(nomeArquivo);
			if(arq.exists()){
				File desc = new File("descompactado.txt");
				if(desc.exists()) {
					desc.createNewFile();
				}
				FileWriter fw = new FileWriter(desc);
				
				ObjectInputStream o = new ObjectInputStream(new FileInputStream(arq));
				ArvoreBinaria r = (ArvoreBinaria) o.readObject();
				BitSet b = BitSet.valueOf(o.readAllBytes());
				
				ArrayList<CodigoBits> codigos = new ArrayList<>();
				r.gerarListaBits(codigos,"");
				String codigo = "";
				for(int i=0; i<b.length(); i++) {
					if(b.get(i) == true) {
						codigo = codigo + "1";
					}else {
						codigo = codigo + "0";
					}
					
					for (CodigoBits codigoBits : codigos) {
						if(codigoBits.getCodigo().equals(codigo)) {
							fw.write(codigoBits.getInfo());
							codigo = "";
						}
					}
				}
				fw.close();
			}
			
		}catch(IOException e) {
			System.out.printf("Erro: %s", e.getMessage());
		}
	}
}
