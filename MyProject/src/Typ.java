import java.sql.*;

public class Typ {
	
	//@Column, ...
	private int id;
	private String name;
	private String modellbez;
	private String entw_baur;
	private String antr_arch;
	private String marke;
	private boolean storniert;
	
	public Typ(int id, String name, String modellbez, String entw_baur, String antr_arch, String marke, boolean storniert) {
		this.id = id;
		this.name = name;
		this.modellbez = modellbez;  
		this.entw_baur = entw_baur;
		this.antr_arch = antr_arch;
		this.marke = marke;
		this.storniert = storniert;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}
	
	public String getModellbez() {
		return modellbez;
	}

	public void setModellbez(String modellbez) {
		this.modellbez = modellbez;
	}
	
	public String getEntw_baur() {
		return entw_baur;
	}

	public void setEntw_baur(String entw_baur) {
		this.entw_baur = entw_baur;
	}
	
	public String getAntr_arch() {
		return antr_arch;
	}

	public void setAntr_arch(String antr_arch) {
		this.antr_arch = antr_arch;
	}
	
	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}
	
	public boolean getStorniert() {
		return storniert;
	}

	public void setStorniert(boolean storniert) {
		this.storniert = storniert;
	}			
	
	@Override
	public String toString() {
		return String.format("typ [id=%s, name=%s, modellbez=%s, entw_baur=%s, antr_arch=%s, marke=%s, storniert=%s]",
						id, name, modellbez, entw_baur, antr_arch, marke, storniert);
	}

}
