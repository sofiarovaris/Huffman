
public class ArvoreBinaria implements Comparable<ArvoreBinaria>{
	private String info;
	private int freq;
	private ArvoreBinaria esq;
	private ArvoreBinaria dir;
	
	public ArvoreBinaria() {
		this.info = null;
		this.freq = 0;
		this.dir = null;
		this.esq = null;
	}
	
	public ArvoreBinaria(String info, int freq) {
		this.info = info;
		this.freq = freq;
		this.dir = null;
		this.esq = null;
	}
	
	public ArvoreBinaria(int freq, ArvoreBinaria esq, ArvoreBinaria dir) {
		this.info = null;
		this.freq = freq;
		this.dir = dir;
		this.esq = esq;
	}
	
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
	
	public void inOrdem() {
		if(this.esq != null) {
			this.esq.inOrdem();
		}
		System.out.print(this.info+" ");
		if(this.dir != null) {
			this.dir.inOrdem();
		}
	}

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
}
