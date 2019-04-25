package eu.skillcraft.qdocflow.shared.lib;

import eu.skillcraft.qdocflow.approval.SendToVerificationCommand;

public interface CommandBus {
	void dispatch(Command command);
}
