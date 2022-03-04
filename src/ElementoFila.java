
public class ElementoFila implements Comparable<ElementoFila>{
	private String info;
	private int freq;
	
	public ElementoFila(String info, int freq) {
		this.info = info;
		this.freq = freq;
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
	public int compareTo(ElementoFila o) {
		// TODO Auto-generated method stub
		if(this.freq == o.freq) {
			return 0;
		}else if(this.freq < o.freq) {
			return -1;
		}
		return 1;
	}
	
	
}
