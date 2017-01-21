package de.hs_weingarten.haplaner.datenbank_Spinner;

import java.io.Serializable;

/**
 * Created by Patrick P. on 14.01.2017.
 */

public class SpinnerValue implements Serializable{
    private int id;
    protected String fach;
    protected String kuerzel;

    public SpinnerValue(){

    }
    public SpinnerValue(String _fach,String _kuerzel){
        fach=_fach;
        kuerzel=_kuerzel;
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

    public String getKuerzel(){return kuerzel;}

    public void setKuerzel(String kuerzel){this.kuerzel=kuerzel;}
}
