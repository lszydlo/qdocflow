package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.DomainEvent;
import eu.skillcraft.qdocflow.shared.kernel.VcId;
import lombok.Value;

@Value
public class VcWasSend implements DomainEvent {
	private final VcId vcId;

}
