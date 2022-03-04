import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Huffman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long tempoInicial = System.currentTimeMillis();
		int opcao;
		Scanner ler = new Scanner(System.in);
		Compactar compactar = new Compactar();
		
		System.out.println("Nome do arquivo");
		Arquivo arquivo = new Arquivo(ler.nextLine());
		
		System.out.println("1. Compactar Arquivo - Caracter");
		System.out.println("2. Descompactar Arquivo - Caracter");
		System.out.println("3. Compactar Arquivo - Palavra");
		System.out.println("4. Descompactar Arquivo - Palavra");
		opcao = ler.nextInt();
		
		ler.close();
		
		switch (opcao) {
		case 1: 
			Queue<ArvoreBinaria> fila = arquivo.lerArquivoCaracter();
			 ArvoreBinaria r = compactar.huffman(fila);
			 /*System.out.println("DEPOIS:");
			 for (ArvoreBinaria arvoreBinaria : fila) {
					System.out.println("Caracter: "+arvoreBinaria.getInfo()+" Frequência: "+arvoreBinaria.getFreq());
				}*/
			 r.inOrdem();
			break;
		case 2: 
					
			break;
		case 3: 
			
			break;
		case 4: 
			
			break;
		default:
			throw new IllegalArgumentException("Opção inválida!");
		}
		System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
	}

}
