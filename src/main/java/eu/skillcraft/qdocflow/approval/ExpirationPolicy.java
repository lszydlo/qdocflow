package eu.skillcraft.qdocflow.approval;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

interface ExpirationPolicy {

	ExpirationPolicy DEFAULT = () -> LocalDate.now().plusDays(20);
	ExpirationPolicy FAST_TRACK = () -> LocalDate.now().plusDays(2);

	LocalDate calculate();

	@AllArgsConstructor
	class ExpirationPolicyFactory {

		private final CurrentUserPort currentUserPort;

		ExpirationPolicy create() {
			if (currentUserPort.isAuditor()) {
				return FAST_TRACK;
			} else {
				return DEFAULT;
			}
		}
	}

}
