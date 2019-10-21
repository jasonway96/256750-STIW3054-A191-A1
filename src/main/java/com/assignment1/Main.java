package com.assignment1;

import java.io.IOException;

public class Main
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
