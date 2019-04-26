package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.VcId;
import lombok.Data;

@Data
public class FindApprovalForId {
	private VcId vcId;

	public FindApprovalForId(VcId vcId) {
		this.vcId = vcId;
	}
}
