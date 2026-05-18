package app.entity;

import java.util.List;

public abstract class BaseEntity<T>{
	protected final List<Rule<T>> rules;

	protected BaseEntity(List<Rule<T>> rules) {
		this.rules = rules;
	}

	public boolean hasViolations() {
		for (var rule : rules) {
			if (!rule.apply())
				return true;
		}


		return false;
	}
	protected void addRule(Rule<T> rule) {
		rules.add(rule);
	}

}
