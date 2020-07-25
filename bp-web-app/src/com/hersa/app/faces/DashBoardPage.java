package com.hersa.app.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.data.NumericPoint;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.scatter.ScatterChartModel;

import com.hersa.app.bom.bpreading.BloodPressure;
import com.hersa.app.bom.bpreading.BloodPressureManager;

@ManagedBean
@ViewScoped
public class DashBoardPage extends FacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3842083859571885607L;

	private LineChartModel lineModel;
	private ScatterChartModel scatterModel;
	  
	public DashBoardPage() {
		super("Dashboard");
	}
	
	@PostConstruct
	public void onPageLoad() {
		createLineModel();
		createScatterModel();
	}
	
	public void createLineModel() {
		BloodPressureManager bpm = new BloodPressureManager();
		List<BloodPressure> readings = bpm.retrieveAllBloodPressure();
		
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet sysSet = new LineChartDataSet();
        LineChartDataSet diaSet = new LineChartDataSet();
        LineChartDataSet pulSet = new LineChartDataSet();
        
        List<Object> sys = new ArrayList<>();
        List<Object> dia = new ArrayList<>();
        List<Object> pul = new ArrayList<>();
        
        for (BloodPressure bloodPressure : readings) {
			sys.add(bloodPressure.getSystolic());
			dia.add(bloodPressure.getDiastolic());
			pul.add(bloodPressure.getPulse());
		}
        
        sysSet.setData(sys);
        sysSet.setFill(false);
        sysSet.setLabel("Systolic");
        sysSet.setBorderColor("rgb(75, 192, 192)");
        sysSet.setLineTension(0.1);
        data.addChartDataSet(sysSet);
        
        diaSet.setData(dia);
        diaSet.setFill(false);
        diaSet.setLabel("Diastolic");
        diaSet.setBorderColor("rgb(204, 102, 255)");
        diaSet.setLineTension(0.1);
        data.addChartDataSet(diaSet);
        
        pulSet.setData(pul);
        pulSet.setFill(false);
        pulSet.setLabel("Pulse");
        pulSet.setBorderColor("rgb(255, 0, 102)");
        pulSet.setLineTension(0.1);
        data.addChartDataSet(pulSet);
         
        List<String> labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        labels.add("Jul");
        labels.add("Aug");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");
        data.setLabels(labels);
         
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Blood Pressure Line Chart");
        options.setTitle(title);
         
        lineModel.setOptions(options);
        lineModel.setData(data);
    }

	public void createScatterModel() {
		
		BloodPressureManager bpm = new BloodPressureManager();
		List<BloodPressure> readings = bpm.retrieveAllBloodPressure();
		
        scatterModel = new ScatterChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet sysSet = new LineChartDataSet();
        LineChartDataSet diaSet = new LineChartDataSet();
        LineChartDataSet pulSet = new LineChartDataSet();
        
        
        List<Object> sys = new ArrayList<>();
        List<Object> dia = new ArrayList<>();
        List<Object> pul = new ArrayList<>();
        
        for (BloodPressure bloodPressure : readings) {
        	
        	DateTime dateTime = new DateTime(bloodPressure.getDate());
        	
        	int dayOfYear = dateTime.getDayOfYear();
        	
			sys.add(new NumericPoint(dayOfYear, bloodPressure.getSystolic()));
			dia.add(new NumericPoint(dayOfYear, bloodPressure.getDiastolic()));
			pul.add(new NumericPoint(dayOfYear, bloodPressure.getPulse()));
			
    	}
        
        sysSet.setData(sys);
        sysSet.setFill(false);
        sysSet.setLabel("Systolic");
        sysSet.setBorderColor("rgb(75, 192, 192)");
        //sysSet.setLineTension(0.1);
        sysSet.setShowLine(false);
        data.addChartDataSet(sysSet);
        
        diaSet.setData(dia);
        diaSet.setFill(false);
        diaSet.setLabel("Diastolic");
        diaSet.setBorderColor("rgb(204, 102, 255)");
        //diaSet.setLineTension(0.1);
        diaSet.setShowLine(false);
        data.addChartDataSet(diaSet);
        
        pulSet.setData(pul);
        pulSet.setFill(false);
        pulSet.setLabel("Pulse");
        pulSet.setBorderColor("rgb(255, 0, 102)");
        //pulSet.setLineTension(0.1);
        pulSet.setShowLine(false);
        data.addChartDataSet(pulSet);
        
        
        List<String> labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        labels.add("Jul");
        labels.add("Aug");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");
        data.setLabels(labels);
         
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Blood Pressure Scatter Plot");
        options.setTitle(title);
         
   
        scatterModel.setOptions(options);
        scatterModel.setData(data);
    }
	
	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public ScatterChartModel getScatterModel() {
		return scatterModel;
	}

	public void setScatterModel(ScatterChartModel scatterModel) {
		this.scatterModel = scatterModel;
	}
}
