/*
 * Created by Viktor Lyakhovych on 2018/10/01
 *
 * print in console cryptocurrency courses from kuna.io
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class kunaParser {

    private static Document getPage() throws IOException {
        String url = "https://kuna.io/markets/ethuah";
        Document page = Jsoup.parse (new URL(url), 5000);
        return page;
    }

    public static void main (String[] args) throws IOException{
        Document page = getPage ();
        Element currencies = page.select ("ul[class=market-menu]").first ();
        Elements pairs = currencies.select ("li[class=market quote-uah");
        Elements blocks = pairs.select ("li[class!=market quote-uah]");
        Elements prices = pairs.select ("ul[class=last price");

        ArrayList<String> blocksArrList = new ArrayList<String> ();
        for (Element s : blocks) {
            blocksArrList.add (s.text ());
        }
        ArrayList<String> pricesArrList = new ArrayList<String> ();
        for (Element s : prices) {
            pricesArrList.add (s.text ());
        }

        int maxCurrencyNameLength = 0;
        for (int i = 0; i < blocksArrList.size (); i++) {
            String t = (String) blocksArrList.toArray ()[i];
            if (maxCurrencyNameLength < t.length ()) maxCurrencyNameLength = t.length ();
        }

        for (int i = 0; i < pricesArrList.size (); i++) {
            String t = (String) blocksArrList.toArray ()[i];
            int curStrLen = t.length ();
            String space = "";
            for (int j = curStrLen; j < maxCurrencyNameLength + 1; j++) {
                space += " ";
                //System.out.println (j);
            }
            System.out.print (blocksArrList.toArray ()[i] + space + "- ");
            System.out.println ("UAH " + pricesArrList.toArray ()[i]);
        }
    }
}
