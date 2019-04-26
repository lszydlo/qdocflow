package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.VcId;

import java.util.function.Consumer;

class VcApprovalRepo {
	private ApprovalFacade.VcApproval load(VcId vcId) {
		return new ApprovalFacade.VcApproval(vcId);
	}

	private void save(ApprovalFacade.VcApproval vcApproval) {

	}

	public void accept(VcId vcId, Consumer<ApprovalFacade.VcApproval> consumer) {
		ApprovalFacade.VcApproval approval = load(vcId);
		consumer.accept(approval);
		save(approval);
	}
}
