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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sophiesepp.client.MostUsedNgramsQuery.MyPopup;
import com.sophiesepp.shared.D3Object3Parameter;
import com.sophiesepp.shared.MostUsedNgrams;

public class MostUsedNgramsQuery extends D3 implements EntryPoint {
	



	static VerticalPanel mostusedngramsPanel = new VerticalPanel();
	static HorizontalPanel mostusedngramscontentPanel = new HorizontalPanel();
	VerticalPanel leftmostusedngramsPanel = new VerticalPanel();
	VerticalPanel rightmostusedngramsPanel = new VerticalPanel();
	HorizontalPanel mostusedngramsPanel1= new HorizontalPanel(); 


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
		        g.setWidget(RootPanel.get("rightmostusedngramsPanel"));
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


		mostusedngramsPanel.addStyleName("mostusedngramsPanel");
		mostusedngramscontentPanel.addStyleName("mostusedngramscontentPanel");
		leftmostusedngramsPanel.addStyleName("leftmostusedngramsPanel");
		rightmostusedngramsPanel.addStyleName("rightmostusedngramsPanel");
		mostusedngramsPanel1.addStyleName("mostusedngramsPanel1");
		b1.addStyleName("informationbutton");
		
		mostusedngramsPanel.add(RootPanel.get("heading13"));
		mostusedngramsPanel.add(mostusedngramscontentPanel);
		mostusedngramscontentPanel.add(leftmostusedngramsPanel);
		mostusedngramscontentPanel.add(rightmostusedngramsPanel);
		
		leftmostusedngramsPanel.add(mostusedngramsPanel1);
		leftmostusedngramsPanel.add(b1);
		mostusedngramsPanel1.add(RootPanel.get("multilinechartMostUsedNgrams"));

	


	}
	
	public static void queryMostUsedNgrams() {

		greetingService.showQueryMostUsedNgrams(buildQueryMostUsedNgrams(),new AsyncCallback<List<MostUsedNgrams>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<MostUsedNgrams> result) {

				List<D3Object3Parameter> object = new ArrayList<D3Object3Parameter>();
				
				
				for(MostUsedNgrams s: result)
				{			
					D3Object3Parameter obj = new D3Object3Parameter(s.publication,s.ngram,s.counts);	
					object.add(obj);
					
				}
				
				String json = createJson3Parameter(object,"publication","ngram","counts");


				displayDataMostUsedNgrams(json);
			}
		});
	}
	

	
	public static String buildQueryMostUsedNgrams(){

		String query = "SELECT ngram AS ngram, count(ngram) AS counts FROM workspace.ngram GROUP BY ngram ORDER BY counts DESC LIMIT 10";
		return query;
	}


	public static native void displayDataMostUsedNgrams(String data) /*-{

	var obj = eval(data);
	$wnd.mostusedngrams(obj);	



	}-*/;	



}
