package com.windhorsesoftware.client;

import com.google.gwt.inject.client.*;
import com.windhorsesoftware.tictactoe.GameView;

@GinModules(GameGuiceModule.class)
public interface GameGinjector extends Ginjector {
	GameView getGameView();
}
