package interfaces;

import model.Miflaga;

public interface ElectionViewable {

	void registerListener(ElectionUiListenable l);
	void updateMiflagot(Miflaga miflaga);
}
