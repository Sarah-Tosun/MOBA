package de.hs_weingarten.haplaner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hs_weingarten.haplaner.datenbank_Aufgaben.Aufgabe;
import de.hs_weingarten.haplaner.datenbank_Aufgaben.AufgabenDBHelper;
import de.hs_weingarten.haplaner.datenbank_Faecher.Fach;
import de.hs_weingarten.haplaner.datenbank_Spinner.SpinnerDBHelper;
import de.hs_weingarten.haplaner.datenbank_Spinner.SpinnerValue;

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
        ImageView imageView;
        CheckBox checkBox;
        TextView kuerzel;
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
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon_image_aufg);
            holder.kuerzel=(TextView) convertView.findViewById(R.id.textview_kuerzel_aufg);
            //Checkbox Listener
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_aufg);
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AufgabenDBHelper db= new AufgabenDBHelper(parent.getContext()); //Öffne die Datenbank
                    final Aufgabe aufgabe=objects.get(position);
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.dialog_delete_fach);
                    Button okButton=(Button) dialog.findViewById(R.id.dialog_ok_button);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            objects.remove(position);//Löscht die Aufgabe aus dem Adapter der Anzeige
                            db.deleteAufgabe(aufgabe); //Löscht die Aufgabe aus der DB
                            dialog.dismiss();
                            notifyDataSetChanged(); //Refreshed die UI
                        }
                    });
                    Button dismissButton=(Button) dialog.findViewById(R.id.dialog_dismiss_button);
                    dismissButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });
                    TextView textView=(TextView) dialog.findViewById(R.id.dialog_title);
                    textView.setText("Möchten Sie "+aufgabe.getFach().toString()+" wirklich löschen?");
                    dialog.show();
                    ((CheckBox)v).setChecked(false);
                    //Toast.makeText(parent.getContext(), "Deletet Item: "+position,Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(objects.get(position).getFach());
        holder.textView2.setText(objects.get(position).getDatum());
        holder.textView3.setText(objects.get(position).getBeschreibung());

        holder.kuerzel.setVisibility(View.INVISIBLE);
        holder.imageView.setVisibility(View.VISIBLE);
        String fach=objects.get(position).getFach();
        if(fach.equals(convertView.getResources().getString(R.string.fach_Deutsch))){
            holder.imageView.setImageResource(R.drawable.deutsch);
        }
        else if(fach.equals(convertView.getResources().getString(R.string.fach_Englisch))){
            holder.imageView.setImageResource(R.drawable.englisch);
        }
        else if(fach.equals(convertView.getResources().getString(R.string.fach_Erdkunde))){
            holder.imageView.setImageResource(R.drawable.erdkunde);
        }
        else if(fach.equals(convertView.getResources().getString(R.string.fach_Geschichte))){
            holder.imageView.setImageResource(R.drawable.geschichte);
        }
        else if(fach.equals(convertView.getResources().getString(R.string.fach_Chemie))){
            holder.imageView.setImageResource(R.drawable.chemie);
        }
        else if(fach.equals(convertView.getResources().getString(R.string.fach_Biologie))){
            holder.imageView.setImageResource(R.drawable.biologie);
        }
        else if(fach.equals(convertView.getResources().getString(R.string.fach_Mathematik))){
            holder.imageView.setImageResource(R.drawable.mathematik);
        }
        else if(fach.equals(convertView.getResources().getString(R.string.fach_Physik))){
            holder.imageView.setImageResource(R.drawable.physik);
        }
        else{
            SpinnerDBHelper spinnerDBHelper=new SpinnerDBHelper(parent.getContext());
            String kuerzel=spinnerDBHelper.getKuerzel(fach);
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.kuerzel.setText(kuerzel);
            holder.kuerzel.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
    public void refresh(List<Aufgabe> myDataset) {
        objects=myDataset;
        notifyDataSetChanged();
    }
}
