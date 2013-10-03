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
import com.sophiesepp.shared.D3Object2ParameterType1;
import com.sophiesepp.shared.WorkScorePercent;

public class WorkScorePercentQuery extends D3 implements EntryPoint{
	

	


	static VerticalPanel worksPanel = new VerticalPanel();
	static HorizontalPanel workscontentPanel = new HorizontalPanel();
	VerticalPanel workscorePanel = new VerticalPanel();
	VerticalPanel workscorePanel1 = new VerticalPanel();
	VerticalPanel workscorePanel2 = new VerticalPanel();
	VerticalPanel workdownloadsPanel = new VerticalPanel();
	VerticalPanel workdownloadsPanel1 = new VerticalPanel();
	VerticalPanel workdownloadsPanel2 = new VerticalPanel();



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
		        g.setWidget(RootPanel.get("rightworkscorePanel"));
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
		        g.setWidget(RootPanel.get("rightworksdownloadsPanel"));
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


		
		worksPanel.addStyleName("worksPanel");
		workscontentPanel.addStyleName("workscontentPanel");
		workscorePanel.addStyleName("workscorePanel");
		workdownloadsPanel.addStyleName("workdownloadsPanel");
		b1.addStyleName("informationbutton");
		b2.addStyleName("informationbutton");
	
		worksPanel.add(workscontentPanel);
		
		workscontentPanel.add(workscorePanel);
		workscontentPanel.add(workdownloadsPanel);
		
		workscorePanel.add(workscorePanel1);
		workscorePanel.add(workscorePanel2);
		workscorePanel1.add(RootPanel.get("heading9"));
		workscorePanel2.add(RootPanel.get("barchartWorkScore"));
		workscorePanel.add(b1);
		workdownloadsPanel.add(workdownloadsPanel1);
		workdownloadsPanel.add(workdownloadsPanel2);
		workdownloadsPanel1.add(RootPanel.get("heading10"));
		workdownloadsPanel2.add(RootPanel.get("barchartWorkDownloads"));
		workdownloadsPanel.add(b2);
		
	
		
		
	}
	

	public static void queryWorkScorePercent() {

		greetingService.showQueryWorkScorePercent(buildQueryWorkScorePercent(),new AsyncCallback<List<WorkScorePercent>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<WorkScorePercent> result) {
				
				List<D3Object2ParameterType1> object = new ArrayList<D3Object2ParameterType1>();
				
				
				for(WorkScorePercent s: result)
				{			
					D3Object2ParameterType1 obj = new D3Object2ParameterType1(s.scores,s.works);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType1(object,"scores","works");



				displayDataWorkScorePercent(json);
			}
		});
	}
	


	public static String buildQueryWorkScorePercent(){


		String query = "SELECT counts AS scores, count(workId) AS works FROM (SELECT table1.workId AS workId,count(table2.scoreId) AS counts FROM workspace.work AS table1 JOIN (SELECT scoreId AS scoreId, workId AS workId FROM workspace.score) AS table2 ON table1.workId=table2.workId GROUP BY workId) GROUP BY scores ORDER BY works ASC";

		System.out.println(query);

		return query;
	}


	public static native void displayDataWorkScorePercent(String data) /*-{

	var obj = eval(data);
	$wnd.workscore(obj);	



	}-*/;


}
