package de.hs_weingarten.haplaner.datenbank_Spinner;

import java.io.Serializable;

/**
 * Created by Patrick P. on 14.01.2017.
 */

public class SpinnerValue implements Serializable{
    private int id;
    protected String fach;

    public SpinnerValue(){

    }
    public SpinnerValue(String _fach){
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
