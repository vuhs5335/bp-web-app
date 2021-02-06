package com.hersa.app.bom.filestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hersa.app.dao.filestore.FileStoreDTO;

public class FileStore {

	private FileStoreDTO dto;
	
	public static final String TYPE_VIDEO = "VIDEO";
	public static final String TYPE_TEXT  = "TEXT";
	
	public FileStore() {
		this.dto = new FileStoreDTO();
	}
	
	public FileStore(FileStoreDTO dto) {
		this.dto = dto;
	}
	
	public String genURI() {
		String uri = "";
	
		SimpleDateFormat yearFormatter = new SimpleDateFormat("YYYY");
		SimpleDateFormat monthFormatter = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormatter = new SimpleDateFormat("dd");
		
		Date today = this.getDateCreated();
		
		String year  = yearFormatter.format(today);
		String month = monthFormatter.format(today);;
		String day   = dayFormatter.format(today);
		
		uri =  year + "/" + month + "/" + day + "/";
		 
		return uri;
	}
	
	public String genFileName() {
		
		String fileName  = "";
		
		Date today = this.getDateCreated();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYYMMdd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmssSSS");
		
		fileName = this.getCategory() + "_"+ this.getType() + "-" + dateFormatter.format(today) + "_" + timeFormatter.format(today);
		
		return fileName;
	}
	
	public FileStoreDTO getDto() {
		return dto;
	}

	public void setDto(FileStoreDTO dto) {
		this.dto = dto;
	}

	/* == dtos ==*/
	
	public String getCaption() {
		return dto.getCaption();
	}

	public void setCaption(String value) {
		dto.setCaption(value);
	}

	public String getCategory() {
		return dto.getCategory();
	}

	public void setCategory(String value) {
		dto.setCategory(value);
	}

	public String getCreatedBy() {
		return dto.getCreatedBy();
	}

	public void setCreatedBy(String value) {
		dto.setCreatedBy(value);
	}

	public Date getDateCreated() {
		return dto.getDateCreated();
	}

	public void setDateCreated(Date value) {
		dto.setDateCreated(value);
	}

	public Date getDateModified() {
		return dto.getDateModified();
	}

	public void setDateModified(Date value) {
		dto.setDateModified(value);
	}

	public String getDescription() {
		return dto.getDescription();
	}

	public void setDescription(String value) {
		dto.setDescription(value);
	}

	public int getEnabled() {
		return dto.getEnabled();
	}

	public void setEnabled(int value) {
		dto.setEnabled(value);
	}

	public String getFileName() {
		return dto.getFileName();
	}

	public void setFileName(String value) {
		dto.setFileName(value);
	}

	public int getFileSize() {
		return dto.getFileSize();
	}

	public void setFileSize(int value) {
		dto.setFileSize(value);
	}

	public long getId() {
		return dto.getId();
	}

	public void setId(long value) {
		dto.setId(value);
	}

	public String getModifiedBy() {
		return dto.getModifiedBy();
	}

	public void setModifiedBy(String value) {
		dto.setModifiedBy(value);
	}

	public String getType() {
		return dto.getType();
	}

	public void setType(String value) {
		dto.setType(value);
	}

	public String getUri() {
		return dto.getUri();
	}

	public void setUri(String value) {
		dto.setUri(value);
	}

	public String getInfo() {
		return dto.getInfo();
	}

	public String getDisplayName() {
		return dto.getDisplayName();
	}

	public void setDisplayName(String value) {
		dto.setDisplayName(value);
	}

	public String getFormat() {
		return dto.getFormat();
	}

	public void setFormat(String value) {
		dto.setFormat(value);
	}
}
