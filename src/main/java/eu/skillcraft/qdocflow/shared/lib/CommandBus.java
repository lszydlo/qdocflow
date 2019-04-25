package eu.skillcraft.qdocflow.shared.lib;

public interface CommandBus {
	void dispatch(Command command);
}
