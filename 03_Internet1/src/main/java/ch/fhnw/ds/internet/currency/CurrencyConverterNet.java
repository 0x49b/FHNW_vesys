package ch.fhnw.ds.internet.currency;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

// uses the currency converter provided by 	
public class CurrencyConverterNet { // extends javafx.application.Application {

    public static void main(String args[]) {
//		launch(args);
        new JFXPanel(); // this will prepare JavaFX toolkit and environment
        CurrencyConverterNet gui = new CurrencyConverterNet();
        Platform.runLater(() -> {
            gui.start(new Stage());
        });
    }

    //	@Override
    public void start(Stage stage) {
        stage.setTitle("Currency Converter");
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));

        ObservableList<String> data = FXCollections.observableArrayList();
        currencies.forEach((key, entry) -> data.add(key + " - " + entry));

        ComboBox<String> from = new ComboBox<>(data);
        ComboBox<String> to = new ComboBox<>(data);

        TextField amount = new TextField();
        TextField result = new TextField();
        result.setEditable(false);

        ObjectProperty<Double> amountProp = new SimpleObjectProperty<>(null);
        amount.textProperty().bindBidirectional(amountProp, new StringDoubleConverter());
        amount.setTextFormatter(new TextFormatter<Double>(new StringDoubleConverter(), 0d, new DoubleFilter()));

        Button submit = new Button("Submit");
        submit.setMaxWidth(Double.MAX_VALUE);
        submit.disableProperty().bind(amountProp.isNull().or(amountProp.isEqualTo(0.0)));
        submit.setOnAction(e -> {
            result.setText("computing...");
            new Thread(() -> {
                result.setText(computeResult(
                        amount.getText(),
                        from.getSelectionModel().getSelectedItem().substring(0, 3),
                        to.getSelectionModel().getSelectedItem().substring(0, 3)
                ));
            }).start();
        });

        from.getSelectionModel().select("CHF - " + currencies.get("CHF"));
        to.getSelectionModel().select("EUR - " + currencies.get("EUR"));

        root.add(from, 0, 0);
        root.add(to, 1, 0);

        root.add(amount, 0, 1);
        root.add(result, 1, 1);

        root.add(submit, 0, 2, 2, 1);

        Scene scene = new Scene(root, 520, 120);
        stage.setScene(scene);
        stage.show();
    }

    static String computeResult(String amount, String from, String to) {
        String TOKEN = "green";
        try {
            String query = "https://www.calculator.net/currency-calculator.html?eamount=" + amount + "&efrom=" + from + "&eto=" + to + "&x=5";
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
                        String res = line.substring(pos + 3);
                        return res.substring(0, res.indexOf("<"));
                    }).orElse("no result found");
        } catch (Exception e) {
            String msg = e.getMessage();
            return "".equals(msg) ? e.toString() : msg;
        }
    }

    static Map<String, String> currencies = new TreeMap<>();

    static {
        currencies.put("AED", "AED - United Arab Emirates Dirham");
        currencies.put("AFN", "AFN - Afghan Afghani");
        currencies.put("ALL", "ALL - Albanian Lek");
        currencies.put("AMD", "AMD - Armenian Dram");
        currencies.put("ANG", "ANG - Netherlands Antillean Guilder");
        currencies.put("AOA", "AOA - Angolan Kwanza");
        currencies.put("ARS", "ARS - Argentine Peso");
        currencies.put("AUD", "AUD - Australian Dollar");
        currencies.put("AWG", "AWG - Aruban Florin");
        currencies.put("AZN", "AZN - Azerbaijani Manat");
        currencies.put("BAM", "BAM - Bosnia-Herzegovina Convertible Mark");
        currencies.put("BBD", "BBD - Barbadian Dollar");
        currencies.put("BDT", "BDT - Bangladeshi Taka");
        currencies.put("BGN", "BGN - Bulgarian Lev");
        currencies.put("BHD", "BHD - Bahraini Dinar");
        currencies.put("BIF", "BIF - Burundian Franc");
        currencies.put("BMD", "BMD - Bermudan Dollar");
        currencies.put("BND", "BND - Brunei Dollar");
        currencies.put("BOB", "BOB - Bolivian Boliviano");
        currencies.put("BRL", "BRL - Brazilian Real");
        currencies.put("BSD", "BSD - Bahamian Dollar");
        currencies.put("BTC", "BTC - Bitcoin");
        currencies.put("BTN", "BTN - Bhutanese Ngultrum");
        currencies.put("BWP", "BWP - Botswanan Pula");
        currencies.put("BYN", "BYN - Belarusian Ruble");
        currencies.put("BZD", "BZD - Belize Dollar");
        currencies.put("CAD", "CAD - Canadian Dollar");
        currencies.put("CDF", "CDF - Congolese Franc");
        currencies.put("CHF", "CHF - Swiss Franc");
        currencies.put("CLF", "CLF - Chilean Unit of Account (UF)");
        currencies.put("CLP", "CLP - Chilean Peso");
        currencies.put("CNH", "CNH - Chinese Yuan (Offshore)");
        currencies.put("CNY", "CNY - Chinese Yuan");
        currencies.put("COP", "COP - Colombian Peso");
        currencies.put("CRC", "CRC - Costa Rican Col?n");
        currencies.put("CUC", "CUC - Cuban Convertible Peso");
        currencies.put("CUP", "CUP - Cuban Peso");
        currencies.put("CVE", "CVE - Cape Verdean Escudo");
        currencies.put("CZK", "CZK - Czech Republic Koruna");
        currencies.put("DJF", "DJF - Djiboutian Franc");
        currencies.put("DKK", "DKK - Danish Krone");
        currencies.put("DOP", "DOP - Dominican Peso");
        currencies.put("DZD", "DZD - Algerian Dinar");
        currencies.put("EGP", "EGP - Egyptian Pound");
        currencies.put("ERN", "ERN - Eritrean Nakfa");
        currencies.put("ETB", "ETB - Ethiopian Birr");
        currencies.put("EUR", "EUR - Euro");
        currencies.put("FJD", "FJD - Fijian Dollar");
        currencies.put("FKP", "FKP - Falkland Islands Pound");
        currencies.put("GBP", "GBP - British Pound Sterling");
        currencies.put("GEL", "GEL - Georgian Lari");
        currencies.put("GGP", "GGP - Guernsey Pound");
        currencies.put("GHS", "GHS - Ghanaian Cedi");
        currencies.put("GIP", "GIP - Gibraltar Pound");
        currencies.put("GMD", "GMD - Gambian Dalasi");
        currencies.put("GNF", "GNF - Guinean Franc");
        currencies.put("GTQ", "GTQ - Guatemalan Quetzal");
        currencies.put("GYD", "GYD - Guyanaese Dollar");
        currencies.put("HKD", "HKD - Hong Kong Dollar");
        currencies.put("HNL", "HNL - Honduran Lempira");
        currencies.put("HRK", "HRK - Croatian Kuna");
        currencies.put("HTG", "HTG - Haitian Gourde");
        currencies.put("HUF", "HUF - Hungarian Forint");
        currencies.put("IDR", "IDR - Indonesian Rupiah");
        currencies.put("ILS", "ILS - Israeli New Sheqel");
        currencies.put("IMP", "IMP - Manx pound");
        currencies.put("INR", "INR - Indian Rupee");
        currencies.put("IQD", "IQD - Iraqi Dinar");
        currencies.put("IRR", "IRR - Iranian Rial");
        currencies.put("ISK", "ISK - Icelandic Kr?na");
        currencies.put("JEP", "JEP - Jersey Pound");
        currencies.put("JMD", "JMD - Jamaican Dollar");
        currencies.put("JOD", "JOD - Jordanian Dinar");
        currencies.put("JPY", "JPY - Japanese Yen");
        currencies.put("KES", "KES - Kenyan Shilling");
        currencies.put("KGS", "KGS - Kyrgystani Som");
        currencies.put("KHR", "KHR - Cambodian Riel");
        currencies.put("KMF", "KMF - Comorian Franc");
        currencies.put("KPW", "KPW - North Korean Won");
        currencies.put("KRW", "KRW - South Korean Won");
        currencies.put("KWD", "KWD - Kuwaiti Dinar");
        currencies.put("KYD", "KYD - Cayman Islands Dollar");
        currencies.put("KZT", "KZT - Kazakhstani Tenge");
        currencies.put("LAK", "LAK - Laotian Kip");
        currencies.put("LBP", "LBP - Lebanese Pound");
        currencies.put("LKR", "LKR - Sri Lankan Rupee");
        currencies.put("LRD", "LRD - Liberian Dollar");
        currencies.put("LSL", "LSL - Lesotho Loti");
        currencies.put("LYD", "LYD - Libyan Dinar");
        currencies.put("MAD", "MAD - Moroccan Dirham");
        currencies.put("MDL", "MDL - Moldovan Leu");
        currencies.put("MGA", "MGA - Malagasy Ariary");
        currencies.put("MKD", "MKD - Macedonian Denar");
        currencies.put("MMK", "MMK - Myanma Kyat");
        currencies.put("MNT", "MNT - Mongolian Tugrik");
        currencies.put("MOP", "MOP - Macanese Pataca");
        currencies.put("MRO", "MRO - Mauritanian Ouguiya (pre-2018)");
        currencies.put("MRU", "MRU - Mauritanian Ouguiya");
        currencies.put("MUR", "MUR - Mauritian Rupee");
        currencies.put("MVR", "MVR - Maldivian Rufiyaa");
        currencies.put("MWK", "MWK - Malawian Kwacha");
        currencies.put("MXN", "MXN - Mexican Peso");
        currencies.put("MYR", "MYR - Malaysian Ringgit");
        currencies.put("MZN", "MZN - Mozambican Metical");
        currencies.put("NAD", "NAD - Namibian Dollar");
        currencies.put("NGN", "NGN - Nigerian Naira");
        currencies.put("NIO", "NIO - Nicaraguan C?rdoba");
        currencies.put("NOK", "NOK - Norwegian Krone");
        currencies.put("NPR", "NPR - Nepalese Rupee");
        currencies.put("NZD", "NZD - New Zealand Dollar");
        currencies.put("OMR", "OMR - Omani Rial");
        currencies.put("PAB", "PAB - Panamanian Balboa");
        currencies.put("PEN", "PEN - Peruvian Nuevo Sol");
        currencies.put("PGK", "PGK - Papua New Guinean Kina");
        currencies.put("PHP", "PHP - Philippine Peso");
        currencies.put("PKR", "PKR - Pakistani Rupee");
        currencies.put("PLN", "PLN - Polish Zloty");
        currencies.put("PYG", "PYG - Paraguayan Guarani");
        currencies.put("QAR", "QAR - Qatari Rial");
        currencies.put("RON", "RON - Romanian Leu");
        currencies.put("RSD", "RSD - Serbian Dinar");
        currencies.put("RUB", "RUB - Russian Ruble");
        currencies.put("RWF", "RWF - Rwandan Franc");
        currencies.put("SAR", "SAR - Saudi Riyal");
        currencies.put("SBD", "SBD - Solomon Islands Dollar");
        currencies.put("SCR", "SCR - Seychellois Rupee");
        currencies.put("SDG", "SDG - Sudanese Pound");
        currencies.put("SEK", "SEK - Swedish Krona");
        currencies.put("SGD", "SGD - Singapore Dollar");
        currencies.put("SHP", "SHP - Saint Helena Pound");
        currencies.put("SLL", "SLL - Sierra Leonean Leone");
        currencies.put("SOS", "SOS - Somali Shilling");
        currencies.put("SRD", "SRD - Surinamese Dollar");
        currencies.put("SSP", "SSP - South Sudanese Pound");
        currencies.put("STD", "STD - S?o Tom? and Pr?ncipe Dobra (pre-2018)");
        currencies.put("STN", "STN - S?o Tom? and Pr?ncipe Dobra");
        currencies.put("SVC", "SVC - Salvadoran Col?n");
        currencies.put("SYP", "SYP - Syrian Pound");
        currencies.put("SZL", "SZL - Swazi Lilangeni");
        currencies.put("THB", "THB - Thai Baht");
        currencies.put("TJS", "TJS - Tajikistani Somoni");
        currencies.put("TMT", "TMT - Turkmenistani Manat");
        currencies.put("TND", "TND - Tunisian Dinar");
        currencies.put("TOP", "TOP - Tongan Pa anga");
        currencies.put("TRY", "TRY - Turkish Lira");
        currencies.put("TTD", "TTD - Trinidad and Tobago Dollar");
        currencies.put("TWD", "TWD - New Taiwan Dollar");
        currencies.put("TZS", "TZS - Tanzanian Shilling");
        currencies.put("UAH", "UAH - Ukrainian Hryvnia");
        currencies.put("UGX", "UGX - Ugandan Shilling");
        currencies.put("USD", "USD - United States Dollar");
        currencies.put("UYU", "UYU - Uruguayan Peso");
        currencies.put("UZS", "UZS - Uzbekistan Som");
        currencies.put("VEF", "VEF - Venezuelan Bol?var Fuerte (Old)");
        currencies.put("VES", "VES - Venezuelan Bol?var Soberano");
        currencies.put("VND", "VND - Vietnamese Dong");
        currencies.put("VUV", "VUV - Vanuatu Vatu");
        currencies.put("WST", "WST - Samoan Tala");
        currencies.put("XAF", "XAF - CFA Franc BEAC");
        currencies.put("XAG", "XAG - Silver Ounce");
        currencies.put("XAU", "XAU - Gold Ounce");
        currencies.put("XCD", "XCD - East Caribbean Dollar");
        currencies.put("XDR", "XDR - Special Drawing Rights");
        currencies.put("XOF", "XOF - CFA Franc BCEAO");
        currencies.put("XPD", "XPD - Palladium Ounce");
        currencies.put("XPF", "XPF - CFP Franc");
        currencies.put("XPT", "XPT - Platinum Ounce");
        currencies.put("YER", "YER - Yemeni Rial");
        currencies.put("ZAR", "ZAR - South African Rand");
        currencies.put("ZMW", "ZMW - Zambian Kwacha");
        currencies.put("ZWL", "ZWL - Zimbabwean Dollar");
    }

    private static final class DoubleFilter implements UnaryOperator<Change> {
        @Override
        public Change apply(Change change) {
            String newText = change.getControlNewText();
            if (newText.trim().isEmpty()) {
                change.setText("");
                return change;
            }
            try {
                Double.parseDouble(newText);
                return change;
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    private static final class StringDoubleConverter extends StringConverter<Double> {
        @Override
        public Double fromString(String s) {
            return s.trim().isEmpty() ? null : Double.parseDouble(s);
        }

        @Override
        public String toString(Double d) {
            return d == null ? "" : Double.toString(d);
        }
    }

}
