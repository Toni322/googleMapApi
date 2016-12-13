package googleAPI;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadFromFile {


    public  ArrayList<String> read(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        ArrayList<String> sb = new ArrayList<String>();
        file.exists();

        try {

            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.add(s);
                }
            } finally {

                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb;
    }

}
