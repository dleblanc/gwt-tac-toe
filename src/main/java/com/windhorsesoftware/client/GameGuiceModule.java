package com.windhorsesoftware.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.windhorsesoftware.tictactoe.GameView;

class GameGuiceModule extends AbstractGinModule {
	@Override
	protected void configure() {
		// For brevity sake, just wire everything up as singleton
		bind(GameView.class).to(GwtGameView.class); //.in(Singleton.class);
		//bind(GamePresenter.class).in(Singleton.class);
	}
}