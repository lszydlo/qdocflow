package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.VcId;
import lombok.Value;

@Value
class ApprovalTimeExpired {

	private final VcId vcId;
}
