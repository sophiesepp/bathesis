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
import com.sophiesepp.client.TimeDownloadsQuery.MyPopup;
import com.sophiesepp.shared.D3Object2ParameterType1;
import com.sophiesepp.shared.D3Object2ParameterType3;
import com.sophiesepp.shared.TimeDownloads;
import com.sophiesepp.shared.TimeDownloadsNormalized;

public class TimeDownloadsQuery extends D3 implements EntryPoint  {
	

	static VerticalPanel timedownloadsPanel = new VerticalPanel();
	
	static HorizontalPanel timedownloadscontentPanel = new HorizontalPanel();
	HorizontalPanel lefttimedownloadsPanel = new HorizontalPanel();
	VerticalPanel righttimedownloadsPanel = new VerticalPanel();
	VerticalPanel seperationPanel = new VerticalPanel();
	

	  public static class MyPopup extends PopupPanel {

		    public MyPopup() {
		      // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
		      // If this is set, the panel closes itself automatically when the user
		      // clicks outside of it.
		      super(true);
		      
		      // PopupPanel is a SimplePanel, so you have to set it's widget property to
		      // whatever you want its contents to be.
		      
		      
		    }
		  }


	

	public void onModuleLoad() {
		
	

		 final Button b1 = new Button("About");
		    b1.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        final MyPopup g = new MyPopup();
		
		        g.setWidget(RootPanel.get("righttimedownloadsPanel1"));
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
			
			        g.setWidget(RootPanel.get("righttimedownloadsPanel2"));
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

			  



		
		timedownloadsPanel.add(RootPanel.get("heading1"));
		timedownloadsPanel.add(timedownloadscontentPanel);
		timedownloadsPanel.add(seperationPanel);
		
		timedownloadscontentPanel.add(lefttimedownloadsPanel);
		timedownloadscontentPanel.add(righttimedownloadsPanel);
		
		lefttimedownloadsPanel.add(RootPanel.get("linechartTimeDownloads"));
		RootPanel.get("linechartTimeDownloads").add(b1);
		righttimedownloadsPanel.add(RootPanel.get("linechartTimeDownloadsNormalized"));
		RootPanel.get("linechartTimeDownloadsNormalized").add(b2);
	
		
			
		timedownloadsPanel.addStyleName("timedownloadsPanel");
		timedownloadscontentPanel.addStyleName("timedownloadscontentPanel");
		lefttimedownloadsPanel.addStyleName("lefttimedownloadsPanel");
		seperationPanel.addStyleName("seperationPanel");
		b1.addStyleName("informationbutton");
		b2.addStyleName("informationbutton");

	
	}

	public static void queryTimeDownloads() {

		greetingService.showQueryTimeDownloads(buildQueryTimeDownloads(),new AsyncCallback<List<TimeDownloads>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<TimeDownloads> result) {

				List<D3Object2ParameterType1> object = new ArrayList<D3Object2ParameterType1>();
				
				
				for(TimeDownloads s: result)
				{			
					D3Object2ParameterType1 obj = new D3Object2ParameterType1(s.publication,s.downloads);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType1(object,"publication","downloads");


				displayDataTimeDownloads(json);
			}
		});
	}
	
	public static void queryTimeDownloadsNormalized() {

		greetingService.showQueryTimeDownloadsNormalized(buildQueryTimeDownloadsNormalized(),new AsyncCallback<List<TimeDownloadsNormalized>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<TimeDownloadsNormalized> result) {

				List<D3Object2ParameterType1> object = new ArrayList<D3Object2ParameterType1>();
				
				
				for(TimeDownloadsNormalized s: result)
				{			
					D3Object2ParameterType1 obj = new D3Object2ParameterType1(s.publication,s.percent);	
					object.add(obj);
					
				}
				
				String json = createJson2ParameterType1(object,"publication","downloads");


				displayDataTimeDownloadsNormalized(json);
			}
		});
	}





	public static String buildQueryTimeDownloads(){

		String query ="SELECT publication AS publication,sum(downloads) AS downloads FROM workspace.work GROUP BY publication ORDER BY publication ASC";
		return query;
	}
	
	public static String buildQueryTimeDownloadsNormalized (){

		String query ="SELECT publication AS publication,INTEGER(sum(downloads)/count(publication)) AS downloads FROM workspace.work GROUP BY publication ORDER BY publication ASC";
		return query;
	}



	public static native void displayDataTimeDownloads(String data) /*-{

	var obj = eval(data);
	$wnd.timedownloads(obj);	

	}-*/;	
	
	public static native void displayDataTimeDownloadsNormalized (String data) /*-{

	var obj = eval(data);
	$wnd.timedownloadsNormalized(obj);	



	}-*/;

}
