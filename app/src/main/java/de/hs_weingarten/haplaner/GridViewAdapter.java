package de.hs_weingarten.haplaner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hs_weingarten.haplaner.datenbank_Faecher.Fach;
import de.hs_weingarten.haplaner.datenbank_Spinner.SpinnerDBHelper;

/**
 * Created by Patrick P. on 14.01.2017.
 */

public class GridViewAdapter extends BaseAdapter{
        private List<Fach> faecher;
        private LayoutInflater inflater;
        private Context context;

        private class ViewHolder {
            ImageView imageView;
            EditText neuesFach;
            EditText kurzZeichen;
            TextView kürzel;
        }
        public GridViewAdapter(Context context, List<Fach> faecher) {
            inflater = LayoutInflater.from(context);
            this.faecher = faecher;
            this.context= context;
        }

        public int getCount() { return faecher.size(); }

        public Object getItem(int position) { return position; }

        public long getItemId(int position) { return position; }

        public View getView(final int position, View convertView,final ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.grid_facher,null);
                holder.imageView = (ImageView) convertView.findViewById(R.id.icon_image_stundp);
                holder.neuesFach = (EditText) convertView.findViewById(R.id.neuesFach);
                holder.kurzZeichen = (EditText) convertView.findViewById(R.id.kürzel);
                holder.kürzel= (TextView) convertView.findViewById(R.id.textview_kuerzel);
                //ImageView Listener
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fach fach=faecher.get(position);
                        Intent stundenplanEinstellungen=new Intent(context,StundenplanBearbeiten.class);
                        stundenplanEinstellungen.putExtra("fach",fach);
                        context.startActivity(stundenplanEinstellungen);
                    }
                });
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String fach=faecher.get(position).getFach();
            holder.kürzel.setVisibility(View.INVISIBLE);
            holder.imageView.setVisibility(View.VISIBLE);
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
            else if(fach.equals(convertView.getResources().getString(R.string.freistunde))){
                holder.imageView.setImageResource(R.drawable.plus);
            }
            else if(fach.equals(convertView.getResources().getString(R.string.leer))){
            }
            else{
                SpinnerDBHelper spinnerDBHelper=new SpinnerDBHelper(parent.getContext());
                String kuerzel=spinnerDBHelper.getKuerzel(fach);
                holder.imageView.setVisibility(View.INVISIBLE);
                holder.kürzel.setText(kuerzel);
                holder.kürzel.setVisibility(View.VISIBLE);
                holder.kürzel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fach fach=faecher.get(position);
                        Intent stundenplanEinstellungen=new Intent(context,StundenplanBearbeiten.class);
                        stundenplanEinstellungen.putExtra("fach",fach);
                        context.startActivity(stundenplanEinstellungen);
                    }
                });
            }

            return convertView;
        }
    public void refresh(List<Fach> myDataset) {
        faecher=myDataset;
        notifyDataSetChanged();
    }
}
