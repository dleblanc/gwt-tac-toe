package com.windhorsesoftware.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.*;
import com.windhorsesoftware.tictactoe.*;

@Singleton
public class GwtGameView extends Composite implements GameView {
	interface MyUiBinder extends UiBinder<Widget, GwtGameView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField
	Grid boardGrid; 
	
	// Workaround for circular references - see http://code.google.com/p/google-gin/issues/detail?id=43 (lazy load the presenter via a guice Provider)
	private final Provider<GamePresenter> presenterProvider;
	
	@Inject
	public GwtGameView(Provider<GamePresenter> presenterProvider) {
		this.presenterProvider = presenterProvider;
	}

	public void initialize() {
		
		VerticalPanel container = new VerticalPanel();
		final TextBox rowField = new TextBox();
		rowField.setText("row");
		final TextBox colField = new TextBox();
		colField.setText("col");
		final TextArea boardOutput = new TextArea();
		boardOutput.setWidth("300px");
		boardOutput.setHeight("300px");
		
		final Button applyButton = new Button();
		applyButton.setText("apply");
		
		container.add(rowField);
		container.add(colField);
		container.add(boardOutput);
		container.add(applyButton);

		initWidget(uiBinder.createAndBindUi(this));
		
		addClickHandlersForCells();
	}

	public void cellIsOccupiedWarning(Position position) {
		Window.alert("Cell is occupied!");
	}

	public void gameWasWon(Mark mark) {
		Window.alert("Game won by " + mark.toString());
	}

	public void setCellOccupied(Position position, Mark mark) {
	}

	public void resetView() {
	}

	private void addClickHandlersForCells() {
		// UIHandlers are for binding to one widget - so just iterate over them here
		for (int row = 0; row < boardGrid.getRowCount(); row++) {
			int cellCount = boardGrid.getCellCount(row);
			for (int col = 0; col < cellCount; col++) {
				
				// Working around Java's implementation of anonymous inner classes (and their not being closures) 
				final int thisRow = row;
				final int thisCol = col;
				
				ClickHandler clickHandler = new ClickHandler() {
					public void onClick(ClickEvent event) {
						presenterProvider.get().positionClicked(Position.getPosition(thisRow, thisCol), Mark.X);
					}
				};
				
				((FocusWidget) boardGrid.getWidget(row, col)).addClickHandler(clickHandler);
			}
		}
	}	
}
