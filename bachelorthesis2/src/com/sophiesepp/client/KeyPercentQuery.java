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
import com.sophiesepp.shared.D3Object2ParameterType2;
import com.sophiesepp.shared.KeyPercent;

public class KeyPercentQuery extends D3 implements EntryPoint {
	


	static VerticalPanel keypercentPanel = new VerticalPanel();
	static HorizontalPanel keypercentcontentPanel = new HorizontalPanel();
	HorizontalPanel leftkeypercentPanel = new HorizontalPanel();
	VerticalPanel rightkeypercentPanel = new VerticalPanel();

	
	VerticalPanel seperationPanel = new VerticalPanel();
	HorizontalPanel keypercentPanel1= new HorizontalPanel(); 
	VerticalPanel keypercentPanel2 = new VerticalPanel();
	VerticalPanel keypercentLabel1= new VerticalPanel();
	VerticalPanel keypercentLabel2= new VerticalPanel();
	VerticalPanel keypercentLabel3= new VerticalPanel();
	VerticalPanel keypercentLabel4= new VerticalPanel();
	
	final Button showKeyPercentQueryButton = new Button("Run Query");
	
	final Label keypercentgenreLabel = new Label("Genre");
	final Label keypercentngramLabel = new Label("Melody");
	final Label keypercentcountryLabel = new Label("Country");
	
	final static SuggestBox genreKeyPercent = new SuggestBox(com.sophiesepp.client.Srsr.genre);
	final static SuggestBox countryKeyPercent = new SuggestBox(com.sophiesepp.client.Srsr.country);
	
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
		        g.setWidget(RootPanel.get("rightkeypercentPanel"));
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

		  


		
		keypercentPanel.addStyleName("keypercentPanel");
		keypercentcontentPanel.addStyleName("keypercentcontentPanel");
		leftkeypercentPanel.addStyleName("leftkeypercentPanel");
		rightkeypercentPanel.addStyleName("rightkeypercentPanel");
		keypercentPanel1.addStyleName("Panel1");
		keypercentPanel2.addStyleName("Panel2");
		keypercentLabel1.addStyleName("label");
		keypercentLabel2.addStyleName("label");
		keypercentLabel3.addStyleName("label");
		keypercentLabel4.addStyleName("buttonlabel");
		
