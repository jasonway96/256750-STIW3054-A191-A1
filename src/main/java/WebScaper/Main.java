package WebScaper;

import java.io.IOException;

public class Main extends WebTableHandle
{
    public static void main (String[] args) throws IOException
    {
        WebTableHandle.findAll();
        WriteExcel.main();
        WebLinkTable.findAll();
        excelLink.main();
        Compare.main();
    }
}
