import java.io.Serializable;
import java.util.HashMap;


/* Finalidade: Classe que cont�m a estrutura da �rvore bin�ria e m�todos relacionados a ela.
 * O atributo info � utilizado para armazenar o caractere ou a palavra correspondente ao n� da �rvore,
 * e o atributo freq � utilizado para armazenar a frequ�ncia dos dados.*/

public class ArvoreBinaria implements Comparable<ArvoreBinaria>, Serializable{
	private static final long serialVersionUID = 1L;
	private String info;
	private int freq;
	private ArvoreBinaria esq;
	private ArvoreBinaria dir;
	
	/*Finalidade: construtor para inicializar um n� da �rvore bin�ria totalmente vazio
	 * Pr� condi��o: nenhuma
	 * P�s condi��o: n� da �rvore inicializado */
	public ArvoreBinaria() {
		this.info = null;
		this.freq = 0;
		this.dir = null;
		this.esq = null;
	}
	
	/*Finalidade: construtor para inicializar um n� da �rvore bin�ria sem filhos
	 * Pr� condi��o: passar a palavra ou caractere e a frequ�ncia do n�
	 * P�s condi��o: n� da �rvore inicializado sem filhos */
	public ArvoreBinaria(String info, int freq) {
		this.info = info;
		this.freq = freq;
		this.dir = null;
		this.esq = null;
	}
	
	/*Finalidade: construtor para inicializar um n� da �rvore bin�ria com filhos
	 * Pr� condi��o: dois n�s de �rvore bin�ria
	 * P�s condi��o: n� da �rvore inicializado com filhos */
	public ArvoreBinaria(int freq, ArvoreBinaria esq, ArvoreBinaria dir) {
		this.info = null;
		this.freq = freq;
		this.dir = dir;
		this.esq = esq;
	}
	
	/*Finalidade: adiciona os n�s na �rvore
	 * Pr� condi��o: passar a palavra ou caractere e a frequ�ncia do n�
	 * P�s condi��o: �rvore com o novo n� adicionado */
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
	
	/*Finalidade: imprime os n�s da �rvore
	 * Pr� condi��o: nenhuma
	 * P�s condi��o: nenhuma */
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

	/*Finalidade: compara as informa��es da �vore bin�ria por frequ�ncia
	 * Pr� condi��o: ter uma �rvore bin�ria
	 * P�s condi��o: nenhuma */
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

	/*Finalidade: cria uma tabela hash com os c�digos gerados a partir da �rvore bin�ria
	 * Pr� condi��o: identificar atrav�s de um inteiro (1 ou !=1) como a tabela ser� gerada
	 * P�s condi��o: hash map com todos os c�digos encontrados na �rvore e seus respectivas informa��es */
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
