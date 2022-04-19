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


public class SavedColorAdapter extends ArrayAdapter<SavedColorItem> {
    private Context context;
    private List<SavedColorItem> colorsList = new ArrayList<>();

    public SavedColorAdapter(Context context, ArrayList<SavedColorItem> lst) {
        super(context, 0 , lst);
        this.context = context;
        colorsList = lst;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.savedcolors_list_item,parent,false);

        SavedColorItem item = colorsList.get(position);
        TextView colorLabel = listItem.findViewById(R.id.color_item);
        colorLabel.setBackgroundColor(Color.parseColor(item.getHexColor()));

        colorLabel.setText(item.getName() + "     " + item.getHexColor());

        return listItem;
    }
}
