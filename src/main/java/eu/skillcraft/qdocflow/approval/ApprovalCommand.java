package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.VcId;
import eu.skillcraft.qdocflow.shared.lib.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public abstract class ApprovalCommand implements Command {

	protected VcId vcId;

	public static class DoSendVcToVerification extends ApprovalCommand {
		public DoSendVcToVerification(VcId vcId) {
			super(vcId);
		}
	}

	public static class DoRejectVc extends ApprovalCommand {
		public DoRejectVc(VcId vcId) {
			super(vcId);
		}
	}

	public static class DoRequestVcPublication extends ApprovalCommand {
		public DoRequestVcPublication(VcId vcId) {
			super(vcId);
		}
	}

	@Data
	@EqualsAndHashCode(callSuper = true)
	public static class DoAddComment extends ApprovalCommand {
		private String commentContent;

		public DoAddComment(VcId vcId, String commentContent) {
			super(vcId);
			this.commentContent = commentContent;
		}
	}



}
