package de.hs_weingarten.haplaner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hs_weingarten.haplaner.datenbank.Aufgabe;
import de.hs_weingarten.haplaner.datenbank.AufgabenDBHelper;

/**
 * Created by Patrick P. on 11.01.2017.
 */

public class ListViewAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<Aufgabe> objects;

    private class ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        CheckBox checkBox;
    }

    public ListViewAdapter(Context context, List<Aufgabe> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public int getCount() {
        return objects.size();
    }

    public Aufgabe getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_aufgabe, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.fach_text_aufg);
            holder.textView2 = (TextView) convertView.findViewById(R.id.datum_textview_aufg);
            holder.textView3 = (TextView) convertView.findViewById(R.id.beschreibung_textview_aufg);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_aufg);
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AufgabenDBHelper db= new AufgabenDBHelper(parent.getContext());
                    Aufgabe aufgabe=objects.get(position);
                    db.deleteAufgabe(aufgabe);
                    objects.remove(position);
                    Toast.makeText(parent.getContext(), "Deletet Item: "+position,Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(objects.get(position).getFach());
        holder.textView2.setText(objects.get(position).getDatum());
        holder.textView3.setText(objects.get(position).getBeschreibung());

        return convertView;
    }
}
