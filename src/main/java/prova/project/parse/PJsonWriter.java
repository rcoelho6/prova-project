package prova.project.parse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import prova.project.model.Campanha;

/**
 * Write the json to comunicate between endpoints. It use the default campanha model there contains in this project
 * 
 * 
 * 
 * @author rcoelho
 *
 */
public class PJsonWriter {
	JsonObjectBuilder objectBuilder;
	
	public PJsonWriter() {
		this.objectBuilder = Json.createObjectBuilder();
	}
	
	private boolean isBuilderSet() {
		return (this.objectBuilder!=null);
	}
	
	public void setInt(String key, Integer value) {
		if (isBuilderSet()) {
			this.objectBuilder.add(key,value);
		}else{
			System.out.println("Json paser not created");
		}
	}
	
	public void setString(String key, String value) {
		if (isBuilderSet()) {
			this.objectBuilder.add(key,value);
		}else{
			System.out.println("Json paser not created");
		}
	}
	
	public void setDate(String key, Date value) {
		if (isBuilderSet()) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.objectBuilder.add(key,format.format(value));
		}else{
			System.out.println("Json paser not created");
		}
	}
	
	public String getJson() {
		if (isBuilderSet()) {
			return this.objectBuilder.build().toString();
		}else{
			System.out.println("Json paser not created");
		}
		return null;
	}

	public String getParsedCampanha(Campanha campanha){
		return this.getParsedCampanha(campanha,null);
	}
	
	public String getParsedCampanha(Campanha campanha,String returnMsg){
		return this.getParsedCampanha(campanha,returnMsg,0);
	}
	
	/**
	 * 
	 * The main method that generate the json, to call the method is not necessary to send returnMsg or clientId
	 * 
	 * @param campanha
	 * @param returnMsg
	 * @param clientId
	 * @return
	 */
	public String getParsedCampanha(Campanha campanha,String returnMsg, Integer clientId){
		this.setInt("campanhaId",campanha.getCampanhaId());
		this.setDate("duration",campanha.getDuration());
		this.setString("idteam",campanha.getIdteam());
		this.setString("name",campanha.getName());
		this.setInt("clientId",clientId);
		this.setString("returnMsg",returnMsg);
		return this.getJson();
	}
	
	/**
	 * 
	 * Write a json list with more then one campanha result
	 * 
	 * @param campanhas
	 * @return
	 */
	public String getParserCampanhaList(List<String> campanhas){
		if (campanhas.size()>0) {
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(campanhas);
			objectBuilder.add("Campanhas",arrayBuilder.build());
			return objectBuilder.build().toString();
			
		}else{
			return null;
		}
	}
}