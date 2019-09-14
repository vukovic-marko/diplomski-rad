package diplomski.rad.Aplikacija;

import com.mongodb.*;
import java.util.List;

public class App {

    public static void main(String[] args) {

        try {
            List<DBObject> i = parser.par("/home/marko/Aplikacija/src/main/java/diplomski/rad/Aplikacija/test.txt");
            System.out.println("Pronadjeno je " + i.size() + " rezultata koji odgovaraju pretrazi.");
            int br = 1;
            for (DBObject obj : i) {
                for (String key : obj.keySet()) {
                    if (key.equals("ime")) {
                        System.out.println("#" + br + " - " + obj.get(key));
                    } else {
                        System.out.println("\t" + key + ": " + obj.get(key));
                    }
                }
                br++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}