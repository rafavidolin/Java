package br.opet.tds172a.veterinarioemcasa.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Reader {

	/**
	 * Atributos da classe Reader
	 */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * 
	 * @return text
	 */
	public static final String readString() {
		String text = scanner.nextLine();
		return text;
	}

	/**
	 * 
	 * @return um inteiro
	 */
	public static final int readInt() {
		String i = scanner.nextLine();
		int ii = 0;
		try {
			ii = Integer.parseInt(i);
		} catch (Exception e) {
			System.out.println("Por favor digite um número válido");
			ii = Reader.readInt();
		}
		return ii;
	}

	/**
	 * 
	 * @return um double
	 */
	public static final double readDouble() {
		String db = scanner.nextLine();
		double dd = 0;
		try {
			dd = Double.parseDouble(db);
		} catch (Exception e) {
			System.out.println("Valor incorreto. Tente novamente");
			dd = Reader.readDouble();
		}
		return dd;
	}

	/**
	 * 
	 * @return um boolean(verdeiro ou falso)
	 */
	public static final boolean readBoolean() {
		String b = scanner.nextLine();
		boolean bb = false;
		try {
			bb = Boolean.parseBoolean(b);
		} catch (Exception e) {
			System.out.println("Valor incorreto. Tente novamente");
			bb = Reader.readBoolean();
		}
		return bb;
	}

	/**
	 * 
	 * @return uma data
	 */
	public static final Date readDate() {
		String d = scanner.nextLine();
		Date dd = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dd = sdf.parse(d);
		} catch (Exception e) {
			System.out.println("Data incorreta. Tente novamente. (dd/MM/yyyy)");
			dd = Reader.readDate();
		}
		return dd;
	}

	/**
	 * 
	 * @return um long
	 */
	public static final long readLong() {
		String l = scanner.nextLine();
		long ll = 0;
		try {
			ll = Long.parseLong(l);
		} catch (Exception e) {
			System.out.println("Por favor digite um número válido");
			ll = Reader.readInt();
		}
		return ll;
	}
}
