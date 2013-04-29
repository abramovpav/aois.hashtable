package by.bsuir.iit.abramov.aois.hashtable;

import java.io.IOException;

import by.bsuir.iit.abramov.aois.hashtable.model.HashTable;

public class Start {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {

		final HashTable table = new HashTable();
		/*
		 * while(true) { byte [] b = new byte [10]; System.in.read(b); char [] c
		 * = new char [10]; for (int i = 0; i < 10; ++i) { c[i] = (char) b[i]; }
		 * String str = String.copyValueOf(c); System.out.println(str);
		 * System.out.println(table.hash(str.hashCode(), table.getCapacity()));
		 * }
		 */

		table.put("lol", 5);
		table.printTable();
		table.put("lol", 6);
		table.printTable();
		table.put("lol1", 3);
		table.printTable();
		table.put("lol2", 2);
		table.printTable();
		table.put("zxvbkdfhsjg41231", 4);
		table.printTable();
		table.put("kristina", 4);
		table.printTable();
		table.put("lol3", 4);
		table.printTable();
		table.put("kristina", 6);
		table.printTable();
		table.remove("lol1");
		table.printTable();
		table.remove("lol");
		table.printTable();
		table.put("lol1", 6);
		table.printTable();
		System.out.println(table.get("lol1"));
		// table.put("asd", 4);
		// table.put("plkmjkl546", 4);

		// table.put("dsaf3y8hjnkafsd", 4);

		//

	}

}
