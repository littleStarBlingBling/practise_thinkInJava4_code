package com.practise.annotations;

import com.practise.annotations.atunit.test.Test;

public class Testable {
	public void execute() {
		System.out.println("Executing..");
	}

	@Test
	void testExecute() {
		execute();
	}
}
