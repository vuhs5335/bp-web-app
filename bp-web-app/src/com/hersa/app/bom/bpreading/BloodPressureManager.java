package com.hersa.app.bom.bpreading;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.hersa.app.AbstractBaseManager;
import com.hersa.app.ApplicationException;
import com.hersa.app.dao.bpreading.BpReadingCreateException;
import com.hersa.app.dao.bpreading.BpReadingDB;
import com.hersa.app.dao.bpreading.BpReadingDTO;
import com.hersa.app.dao.bpreading.BpReadingFinderException;
import com.hersa.app.dao.bpreading.BpReadingUpdateException;

public class BloodPressureManager extends AbstractBaseManager {

	public void create(BloodPressure bp) throws ApplicationException{
		try {
			this.getBloodPressureDAO().createBpReading(bp.getDto());
		} catch (BpReadingCreateException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	public void create(BloodPressure bp, Connection connection) throws ApplicationException{
		try {
			this.getBloodPressureDAO(connection).createBpReading(bp.getDto());
		} catch (BpReadingCreateException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	public void update(BloodPressure bp) throws ApplicationException{
		try {
			Connection con = this.getDefautlConnectionProvider().openConnection();
			this.update(bp, con);
		} catch (Exception e) {
			throw new ApplicationException("An error occrurred.");
		}
	}
	
	public void deleteById(long id) throws ApplicationException{
		try {
			this.getBloodPressureDAO().deleteBpReadingById(id);
		} catch (Exception e) {
			throw new ApplicationException("An error occrurred.");
		}
	}
	
	public void update(BloodPressure bp, Connection connection) throws ApplicationException{
		try {
			this.getBloodPressureDAO(connection).updateBpReading(bp.getDto());
		} catch (BpReadingUpdateException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} catch (BpReadingFinderException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	public List<BloodPressure> retrieveAllBloodPressure(){
		BpReadingDB helper = new BpReadingDB();   
		String order = helper.add(BpReadingDB.ORDERBY_DATE, 0).toString();
		return convert(this.getBloodPressureDAO().listAllBpReading(order));
	}
	
	public BloodPressure retrieveBloodPressureById(long id) throws ApplicationException{
		
		BloodPressure bom = null;
		
		try {
			BpReadingDTO[] dtos = this.getBloodPressureDAO().listBpReadingById(id);
			bom = convert(dtos).get(0);
		} catch (Exception e) {
			throw new ApplicationException("An error occured while reading Bp info.");
		}
		
		return bom;
	}
	
	private List<BloodPressure> convert(BpReadingDTO[] dtos){
		List<BloodPressure> list = new ArrayList<BloodPressure>();
		
		if (dtos == null) {
			return list;
		}
		
		for (BpReadingDTO dto : dtos) {
			list.add(new BloodPressure(dto));
		}
		
		return list;
	}
}
