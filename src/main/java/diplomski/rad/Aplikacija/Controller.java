package diplomski.rad.Aplikacija;

import com.mongodb.DBObject;
import diplomski.rad.Aplikacija.DTO.Biljka;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/biljke")
public class Controller {

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody Biljka b) {

        String komanda = "dodaj " + "\"" + b.getIme().replaceAll(" ", "_") + "\"" + " (" + b.getMesecSadnje() + ", " + b.getMesecBerbe() + ") " +
                b.getMestoSadnje() + ", " + b.getDubinaSadnje() + ", " + b.getSvetlo() + ", " + b.getVoda();

        if (b.getNapomena() != null && !b.getNapomena().equals("")) {
            komanda += " \"" + b.getNapomena().replaceAll(" ", "_") + "\"";
        }

        System.out.println(komanda);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(System.getProperty("user.dir") + "/temp.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(komanda);
            printWriter.close();

            parser.par(System.getProperty("user.dir") + "/temp.txt");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (parser.success == true) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/search")
    public List<Biljka> search(@RequestBody Biljka b) {
        String komanda = "pretrazi";

        if (b.getIme() != null && !b.getIme().equals("")) {
            komanda += " ime=" + b.getIme().replaceAll(" ", "_");
            komanda += ",";
        }

        if (b.getMesecSadnje() != null && !b.getMesecSadnje().equals("")) {
            komanda += "  sadnja=" + b.getMesecSadnje();
            komanda += ",";
        }

        if (b.getMesecBerbe() != null && !b.getMesecBerbe().equals("")) {
            komanda += " berba=" + b.getMesecBerbe();
            komanda += ",";
        }

        if (b.getMestoSadnje() != null && !b.getMestoSadnje().equals("")) {
            komanda += " mesto=" + b.getMestoSadnje();
            komanda += ",";
        }

        if (b.getDubinaSadnje() != null && !b.getDubinaSadnje().equals("")) {
            komanda += " dubina=" + b.getDubinaSadnje();
            komanda += ",";
        }

        if (b.getSvetlo() != null && !b.getSvetlo().equals("")) {
            komanda += " svetlo=" + b.getSvetlo();
            komanda += ",";
        }

        if (b.getVoda() != null && !b.getVoda().equals("")) {
            komanda += " voda=" + b.getVoda();
            komanda += ",";
        }

        if (b.getNapomena() != null && !b.getNapomena().equals("")) {
            komanda += " napomena=" + b.getNapomena().replaceAll(" ", "_");
            komanda += ",";
        }

        if (komanda.lastIndexOf(',') == komanda.length() - 1) {
            komanda = komanda.substring(0, komanda.length()-1);
        }

        System.out.println(komanda);

        FileWriter fileWriter = null;
        List<DBObject> res = null;

        try {
            fileWriter = new FileWriter(System.getProperty("user.dir") + "/temp.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(komanda);
            printWriter.close();

            res = parser.par(System.getProperty("user.dir") + "/temp.txt");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Biljka> ret = new ArrayList<>();

        for (DBObject obj : res) {
            Biljka temp = new Biljka();
            for (String key : obj.keySet()) {

                if (key.equals("ime")) {
                    temp.setIme(obj.get("ime").toString());
                } else if (key.equals("sadnja")) {
                    temp.setMesecSadnje(obj.get("sadnja").toString());
                } else if (key.equals("berba")) {
                    temp.setMesecBerbe(obj.get("berba").toString());
                } else if (key.equals("mesto")) {
                    temp.setMestoSadnje(obj.get("mesto").toString());
                } else if (key.equals("dubina")) {
                    temp.setDubinaSadnje(obj.get("dubina").toString());
                } else if (key.equals("svetlo")) {
                    temp.setSvetlo(obj.get("svetlo").toString());
                } else if (key.equals("voda")) {
                    temp.setVoda(obj.get("voda").toString());
                } else if (key.equals("napomena")) {
                    temp.setNapomena(obj.get("napomena").toString());
                }
            }
            ret.add(temp);
        }

        return ret;
    }

    @GetMapping("/search/{range}")
    public List<Biljka> searchRange(@PathVariable("range") Integer range) {
        String komanda = "pretrazi_raspon " + range;

        System.out.println(komanda);

        FileWriter fileWriter = null;
        List<DBObject> res = null;

        try {
            fileWriter = new FileWriter(System.getProperty("user.dir") + "/temp.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(komanda);
            printWriter.close();

            res = parser.par(System.getProperty("user.dir") + "/temp.txt");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Biljka> ret = new ArrayList<>();

        for (DBObject obj : res) {
            Biljka temp = new Biljka();
            for (String key : obj.keySet()) {

                if (key.equals("ime")) {
                    temp.setIme(obj.get("ime").toString());
                } else if (key.equals("sadnja")) {
                    temp.setMesecSadnje(obj.get("sadnja").toString());
                } else if (key.equals("berba")) {
                    temp.setMesecBerbe(obj.get("berba").toString());
                } else if (key.equals("mesto")) {
                    temp.setMestoSadnje(obj.get("mesto").toString());
                } else if (key.equals("dubina")) {
                    temp.setDubinaSadnje(obj.get("dubina").toString());
                } else if (key.equals("svetlo")) {
                    temp.setSvetlo(obj.get("svetlo").toString());
                } else if (key.equals("voda")) {
                    temp.setVoda(obj.get("voda").toString());
                } else if (key.equals("napomena")) {
                    temp.setNapomena(obj.get("napomena").toString());
                }
            }
            ret.add(temp);
        }

        return ret;
    }
}
