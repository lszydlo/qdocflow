package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoAddComment;
import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoRejectVc;
import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoRequestVcPublication;
import eu.skillcraft.qdocflow.approval.ApprovalCommand.DoSendVcToVerification;
import eu.skillcraft.qdocflow.shared.DomainEvent;
import eu.skillcraft.qdocflow.shared.kernel.VcId;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static eu.skillcraft.qdocflow.approval.ApprovalFacade.ApprovalState.TO_VERIFICATION;

@AllArgsConstructor
public class ApprovalFacade  {


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

	static class VcApproval  {

		private final VcId vcId;
		private final ApprovalState currentState;
		private final Comments comments;



		VcApproval(VcApprovalRepo.VcApprovalRecord record) {
			this.vcId = new VcId(record.id);
			this.currentState = record.currentState;
			this.comments = new Comments(record.comments);
		}

		DomainEvent sendToVerification(LocalDate exprationDate) {
			if (TO_VERIFICATION.isNot(currentState)) {
				throw new RuntimeException();
			} else {
				return new VcWasSend(vcId);
			}
		}

		private class Comments {
			private List<String> comments;

			private Comments(Set<VcApprovalRepo.CommentRecord> comments) {

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
