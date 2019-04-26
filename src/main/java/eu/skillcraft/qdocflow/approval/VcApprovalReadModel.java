package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.VcId;

import java.util.List;

public class VcApprovalReadModel {

	VcId id;
	ApprovalFacade.ApprovalState state;
	List<String> comments;

	public VcApprovalReadModel(VcApprovalRepo.VcApprovalRecord vcApprovalRecord) {
		this.id = new VcId(vcApprovalRecord.id);
	}
}
