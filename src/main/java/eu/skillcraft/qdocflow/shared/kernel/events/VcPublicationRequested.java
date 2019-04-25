package eu.skillcraft.qdocflow.shared.kernel.events;

import eu.skillcraft.qdocflow.shared.kernel.VcId;
import lombok.Value;

@Value
public class VcPublicationRequested {

	private final VcId vcId;
}
