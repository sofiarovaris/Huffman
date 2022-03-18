import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

public class Huffman {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		// TODO Auto-generated method stub
		long tempoInicial = 0;
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
			tempoInicial = System.currentTimeMillis();
			//System.out.println("Lendo caracteres...");
			fila = arquivo.leArquivoCaracter(); //otimizado
			//System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			
			//System.out.println("Criando Fila de n�s...");
			r = compactar.criaFilaArvore(fila); //ok
			//System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			
			//System.out.println("Compactando..."); //ok
			compactar.compactarArquivoCaracter(r, arquivo.getNomeArquivo());
			//System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 2: 
			tempoInicial = System.currentTimeMillis();
			//System.out.println("Descompactando...");
			descompactar.descompactarArquivo(arquivo.getNomeArquivo());
			//System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 3: 
			tempoInicial = System.currentTimeMillis();
			System.out.println("Lendo palavras...");
			fila = arquivo.lerArquivoPalavra();
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			 
			/*System.out.println("DEPOIS:");
			for (ArvoreBinaria arvoreBinaria : fila) {
				System.out.println("Caracter: "+arvoreBinaria.getInfo()+" Frequ�ncia: "+arvoreBinaria.getFreq());
			}*/
			System.out.println("Criando �rvore...");
			r = compactar.criaFilaArvore(fila);
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			//r.inOrdem();
			//System.out.println();
			//arquivo.guardarArquivoArvore(r);
			//r = compactar.criarArvore(fila);
			System.out.println("Compactando...");
			compactar.compactarArquivoPalavra(r, arquivo.getNomeArquivo());
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 4: 
			descompactar.descompactarArquivo(arquivo.getNomeArquivo());
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida!");
		}
		System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
	}

}
