package de.hs_weingarten.haplaner.datenbank;

import android.provider.BaseColumns;

/**
 * Created by Patrick P on 09.01.2017.
 */

public class AufgabenTable {
    public class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME ="aufgaben";
        public static final String COLUMN_TASK = "aufgabe";
    }
}
