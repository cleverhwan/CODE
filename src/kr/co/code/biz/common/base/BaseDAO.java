/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.code.common.util.DataMap;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;



public class BaseDAO  {

	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate smct;

	public Object insert(String queryId, Object parameterObject) throws DataAccessException {
        return smct.insert(queryId, parameterObject);
    }

	public int update(String queryId, Object parameterObject)  throws DataAccessException {
        return smct.update(queryId, parameterObject);
    }

	public int delete(String queryId, Object parameterObject)  throws DataAccessException {
        return smct.delete(queryId, parameterObject);
    }

	public Object selectByPk(String queryId, Object parameterObject)  throws DataAccessException {
        return smct.queryForObject(queryId,
            parameterObject);
    }

	public Map selectForMap(String queryId, Object parameterObject, String keyProperty)  throws DataAccessException {
		return smct.queryForMap(queryId, parameterObject, keyProperty);
	}

	public Map selectForMap(String queryId, Object parameterObject, String keyProperty, String valueProperty)  throws DataAccessException {
		return smct.queryForMap(queryId, parameterObject, keyProperty, valueProperty);
	}

	@SuppressWarnings("unchecked")
    public List list(String queryId, Object parameterObject)  throws DataAccessException {
        return smct.queryForList(queryId, parameterObject);
    }

	@SuppressWarnings("unchecked")
    public List listByPaging(String queryId, Object parameterObject,
            int pageIndex, int pageSize)  throws DataAccessException  {
        int skipResults = pageIndex * pageSize;
        //int maxResults = (pageIndex * pageSize) + pageSize;
        return smct.queryForList(queryId, parameterObject,
            skipResults, pageSize);
    }
	
	public List<DataMap> dataMapList(String queryId, Object parameterObject) {
		return (List<DataMap>)smct.queryForList(queryId, parameterObject);
	}

	
	
	/*
	 * transaction autocommit fail 시 사용
	public void executeBatchAll(final List<BatchQueryVO> statementList)  throws DataAccessException {
		SqlMapClient smc = batchSmct.getSqlMapClient();

		try {
			smc.startTransaction();
			smc.getCurrentConnection().setAutoCommit(false);

			smc.startBatch();

			for(BatchQueryVO q : statementList) {
				if(q.equals(StatementType.INSERT)) smc.insert(q.getQueryId(), q.getParam());
				else if(q.equals(StatementType.UPDATE)) smc.update(q.getQueryId(), q.getParam());
				else if(q.equals(StatementType.DELETE)) smc.delete(q.getQueryId(), q.getParam());
			}

			smc.executeBatch();
			smc.commitTransaction();
			smc.getCurrentConnection().commit();
		} catch (SQLException e) {
			try {
				smc.getCurrentConnection().rollback();
			} catch (SQLException rex) {}
			throw new DataAccessException("Batch Execute Error", e) {};
		} finally {
			try {
				smc.endTransaction();
			} catch(SQLException e) {}
		}
	}
	*/
}
