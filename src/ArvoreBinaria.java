import java.io.Serializable;
import java.util.HashMap;


/* Finalidade: Classe que contém a estrutura da árvore binária e métodos relacionados a ela.
 * O atributo info é utilizado para armazenar o caractere ou a palavra correspondente ao nó da árvore,
 * e o atributo freq é utilizado para armazenar a frequência dos dados.*/

public class ArvoreBinaria implements Comparable<ArvoreBinaria>, Serializable{
	private static final long serialVersionUID = 1L;
	private String info;
	private int freq;
	private ArvoreBinaria esq;
	private ArvoreBinaria dir;
	
	/*Finalidade: construtor para inicializar um nó da árvore binária totalmente vazio
	 * Pré condição: nenhuma
	 * Pós condição: nó da árvore inicializado */
	public ArvoreBinaria() {
		this.info = null;
		this.freq = 0;
		this.dir = null;
		this.esq = null;
	}
	
	/*Finalidade: construtor para inicializar um nó da árvore binária sem filhos
	 * Pré condição: passar a palavra ou caractere e a frequência do nó
	 * Pós condição: nó da árvore inicializado sem filhos */
	public ArvoreBinaria(String info, int freq) {
		this.info = info;
		this.freq = freq;
		this.dir = null;
		this.esq = null;
	}
	
	/*Finalidade: construtor para inicializar um nó da árvore binária com filhos
	 * Pré condição: dois nós de árvore binária
	 * Pós condição: nó da árvore inicializado com filhos */
	public ArvoreBinaria(int freq, ArvoreBinaria esq, ArvoreBinaria dir) {
		this.info = null;
		this.freq = freq;
		this.dir = dir;
		this.esq = esq;
	}
	
	/*Finalidade: adiciona os nós na árvore
	 * Pré condição: passar a palavra ou caractere e a frequência do nó
	 * Pós condição: árvore com o novo nó adicionado */
	public void add(String info, int freq) {
		if(this.info == null && this.freq == 0) {
			this.info = info;
			this.freq = freq;
		}else {
			if(this.freq > freq) {
				if(this.esq == null) {
					this.esq = new ArvoreBinaria(info, freq);
				}else {
					this.esq.add(info, freq);
				}
			}else {
				if(this.dir == null) {
					this.dir = new ArvoreBinaria(info, freq);
				}else {
					this.dir.add(info, freq);
				}
			}
		}
	}
	
	/*Finalidade: imprime os nós da árvore
	 * Pré condição: nenhuma
	 * Pós condição: nenhuma */
	public void inOrdem() {
		if(this.esq != null) {
			this.esq.inOrdem();
		}
		System.out.print(this.info+" ");
		if(this.dir != null) {
			this.dir.inOrdem();
		}
	}

	// getters e setter dos atributos da classe
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	// ---------------------------------------

	/*Finalidade: compara as informações da ávore binária por frequência
	 * Pré condição: ter uma árvore binária
	 * Pós condição: nenhuma */
	@Override
	public int compareTo(ArvoreBinaria o) {
		// TODO Auto-generated method stub
		if(this.freq == o.freq) {
			return 0;
		}else if(this.freq < o.freq) {
			return -1;
		}
		return 1;
	}

	/*Finalidade: cria uma tabela hash com os códigos gerados a partir da árvore binária
	 * Pré condição: identificar através de um inteiro (1 ou !=1) como a tabela será gerada
	 * Pós condição: hash map com todos os códigos encontrados na árvore e seus respectivas informações */
	public void gerarTabelaHashCodigos(HashMap<String, String> tabelaHash, String codigo, int opcao) {
		if(this.esq != null) {
			this.esq.gerarTabelaHashCodigos(tabelaHash, codigo+"0", opcao);
		}
		if(this.info != null) {
			if(opcao==1) {
				tabelaHash.put(this.info, codigo);
			}else {
				tabelaHash.put(codigo, this.info);
			}
		}
		if(this.dir != null) {
			this.dir.gerarTabelaHashCodigos(tabelaHash, codigo+"1", opcao);
		}
	}
}
