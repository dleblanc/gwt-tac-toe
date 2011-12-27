package com.windhorsesoftware.tictactoe;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class MarkTest {
	
	@Test
	public void getOpposingPlayerForOShouldReturnX() throws Exception {
		assertThat(Mark.O.getOpposingPlayer(), equalTo(Mark.X));
	}

	@Test
	public void getOpposingPlayerForXShouldReturnO() throws Exception {
		assertThat(Mark.X.getOpposingPlayer(), equalTo(Mark.O));
	}

	@Test(expected=RuntimeException.class)
	public void getOpposingPlayerForEmptyShouldThrowException() throws Exception {
		Mark.EMPTY.getOpposingPlayer();
	}
	
}
