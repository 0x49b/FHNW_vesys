package ch.fhnw.ds.internet.currency;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

// uses the currency converter provided at https://www.calculator.net/currency-calculator.html
public class CurrencyConverterNetSwing {
	
	public static void main(String[] args) {

		final JTextField amount = new JTextField(10);
		final JTextField result = new JTextField(10);
		result.setEditable(false);
		final JComboBox<String> from = new JComboBox<String>(currencies.keySet().toArray(new String[]{}));
		from.setSelectedItem("EUR - Euro");
		final JComboBox<String> to   = new JComboBox<String>(currencies.keySet().toArray(new String[]{}));
		to.setSelectedItem("CHF - Swiss Franc");
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				result.setText("computing...");
				new Thread(){
					@Override
					public void run(){
						result.setText(computeResult(
								amount.getText(),
								currencies.get(from.getSelectedItem()),
								currencies.get(to.getSelectedItem()))
							);
					}
				}.start();
			}
		});
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(2,2));
		
		p.add(from);
		p.add(to);	
		p.add(amount);
		p.add(result);
		
		f.add("Center", p);
		f.add("South",  submit);
		
		f.pack();
		f.setVisible(true);
	}

	static String computeResult(String amount, String from, String to){
		String TOKEN = "green";
		try {
			String query = "https://www.calculator.net/currency-calculator.html?eamount="+amount+"&efrom="+from+"&eto="+to+"&x=5";
			System.out.println(query);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(query)).GET().build();
			HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());
			return response.body()
					.filter(line -> line.contains(TOKEN))
					.findFirst()
					.map(line -> {
						int pos = line.indexOf(TOKEN, 0);
						pos = line.indexOf("<b>", pos);
						String res = line.substring(pos+3);
						return res.substring(0, res.indexOf("<"));
			}).orElse("no result found");
		} catch (Exception e) {
			String msg = e.getMessage();
			return "".equals(msg) ? e.toString() : msg;
		}
	}

	static Map<String,String> currencies = new TreeMap<String,String>();
	static {
		currencies.put("AED - United Arab Emirates Dirham", "AED");
		currencies.put("AFN - Afghan Afghani", "AFN");
		currencies.put("ALL - Albanian Lek", "ALL");
		currencies.put("AMD - Armenian Dram", "AMD");
		currencies.put("ANG - Netherlands Antillean Guilder", "ANG");
		currencies.put("AOA - Angolan Kwanza", "AOA");
		currencies.put("ARS - Argentine Peso", "ARS");
		currencies.put("AUD - Australian Dollar", "AUD");
		currencies.put("AWG - Aruban Florin", "AWG");
		currencies.put("AZN - Azerbaijani Manat", "AZN");
		currencies.put("BAM - Bosnia-Herzegovina Convertible Mark", "BAM");
		currencies.put("BBD - Barbadian Dollar", "BBD");
		currencies.put("BDT - Bangladeshi Taka", "BDT");
		currencies.put("BGN - Bulgarian Lev", "BGN");
		currencies.put("BHD - Bahraini Dinar", "BHD");
		currencies.put("BIF - Burundian Franc", "BIF");
		currencies.put("BMD - Bermudan Dollar", "BMD");
		currencies.put("BND - Brunei Dollar", "BND");
		currencies.put("BOB - Bolivian Boliviano", "BOB");
		currencies.put("BRL - Brazilian Real", "BRL");
		currencies.put("BSD - Bahamian Dollar", "BSD");
		currencies.put("BTC - Bitcoin", "BTC");
		currencies.put("BTN - Bhutanese Ngultrum", "BTN");
		currencies.put("BWP - Botswanan Pula", "BWP");
		currencies.put("BYN - Belarusian Ruble", "BYN");
		currencies.put("BZD - Belize Dollar", "BZD");
		currencies.put("CAD - Canadian Dollar", "CAD");
		currencies.put("CDF - Congolese Franc", "CDF");
		currencies.put("CHF - Swiss Franc", "CHF");
		currencies.put("CLF - Chilean Unit of Account (UF)", "CLF");
		currencies.put("CLP - Chilean Peso", "CLP");
		currencies.put("CNH - Chinese Yuan (Offshore)", "CNH");
		currencies.put("CNY - Chinese Yuan", "CNY");
		currencies.put("COP - Colombian Peso", "COP");
		currencies.put("CRC - Costa Rican Col�n", "CRC");
		currencies.put("CUC - Cuban Convertible Peso", "CUC");
		currencies.put("CUP - Cuban Peso", "CUP");
		currencies.put("CVE - Cape Verdean Escudo", "CVE");
		currencies.put("CZK - Czech Republic Koruna", "CZK");
		currencies.put("DJF - Djiboutian Franc", "DJF");
		currencies.put("DKK - Danish Krone", "DKK");
		currencies.put("DOP - Dominican Peso", "DOP");
		currencies.put("DZD - Algerian Dinar", "DZD");
		currencies.put("EGP - Egyptian Pound", "EGP");
		currencies.put("ERN - Eritrean Nakfa", "ERN");
		currencies.put("ETB - Ethiopian Birr", "ETB");
		currencies.put("EUR - Euro", "EUR");
		currencies.put("FJD - Fijian Dollar", "FJD");
		currencies.put("FKP - Falkland Islands Pound", "FKP");
		currencies.put("GBP - British Pound Sterling", "GBP");
		currencies.put("GEL - Georgian Lari", "GEL");
		currencies.put("GGP - Guernsey Pound", "GGP");
		currencies.put("GHS - Ghanaian Cedi", "GHS");
		currencies.put("GIP - Gibraltar Pound", "GIP");
		currencies.put("GMD - Gambian Dalasi", "GMD");
		currencies.put("GNF - Guinean Franc", "GNF");
		currencies.put("GTQ - Guatemalan Quetzal", "GTQ");
		currencies.put("GYD - Guyanaese Dollar", "GYD");
		currencies.put("HKD - Hong Kong Dollar", "HKD");
		currencies.put("HNL - Honduran Lempira", "HNL");
		currencies.put("HRK - Croatian Kuna", "HRK");
		currencies.put("HTG - Haitian Gourde", "HTG");
		currencies.put("HUF - Hungarian Forint", "HUF");
		currencies.put("IDR - Indonesian Rupiah", "IDR");
		currencies.put("ILS - Israeli New Sheqel", "ILS");
		currencies.put("IMP - Manx pound", "IMP");
		currencies.put("INR - Indian Rupee", "INR");
		currencies.put("IQD - Iraqi Dinar", "IQD");
		currencies.put("IRR - Iranian Rial", "IRR");
		currencies.put("ISK - Icelandic Kr�na", "ISK");
		currencies.put("JEP - Jersey Pound", "JEP");
		currencies.put("JMD - Jamaican Dollar", "JMD");
		currencies.put("JOD - Jordanian Dinar", "JOD");
		currencies.put("JPY - Japanese Yen", "JPY");
		currencies.put("KES - Kenyan Shilling", "KES");
		currencies.put("KGS - Kyrgystani Som", "KGS");
		currencies.put("KHR - Cambodian Riel", "KHR");
		currencies.put("KMF - Comorian Franc", "KMF");
		currencies.put("KPW - North Korean Won", "KPW");
		currencies.put("KRW - South Korean Won", "KRW");
		currencies.put("KWD - Kuwaiti Dinar", "KWD");
		currencies.put("KYD - Cayman Islands Dollar", "KYD");
		currencies.put("KZT - Kazakhstani Tenge", "KZT");
		currencies.put("LAK - Laotian Kip", "LAK");
		currencies.put("LBP - Lebanese Pound", "LBP");
		currencies.put("LKR - Sri Lankan Rupee", "LKR");
		currencies.put("LRD - Liberian Dollar", "LRD");
		currencies.put("LSL - Lesotho Loti", "LSL");
		currencies.put("LYD - Libyan Dinar", "LYD");
		currencies.put("MAD - Moroccan Dirham", "MAD");
		currencies.put("MDL - Moldovan Leu", "MDL");
		currencies.put("MGA - Malagasy Ariary", "MGA");
		currencies.put("MKD - Macedonian Denar", "MKD");
		currencies.put("MMK - Myanma Kyat", "MMK");
		currencies.put("MNT - Mongolian Tugrik", "MNT");
		currencies.put("MOP - Macanese Pataca", "MOP");
		currencies.put("MRO - Mauritanian Ouguiya (pre-2018)", "MRO");
		currencies.put("MRU - Mauritanian Ouguiya", "MRU");
		currencies.put("MUR - Mauritian Rupee", "MUR");
		currencies.put("MVR - Maldivian Rufiyaa", "MVR");
		currencies.put("MWK - Malawian Kwacha", "MWK");
		currencies.put("MXN - Mexican Peso", "MXN");
		currencies.put("MYR - Malaysian Ringgit", "MYR");
		currencies.put("MZN - Mozambican Metical", "MZN");
		currencies.put("NAD - Namibian Dollar", "NAD");
		currencies.put("NGN - Nigerian Naira", "NGN");
		currencies.put("NIO - Nicaraguan C�rdoba", "NIO");
		currencies.put("NOK - Norwegian Krone", "NOK");
		currencies.put("NPR - Nepalese Rupee", "NPR");
		currencies.put("NZD - New Zealand Dollar", "NZD");
		currencies.put("OMR - Omani Rial", "OMR");
		currencies.put("PAB - Panamanian Balboa", "PAB");
		currencies.put("PEN - Peruvian Nuevo Sol", "PEN");
		currencies.put("PGK - Papua New Guinean Kina", "PGK");
		currencies.put("PHP - Philippine Peso", "PHP");
		currencies.put("PKR - Pakistani Rupee", "PKR");
		currencies.put("PLN - Polish Zloty", "PLN");
		currencies.put("PYG - Paraguayan Guarani", "PYG");
		currencies.put("QAR - Qatari Rial", "QAR");
		currencies.put("RON - Romanian Leu", "RON");
		currencies.put("RSD - Serbian Dinar", "RSD");
		currencies.put("RUB - Russian Ruble", "RUB");
		currencies.put("RWF - Rwandan Franc", "RWF");
		currencies.put("SAR - Saudi Riyal", "SAR");
		currencies.put("SBD - Solomon Islands Dollar", "SBD");
		currencies.put("SCR - Seychellois Rupee", "SCR");
		currencies.put("SDG - Sudanese Pound", "SDG");
		currencies.put("SEK - Swedish Krona", "SEK");
		currencies.put("SGD - Singapore Dollar", "SGD");
		currencies.put("SHP - Saint Helena Pound", "SHP");
		currencies.put("SLL - Sierra Leonean Leone", "SLL");
		currencies.put("SOS - Somali Shilling", "SOS");
		currencies.put("SRD - Surinamese Dollar", "SRD");
		currencies.put("SSP - South Sudanese Pound", "SSP");
		currencies.put("STD - S�o Tom� and Pr�ncipe Dobra (pre-2018)", "STD");
		currencies.put("STN - S�o Tom� and Pr�ncipe Dobra", "STN");
		currencies.put("SVC - Salvadoran Col�n", "SVC");
		currencies.put("SYP - Syrian Pound", "SYP");
		currencies.put("SZL - Swazi Lilangeni", "SZL");
		currencies.put("THB - Thai Baht", "THB");
		currencies.put("TJS - Tajikistani Somoni", "TJS");
		currencies.put("TMT - Turkmenistani Manat", "TMT");
		currencies.put("TND - Tunisian Dinar", "TND");
		currencies.put("TOP - Tongan Pa anga", "TOP");
		currencies.put("TRY - Turkish Lira", "TRY");
		currencies.put("TTD - Trinidad and Tobago Dollar", "TTD");
		currencies.put("TWD - New Taiwan Dollar", "TWD");
		currencies.put("TZS - Tanzanian Shilling", "TZS");
		currencies.put("UAH - Ukrainian Hryvnia", "UAH");
		currencies.put("UGX - Ugandan Shilling", "UGX");
		currencies.put("USD - United States Dollar", "USD");
		currencies.put("UYU - Uruguayan Peso", "UYU");
		currencies.put("UZS - Uzbekistan Som", "UZS");
		currencies.put("VEF - Venezuelan Bol�var Fuerte (Old)", "VEF");
		currencies.put("VES - Venezuelan Bol�var Soberano", "VES");
		currencies.put("VND - Vietnamese Dong", "VND");
		currencies.put("VUV - Vanuatu Vatu", "VUV");
		currencies.put("WST - Samoan Tala", "WST");
		currencies.put("XAF - CFA Franc BEAC", "XAF");
		currencies.put("XAG - Silver Ounce", "XAG");
		currencies.put("XAU - Gold Ounce", "XAU");
		currencies.put("XCD - East Caribbean Dollar", "XCD");
		currencies.put("XDR - Special Drawing Rights", "XDR");
		currencies.put("XOF - CFA Franc BCEAO", "XOF");
		currencies.put("XPD - Palladium Ounce", "XPD");
		currencies.put("XPF - CFP Franc", "XPF");
		currencies.put("XPT - Platinum Ounce", "XPT");
		currencies.put("YER - Yemeni Rial", "YER");
		currencies.put("ZAR - South African Rand", "ZAR");
		currencies.put("ZMW - Zambian Kwacha", "ZMW");
		currencies.put("ZWL - Zimbabwean Dollar", "ZWL");
	}
}