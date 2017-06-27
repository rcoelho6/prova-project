package prova.project.validate;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prova.project.dao.CampanhaDAO;
import prova.project.model.Campanha;

@Service
public class CampanhaValidator {
	
	@Autowired
	CampanhaDAO campanhaDAO;
	
	public void changeDurations(Campanha campanha){
		if(campanha!=null){
			Date baseDate = campanha.getDuration();
			List<Campanha> list = campanhaDAO.retrieveSameDuration(baseDate);
			for (Campanha result:list) {
				baseDate = new Date(baseDate.getTime() + (1000*60*60*24));
				result.setDuration(baseDate);
				campanhaDAO.update(result);
			}
		}
	}
	
	public boolean isExpired(Campanha campanha){
		Date now = new Date();
		if (campanha.getDuration().before(now)){
			return true;
		}
		return false;
	}
}
