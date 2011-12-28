package com.windhorsesoftware.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.windhorsesoftware.tictactoe.GameView;

class GameGuiceModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(GameView.class).to(GwtGameView.class);
	}
}