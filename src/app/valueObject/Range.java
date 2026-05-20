package app.valueObject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Range {
	private final List<Integer> values;
	private Integer previous;

	public Range(int min, int max, List<Integer> ignore) {
		this.values = IntStream.rangeClosed(min, max)
			   .boxed()
			   .filter(value -> !ignore.contains(value))
			   .collect(Collectors.toList());
		Collections.shuffle(values);
	}
	public Range(int min, int max, Integer ignore) {
		this.values = IntStream.rangeClosed(min, max)
			   .boxed()
			   .filter(integer -> ignore.intValue() != integer.intValue())
			   .collect(Collectors.toList());
		Collections.shuffle(values);
	}
	public Range(int min, int max) {
		this.values = IntStream.rangeClosed(min, max)
			   .boxed()
			   .collect(Collectors.toList());
		Collections.shuffle(values);
	}

	/**
	 * @return Devolve um Range sem replicas de 1 ao 9 (sem nulos)
	 */
	public Integer next() {
		if (values.isEmpty()) {
			throw new IllegalArgumentException("Impossivel gerar uma range com 0 possibildiade de valores!");
		}
		var removed =  values.remove(0);
		previous = removed;
		return removed;
	}
}
