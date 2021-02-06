package com.hersa.app.bom.filestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hersa.app.AbstractBaseManager;
import com.hersa.app.dao.filestore.FileStoreCreateException;
import com.hersa.app.dao.filestore.FileStoreDTO;

public class FileStoreManager extends AbstractBaseManager {

	public void save(FileStore store) throws IOException, FileStoreCreateException {

		try {
			
			this.getFileStoreDAO().createFileStore(store.getDto());
			
		} catch (FileStoreCreateException e) {
			e.printStackTrace();
			throw e;
		}finally {
			
		}
	}
	
	public FileStore getFileStoreById(Long id) {
		List<FileStore> list = convert( this.getFileStoreDAO().listFileStoreById(id));
		FileStore store = null;
		
		if (list.isEmpty() || list.size() > 1) {
			return store;
		}
		
		return list.get(0);
	}
	public List<FileStore> getAllFilesByCategory(String category){
		return convert(this.getFileStoreDAO().listFileStoreByCategory(category));
	}
	
	public List<FileStore> getAllFiles(){
		return convert(this.getFileStoreDAO().listAllFileStore());
	}
	
	private List<FileStore> convert(FileStoreDTO[] dtos){
		List<FileStore> list = new ArrayList<FileStore>();
		
		if (dtos == null) {
			return list;
		}
		
		for (FileStoreDTO dto : dtos) {
			list.add(new FileStore(dto));
		}
		
		return list;
	}
}
