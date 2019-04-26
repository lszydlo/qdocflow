package eu.skillcraft.qdocflow.approval;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface VcApprovalDaoPort extends JpaRepository<VcApprovalRepo.VcApprovalRecord, UUID> {
}
