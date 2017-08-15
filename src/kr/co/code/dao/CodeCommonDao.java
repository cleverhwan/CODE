package kr.co.code.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CodeCommonDao {
	
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClient;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> select(String sQueryID) throws DataAccessException {
		return (List<Map>)sqlMapClient.queryForList( sQueryID);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> select(String sQueryID, Map mParam) throws DataAccessException {
		return (List<Map>)sqlMapClient.queryForList(sQueryID, mParam);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public int selectCnt(String sQueryID, Map mParam) throws DataAccessException {
		return (Integer)sqlMapClient.queryForObject(sQueryID, mParam);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> select(String sQueryID, Object ... obj) throws DataAccessException {
		return (List<Map>)sqlMapClient.queryForList(sQueryID, obj);
	}

	@SuppressWarnings({ "rawtypes" })
	public Map selectRow(String sQueryID) throws DataAccessException {
		List<Map> lResult = select(sQueryID);
		return (lResult == null || lResult.isEmpty())?null:lResult.get(0);
	}

	@SuppressWarnings({ "rawtypes" })
	public Map selectRow(String sQueryID, Map mParam) throws DataAccessException {
		List<Map> lResult = select(sQueryID, mParam);
		return (lResult == null || lResult.isEmpty())?null:lResult.get(0);
	}

	@SuppressWarnings({ "rawtypes" })
	public Map selectRow(String sQueryID, Object ... obj) throws DataAccessException {
		List<Map> lResult = select(sQueryID, obj);
		return (lResult == null || lResult.isEmpty())?null:lResult.get(0);
	}

	public Object selectItem(String sQueryID) throws DataAccessException {
		return sqlMapClient.queryForObject(sQueryID);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object selectItem(String sQueryID, Map mParam) throws DataAccessException {
		return sqlMapClient.queryForObject(sQueryID, mParam);
	}

	public Object selectItem(String sQueryID, Object o) throws DataAccessException {
		return sqlMapClient.queryForObject(sQueryID, o);
	}

	public Object selectItem(String sQueryID, String str) throws DataAccessException {
		return sqlMapClient.queryForObject(sQueryID, str);
	}

	@SuppressWarnings("rawtypes")
	public Object insert(String sQueryID, Map mParam) throws DataAccessException {
		return sqlMapClient.insert(sQueryID, mParam);
	}

	public Object insert(String sQueryID, Object o) throws DataAccessException {
		return sqlMapClient.insert(sQueryID, o);
	}

	@SuppressWarnings("rawtypes")
	public int update(String sQueryID, Map mParam) throws DataAccessException {
		return sqlMapClient.update(sQueryID, mParam);
	}

	public int update(String sQueryID, Object o) throws DataAccessException {
		return sqlMapClient.update(sQueryID, o);
	}

	@SuppressWarnings("rawtypes")
	public int delete(String sQueryID, Map mParam) throws DataAccessException {
		return sqlMapClient.delete(sQueryID, mParam);
	}
	@SuppressWarnings("rawtypes")
	public int delete(String sQueryID, Object o) throws DataAccessException {
		return sqlMapClient.delete(sQueryID, o);
	}
}