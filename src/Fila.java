import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Fila {
	private Queue<ElementoFila> fila;
	
	public Fila() {
		this.fila = new LinkedList<>();
	}

	public void adicionaElemento(ElementoFila e) {
		this.fila.add(e);
	}
	
	public Boolean vazia() {
		return this.fila.isEmpty();
	}

	public Queue<ElementoFila> getFila() {
		return fila;
	}

	public void setFila(Queue<ElementoFila> fila) {
		this.fila = fila;
	}

	public boolean elementoNaFila(String elemento) {
		for (ElementoFila elementoFila : fila) {
			if(elementoFila.getInfo().equals(elemento)) {
				elementoFila.setFreq(elementoFila.getFreq()+1);
				return true;
			}
		}
		return false;
	}
	
	public void imprimeFila() {
		for (ElementoFila elementoFila : fila) {
			System.out.println("Caractere: "+elementoFila.getInfo()+" Frequencia: "+elementoFila.getFreq());
		}
	}
	
	public void organizarFila() {
		Collections.sort((List<ElementoFila>) this.fila);
	}
	
}
