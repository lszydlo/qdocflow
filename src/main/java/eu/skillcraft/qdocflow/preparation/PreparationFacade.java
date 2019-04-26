package eu.skillcraft.qdocflow.preparation;

public class PreparationFacade {


		void handle() {

			String s = numberPolicy();
			new Qdoc(s);

		}

	private String numberPolicy() {
		return "PJ/123/321";
	}

	private class Qdoc {
		private String s;

		Qdoc(String s) {
			this.s = s;
		}
	}
}
