package com.hersa.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.hersa.app.bom.filestore.FileDAO;
import com.hersa.app.bom.filestore.FileStore;
import com.hersa.app.bom.filestore.FileStoreManager;
import com.hersa.app.dao.filestore.FileStoreCreateException;

public class FileStoreService implements FileStoreInterfaces {

	public static final String MP4 = "mp4";
	public static final String TXT = "txt";
	public static final String DOC = "docx";
	
	//public static final String STORE_PATH = "C:\\Users\\vuhernandez\\Documents\\AppData\\FILE_STORE\\";
		
	public static final String STORE_PATH = "/opt/tomcat/AppData/FILE_STORE/";
	
	@Override
	public File getVideoFileInputStream(String id) throws FileNotFoundException {
		
		FileStoreManager fsm = new FileStoreManager();
		
		FileStore store =  fsm.getFileStoreById(new Long(id));
				
	    if (store == null) {
	    	 return null;
		}
		
		String uri = store.getUri();
		
		File videoFile = new File(STORE_PATH + uri);
		
		return videoFile;
	}
	
	public List<FileStore> getAllTrainingResources() {
		FileStoreManager fsm = new FileStoreManager();
		return fsm.getAllFilesByCategory("TRAINING");
	}
	
	@Override
	public void saveVideo(FileStore store, InputStream inputStream) throws IOException, FileStoreCreateException {
		
		try {
			
			String uri = store.genURI();
			
			File directory = new File(STORE_PATH + uri);
			
			if (!directory.exists()) {
				directory.mkdirs();
			}
			
			String format = store.getFormat();
			
			FileStoreManager fsm = new FileStoreManager();
			
			String type = "OTHER";
			
			if (MP4.equalsIgnoreCase(format)) {
				type = FileStore.TYPE_VIDEO;
			}else if (TXT.equalsIgnoreCase(format) || DOC.equalsIgnoreCase(format)) {
				type = FileStore.TYPE_TEXT;
			}
			
			store.setType(type);
			
			String fileName = store.genFileName() + "." + format;
			
			File file = new File(directory, fileName);
			
			FileDAO fileDAO = new FileDAO();
			
			fileDAO.save(inputStream, file);
			
			store.setFileName(fileName);
			
			store.setUri(uri + fileName);
			
			fsm.save(store);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
