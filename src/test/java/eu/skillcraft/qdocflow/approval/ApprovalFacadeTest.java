package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.VcId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ApprovalFacadeTest {


	@Test
	public void second_sending_should_not_change_state() {

		// Given
		ApprovalFacade approvalFacade = getApprovalFacade();
		VcId vcId = new VcId(UUID.randomUUID());
		approvalFacade.handle(new ApprovalCommand.DoSendVcToVerification(vcId));

		// When
		approvalFacade.handle(new ApprovalCommand.DoSendVcToVerification(vcId));

		// Then
		VcApprovalReadModel approval =  approvalFacade.execute(new FindApprovalForId(vcId));
		assertThat(approval).isNotNull();

	}

	private ApprovalFacade getApprovalFacade() {
		DummyDao dao = new DummyDao();
		return new ApprovalFacade(new VcApprovalRepo(dao, new InMemeoryEventStore()), dao ,new ExpirationPolicy.ExpirationPolicyFactory(() -> false));
	}

	private class DummyDao implements VcApprovalDaoPort, VcApprovalFinder{

		ConcurrentHashMap<UUID, VcApprovalRepo.VcApprovalRecord> map = new ConcurrentHashMap<>();

		@Override
		public Optional<VcApprovalRepo.VcApprovalRecord> findById(UUID id) {

			return Optional.ofNullable(map.get(id));
		}

		@Override
		public void save(VcApprovalRepo.VcApprovalRecord record) {
			map.put(record.id, record);
		}

		@Override
		public VcApprovalReadModel findById(FindApprovalForId findApprovalForId) {
			VcApprovalRepo.VcApprovalRecord vcApprovalRecord = map.get(findApprovalForId.getVcId().getId());
			return new VcApprovalReadModel(vcApprovalRecord);
		}
	}
}