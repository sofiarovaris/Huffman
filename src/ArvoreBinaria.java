
public class ArvoreBinaria {
	private ElementoFila elemento;
	private ArvoreBinaria esq;
	private ArvoreBinaria dir;
	
	public ArvoreBinaria() {
		this.elemento = null;
		this.dir = null;
		this.esq = null;
	}
	
	private ArvoreBinaria(ElementoFila elemento) {
		this.elemento = elemento;
		this.dir = null;
		this.esq = null;
	}
	
	private ArvoreBinaria(ElementoFila elemento, ArvoreBinaria esq, ArvoreBinaria dir) {
		this.elemento = elemento;
		this.dir = dir;
		this.esq = esq;
	}
	
	public void add(ElementoFila elemento) {
		if(this.elemento == null) {
			this.elemento = elemento;
		}else {
			if(this.elemento.compareTo(elemento) > 0) {
				if(this.esq == null) {
					this.esq = new ArvoreBinaria(elemento);
				}else {
					this.esq.add(elemento);
				}
			}else {
				if(this.dir == null) {
					this.dir = new ArvoreBinaria(elemento);
				}else {
					this.dir.add(elemento);
				}
			}
		}
	}
	
	public void inOrdem() {
		if(this.esq != null) {
			this.esq.inOrdem();
		}
		System.out.print(this.elemento+" ");
		if(this.dir != null) {
			this.dir.inOrdem();
		}
	}
}
