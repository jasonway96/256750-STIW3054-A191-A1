package WebScaper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebLinkTable
{
    public static LinkedList<info> findAll() throws IOException {
        LinkedList<info> info = new LinkedList<info>();
        final String url = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
        final Document file = Jsoup.connect(url).get();
        Elements row = file.select("table");

        try {
            for (int i = 1; i < row.size(); i++) {
                Elements getData = row.get(i).select("p");

                for (int j = 0; j < getData.size(); j++) {
                    String line1 = null;
                    Pattern matric = Pattern.compile("([0-9]{6})");
                    Matcher getMatric = matric.matcher(getData.get(j).text());

                    if (getMatric.find()) {
                        line1 = getMatric.group();
                    } else {
                        line1 = "";
                    }

                    Pattern name = Pattern.compile("(Name)(.*)(Matric)");
                    Matcher getName = name.matcher(getData.get(j).text());

                    Pattern name2 = Pattern.compile("(Name)(.*)(Link)");
                    Matcher getName2 = name2.matcher(getData.get(j).text());

                    Pattern name3 = Pattern.compile("(name)(.*)(link)");
                    Matcher getName3 = name3.matcher(getData.get(j).text());

                    String line2 = null;

                    if (getName.find()) {
                        line2 = getName.group(2);


                    } else if (getName2.find()) {
                        line2 = getName2.group(2);

                    } else if (getName3.find()) {
                        line2 = getName3.group(2);
                    }

                    Pattern link = Pattern.compile("https://.*");
                    Matcher line3 = link.matcher(getData.get(j).text());

                    line3.find();


                    info.add(new info(line1, line2, line3.group()));

                }
            }


            return info;
        }

        catch (Exception e)

        {
            return null;
        }

    }

}

