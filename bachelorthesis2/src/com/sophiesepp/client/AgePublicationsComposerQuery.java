package com.sophiesepp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
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
import com.sophiesepp.client.AgePublicationsComposerQuery.MyPopup;
import com.sophiesepp.shared.AgePublicationsComposer;
import com.sophiesepp.shared.D3Object2ParameterType1;

public class AgePublicationsComposerQuery extends D3 implements EntryPoint{


	static VerticalPanel agepublicationscomposerPanel = new VerticalPanel();
	static HorizontalPanel agepublicationscomposercontentPanel = new HorizontalPanel();
	HorizontalPanel leftagepublicationscomposerPanel = new HorizontalPanel();
	VerticalPanel rightagepublicationscomposerPanel = new VerticalPanel();

	
	VerticalPanel seperationPanel = new VerticalPanel();

	
	VerticalPanel agepublicationscomposerPanel1= new VerticalPanel();
	VerticalPanel  agepublicationscomposerPanel2 = new VerticalPanel();
	
	
	VerticalPanel agepublicationscomposerLabel1= new VerticalPanel();
	VerticalPanel agepublicationscomposerLabel2= new VerticalPanel();
	VerticalPanel agepublicationscomposerLabel3= new VerticalPanel();
	VerticalPanel agepublicationscomposerLabel4= new VerticalPanel();
	
	final Button showAgePublicationsComposerQueryButton = new Button("Run Query");
	

	final Label agepublicationscomposergenreLabel = new Label("Genre");
	final Label agepublicationscomposerkeyLabel = new Label("Key");	
	final Label agepublicationscomposerngramLabel = new Label("Melody");
	

	final static SuggestBox genreAgePublicationsComposer = new SuggestBox(Srsr.genre);
	final static SuggestBox keyAgePublicationsComposer = new SuggestBox(Srsr.key);
	final static TextBox ngramAgePublicationsComposer = new TextBox();

	  public static class MyPopup extends PopupPanel {

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
		        g.setWidget(RootPanel.get("rightpublicationscomposerPanel"));
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

		    
		agepublicationscomposerPanel.addStyleName("agepublicationscomposerPanel");
		agepublicationscomposercontentPanel.addStyleName("agepublicationscomposercontentPanel");
		leftagepublicationscomposerPanel.addStyleName("leftagepublicationscomposerPanel");
		rightagepublicationscomposerPanel.addStyleName("rightagepublicationscomposerPanel");
		agepublicationscomposerPanel1.addStyleName("agepublicationscomposerPanel1");
		agepublicationscomposerPanel2.addStyleName("Panel2");
	
		seperationPanel.addStyleName("seperationPanelComposer");

		agepublicationscomposerLabel1.addStyleName("label");
		agepublicationscomposerLabel2.addStyleName("label");
		agepublicationscomposerLabel3.addStyleName("label");
		agepublicationscomposerLabel4.addStyleName("buttonlabel");
		
		agepublicationscomposergenreLabel.addStyleName("text3");
		agepublicationscomposerkeyLabel.addStyleName("text3");
		agepublicationscomposerngramLabel.addStyleName("text3");
		
		genreAgePublicationsComposer.addStyleName("textfield1");
		ngramAgePublicationsComposer.addStyleName("textfield1");
		keyAgePublicationsComposer.addStyleName("textfield1");
		
