package mkrzyzanowski.pl.laboratoriumandroidstudiocw2;

import com.orm.SugarRecord;

/**
 * Created by Michał on 2018-02-24.
 */

public class Kontakt extends SugarRecord {
    String nazwa;
    String numer;

    public Kontakt(){}

    public Kontakt(String nazwa, String numer){
        this.nazwa = nazwa;
        this.numer = numer;
    }
    private String notNull(String string){
        if (string.equals("")) {
            return "NULL";
        } else {
            return string;
        }
    }

    public String getNazwa(){
        return notNull(nazwa);
    }
    public String getNumer(){
        return notNull(numer);
    }
}
