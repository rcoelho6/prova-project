package prova.project.parse;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import prova.project.model.Campanha;

/**
 * 
 * Read the json sent between endpoints. It use the default campanha model there contains in this project
 * 
 * @author rcoelho
 *
 */
public class PJsonReader {
	
	private JsonObject personObject;
	
	
	public PJsonReader(String json) {
		if (json!=null&&!"".equals(json)) {
			JsonReader reader = Json.createReader(new StringReader(json));
			this.personObject = reader.readObject();
		}else{
			System.out.println("Json paser not created");
		}
	}
	
	private boolean isParserSet() {
		return (this.personObject!=null);
	}
	
	public Integer getInt(String key) {
		if (isParserSet()) {
			if(personObject.containsKey(key)) {
				return personObject.getInt(key);
			}else{
				return null;
			}
		}else{
			System.out.println("Json paser not created");
		}
		return null;
	}
	public String getString(String key) {
		if (isParserSet()) {
			if(personObject.containsKey(key)) {
				return personObject.getString(key);
			}else{
				return null;
			}
		}else{
			System.out.println("Json paser not created");
		}
		return null;
	}
	public Date getDate(String key) {
		if (isParserSet()) {
			try {
				if(personObject.containsKey(key)) {
					String sdate = personObject.getString(key);
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					Date date = format.parse(sdate);
					return date;
				}else{
					return null;
				}
			} catch (ParseException e) {
				System.out.println("Json date in wrong format");
				e.printStackTrace();
			}
		}else{
			System.out.println("Json paser not created");
		}
		return null;
	}

	/**
	 * Since you input a json string on the constructor of this class, it will return a model populate with json
	 * 
	 * @return
	 */
	public Campanha getParsedCampanha(){
		Campanha campanha = new Campanha();
		campanha.setCampanhaId(this.getInt("campanhaId"));
		campanha.setDuration(this.getDate("duration"));
		campanha.setIdteam(this.getString("idteam"));
		campanha.setName(this.getString("name"));
		campanha.setClientId(this.getInt("clientId"));
		return campanha;
	}
}

