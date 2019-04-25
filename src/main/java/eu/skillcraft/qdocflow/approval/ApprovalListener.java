package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.events.ApprovalTimeExpired;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

import javax.transaction.Transactional;

public class ApprovalListener {


	@AllArgsConstructor
	static class RejectionPolicy {


		private final ApprovalFacade facade;

		@EventListener
		@Transactional
		public void listenFor(ApprovalTimeExpired event) {
			facade.handle(new ApprovalCommand.DoAddComment(event.getVcId(),"expired"));
			facade.handle(new ApprovalCommand.DoRejectVc(event.getVcId()));
		}
	}

}
