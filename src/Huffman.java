import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

public class Huffman {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		// TODO Auto-generated method stub
		long tempoInicial = System.currentTimeMillis();
		int opcao;
		Scanner ler = new Scanner(System.in);
		Compactar compactar = new Compactar();
		Descompactar descompactar = new Descompactar();
		
		System.out.println("Nome do arquivo");
		Arquivo arquivo = new Arquivo(ler.nextLine());
		
		System.out.println("1. Compactar Arquivo - Caracter");
		System.out.println("2. Descompactar Arquivo - Caracter");
		System.out.println("3. Compactar Arquivo - Palavra");
		System.out.println("4. Descompactar Arquivo - Palavra");
		opcao = ler.nextInt();
		
		ler.close();
		Queue<ArvoreBinaria> fila;
		ArvoreBinaria r;
		
		
		switch (opcao) {
		case 1: 
			 fila = arquivo.lerArquivoCaracter();
			 r = compactar.criarArvore(fila);
			 /*System.out.println("DEPOIS:");
			 for (ArvoreBinaria arvoreBinaria : fila) {
					System.out.println("Caracter: "+arvoreBinaria.getInfo()+" Frequ�ncia: "+arvoreBinaria.getFreq());
				}*/
			 //r.inOrdem();
			 //System.out.println();
			 //arquivo.guardarArquivoArvore(r);
			compactar.compactarArquivo(r, arquivo.getNomeArquivo());
			break;
		case 2: 
			descompactar.descompactarArquivo(arquivo.getNomeArquivo());
			break;
		case 3: 
			 fila = arquivo.lerArquivoPalavra();
			 /*System.out.println("DEPOIS:");
			 for (ArvoreBinaria arvoreBinaria : fila) {
					System.out.println("Caracter: "+arvoreBinaria.getInfo()+" Frequ�ncia: "+arvoreBinaria.getFreq());
			 }*/
			 r = compactar.criarArvore(fila);
			 r.inOrdem();
			 //System.out.println();
			 arquivo.guardarArquivoArvore(r);
			 r = compactar.criarArvore(fila);
			break;
		case 4: 
			
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida!");
		}
		System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
	}

}
