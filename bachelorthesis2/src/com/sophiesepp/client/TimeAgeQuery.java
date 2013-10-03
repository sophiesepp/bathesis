package com.sophiesepp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sophiesepp.shared.HeatmapObject;
import com.sophiesepp.shared.TimeAge;

public class TimeAgeQuery extends Heatmap implements EntryPoint   {
	


	static VerticalPanel timeagePanel = new VerticalPanel();
	static HorizontalPanel timeagecontentPanel = new HorizontalPanel();
	HorizontalPanel lefttimeagePanel = new HorizontalPanel();
	VerticalPanel righttimeagePanel = new VerticalPanel();

	VerticalPanel seperationPanel = new VerticalPanel();
	
	HorizontalPanel timeagePanel1= new HorizontalPanel();
	VerticalPanel timeagePanel2 = new VerticalPanel();
	VerticalPanel timeageLabel1= new VerticalPanel();
	VerticalPanel timeageLabel2= new VerticalPanel();
	VerticalPanel timeageLabel3= new VerticalPanel();
	VerticalPanel timeageLabel4= new VerticalPanel();
	
	final Label timeagengramLabel = new Label("Melody");
	final Label timeagegenreLabel = new Label("Genre");
	final Label timeagekeyLabel = new Label("Key");
	
	final Button showTimeAgeQueryButton = new Button("Run Query");
	
	

	 static TextBox ngramTimeAge = new TextBox();
	 static SuggestBox genreTimeAge = new SuggestBox(com.sophiesepp.client.Srsr.genre);	
	 static SuggestBox keyTimeAge = new SuggestBox(com.sophiesepp.client.Srsr.key);
	
	  private static class MyPopup extends PopupPanel {

		    public MyPopup() {
		      // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
		      // If this is set, the panel closes itself automatically when the user
		      // clicks outside of it.
		      super(true);

		      // PopupPanel is a SimplePanel, so you have to set it's widget property to
		      // whatever you want its contents to be.
		      setWidget(new Label("Click outside of this popup to close it"));
		      
		    }
		  }


	
	
