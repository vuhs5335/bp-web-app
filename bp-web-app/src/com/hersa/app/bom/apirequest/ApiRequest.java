package com.hersa.app.bom.apirequest;

import java.util.Date;

import com.hersa.app.dao.apirequest.ApiRequestDTO;

public class ApiRequest {

	private ApiRequestDTO dto;
	
	public ApiRequest() {
		this.dto = new ApiRequestDTO();
	}
	
	public ApiRequest(ApiRequestDTO dto) {
		this.dto = dto;
	}

	public long getId() {
		return dto.getId();
	}

	public void setId(long value) {
		dto.setId(value);
	}

	public String getIpAddress() {
		return dto.getIpAddress();
	}

	public void setIpAddress(String value) {
		dto.setIpAddress(value);
	}

	public Date getReqDate() {
		return dto.getReqDate();
	}

	public void setReqDate(Date value) {
		dto.setReqDate(value);
	}

	public String getResource() {
		return dto.getResource();
	}

	public void setResource(String value) {
		dto.setResource(value);
	}

	public String getResponse() {
		return dto.getResponse();
	}

	public void setResponse(String value) {
		dto.setResponse(value);
	}

	public String getInfo() {
		return dto.getInfo();
	}
}
