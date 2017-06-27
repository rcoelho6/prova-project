package prova.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import prova.project.model.Campanha;
@Repository
public class CampanhaDAO {
	
	private String CREATE = "insert into campanha values (?,?,?,?);";
	private String UPDATE = "update campanha"+
							"   set name = ?"+
							"     , idTeam = ?"+
							"     , duration = ?"+
							"where campanhaId = ?";
	private String DELETE = "delete from campanha where campanhaId = ?";
	private String RETRIEVE = "select x.campanhaId"+
	                          "     , x.name"+
			                  "     , x.idTeam"+
			                  "     , x.duration"+
			                  "  from campanha x";
	/* Because the new version of MySQL doens´t work well with sequences*/
	private String NEXT = "select max(campanhaId)+1 nextval from campanha";
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	
	@PostConstruct
	private void setJdbcTemplate() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private Integer getNextCampanhaId() {
		String sql = this.NEXT;
		return jdbcTemplate.query(sql,new NextvalMapper()).get(0);
	}
	
	public Integer create(Campanha campanha){
		String sql = this.CREATE;
		Integer newId = getNextCampanhaId();
		jdbcTemplate.update(sql, newId,campanha.getName(),campanha.getIdteam(),campanha.getDuration());
		return newId;
	}
	
	public void update(Campanha campanha){
		String sql = this.UPDATE;
		jdbcTemplate.update(sql,campanha.getName(),campanha.getIdteam(),campanha.getDuration(),campanha.getCampanhaId());
	}
	
	public Campanha retrieve(Integer campanhaId){
		String sql = this.RETRIEVE+" where campanhaId = ?";
		Campanha campanha = jdbcTemplate.query(sql, new Object[]{campanhaId}, new CampanhaMapper()).get(0);
		return campanha;
	}
	
	public List<Campanha> retrieveByTeam(String team){
		String sql = this.RETRIEVE+" where idTeam = ? and duration >= ?";
		List<Campanha> campanha = jdbcTemplate.query(sql,new Object[]{team,new Date()},new CampanhaMapper());
		return campanha;
	}
	
	public List<Campanha> retrieveByClient(Integer clientId){
		String sql = this.RETRIEVE+", cliente_campanha c where x.campanhaId = c.campanhaId and c.clientId = ?";
		List<Campanha> campanha = jdbcTemplate.query(sql,new Object[]{clientId},new CampanhaMapper());
		return campanha;		
	}
	
	public List<Campanha> retrieveSameDuration(Date duration){
		String sql = this.RETRIEVE+" where duration = ? and duration >= ?";
		List<Campanha> campanha = jdbcTemplate.query(sql,new Object[]{duration,new Date()},new CampanhaMapper());
		return campanha;
	}
	
	public List<Campanha> retrieveAll(){
		String sql = this.RETRIEVE+" where duration >= ?";
		List<Campanha> campanha = jdbcTemplate.query(sql,new Object[]{new Date()},new CampanhaMapper());
		return campanha;
	}
	
	public void delete(Campanha campanha){ 
		String sql = this.DELETE;
		jdbcTemplate.update(sql,campanha.getCampanhaId());
	}
	
	public class NextvalMapper implements RowMapper<Integer> {
		public Integer mapRow(ResultSet rs, int rownum) throws SQLException {
			return rs.getInt("nextval");
		}
	}
	
	public class CampanhaMapper implements RowMapper<Campanha> {
		public Campanha mapRow(ResultSet rs, int rownum) throws SQLException {
			Campanha campanha = new Campanha();
			campanha.setCampanhaId(rs.getInt("campanhaId"));
			campanha.setName(rs.getString("name"));
			campanha.setIdteam(rs.getString("idTeam"));
			campanha.setDuration(rs.getDate("duration"));
			return campanha;
		}
	}
}
