package eu.skillcraft.qdocflow.approval;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

interface ExpirationPolicy {

	LocalDate calculate();

	@AllArgsConstructor
	class ExpirationPolicyFactory {

		private final CurrentUserPort currentUserPort;

		ExpirationPolicy create() {
			if (currentUserPort.isAuditor()) {
				return new FastTrackExpirationPolicy();
			} else {
				return new DefaultExpirationPolicy();
			}
		}
	}


	class DefaultExpirationPolicy implements ExpirationPolicy {

		@Override
		public LocalDate calculate() {
			return LocalDate.now().plusDays(20);
		}
	}

	class FastTrackExpirationPolicy implements ExpirationPolicy {

		@Override
		public LocalDate calculate() {
			return LocalDate.now().plusDays(21);
		}
	}

}
