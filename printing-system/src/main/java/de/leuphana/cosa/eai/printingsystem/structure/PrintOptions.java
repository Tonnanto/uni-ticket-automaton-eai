package de.leuphana.cosa.eai.printingsystem.structure;

public class PrintOptions {

	public int getNumberOfPages() {
		// TODO change hard coded value
		return 20;
	}

	public double getPricePerPage() {
		// TODO change hard coded value
		return 0.02;
	}

	public double getTotalPrice() {
		return getPricePerPage() * getNumberOfPages();
	}

}
