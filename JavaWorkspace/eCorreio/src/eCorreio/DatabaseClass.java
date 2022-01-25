package eCorreio;

import java.util.*;
import exceptions.*;

public class DatabaseClass implements Database {
	// address
	private Map<String, Email> sThird;
	private Map<String, Email> rThird;
	// subject
	private Map<String, Map<String, Email>> sSecond;
	private Map<String, Map<String, Email>> rSecond;
	// date / subject / address
	private Map<String, Map<String, Map<String, Email>>> sentEmails;
	private Map<String, Map<String, Map<String, Email>>> receivedEmails;
	private Comparator<? super Email> c;

	public DatabaseClass() {
		sentEmails = new HashMap<String, Map<String, Map<String, Email>>>();
		receivedEmails = new HashMap<String, Map<String, Map<String, Email>>>();
		sThird = new HashMap<>();
		sSecond = new HashMap<>();
		rThird = new HashMap<>();
		rSecond = new HashMap<>();
		c = new EmailComparator();
	}

	@Override
	public void send(String address, String date, String subject, String text) throws DuplicateMessageException {
		if (hasSentEmail(address, date, subject)) {
			throw new DuplicateMessageException();
		} else {
			Email e = new EmailClass(address, date, subject, text);
			sThird.put(address, e);
			sSecond.put(subject, sThird);
			sentEmails.put(date, sSecond);
		}
	}

	@Override
	public void receive(String address, String date, String subject, String text) throws DuplicateMessageException {
		if (hasReceivedEmail(address, date, subject)) {
			throw new DuplicateMessageException();
		} else {
			Email e = new EmailClass(address, date, subject, text);
			rThird.put(address, e);
			rSecond.put(subject, rThird);
			receivedEmails.put(date, rSecond);
		}
	}

	@Override
	public boolean hasSentEmail(String address, String date, String subject) {
		if (sentEmails.containsKey(date)) {
			if (sSecond.containsKey(subject)) {
				if (sThird.containsKey(address)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasReceivedEmail(String address, String date, String subject) {
		if (receivedEmails.containsKey(date)) {
			if (rSecond.containsKey(subject)) {
				if (rThird.containsKey(address)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasSubject(String subject) {
		return (sSecond.containsKey(subject) || rSecond.containsKey(subject));
	}

	@Override
	public boolean hasAddress(String address) {
		return (sThird.containsKey(address) || rThird.containsKey(address));
	}

	@Override
	public Iterator<Email> listSentMessages() {
		ArrayList<Email> emails = new ArrayList<Email>();
		emails.addAll(sThird.values());
		emails.sort(c);
		return emails.iterator();
	}

	@Override
	public Iterator<Email> listReceivedMessages() {
		ArrayList<Email> emails = new ArrayList<Email>();
		emails.addAll(rThird.values());
		emails.sort(c);
		return emails.iterator();
	}

	@Override
	public Iterator<Email> listSubjectMessages(String subject) throws ListEmptyException {
		ArrayList<Email> emails = new ArrayList<Email>();

		Map<String, Email> stmp;
		Map<String, Email> rtmp;

		if (sSecond.containsKey(subject)) {
			stmp = sSecond.get(subject);
			emails.addAll(stmp.values());
		}
		if (rSecond.containsKey(subject)) {
			rtmp = rSecond.get(subject);
			emails.addAll(rtmp.values());
		}

		if (emails.isEmpty()) {
			throw new ListEmptyException();
		} else {
			return emails.iterator();
		}
	}

	@Override
	public Iterator<Email> listEmailMessages(String address) throws ListEmptyException {
		ArrayList<Email> emails = new ArrayList<Email>();

		if (sThird.containsKey(address))
			emails.add(sThird.get(address));
		if (rThird.containsKey(address))
			emails.add(rThird.get(address));

		if (emails.isEmpty()) {
			throw new ListEmptyException();
		} else {
			return emails.iterator();
		}
	}

	@Override
	public Iterator<String> listSubjects() {
		ArrayList<String> subjects = new ArrayList<String>();

		subjects.addAll(rSecond.keySet());
		subjects.addAll(sSecond.keySet());
		subjects.sort(String::compareToIgnoreCase);

		return subjects.iterator();
	}

}
