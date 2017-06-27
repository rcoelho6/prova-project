package prova.project.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "CampanhaService", targetNamespace = "http://service.project.prova/")
public interface CampanhaService {

	@WebMethod(operationName = "create", action = "urn:Create")
	public String create(@WebParam(name = "json") String json);

	@WebMethod(operationName = "update", action = "urn:Update")
	public String update(@WebParam(name = "json") String json);

	@WebMethod(operationName = "delete", action = "urn:Delete")
	public String delete(@WebParam(name = "json") String json);

	@WebMethod(operationName = "retreiveByCampanha", action = "urn:RetreiveByCampanha")
	public String retreiveByCampanha(@WebParam(name = "json") String json);

	@WebMethod(operationName = "retreiveByClient", action = "urn:RetreiveByClient")
	public String retreiveByClient(@WebParam(name = "json") String json);

	@WebMethod(operationName = "retreiveByTeam", action = "urn:RetreiveByTeam")
	public String retreiveByTeam(@WebParam(name = "json") String json);

}