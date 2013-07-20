package voyager.nove.model.viaggio.basic;

public class Info {
	
	private String info;
	
	public Info(){
		this.info = "No info";
	}
	
	public Info(String info){
		this.info = info;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public void updateInfo(String info){
		String s;
		s = this.info + " " + info;
		this.info = s;
	}
	
	public String toString(){
		return info;
	}

}
