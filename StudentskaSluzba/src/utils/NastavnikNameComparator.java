package utils;

import java.util.Comparator;

import model.Nastavnik;

public class NastavnikNameComparator implements Comparator<Nastavnik> {

	int direction = 1;

	public NastavnikNameComparator(int direction) {
		if (direction != 1 && direction != -1) {
			direction = 1;
		}
		this.direction = direction;
	}

	@Override
	public int compare(Nastavnik o1, Nastavnik o2) {
		int retVal = 0;
		if (o1 != null && o2 != null) {
			retVal = o1.getIme().compareTo(o2.getIme());
		}
		return retVal * direction;
	}

}
