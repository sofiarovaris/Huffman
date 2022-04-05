import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

/* Programa: Huffman
 * Finalidade: Software que faz a compactação e descompactação de arquivos de texto 
 * utilizando o método de Huffman
 * Desenvolvedores: Gabriel Pereira e Sofia Rovaris
 * Data da criação: 03/03/2022 */

public class Huffman {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		
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
			//compactação por caractere
			tempoInicial = System.currentTimeMillis();
			
			fila = arquivo.leArquivoCaracter();
			r = compactar.criaFilaArvore(fila); 
			compactar.compactarArquivoCaracter(r, arquivo.getNomeArquivo());
			
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
		break;
		case 2: 
			//descompactação por caractere
			tempoInicial = System.currentTimeMillis();
			
			descompactar.descompactarArquivo(arquivo.getNomeArquivo());
			
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 3: 
			//compactação por palavra
			tempoInicial = System.currentTimeMillis();
			
			fila = arquivo.lerArquivoPalavra();
			r = compactar.criaFilaArvore(fila);
			compactar.compactarArquivoPalavra(r, arquivo.getNomeArquivo());
			
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		case 4: 
			//descompactação por palavra
			tempoInicial = System.currentTimeMillis();
			
			descompactar.descompactarArquivo(arquivo.getNomeArquivo());
			
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/60000));
			break;
		default:
			throw new IllegalArgumentException("Opção inválida!");
		}
	}

}
