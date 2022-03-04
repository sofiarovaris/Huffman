import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Compactar {

	public ArvoreBinaria huffman(Queue<ArvoreBinaria> fila) {
		// TODO Auto-generated method stub
		while(fila.size()>1) {
			System.out.println("TAMANHO FILA: "+fila.size());
			System.out.println("-------LISTA ATUALIZADA--------");
			for (ArvoreBinaria arvoreBinaria : fila) {
				System.out.println("Caracter: "+arvoreBinaria.getInfo()+" Frequência: "+arvoreBinaria.getFreq());
			}
			System.out.println("-------------------------------");
			ArvoreBinaria x = fila.element();
			System.out.println("x:"+x.getInfo());
			fila.remove();
			ArvoreBinaria y = fila.element();
			System.out.println("y:"+y.getInfo());
			fila.remove();
			ArvoreBinaria z = new ArvoreBinaria(x.getFreq()+y.getFreq(), x, y);
			System.out.println("z:"+z.getInfo()+" freq:"+z.getFreq());
			fila.add(z);
			Collections.sort((List<ArvoreBinaria>) fila);
		}
		return fila.element();
	}
	
	
}
