package com.hersa.app.faces;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.hersa.app.ApplicationException;
import com.hersa.app.bom.bpreading.BloodPressure;
import com.hersa.app.bom.bpreading.BloodPressureManager;

@ManagedBean
@ViewScoped
public class AddReadingPage extends FacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369193024900998381L;
	
	private Date readingDate;
	private Date readingTime;
	    
	private BloodPressure reading;
	
	private List<List<Tag>> tagsList; 
	
	private Long editId;
	private boolean isTextInputMode;
	
	public AddReadingPage() {
		super("Add Reading");
	}

	@PostConstruct
	public void onPageLoad() {
		
		super.setTitle("Add BP Reading");
		super.onPageLoad();
		readingDate = new Date();
		readingTime = new Date();
		
		reading 	= new BloodPressure();
		reading.setWeight(BigDecimal.ZERO);
		
		loadTagList();
		
		// check if this is an edit request.
		editId = getSessionPage().getEditBpId();
		
		if (isEditOperation()) {
			super.setTitle("Edit BP Reading");
			reading = loadForEdit(editId);
		}
		
		// check session for settings. 
		isTextInputMode = getSessionPage().isInputTextPreferred();
	}
	
	private BloodPressure loadForEdit(Long editId2) {
		BloodPressureManager man = new BloodPressureManager();
		BloodPressure toEdit = null;
		try {
			// load base info
			toEdit = man.retrieveBloodPressureById(editId2);
		
			// Set dates
			readingDate = toEdit.getDate();
			readingTime = toEdit.getDate();
			
			// parse out tags. 
			String tags = StringUtils.defaultIfEmpty(toEdit.getTags(), "");
			
			List<String> ts = Arrays.asList(StringUtils.split(tags, ","));
			
			for (List<Tag> l : tagsList) {
				for (Tag tag : l) {
					tag.setSelected(ts.contains(tag.getTagName()));
				}
			}
			
		} catch (ApplicationException e) {
			addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return toEdit;
	}

	private void loadTagList() {
		tagsList = new ArrayList<List<Tag>>();
		
		List<Tag> list = new ArrayList<Tag>();
		
		list.add(new Tag("resting"));
		list.add(new Tag("tired"));
		list.add(new Tag("tag 10"));
		list.add(new Tag("left arm"));
		list.add(new Tag("tag 4"));
		list.add(new Tag("happy"));
		list.add(new Tag("sad"));
		list.add(new Tag("anrgy"));
		list.add(new Tag("tag 3"));
		list.add(new Tag("tag 15"));
		
		Collections.sort(list, new Comparator<Tag>() {

			@Override
			public int compare(Tag o1, Tag o2) {
				// TODO Auto-generated method stub
				return Integer.valueOf(o1.getTagName().length()).compareTo(Integer.valueOf(o2.getTagName().length()));
			}
		});
		
		List<Tag> tempList = new ArrayList<Tag>();
		
		int charLimit = 25;
		int delta     = 3;
		int count     = 0;
		int charCount = 0;
		
		for (Tag tag : list) {
			
			int m = (charCount + tag.getTagName().length()) % charLimit;
			
			if(( m >= (charLimit - delta)) || m == 0) {
				tagsList.add(tempList);
				tempList = new ArrayList<Tag>();
			}
			
			tempList.add(tag);
			
			charCount += tag.getTagName().length();
			count ++;
			
			if (count == list.size()) {
				tagsList.add(tempList);
			}
		}
		
	}

	public String onTagSelectBtnClick() {
		
		UIComponent component = UIComponent.getCurrentComponent(getFacesContext());
		try {
			Tag val = (Tag) component.getAttributes().get("tagVal");
			
			if (val.isSelected()) {
				val.setSelected(false);
			}else {
				val.setSelected(true);
			}
		} catch (Exception e) {
			addErrorMessage("An error occurred while setting tag selection.");
		}

		return "";
	}
	public String onSaveReadingBtnClick() {
		try {
			
			onBeforeSave();
			validateReading();
			saveReading();
			onAfterSave();

		} catch (ApplicationException e) {
			addErrorMessage(e.getMessage());
		}catch (Exception e) {
			addErrorMessage("An unexpected error has occurred while saving reading.");
		}
		return "";
	}
	
	private void onBeforeSave() throws ApplicationException{
		try {
			// merge date time components. 
			reading.setDate(mergeDateTime());
			
			// Set the selected tags. 
			
			StringBuilder tags = new StringBuilder();
			
			int listCount = 0;
			
			for (List<Tag> tagList : tagsList) {
				
				int tagCount = 0;
			
				for (Tag tag : tagList) {
					
					if (tag.isSelected()) {
						
						tags.append(tag.getTagName());
					
						if (tagCount < (tagList.size() - 1) || listCount == (tagsList.size() - 1)) {
							tags.append(",");
						}
					}
					
					tagCount++;
				}
				
				listCount++;
			}
			
			reading.setTags(tags.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("An error occurred while validating reading.");
		}
		
		
	}

	private void saveReading() throws ApplicationException{
		BloodPressureManager man = new BloodPressureManager();
		if (isEditOperation()) {
			man.update(reading);
		}else {
			man.create(reading);
		}
	}

	private void onAfterSave() throws ApplicationException{
		// reset the model
		reload();
		// add message
		addInfoMessage("Reading Saved Successfuly");
	}

	private void validateReading() throws ApplicationException{
		
		if (reading.getSystolic() < 1) {
			throw new ApplicationException("Invlalid Systolic Reading");
		}
		
		if (reading.getDiastolic() < 1) {
			throw new ApplicationException("Invlalid Diastolic Reading");
		}
		
		if (!StringUtils.isEmpty(reading.getDescription())) {
			if (reading.getDescription().length() > 200) {
				throw new ApplicationException("200 max characters for Description allowed.");
			}
		}
		
	}

	public Date mergeDateTime() {

		LocalDate datePart = new LocalDate(readingDate);
		LocalTime timePart = new LocalTime(readingTime);
		LocalDateTime dateTime = datePart.toLocalDateTime(timePart);
		Date result = dateTime.toDate();
		
		return result;
	}

	public void reload() {
		onExitEditMode();
	}
	
	public String onExitEditMode() {
		try {
			// Remove session edit id. 
			this.getSessionPage().setEditBpId(null);
			
			// reload page
			onPageLoad();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage("An error occured.");
		}
		return "";
	}
	
	public void inputToggleListener() {
		getSessionPage().setInputTextPreferred(isTextInputMode);
	}
	
	public boolean isEditOperation() {
		return editId != null && editId.compareTo((long) 0) > 0;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public Date getReadingTime() {
		return readingTime;
	}

	public void setReadingTime(Date readingTime) {
		this.readingTime = readingTime;
	}

	public BloodPressure getReading() {
		return reading;
	}

	public void setReading(BloodPressure reading) {
		this.reading = reading;
	}

	public List<List<Tag>> getTagsList() {
		return tagsList;
	}

	public void setTagsList(List<List<Tag>> tagsList) {
		this.tagsList = tagsList;
	}

	public boolean isTextInputMode() {
		return isTextInputMode;
	}

	public void setTextInputMode(boolean isTextInputMode) {
		this.isTextInputMode = isTextInputMode;
	}
}
