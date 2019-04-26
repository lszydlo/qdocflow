package eu.skillcraft.qdocflow.approval;

import java.util.Optional;
import java.util.UUID;

interface VcApprovalDaoPort {
	Optional<VcApprovalRepo.VcApprovalRecord> findById(UUID id);

	void save(VcApprovalRepo.VcApprovalRecord record);
}
