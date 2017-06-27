package prova.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import prova.project.dao.CampanhaDAO;
import prova.project.model.Campanha;
import prova.project.parse.PJsonWriter;
import prova.project.parse.PJsonReader;
import prova.project.validate.CampanhaValidator;

/**
 * 
 * Efective service class, the json is read or write with auxliar class inside this project
 * 
 * @author rcoelho
 *
 */
@WebService(targetNamespace = "http://service.project.prova/", portName = "CampanhaServiceImplPort", serviceName = "CampanhaServiceImplService", endpointInterface = "prova.project.service.CampanhaService")
public class CampanhaServiceImpl implements CampanhaService {

	@Autowired
	CampanhaDAO campanhaDAO;

	@Autowired
	private CampanhaValidator validator;
	
	public String create(@WebParam(name = "json") String json) {
		System.out.println("Resultado: "+json);
		Campanha campanha = (new PJsonReader(json)).getParsedCampanha();
		Integer campanhaId = campanhaDAO.create(campanha);
		campanha.setCampanhaId(campanhaId);
		this.validator.changeDurations(campanha);
		return (new PJsonWriter()).getParsedCampanha(campanha,"OK");
	}
	
	public String update(@WebParam(name = "json") String json) {
		System.out.println("Resultado: "+json);
		Campanha campanha = (new PJsonReader(json)).getParsedCampanha();
		if (campanha.getCampanhaId()==null) {
			return (new PJsonWriter()).getParsedCampanha(campanha,"Missing Campanha ID");
		}else{
			/* Populate empty fields */
			Campanha aux = campanhaDAO.retrieve(campanha.getCampanhaId());
			if (campanha.getDuration()==null) {
				campanha.setDuration(aux.getDuration());
			}
			if (campanha.getIdteam()==null) {
				campanha.setIdteam(aux.getIdteam());
			}
			if (campanha.getName()==null) {
				campanha.setName(aux.getName());
			}
		}
		campanhaDAO.update(campanha);
		return (new PJsonWriter()).getParsedCampanha(campanha,"OK");
	}
	
	public String delete(@WebParam(name = "json") String json) {
		System.out.println("Resultado: "+json);
		Campanha campanha = (new PJsonReader(json)).getParsedCampanha();
		campanhaDAO.delete(campanha);
		return (new PJsonWriter()).getParsedCampanha(campanha,"OK");
	}
	
	public String retreiveByCampanha(@WebParam(name = "json") String json) {
		System.out.println("Resultado: "+json);
		Campanha campanha = (new PJsonReader(json)).getParsedCampanha();
		campanha = campanhaDAO.retrieve(campanha.getCampanhaId());
		return (new PJsonWriter()).getParsedCampanha(campanha,"OK");
	}

	public String retreiveByClient(@WebParam(name = "json") String json) {
		System.out.println("Resultado: "+json);
		Campanha campanha = (new PJsonReader(json)).getParsedCampanha();
		
		List<Campanha> campanhaList = campanhaDAO.retrieveByClient(campanha.getClientId());
		List<String> resultList = new ArrayList<String>();
		
		for (Campanha result:campanhaList){
			resultList.add((new PJsonWriter()).getParsedCampanha(result,"OK",campanha.getClientId()));
		}
		
		return (new PJsonWriter()).getParserCampanhaList(resultList);
	}

	public String retreiveByTeam(@WebParam(name = "json") String json) {
		System.out.println("Resultado: "+json);
		Campanha campanha = (new PJsonReader(json)).getParsedCampanha();
		
		List<Campanha> campanhaList = campanhaDAO.retrieveByTeam(campanha.getIdteam());
		List<String> resultList = new ArrayList<String>();
		
		for (Campanha result:campanhaList){
			resultList.add((new PJsonWriter()).getParsedCampanha(result,"OK"));
		}
		
		return (new PJsonWriter()).getParserCampanhaList(resultList);
	}
}
