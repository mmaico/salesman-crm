package br.com.kproj.salesman.infrastructure.helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FormatMoneyHelper {
	private static Map<Language, Locale> locale = new HashMap<Language, Locale>();
	private static String MASK_USD = "USD" + "###,###,##0.00";
	private static String MASK = ((char) 164) + " ###,###,##0.00";
	
	private static String MASK2 = " ###,###,##0.00";
	
	private static Map<Language, String> symbols = new HashMap<Language, String>();
	private static Map<Language, String> masks = new HashMap<Language, String>();
	
	static {
		locale.put(Language.PT, new Locale("pt", "BR"));
		locale.put(Language.EN, Locale.US);
		
		symbols.put(Language.PT, "R$");
		symbols.put(Language.EN, "USD");
		
		masks.put(Language.PT, MASK);
		masks.put(Language.EN, MASK_USD);
	}

    public static String formatMoney(Object value) {
        return formatMoney(Language.PT, value);
    }

    public static String formatMoney2(Object value) {
        return formatMoney2(Language.PT, value);
    }

	public static String formatMoney(Language lang, Object value) {
		
		DecimalFormat df = new DecimalFormat(masks.get(lang), new DecimalFormatSymbols(locale.get(lang)));
		
		if (value == null)
			return df.format(new BigDecimal(0));
		
		return df.format(value);
	}
	
	public static String formatMoney2(Language lang, Object value) {
		
		DecimalFormat df = new DecimalFormat(MASK2, new DecimalFormatSymbols(locale.get(lang)));
		
		if (value == null)
			return df.format(new BigDecimal(0)).trim();
		
		return df.format(value).trim();
	}
	
	public static String getMoneySymbol(Language lang) {
		return symbols.get(lang);
	}
	
	public static Long formatMoneyToLong(Object value) {
		
		DecimalFormat df = new DecimalFormat(MASK2);
		
		if (value == null)
			return 1l;
		
		String valuesFixed = df.format(value).replaceAll("[.,\\s]", "");
		
		return Long.valueOf(valuesFixed);
	}
	
	
}
