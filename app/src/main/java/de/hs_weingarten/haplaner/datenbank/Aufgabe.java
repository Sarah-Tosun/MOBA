package de.hs_weingarten.haplaner.datenbank;

/**
 * Created by Patrick P. on 10.01.2017.
 */

public class Aufgabe {
    private int id;
    protected String fach;
    protected String datum;
    protected String beschreibung;
    public Aufgabe(){

    }
    public Aufgabe(String _fach,String _datum,String _beschreibung){
        fach=_fach;
        datum=_datum;
        beschreibung=_beschreibung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }
}
