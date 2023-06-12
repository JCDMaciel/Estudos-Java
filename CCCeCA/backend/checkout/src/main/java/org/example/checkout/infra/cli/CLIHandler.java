package org.example.checkout.infra.cli;

import java.util.HashMap;
import java.util.Map;

public abstract class CLIHandler {
	private Map<String, Function> commands = new HashMap<>();

	public void on(String command, Function callback) {
		commands.put(command, callback);
	}

	public void type(String text) {
		String[] parts = text.split(" ");
		String command = parts[0];
		if (!commands.containsKey(command))
			return;
		String params = text.replace(command, "").trim();
		commands.get(command).apply(params);
	}

	public abstract void write(String text);

	public interface Function {
		void apply(String params);
	}
}
