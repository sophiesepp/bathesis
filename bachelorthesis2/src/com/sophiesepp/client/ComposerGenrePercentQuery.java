package com.sophiesepp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sophiesepp.client.ComposerGenrePercentQuery.MyPopup;
import com.sophiesepp.shared.ComposerGenrePercent;
import com.sophiesepp.shared.D3Object2ParameterType2;

public class ComposerGenrePercentQuery extends D3 implements EntryPoint {




	static VerticalPanel composergenrekeyPanel = new VerticalPanel();
	static HorizontalPanel composergenrekeycontentPanel = new HorizontalPanel();
	VerticalPanel composergenrePanel = new VerticalPanel();
	VerticalPanel composergenrePanel1 = new VerticalPanel();
	VerticalPanel composergenrePanel2 = new VerticalPanel();
	VerticalPanel composerkeyPanel = new VerticalPanel();
	VerticalPanel composerkeyPanel1 = new VerticalPanel();
	VerticalPanel composerkeyPanel2 = new VerticalPanel();
	
	VerticalPanel seperationPanel = new VerticalPanel();

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
		        g.setWidget(RootPanel.get("rightcomposergenrepercentPanel"));
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

			
			 final Button b2 = new Button("About");
			    b1.addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			        final MyPopup g = new MyPopup();
			        g.setWidget(RootPanel.get("rightcomposerkeypercentPanel"));
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

		composergenrekeyPanel.addStyleName("composergenrekeyPanel");
		composergenrekeycontentPanel.addStyleName("composergenrekeycontentPanel");
		composergenrePanel.addStyleName("composergenrePanel");
		composerkeyPanel.addStyleName("composerkeyPanel");
	
		seperationPanel.addStyleName("seperationPanelComposer");
		b1.addStyleName("informationbutton");
		b2.addStyleName("informationbutton");
		composergenrekeyPanel.add(composergenrekeycontentPanel);
		composergenrekeyPanel.add(seperationPanel);
		
		composergenrekeycontentPanel.add(composergenrePanel );
		composergenrekeycontentPanel.add(composerkeyPanel);
		
		composergenrePanel.add(composergenrePanel1);
		composergenrePanel.add(composergenrePanel2);
		composergenrePanel1.add(RootPanel.get("heading17"));
		composergenrePanel2.add(RootPanel.get("pieComposerGenre"));
		composergenrePanel2.add(b1);
		composerkeyPanel.add(composerkeyPanel1);
		composerkeyPanel.add(composerkeyPanel2);
		composerkeyPanel1.add(RootPanel.get("heading18"));
		composerkeyPanel2.add(RootPanel.get("pieComposerKey"));
		composerkeyPanel2.add(b2);
		
	
		
		
		
	}
	
	
	public static void queryComposerGenrePercent() {

		greetingService.showQueryComposerGenrePercent(buildQueryComposerGenre(),new AsyncCallback<List<ComposerGenrePercent>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<ComposerGenrePercent> result) {

		
				List<D3Object2ParameterType2> object = new ArrayList<D3Object2ParameterType2>();
				
				
				for(ComposerGenrePercent s: result)
				{			
					D3Object2ParameterType2 obj = new D3Object2ParameterType2(s.genre,s.counts);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType2(object,"genre","counts");


				displayDataComposerGenrePercent(json);
			
			}
		});
	}


	public static void queryComposerGenrePercentBeginning() {

		greetingService.showQueryComposerGenrePercent("SELECT genre AS genre, count(workId) AS workId FROM (SELECT table2.workId AS workId, table2.genre AS genre FROM workspace.composer AS table1 JOIN (SELECT personId AS personId, genre AS genre, workId AS workId FROM workspace.work) AS table2 ON table1.personId=table2.personId WHERE table1.personId='K1')GROUP BY genre ORDER BY workId DESC",new AsyncCallback<List<ComposerGenrePercent>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<ComposerGenrePercent> result) {

		
				List<D3Object2ParameterType2> object = new ArrayList<D3Object2ParameterType2>();
				
				
				for(ComposerGenrePercent s: result)
				{			
					D3Object2ParameterType2 obj = new D3Object2ParameterType2(s.genre,s.counts);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType2(object,"genre","counts");


				displayDataComposerGenrePercent(json);
			
			}
		});
	}



	public static String buildQueryComposerGenre(){

		String c = Srsr.composerBox.getText();

		String query = "SELECT genre AS genre, count(workId) AS workId FROM (SELECT table2.workId AS workId, table2.genre AS genre FROM workspace.composer AS table1 JOIN (SELECT personId AS personId, genre AS genre, workId AS workId FROM workspace.work) AS table2 ON table1.personId=table2.personId WHERE table1.personId='";
		query += c;
		query += "')GROUP BY genre ORDER BY workId DESC";
		System.out.println(query);

		return query;
	}

	public static native void displayDataComposerGenrePercent(String data) /*-{

	var obj = eval(data);
	$wnd.composergenre(obj);	


	}-*/;
}
