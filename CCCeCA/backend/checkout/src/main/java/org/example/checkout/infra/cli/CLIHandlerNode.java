package org.example.checkout.infra.cli;

import java.util.Scanner;

public class CLIHandlerNode extends CLIHandler {

	public CLIHandlerNode() {
		super();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String command = scanner.nextLine().trim();
			type(command);
		}
	}

	@Override
	public void write(String text) {
		System.out.println(text);
	}
}
