package de.hs_weingarten.haplaner.datenbank_Faecher;

import java.io.Serializable;

/**
 * Created by Patrick P. on 14.01.2017.
 */

public class Fach implements Serializable{
    private int id;
    protected String fach;
    protected int tag; //1-Montag,2-Dienstag,...,5-Freitag
    protected int stunde; //1-1.Stunde,2-2.Stunde...

    public Fach(){

    }
    public Fach(String _fach,int _tag,int _stunde){
        fach=_fach;
        tag=_tag;
        stunde=_stunde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStunde() {
        return stunde;
    }

    public void setStunde(int stunde) {
        this.stunde = stunde;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }
}
