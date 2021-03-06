package com.hersa.app.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import com.hersa.app.ApplicationException;
import com.hersa.app.bom.bpreading.BloodPressure;
import com.hersa.app.bom.bpreading.BloodPressureManager;

@ManagedBean
@ViewScoped
public class ReadingsPage extends FacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4162007323141884366L;

	private List<BloodPressure> readingsList;
	
	public ReadingsPage() {
		super("BP Readings");
		initialize();
	}

	private void initialize() {
		this.setReadingsList(new ArrayList<BloodPressure>());
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
		// get session user
		loadBloodPressureReadings();
	}

	private void loadBloodPressureReadings() {
		BloodPressureManager bpm = new BloodPressureManager();
		setReadingsList(bpm.retrieveAllBloodPressure());
	}
	
	public String onDeleteBtnClick() {
		
		Map<String, String> map = getFacesContext().getExternalContext().getRequestParameterMap();
		String id = map.get("editId");
		
		try {
		
			Long idL = StringUtils.isEmpty(id) ? null : Long.valueOf(id);

			BloodPressureManager man = new BloodPressureManager();
			man.deleteById(idL);
			
			// check if we are 'editing' this reading in addreading page. 
			// if we are, remove from session.
			
			SessionPage sessionPage = getSessionPage();
			
			Long editId = sessionPage.getEditBpId();
			
			if (editId != null && editId.compareTo(idL) == 0) {
				sessionPage.setEditBpId(null);
				System.out.println("Edit id cleared.");
			}
			
			loadBloodPressureReadings();
			addInfoMessage("Reading Deleted.");
			
			// do external page logic
			if (isAddReadingsPageInScope()) {
				getAddReadingPage().onPageLoad();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage());
		}

		return "";
	}
	
	public String goToEditPage() {
		
		String response = "";
		
		Map<String, String> map = getFacesContext().getExternalContext().getRequestParameterMap();
		
		String id = map.get("editId");
	    
		Long idL = StringUtils.isEmpty(id) ? null : Long.valueOf(id);
		
		SessionPage sessionPage = getSessionPage();
		
	    sessionPage.setEditBpId(idL);
	    
		if (sessionPage.isEditRequest()) {
			
			response = "addreading";
			
			if(isAddReadingsPageInScope()) {
				response = "";
				AddReadingPage page = getAddReadingPage();
				page.onPageLoad();
			}
			
		}
		
		return response;
	}
	
	

	public List<BloodPressure> getReadingsList() {
		return readingsList;
	}

	public void setReadingsList(List<BloodPressure> readingsList) {
		this.readingsList = readingsList;
	}
}
