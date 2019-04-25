package eu.skillcraft.qdocflow.approval;

import eu.skillcraft.qdocflow.shared.kernel.VcId;
import eu.skillcraft.qdocflow.shared.lib.Command;
import lombok.Data;

@Data
public class SendToVerificationCommand implements Command {

	private VcId vcId;
}
