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
import com.sophiesepp.client.MostUsedGenresQuery.MyPopup;
import com.sophiesepp.shared.D3Object3Parameter;
import com.sophiesepp.shared.MostUsedGenres;

public class MostUsedGenresQuery extends D3 implements EntryPoint {
	

	static VerticalPanel mostusedgenresPanel = new VerticalPanel();
	static HorizontalPanel mostusedgenrescontentPanel = new HorizontalPanel();
	VerticalPanel leftmostusedgenresPanel = new VerticalPanel();
	HorizontalPanel mostusedgenresPanel1= new HorizontalPanel(); 
	VerticalPanel rightmostusedgenresPanel = new VerticalPanel();
	
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
		        g.setWidget(RootPanel.get("rightmostusedgenresPanel"));
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


		mostusedgenresPanel.addStyleName("mostusedgenresPanel");
		mostusedgenrescontentPanel.addStyleName("mostusedgenrescontentPanel");
		leftmostusedgenresPanel.addStyleName("leftmostusedgenresPanel");
		rightmostusedgenresPanel.addStyleName("rightmostusedgenresPanel");
		mostusedgenresPanel1.addStyleName("mostusedgenresPanel1");
		b1.addStyleName("informationbutton");
		
		mostusedgenresPanel.add(RootPanel.get("heading12"));
		mostusedgenresPanel.add(mostusedgenrescontentPanel);
		mostusedgenrescontentPanel.add(leftmostusedgenresPanel);
		mostusedgenrescontentPanel.add(rightmostusedgenresPanel);
		
		leftmostusedgenresPanel.add(mostusedgenresPanel1);
		leftmostusedgenresPanel.add(b1);
		mostusedgenresPanel1.add(RootPanel.get("multilinechartMostUsedGenres"));

	
	}

	public static void queryMostUsedGenres() {

		greetingService.showQueryMostUsedGenres(buildQueryMostUsedGenres(),new AsyncCallback<List<MostUsedGenres>>(){
			public void onFailure(Throwable caught) {


			}

			public void onSuccess(List<MostUsedGenres> result) {
				
				List<D3Object3Parameter> object = new ArrayList<D3Object3Parameter>();
				
				
				for(MostUsedGenres s: result)
				{			
					D3Object3Parameter obj = new D3Object3Parameter(s.publication,s.genre,s.counts);	
					object.add(obj);
					
				}
				
				String json = createJson3Parameter(object,"publication","genre","counts");

				displayDataMostUsedGenres(json);
			}
		});
	}


	public static String buildQueryMostUsedGenres(){

		String query = "SELECT genre AS genre, count(genre) AS counts FROM workspace.work GROUP BY genre ORDER BY counts DESC LIMIT 10";
		return query;
	}

	
	public static native void displayDataMostUsedGenres(String data) /*-{

	var obj = eval(data);
	$wnd.mostusedgenres(obj);	



	}-*/;
	
	

}


