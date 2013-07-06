package ispw.entity;


public class Session {
	private String username;
	private String ruolo;
	private static Session istance = null;
	private Session(){
		
	}
	public static Session getIstance(){
		if (istance == null)
			istance = new Session();
		return istance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

}
