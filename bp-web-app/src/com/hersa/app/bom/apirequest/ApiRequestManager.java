package com.hersa.app.bom.apirequest;

import java.util.ArrayList;
import java.util.List;

import com.hersa.app.AbstractBaseManager;
import com.hersa.app.dao.apirequest.ApiRequestDB;
import com.hersa.app.dao.apirequest.ApiRequestDTO;

public class ApiRequestManager extends AbstractBaseManager{

	public List<ApiRequest> retrieveAllApiRequests(){
		ApiRequestDB apiDb = new ApiRequestDB();
		String order = apiDb.add(ApiRequestDB.ORDERBY_REQDATE, 0).toString();
		return convert(this.getApiRequestDAO().listAllApiRequest(order));
	}
	
	private List<ApiRequest> convert(ApiRequestDTO[] dtos){
		List<ApiRequest> list = new ArrayList<ApiRequest>();
		
		if (dtos == null) {
			return list;
		}
		
		for (ApiRequestDTO dto : dtos) {
			list.add(new ApiRequest(dto));
		}
		
		return list;
	}
}
