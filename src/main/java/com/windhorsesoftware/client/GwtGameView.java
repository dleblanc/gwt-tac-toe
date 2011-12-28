package com.windhorsesoftware.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.*;
import com.windhorsesoftware.tictactoe.*;

@Singleton
public class GwtGameView extends Composite implements GameView {
	
	// Workaround for circular references - see http://code.google.com/p/google-gin/issues/detail?id=43
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

		initWidget(container);
		
		applyButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int row = Integer.parseInt(rowField.getText());
				int col = Integer.parseInt(colField.getText());
				
				GamePresenter presenter = presenterProvider.get();
				
				presenter.positionClicked(Position.getPosition(row, col), Mark.X);
				
				boardOutput.setText(presenter.getBoard().toString());
			}
		});

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
}
