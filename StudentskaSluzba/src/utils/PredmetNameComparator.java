package utils;

import java.util.Comparator;

import model.Predmet;

public class PredmetNameComparator implements Comparator<Predmet> {

	int direction = 1;

	public PredmetNameComparator(int direction) {
		if (direction != 1 && direction != -1) {
			direction = 1;
		}
		this.direction = direction;
	}

	@Override
	public int compare(Predmet p1, Predmet p2) {
		int retVal = 0;
		if (p1 != null && p2 != null) {
			retVal = p1.getNaziv().compareTo(p2.getNaziv());
		}
		return retVal * direction;
	}

}