	public void onModuleLoad() {
		
		 final Button b1 = new Button("About");
		    b1.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        final MyPopup g = new MyPopup();
		        g.setWidget(RootPanel.get("righttimeagePanel"));
		        g.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
		            public void setPosition(int offsetWidth, int offsetHeight) {
		              g.setPopupPosition(b1.getAbsoluteLeft(), b1.getAbsoluteTop());
		              g.setAutoHideEnabled(true);
		            }
		          });

		        g.setVisible(true);
		        g.setWidth("500px");
		        g.setHeight("500px");
		      
		        g.show();
		       
		      }
		    });

		  


		timeagePanel.add(RootPanel.get("heading2"));
		timeagePanel.add(timeagecontentPanel);
		timeagePanel.add(seperationPanel);
		
		timeagecontentPanel.add(lefttimeagePanel);
		timeagecontentPanel.add(righttimeagePanel);
	
		lefttimeagePanel.add(timeagePanel1);
		lefttimeagePanel.add(timeagePanel2);
		
	
		
		
		righttimeagePanel.add(b1);
	
		

		
		timeagePanel1.add(RootPanel.get("heatmapTimeAgeCanvas"));
		timeagePanel1.add(RootPanel.get("legende200"));


		
		timeageLabel1.add(timeagegenreLabel);
		timeageLabel1.add(genreTimeAge);
		timeageLabel2.add(timeagekeyLabel);
		timeageLabel2.add(keyTimeAge);
		timeageLabel3.add(RootPanel.get("KeyboardTimeAgeContainer"));
		timeageLabel4.add(showTimeAgeQueryButton);
		

		timeagePanel2.add(timeageLabel1);
		timeagePanel2.add(timeageLabel2);
		timeagePanel2.add(timeageLabel3);
		timeagePanel2.add(timeageLabel4);

		
	timeagePanel.addStyleName("timeagePanel");
	timeagecontentPanel.addStyleName("timeagecontentPanel");
	lefttimeagePanel.addStyleName("lefttimeagePanel");
	righttimeagePanel.addStyleName("righttimeagePanel");
	timeagePanel1.addStyleName("Panel1");
	timeagePanel2.addStyleName("Panel2");
	

	timeageLabel1.addStyleName("label");
	timeageLabel2.addStyleName("label");
	timeageLabel3.addStyleName("label");
	timeageLabel4.addStyleName("buttonlabel");
	
	ngramTimeAge.addStyleName("textfield1");
	genreTimeAge.addStyleName("textfield1");
	keyTimeAge.addStyleName("textfield1");
	
	timeagengramLabel.addStyleName("text3");
	timeagegenreLabel.addStyleName("text3");
	timeagekeyLabel.addStyleName("text3");	
	
	seperationPanel.addStyleName("seperationPanel");
	
	showTimeAgeQueryButton.addStyleName("button1");
	b1.addStyleName("informationbutton");
	showTimeAgeQueryButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			TimeAgeQuery.queryTimeAge();
		}
	});
	
	

	}
	
	public static void queryTimeAge() {

		greetingService.showQueryTimeAge(buildQueryTimeAge(),
				new AsyncCallback<List<TimeAge>>() {
			public void onFailure(Throwable caught) {

			}
			public void onSuccess(List<TimeAge> result) {
				
				List<HeatmapObject> object = new ArrayList<HeatmapObject>();		
				
				for(TimeAge s: result)
				{			
				
					HeatmapObject obj = new HeatmapObject(s.publication-1700+40,(200-((s.age)*2)+10),s.percent);	
					object.add(obj);
				
					
				}
	
				String json = createJson(object);
				String json2 = createJsonForLegend(object,"parameter1","parameter2","parameter3");
				System.out.println(json);
				displayDataTimeAge(json,json2);
			}

		});
	

	}
	
	public static String buildQueryTimeAge(){

		String ngram="";
		String genre="";
		String key="";
		String query = "";
		int x=0;
		int y=0;
		int z=0;
		InputElement ngraminput = (InputElement)(Element)DOM.getElementById("ngramTimeAge");

		if(ngraminput.getValue().length() > 0) {
			System.out.println("ngram true");
			x=1;
			ngram= ngraminput.getValue();			
		} 
		
		if(!(genreTimeAge.getText().isEmpty()))
		{
			y=1;
			genre= genreTimeAge.getText();			
		}
		if(!(keyTimeAge.getText().isEmpty()))
		{
			z=1;
			key= keyTimeAge.getText();			
		}
		
		

		if((x==0) &&  (y==0) && (z==0)){
			query="SELECT publication AS value1,(publication-birth) AS value2, count(publication-birth) AS value3 FROM workspace.composer AS composers JOIN (SELECT publication,personId FROM workspace.work) AS works ON composers.personId=works.personId GROUP BY value1, value2"; 

		}
		if((x==1) &&  (y==0) && (z==0)){
			query= "SELECT table3.publication AS value1, (table3.publication-table4.birth) AS value2,count(table3.publication-table4.birth) AS value3 FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+= ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication FROM workspace.work) AS table2 ON table1.worka = table2.workb) AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer) AS table4 ON table3.persona = table4.personb GROUP BY value1, value2";
		}
		if((x==0) &&  (y==1) && (z==0)){
			query= "SELECT table1.publication AS value1,(table1.publication-table2.birth) AS value2,count(table1.publication-table2.birth) AS value3 FROM(SELECT personId AS personId,publication AS publication,genre AS genre FROM workspace.work WHERE genre='";
			query+=genre;
			query+="') AS table1 JOIN(SELECT personId AS personId, birth AS birth FROM workspace.composer) AS table2 ON table1.personId = table2.personId GROUP BY value1, value2";

		}
		if((x==0) &&  (y==0) && (z==1)){
			query= "SELECT table1.publication AS value1,(table1.publication-table2.birth) AS value2, count(table1.publication-table2.birth) AS value3 FROM(SELECT personId AS personId,publication AS publication,key AS key FROM workspace.work WHERE key='";
			query+=key;
			query+="') AS table1 JOIN(SELECT personId AS personId, birth AS birth FROM workspace.composer) AS table2 ON table1.personId = table2.personId GROUP BY value1, value2";

		}
		if((x==1) &&  (y==1) && (z==0)){
			query= "SELECT table3.publication AS value1, (table3.publication-table4.birth) AS value2,count(table3.publication-table4.birth) AS value3 FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+=ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication,genre AS genre FROM workspace.work) AS table2 ON table1.worka = table2.workb WHERE table2.genre='";
			query+=genre;
			query+="') AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer) AS table4 ON table3.persona = table4.personb GROUP BY value1, value2";
		}
		if((x==0) &&  (y==1) && (z==1)){
			query= "SELECT table1.publication AS value1,(table1.publication-table2.birth) AS value2, count(table1.publication-table2.birth) AS value3 FROM(SELECT personId AS personId,publication AS publication,genre AS genre,key AS key FROM workspace.work WHERE genre='";
			query+=genre;
			query+="' AND key='";
			query+=key;
			query+="') AS table1 JOIN(SELECT personId AS personId, birth AS birth FROM workspace.composer) AS table2 ON table1.personId = table2.personId GROUP BY value1, value2";
		}
		if((x==1) &&  (y==0) && (z==1)){
			query= "SELECT table3.publication AS value1, (table3.publication-table4.birth) AS value2,count(table3.publication-table4.birth) AS value3 FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+=ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication,key AS key FROM workspace.work) AS table2 ON table1.worka = table2.workb WHERE table2.key='";
			query+=key;
			query+="') AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer) AS table4 ON table3.persona = table4.personb GROUP BY value1, value2";
		}
		if((x==1) &&  (y==1) && (z==1)){
			query= "SELECT table3.publication AS value1, (table3.publication-table4.birth) AS value2,count(table3.publication-table4.birth) AS value3 FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+=ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication,genre AS genre,key AS key FROM workspace.work) AS table2 ON table1.worka = table2.workb WHERE table2.genre='";
			query+=genre;
			query+="' AND table2.key='";
			query+=key;
			query+="') AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer) AS table4 ON table3.persona = table4.personb GROUP BY value1, value2";

		}

		System.out.println(query);

		return query;
	}


	
	public static native void displayDataTimeAge(String json, String jsonforlegend) /*-{


	var obj = eval('('+json+')');
	var objforlegend = eval('('+jsonforlegend+')');


	// call the heatmap's store's setDataSet method in order to set static data
	$wnd.xx.store.setDataSet(obj);
	//$wnd.timeagelegend(objforlegend);
	}-*/;
	
}
