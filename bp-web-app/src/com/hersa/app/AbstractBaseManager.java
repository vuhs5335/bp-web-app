package com.hersa.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.hersa.app.dao.apirequest.ApiRequestDAO;
import com.hersa.app.dao.apirequest.ApiRequestDAOFactory;
import com.hersa.app.dao.bpreading.BpReadingDAO;
import com.hersa.app.dao.bpreading.BpReadingDAOFactory;
import com.hersa.app.dao.filestore.FileStoreDAO;
import com.hersa.app.dao.filestore.FileStoreDAOFactory;



public class AbstractBaseManager {

	public void markCreated(BOM bom, String userName) {
		markCreated(bom, userName, new Date());
	}
	
	public void markCreated(BOM bom , String userName, Date date) {
		bom.setCreatedBy(userName);
		bom.setCreatedOn(date);
	    markUpdated(bom, userName, date);
	}
	
	public void markUpdated(BOM bom , String userName) {
		markUpdated(bom, userName, new Date());
	}
	
	public void markUpdated(BOM bom , String userName, Date date) {
		bom.setModifiedBy(userName);
		bom.setModifiedOn(date);
	}
	
	public void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ConnectionProvider getDefautlConnectionProvider() {
		ConnectionProvider factory = new DefaultConnectionProvider(JNDI.BPMONITOR);
		return factory;
	}
	
	public BpReadingDAO getBloodPressureDAO() {
		BpReadingDAO dao = BpReadingDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}

	public BpReadingDAO getBloodPressureDAO(Connection connection) {
		BpReadingDAO dao = BpReadingDAOFactory.getDAO(connection);
		return dao;
	}
	 
	public ApiRequestDAO getApiRequestDAO() {
		ApiRequestDAO dao = ApiRequestDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}

	public ApiRequestDAO getApiRequestDAO(Connection connection) {
		ApiRequestDAO dao = ApiRequestDAOFactory.getDAO(connection);
		return dao;
	}

	public FileStoreDAO getFileStoreDAO() {
		FileStoreDAO dao = FileStoreDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}
	
	public FileStoreDAO getFileStoreDAO(Connection connection) {
		FileStoreDAO dao = FileStoreDAOFactory.getDAO(connection);
		return dao;
	}
}
