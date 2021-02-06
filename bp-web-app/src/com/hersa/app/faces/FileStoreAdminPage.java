package com.hersa.app.faces;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.hersa.app.bom.filestore.FileStore;
import com.hersa.app.dao.filestore.FileStoreCreateException;
import com.hersa.app.service.FileStoreInterfaces;
import com.hersa.app.service.FileStoreService;

@ManagedBean
@ViewScoped
public class FileStoreAdminPage extends FacesPage implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2927308129732693584L;
	
	private UploadedFile file;
	
	private String displayName;
	private String category;
	private String description;
	private String caption;
	
	public FileStoreAdminPage() {
		super("File Manager");
	}
	
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
        	
        	try {
        		
        		Date today = new Date();
        		
				InputStream inputStream = file.getInputStream();
				
				FileStore store = new FileStore();
				
				validateFile();
				
				store.setCaption(caption);
				store.setDescription(description);
				store.setDisplayName(displayName);
				store.setCategory(category);

				store.setFileSize((int) file.getSize());
				
				store.setEnabled(1);
				store.setDateCreated(today);
				store.setCreatedBy(this.getSessionUser().getUserName());
				store.setDateModified(today);
				store.setModifiedBy(this.getSessionUser().getUserName());
				
				String format = StringUtils.substringAfter(file.getFileName(), ".").toLowerCase();
				
				store.setFormat(format);
				
				FileStoreInterfaces videoService = new FileStoreService();
				
				videoService.saveVideo(store, inputStream);
				
				addInfoMessage(store.getFileName() + " is uploaded.");
				
			} catch (IOException e) {
				e.printStackTrace();
				addErrorMessage("1" + e.getMessage());
			} catch (FileStoreCreateException e) {
				e.printStackTrace();
				addErrorMessage("2" + e.getMessage());
			} catch(Exception e) {
				e.printStackTrace();
				addErrorMessage("3" + e.getMessage());
			} 
        }
    }

    private void validateFile() {
		// TODO Auto-generated method stub
		
	}

	public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desription) {
		this.description = desription;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}