		showAgePublicationsComposerQueryButton.addStyleName("button1");
		b1.addStyleName("informationbutton");
		showAgePublicationsComposerQueryButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AgePublicationsComposerQuery.queryAgePublicationsComposer();
			}
		});

		

		
	
		
		agepublicationscomposerPanel.add(RootPanel.get("heading16"));
		agepublicationscomposerPanel.add(agepublicationscomposercontentPanel);
		agepublicationscomposerPanel.add(seperationPanel);
		agepublicationscomposercontentPanel.add(leftagepublicationscomposerPanel);
		agepublicationscomposercontentPanel.add(rightagepublicationscomposerPanel);
		leftagepublicationscomposerPanel.add(agepublicationscomposerPanel1);
		leftagepublicationscomposerPanel.add(agepublicationscomposerPanel2);
		rightagepublicationscomposerPanel.add(b1);
		
		agepublicationscomposerPanel1.add(RootPanel.get("linechartAgePublicationsComposer"));


		agepublicationscomposerLabel1.add(agepublicationscomposergenreLabel);
		agepublicationscomposerLabel1.add(genreAgePublicationsComposer);
		
		agepublicationscomposerLabel2.add(agepublicationscomposerkeyLabel);
		agepublicationscomposerLabel2.add(keyAgePublicationsComposer);
		agepublicationscomposerLabel3.add(RootPanel.get("KeyboardAgePublicationsComposerContainer"));
		agepublicationscomposerLabel4.add(showAgePublicationsComposerQueryButton);

		agepublicationscomposerPanel2.add(agepublicationscomposerLabel1);
		agepublicationscomposerPanel2.add(agepublicationscomposerLabel2);
		agepublicationscomposerPanel2.add(agepublicationscomposerLabel3);
		agepublicationscomposerPanel2.add(agepublicationscomposerLabel4);
		
	

		


	}	

	public static void queryAgePublicationsComposer() {

		greetingService.showQueryAgePublicationsComposer(buildQueryAgePublicationsComposer(),new AsyncCallback<List<AgePublicationsComposer>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<AgePublicationsComposer> 
			result) {
				
				List<D3Object2ParameterType1> object = new ArrayList<D3Object2ParameterType1>();
				
				
				for(AgePublicationsComposer s: result)
				{			
					D3Object2ParameterType1 obj = new D3Object2ParameterType1(s.age,s.publications);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType1(object,"age","publications");


				displayDataAgePublicationsComposer(json);
			}
			
			
		});
	}
	
	public static void queryAgePublicationsComposerBeginning() {

		greetingService.showQueryAgePublicationsComposer("SELECT (publication-birth) AS age, count(publication) AS publications FROM (SELECT personId AS personId, birth AS birth FROM workspace.composer WHERE personId='K1') AS composers JOIN (SELECT publication,personId FROM workspace.work) AS works ON composers.personId=works.personId GROUP BY age ORDER BY age ASC" ,new AsyncCallback<List<AgePublicationsComposer>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<AgePublicationsComposer> 
			result) {
				
				List<D3Object2ParameterType1> object = new ArrayList<D3Object2ParameterType1>();
				
				
				for(AgePublicationsComposer s: result)
				{			
					D3Object2ParameterType1 obj = new D3Object2ParameterType1(s.age,s.publications);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType1(object,"age","publications");


				displayDataAgePublicationsComposer(json);
			}
			
			
		});
	}
	

	public static String buildQueryAgePublicationsComposer(){

		String composer="";
		String genre="";
		String key="";
		String ngram="";
		String query = "";
		int x=0;
		int y=0;
		int z=0;


		if(!(Srsr.composerBox.getText().isEmpty()))
		{

			composer= Srsr.composerBox.getText();			
		}
		if(!(genreAgePublicationsComposer.getText().isEmpty()))
		{
			x=1;
			genre= genreAgePublicationsComposer.getText();			
		}
		if(!(keyAgePublicationsComposer.getText().isEmpty()))
		{
			y=1;
			key= keyAgePublicationsComposer.getText();			
		}
		InputElement ngraminput = (InputElement)(Element)DOM.getElementById("ngramAgePublicationsComposer");

		if(ngraminput.getValue().length() > 0) {
			System.out.println("ngram true");
			x=1;
			ngram= ngraminput.getValue();			
		} 
		
		if((x==0) &&  (y==0) && (z==0)){
			query="SELECT (publication-birth) AS age, count(publication) AS publications FROM (SELECT personId AS personId, birth AS birth FROM workspace.composer WHERE personId='";
			query+=composer;
			query+="') AS composers JOIN (SELECT publication,personId FROM workspace.work) AS works ON composers.personId=works.personId GROUP BY age ORDER BY age ASC"; 

		}
		if((x==1) &&  (y==0) && (z==0)){
			query= "SELECT (table3.publication-table4.birth) AS age,count(table3.publication-table4.birth) AS counts FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+= ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication FROM workspace.work) AS table2 ON table1.worka = table2.workb) AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer WHERE personId='";
			query+= composer;
			query+="') AS table4 ON table3.persona = table4.personb GROUP BY age ORDER BY age ASC";
		}
		if((x==0) &&  (y==1) && (z==0)){
			query= "SELECT (table1.publication-table2.birth) AS age,count(table1.publication-table2.birth) AS counts FROM(SELECT personId AS personId,publication AS publication,genre AS genre FROM workspace.work WHERE genre='";
			query+=genre;
			query+="') AS table1 JOIN(SELECT personId AS personId, birth AS birth FROM workspace.composer WHERE personId='";
			query+=composer;
			query+="') AS table2 ON table1.personId = table2.personId GROUP BY age ORDER BY age ASC";

		}
		if((x==0) &&  (y==0) && (z==1)){
			query= "SELECT (table1.publication-table2.birth) AS age, count(table1.publication-table2.birth) AS counts FROM(SELECT personId AS personId,publication AS publication,key AS key FROM workspace.work WHERE key='";
			query+=key;
			query+="') AS table1 JOIN(SELECT personId AS personId, birth AS birth FROM workspace.composer WHERE personId='";
			query+=composer;
			query+="') AS table2 ON table1.personId = table2.personId GROUP BY age ORDER BY age ASC";

		}
		if((x==1) &&  (y==1) && (z==0)){
			query= "SELECT (table3.publication-table4.birth) AS age,count(table3.publication-table4.birth) AS counts FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+=ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication,genre AS genre FROM workspace.work) AS table2 ON table1.worka = table2.workb WHERE table2.genre='";
			query+=genre;
			query+="') AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer WHERE personId='";
			query+=composer;
			query+="') AS table4 ON table3.persona = table4.personb GROUP BY age ORDER BY age ASC";
		}
		if((x==0) &&  (y==1) && (z==1)){
			query= "SELECT (table1.publication-table2.birth) AS age, count(table1.publication-table2.birth) AS counts FROM(SELECT personId AS personId,publication AS publication,genre AS genre,key AS key FROM workspace.work WHERE genre='";
			query+=genre;
			query+="' AND key='";
			query+=key;
			query+="') AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer WHERE personId='";
			query+=composer;
			query+="') AS table4 ON table3.persona = table4.personb GROUP BY age ORDER BY age ASC";
		}
		if((x==1) &&  (y==0) && (z==1)){
			query= "SELECT (table3.publication-table4.birth) AS age,count(table3.publication-table4.birth) AS counts FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+=ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication,key AS key FROM workspace.work) AS table2 ON table1.worka = table2.workb WHERE table2.key='";
			query+=key;
			query+="') AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer WHERE personId='";
			query+=composer;
			query+="') AS table4 ON table3.persona = table4.personb GROUP BY age ORDER BY age ASC";
		}
		if((x==1) &&  (y==1) && (z==1)){
			query= "SELECT (table3.publication-table4.birth) AS age,count(table3.publication-table4.birth) AS counts FROM(SELECT table2.personId AS persona,table2.publication AS publication FROM(SELECT scores.workId AS worka FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
			query+=ngram;
			query+="') AS table1 JOIN(SELECT workId AS workb, personId AS personId,publication AS publication,genre AS genre,key AS key FROM workspace.work) AS table2 ON table1.worka = table2.workb WHERE table2.genre='";
			query+=genre;
			query+="' AND table2.key='";
			query+=key;
			query+="') AS table3 JOIN (SELECT personId AS personb, birth AS birth FROM workspace.composer WHERE personId='";
			query+=composer;
			query+="') AS table4 ON table3.persona = table4.personb GROUP BY age ORDER BY age ASC";

		}
	
		System.out.println(query);
		return query;
	}



	public static native void displayDataAgePublicationsComposer(String data) /*-{

	var obj = eval(data);
	$wnd.agepublicationscomposer(obj);	



	}-*/;


}
