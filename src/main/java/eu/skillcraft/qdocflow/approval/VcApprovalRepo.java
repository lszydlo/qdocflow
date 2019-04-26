package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.DomainEvent;
import eu.skillcraft.qdocflow.shared.kernel.VcId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@AllArgsConstructor
class VcApprovalRepo {

	VcApprovalDaoPort dao;


	@Transactional
	void accept(VcId vcId, Function<ApprovalFacade.VcApproval, DomainEvent> function) {

		VcApprovalRecord record = dao.findById(vcId.getId()).orElse(new VcApprovalRecord(vcId.getId()));

		ApprovalFacade.VcApproval approval = new ApprovalFacade.VcApproval(record);
		DomainEvent event = function.apply(approval);

		record.apply(List.of(event));
		dao.save(record);
	}

	@Entity
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static class VcApprovalRecord {

		@Id
		UUID id;

		@Enumerated(value = EnumType.STRING)
		ApprovalFacade.ApprovalState currentState;

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
		Set<CommentRecord> comments;


		VcApprovalRecord(UUID id) {
			this.id = id;
		}

		void apply(List<DomainEvent> unpublishedEvents) {
			for (DomainEvent unpublishedEvent : unpublishedEvents) {
				if (unpublishedEvent instanceof VcWasSend) {
					currentState = ApprovalFacade.ApprovalState.TO_VERIFICATION;
				} else {
					throw new RuntimeException();
				}
			}
		}
	}

	static class CommentRecord {

	}
}
