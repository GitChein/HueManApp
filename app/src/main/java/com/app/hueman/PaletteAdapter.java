package com.app.hueman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;


public class PaletteAdapter extends ArrayAdapter<PaletteItem> {
    private Context context;
    private List<PaletteItem> paletteList = new ArrayList<>();

    public PaletteAdapter(Context context, ArrayList<PaletteItem> lst) {
        super(context, 0 , lst);
        this.context = context;
        paletteList = lst;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.palette_list_item,parent,false);

        PaletteItem item = paletteList.get(position);
        TextView colorLabel1 = listItem.findViewById(R.id.clr1);
        TextView colorLabel2 = listItem.findViewById(R.id.clr2);
        TextView colorLabel3 = listItem.findViewById(R.id.clr3);
        TextView colorLabel4 = listItem.findViewById(R.id.clr4);
        TextView colorLabel5 = listItem.findViewById(R.id.clr5);
        TextView nameLabel = listItem.findViewById(R.id.paletteName);

        String[] colors = item.getHexColors();

        colorLabel1.setBackgroundColor(Color.parseColor("#" + colors[0]));
        colorLabel2.setBackgroundColor(Color.parseColor("#" + colors[1]));
        colorLabel3.setBackgroundColor(Color.parseColor("#" + colors[2]));
        colorLabel4.setBackgroundColor(Color.parseColor("#" + colors[3]));
        colorLabel5.setBackgroundColor(Color.parseColor("#" + colors[4]));

        nameLabel.setText(item.getName());

        return listItem;
    }
}
