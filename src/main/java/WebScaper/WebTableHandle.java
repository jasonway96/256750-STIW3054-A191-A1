package WebScaper;

import java.io.IOException;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class WebTableHandle {
    public static LinkedList<info> findAll() {
        LinkedList<info> info= new LinkedList<info>();
        final String url= "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";

        try {
            final Document file = Jsoup.connect(url).get();

            for (Element row : file.select("#wiki-body > div > table > tbody >tr"))

            {
                String no = row.select("td:nth-child(1)").text();
                String name = row.select("td:nth-child(2)").text();
                String noMatric = row.select("td:nth-child(3)").text();
                info.add(new info(no,name,noMatric));
            }return info;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}