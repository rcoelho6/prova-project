package prova.project.model;

import java.util.Date;

public class Campanha {
	Integer campanhaId;
	String name;
	String idteam;
	Date duration;
	Integer clientId;
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getCampanhaId() {
		return campanhaId;
	}
	public void setCampanhaId(Integer campanhaId) {
		this.campanhaId = campanhaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdteam() {
		return idteam;
	}
	public void setIdteam(String idteam) {
		this.idteam = idteam;
	}
	public Date getDuration() {
		return duration;
	}
	public void setDuration(Date duration) {
		this.duration = duration;
	}
}