		showKeyPercentQueryButton.addStyleName("button1");
		b1.addStyleName("informationbutton");
		seperationPanel.addStyleName("seperationPanel");
		
		
		showKeyPercentQueryButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				KeyPercentQuery.queryKeyPercent();
			}
		});
		
		
		keypercentgenreLabel.addStyleName("text3");	
		keypercentngramLabel.addStyleName("text3");
		keypercentcountryLabel.addStyleName("text3");
		
	
		
		
		keypercentPanel.add(RootPanel.get("heading5"));
		keypercentPanel.add(keypercentcontentPanel);
		keypercentPanel.add(seperationPanel);
		
		keypercentcontentPanel.add(leftkeypercentPanel);
		keypercentcontentPanel.add(rightkeypercentPanel);
		
		leftkeypercentPanel.add(keypercentPanel1);
		leftkeypercentPanel.add(keypercentPanel2);
		keypercentPanel1.add(RootPanel.get("pieKeyPercent"));
		keypercentPanel2.add(keypercentLabel1);
		keypercentPanel2.add(keypercentLabel2);
		keypercentPanel2.add(keypercentLabel3);
		keypercentPanel2.add(keypercentLabel4);
		
		rightkeypercentPanel.add(b1);
		keypercentLabel1.add(keypercentgenreLabel);
		keypercentLabel1.add(genreKeyPercent);
		keypercentLabel2.add(keypercentcountryLabel);
		keypercentLabel2.add(countryKeyPercent);
		keypercentLabel3.add(RootPanel.get("KeyboardKeyPercentContainer"));
		keypercentLabel4.add(showKeyPercentQueryButton);
		
	
	}
	
	public static void queryKeyPercent() {

		greetingService.showQueryKeyPercent(buildQueryKeyPercent(),new AsyncCallback<List<KeyPercent>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<KeyPercent> result) {

				List<D3Object2ParameterType2> object = new ArrayList<D3Object2ParameterType2>();
				
				
				for(KeyPercent s: result)
				{			
					D3Object2ParameterType2 obj = new D3Object2ParameterType2(s.key,s.counts);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType2(object,"key","counts");


				displayDataKeyPercent(json);
			}
		});
	}


	public static String buildQueryKeyPercent(){

			
		String genre="";
		String ngram="";
		String country="";
		String query = "";
		int x=0;
		int y=0;
		int z=0;

		String a = "SELECT key AS key, count(workId) AS workId FROM ";
		String b = "workspace.work " ;
		String c = "WHERE genre=";
		String d = "(SELECT scores.workId AS workId FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram =";
		String e = ") AS table1 JOIN(SELECT workId AS workId, personId AS personId,key AS key FROM workspace.work) AS table2 ON table1.workId = table2.workId";
		String f = "workspace.work AS table1 JOIN(SELECT personId AS personId, country AS country FROM workspace.composer WHERE country=";
		String g = ") AS table2 ON table1.personId = table2.personId";
		String h = ") AS table1 JOIN(SELECT workId AS workId, personId AS personId,genre AS genre,key AS key FROM workspace.work ";
		String i = ") AS table2 ON table1.workId = table2.workId";
		String j = "(SELECT table2.personId AS personId,table2.key AS key,table2.workId AS workId FROM";
		String k =  " JOIN (SELECT personId AS personId, country AS country FROM workspace.composer WHERE country='";
		String l = "') AS table4 ON table3.personId = table4.personId";
		String m = "SELECT table1.key AS key, count(table1.workId) AS workId FROM (SELECT key AS key, personId AS personId, workId AS workId, genre AS genre FROM workspace.work WHERE genre='";
		String n = ") AS table1";
		String o = ") AS table3";
		String p =  "SELECT table3.key AS key, count(table3.workId) AS workId FROM";
		String q = "') AS table4 ON table3.personId = table4.personId"; 
		String r = "SELECT table2.key AS key, count(table2.workId) AS workId FROM";
		String s = "(SELECT table2.personId AS personId,table2.key AS key,table2.genre AS genre,table2.workId AS workId FROM";
		String t = "(SELECT scores.workId AS workId FROM workspace.ngram AS ngrams JOIN workspace.score AS scores ON  ngrams.scoreId = scores.scoreId WHERE ngrams.ngram ='";
		String w = " GROUP BY key ORDER BY workId DESC";
		
		
		
		if(!(genreKeyPercent.getText().isEmpty()))
		{
			x=1;
			genre= genreKeyPercent.getText();			
		}
		InputElement ngraminput = (InputElement)(Element)DOM.getElementById("ngramKeyPercent");

		if(ngraminput.getValue().length() > 0) {
			System.out.println("ngram true");
			x=1;
			ngram= ngraminput.getValue();			
		} 
		
		if(!(countryKeyPercent.getText().isEmpty()))
		{
			z=1;
			country= countryKeyPercent.getText();			
		}

		if((x==0) &&  (y==0) && (z==0)){
			query= a+b+w;
		

		}
		if((x==1) &&  (y==0) && (z==0)){
			query= a+b+c;
			query+= "'"+genre+"'";
			query+= w;
			
		}
		if((x==0) &&  (y==1) && (z==0)){
			query= r+d;
			query+="'"+ngram+"'";
			query+=e+w;
			

		}
		if((x==0) &&  (y==0) && (z==1)){
			query= a+f;
			query+="'"+country+"'";
			query+=g+w;
			

		}
		if((x==1) &&  (y==1) && (z==0)){
			query= r+d;
			query+="'"+ngram+"'";
			query+=h+c;
			query+="'"+genre+"'";
			query+= i+w;
			
		}
		if((x==0) &&  (y==1) && (z==1)){
			query= p+j+t;
			query+=ngram;
			query+="'"+e+o+k;
			query+= country;
			query+=l+w;
			
		}
		if((x==1) &&  (y==0) && (z==1)){
			query= m;
			query+=genre;
			query+=n+k;
			query+=country;
			query+=g+w;
			
		}
		if((x==1) &&  (y==1) && (z==1)){
			query= p+s+t;
			query+=ngram+"'";
			query+=h+c;
			query+="'"+genre+"'";
			query+=i+o+k;
			query+=country;
			query+=q+w;
			

		}
		
		
		return query;
	}

	public static native void displayDataKeyPercent(String data) /*-{

	var obj = eval(data);
	$wnd.keypercent(obj);	



	}-*/;	


}
