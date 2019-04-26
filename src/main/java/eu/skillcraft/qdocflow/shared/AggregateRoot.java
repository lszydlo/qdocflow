package eu.skillcraft.qdocflow.shared;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class AggregateRoot {

	@Transient
	private final List<DomainEvent> unpublishedEvents = new ArrayList<>();


	protected void emit(DomainEvent event) {
		unpublishedEvents.add(event);
	}

	public List<DomainEvent> getUnpublishedEvents() {
		return unpublishedEvents;
	}
}
