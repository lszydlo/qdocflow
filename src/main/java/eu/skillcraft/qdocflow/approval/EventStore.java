package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.DomainEvent;

import java.util.List;

public interface EventStore {
	void append(List<DomainEvent> event);

}
