package com.windhorsesoftware.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;
import com.windhorsesoftware.tictactoe.GameView;

public class MvnGwtSample implements EntryPoint {

	public void onModuleLoad() {
		final GameGinjector injector = GWT.create(GameGinjector.class);
		
		GameView gameView = injector.getGameView();
		gameView.initialize();
		
	    RootPanel.get().add((Widget) gameView);
	}
}
