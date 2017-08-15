/**
 * 공통코드 관련 service
 */
package kr.co.code.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.code.dao.CodeCommonDao;

@Service
public class CodeCodeService {

	protected Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private CodeCommonDao commonDao;
	
	/**
	 * code select list data
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getCommonCodeList(Map<String,Object> param){
		List<Map> map = new ArrayList<Map>();
		map = commonDao.select("code.getCommonCodeList", param);
		return map;
	}
	
	/**
	 * category top select list data
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getJobGroupTopList(Map<String,Object> param){
		List<Map> map = new ArrayList<Map>();
		map = commonDao.select("code.getJobGroupTopList", param);
		return map;
	}
	
	/**
	 * category top select list data
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getJobGroupMiddleList(Map<String,Object> param){
		List<Map> map = new ArrayList<Map>();
		map = commonDao.select("code.getJobGroupMiddleList", param);
		return map;
	}
	
	/**
	 * category top select list data
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getJobGroupBottomList(Map<String,Object> param){
		List<Map> map = new ArrayList<Map>();
		map = commonDao.select("code.getJobGroupBottomList", param);
		return map;
	}
	
	/**
	 * 사용자 목록 가져오기.
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getMemberSelectList(Map<String,Object> param){
		List<Map> map = new ArrayList<Map>();
		map = commonDao.select("code.getMemberSelectList", param);
		return map;
	}
}