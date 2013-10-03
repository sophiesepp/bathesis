package com.sophiesepp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sophiesepp.client.ComposerNgramQuery.MyPopup;

public class ComposerNgramQuery extends TextResults implements EntryPoint{

	static VerticalPanel composerngramPanel = new VerticalPanel();
	HorizontalPanel composerngramcontentPanel= new HorizontalPanel(); 
	VerticalPanel rightcomposerngramPanel = new VerticalPanel();
	VerticalPanel leftcomposerngramPanel = new VerticalPanel();
	HorizontalPanel composerngramPanel1= new HorizontalPanel();
	HorizontalPanel composerngramPanel2 = new HorizontalPanel();
	
	
	VerticalPanel seperationPanel = new VerticalPanel();
	final static TextArea dataComposerNgram = new TextArea();
	
	final Label ngramLabel = new Label("Melody");
	
	
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
		        g.setWidget(RootPanel.get("rightcomposerngramPanel"));
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


		composerngramcontentPanel.addStyleName("composerngramcontentPanel");
		rightcomposerngramPanel.addStyleName("rightcomposerngramPanel");
		 final Button b2 = new Button("About");
		    b1.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        final MyPopup g = new MyPopup();
		        g.setWidget(RootPanel.get("rightcountrieskeyPanel"));
		        g.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
		            public void setPosition(int offsetWidth, int offsetHeight) {
		              g.setPopupPosition(b1.getAbsoluteLeft(), b1.getAbsoluteTop());
		              g.setAutoHideEnabled(true);
		              g.setGlassEnabled(true);
		            }
		          });

		        g.setVisible(true);
		        g.setWidth("500px");
		        g.setHeight("500px");
		      
		        g.show();
		       
		      }
		    });

		  
		leftcomposerngramPanel.addStyleName("leftcomposerngramPanel");
	
		composerngramPanel1.addStyleName("Panel1");
		composerngramPanel2.addStyleName("Panel2");
		
		seperationPanel.addStyleName("seperationPanelComposer");
		b1.addStyleName("informationbutton");
		
		ngramLabel.addStyleName("text3");
		
		dataComposerNgram.setWidth("600px");
		dataComposerNgram.setVisibleLines(20);
		dataComposerNgram.setEnabled(false);
		
		composerngramPanel.add(RootPanel.get("heading19"));
		composerngramPanel.add(composerngramcontentPanel);
		composerngramPanel.add(seperationPanel);
		composerngramcontentPanel.add(leftcomposerngramPanel);
		composerngramcontentPanel.add(rightcomposerngramPanel);
		
		leftcomposerngramPanel.add(composerngramPanel1);
		leftcomposerngramPanel.add(composerngramPanel2);
		
		rightcomposerngramPanel.add(RootPanel.get("rightcomposerngramPanel"));
		rightcomposerngramPanel.add(b1);
		composerngramPanel2.add(dataComposerNgram);	

		
	}


	public static void queryComposerNgram() {

		greetingService.showQueryComposerNgram(buildQueryComposerNgram(),new AsyncCallback<String>(){

			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user

			}

			public void onSuccess(String result) {

				dataComposerNgram.getElement().setInnerText(result);
			}

		});
	}
	
	public static void queryComposerNgramBeginning() {

		greetingService.showQueryComposerNgram( "SELECT table4.ngram AS ngram, count(ngram) AS counts FROM (SELECT scoreId AS scoreId FROM(SELECT workId AS workId FROM workspace.composer AS composers JOIN workspace.work AS works ON composers.personId = works.personId WHERE composers.personId ='K1') AS table1 JOIN EACH(SELECT workId AS workId, scoreId AS scoreId FROM workspace.score) AS table2 ON table1.workId = table2.workId) AS table3 JOIN EACH(SELECT scoreId AS scoreId, ngram AS ngram FROM workspace.ngram) AS table4 ON table3.scoreId = table4.scoreId GROUP EACH BY ngram ORDER BY counts DESC LIMIT 10",new AsyncCallback<String>(){

			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user

			}

			public void onSuccess(String result) {

				dataComposerNgram.getElement().setInnerText(result);
			}

		});
	}
	
	
	public static String buildQueryComposerNgram(){

		String c = Srsr.composerBox.getText();

		String query = "SELECT table4.ngram AS ngram, count(ngram) AS counts FROM (SELECT scoreId AS scoreId FROM(SELECT workId AS workId FROM workspace.composer AS composers JOIN workspace.work AS works ON composers.personId = works.personId WHERE composers.personId ='";
		query +=c;
		query += "') AS table1 JOIN EACH(SELECT workId AS workId, scoreId AS scoreId FROM workspace.score) AS table2 ON table1.workId = table2.workId) AS table3 JOIN EACH(SELECT scoreId AS scoreId, ngram AS ngram FROM workspace.ngram) AS table4 ON table3.scoreId = table4.scoreId GROUP EACH BY ngram ORDER BY counts DESC LIMIT 10";

		return query;
	}

}
