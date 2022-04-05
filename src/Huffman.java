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
			System.out.println("Lendo palavras...");
			tempoInicial = System.currentTimeMillis();
			fila = arquivo.leArquivoCaracter(); 
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			System.out.println("Criando árvore...");
			tempoInicial = System.currentTimeMillis();
			r = compactar.criaFilaArvore(fila); 
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			System.out.println("Compactando...");
			tempoInicial = System.currentTimeMillis();
			compactar.compactarArquivoCaracter(r, arquivo.getNomeArquivo());
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 2: 
			System.out.println("Descompactando por caracter...");
			tempoInicial = System.currentTimeMillis();
			descompactar.descompactarArquivo(arquivo.getNomeArquivo());
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 3: 
			System.out.println("Lendo palavras...");
			tempoInicial = System.currentTimeMillis();
			fila = arquivo.lerArquivoPalavra();
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			System.out.println("Criando árvore...");
			tempoInicial = System.currentTimeMillis();
			r = compactar.criaFilaArvore(fila);
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			System.out.println("Compactando...");
			tempoInicial = System.currentTimeMillis();
			compactar.compactarArquivoPalavra(r, arquivo.getNomeArquivo());
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 4: 
			System.out.println("Descompactando por palavra...");
			tempoInicial = System.currentTimeMillis();
			descompactar.descompactarArquivo(arquivo.getNomeArquivo());
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		default:
			throw new IllegalArgumentException("Opção inválida!");
		}
	}

}
