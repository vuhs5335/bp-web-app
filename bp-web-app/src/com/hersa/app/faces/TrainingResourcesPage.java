package com.hersa.app.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hersa.app.bom.filestore.FileStore;
import com.hersa.app.service.FileStoreService;

@ManagedBean
@ViewScoped

public class TrainingResourcesPage extends FacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3429070439729670267L;

	private List<FileStore> files;
	
	public TrainingResourcesPage() {
		super("Training");
		setFiles(new ArrayList<FileStore>());
	}
	
	@PostConstruct
	public void onPageLoad() {
		FileStoreService fss = new FileStoreService();
		setFiles(fss.getAllTrainingResources());
	}

	public List<FileStore> getFiles() {
		return files;
	}

	public void setFiles(List<FileStore> files) {
		this.files = files;
	}
}
