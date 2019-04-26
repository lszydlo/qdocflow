package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoAddComment;
import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoRejectVc;
import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoRequestVcPublication;
import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoSendVcToVerification;
import eu.skillcraft.qdocflow.shared.kernel.VcId;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import static eu.skillcraft.qdocflow.approval.ApprovalFacade.ApprovalState.TO_VERIFICATION;

@AllArgsConstructor
public class ApprovalFacade {


	private final VcApprovalRepo repo;
	private final ExpirationPolicy.ExpirationPolicyFactory policyFactory;


	public void handle(DoSendVcToVerification command) {

		LocalDate expirationDate = policyFactory.create().calculate();
		repo.accept(command.getVcId(), vcApproval -> vcApproval.sendToVerification(expirationDate));


	}

	public void handle(DoRejectVc command) {

	}

	public void handle(DoRequestVcPublication command) {

	}

	public void handle(DoAddComment command) {

	}

	static class VcApproval {

		private ApprovalState currentState;

		VcApproval(VcId vcId) {

		}

		void sendToVerification(LocalDate exprationDate) {
			if (TO_VERIFICATION.isNot(currentState)) {
				throw new RuntimeException();
			} else {
				currentState = TO_VERIFICATION;
			}
		}
	}

	enum ApprovalState {
		TO_VERIFICATION, VERIFIED, REJECTED, APPROVED;

		public boolean isNot(ApprovalState state) {
			return !this.equals(state);
		}
	}
}
