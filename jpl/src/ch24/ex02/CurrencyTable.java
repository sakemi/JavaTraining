package ch24.ex02;

import java.util.Currency;
import java.util.Locale;

public class CurrencyTable {
	public static void main(String[] args) {
		Locale[] locales = { new Locale("ja", "JP"), new Locale("de", "DE"), new Locale("it", "IT"),
				new Locale("en", "US"), new Locale("zh", "CN"), new Locale("en", "AU") };
		System.out.printf("   %3s %3s %3s %3s %3s %3s%n", "JP", "DE", "IT", "US", "CN", "AU");
		for (int i = 0; i < locales.length; i++) {
			Locale.setDefault(locales[i]);
			System.out.printf("%3s ", locales[i].getCountry());
			for (int j = 0; j < locales.length; j++) {
				System.out.printf("%3s ", Currency.getInstance(locales[j]).getSymbol());
			}
			System.out.println();
		}
	}
}
