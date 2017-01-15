package de.hs_weingarten.haplaner.datenbank_Faecher;

import java.io.Serializable;

/**
 * Created by Patrick P. on 14.01.2017.
 */

public class Fach implements Serializable{
    private int id;
    protected String fach;

    public Fach(){

    }
    public Fach(String _fach,int _tag,int _stunde){
        fach=_fach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }
}
