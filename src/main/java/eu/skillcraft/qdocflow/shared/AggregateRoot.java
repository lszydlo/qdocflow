package eu.skillcraft.qdocflow.shared;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {

	private final List<DomainEvent> unpublishedEvents = new ArrayList<>();


	protected void emit(DomainEvent event) {
		unpublishedEvents.add(event);
	}

}
