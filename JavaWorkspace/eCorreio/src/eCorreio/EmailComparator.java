package eCorreio;

import java.util.Comparator;

public class EmailComparator implements Comparator<Email> {

	@Override
	public int compare(Email e1, Email e2) {
		if (e1.getDate().equals(e2.getDate())) {
			if (e1.getSubject().equals(e2.getSubject())) {
				if (e1.getAddress().equals(e2.getAddress())) {
					return 0;
				} else {
					return (e1.getAddress().compareTo(e2.getAddress()));
				}
			} else {
				return (e1.getSubject().compareTo(e2.getSubject()));
			}
		} else {
			return (e1.getDate().compareTo(e2.getDate()));
		}
	}

}
