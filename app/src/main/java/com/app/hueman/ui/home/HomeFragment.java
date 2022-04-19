package com.app.hueman.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.app.Activity;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;

import com.app.hueman.ColorCategorizer;
import com.app.hueman.ColorDao;
import com.app.hueman.ColorRoomDatabase;
import com.app.hueman.DisplayPaletteActivity;
import com.app.hueman.R;
import com.app.hueman.SavedColor;
import com.app.hueman.SavedColorDao;
import com.app.hueman.SavedColorRoomDatabase;
import com.app.hueman.databinding.FragmentHomeBinding;

@SuppressWarnings("deprecation")
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static final int REQUEST_CODE = 123;
    Button btnPick;
    Button btnPalettes;
    Button btnSave;
    ImageView image;
    TextView hexText;
    TextView nameText;
    TextView nameEdit;
    TextView typeText;
    TextView colorBox;
    Bitmap bm;
    ColorRoomDatabase cdb;
    ColorDao colorDao;

    SavedColorRoomDatabase sc_db;
    SavedColorDao sc_dao;


    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        image = root.findViewById(R.id.image);
        btnPick = root.findViewById(R.id.btnPick);

        btnPalettes = (Button) root.findViewById(R.id.paletteGeneratorButton);
        btnSave = (Button) root.findViewById(R.id.saveColorButton);
        btnPalettes.setEnabled(false);
        btnSave.setEnabled(false);

        hexText = root.findViewById(R.id.hexLabel);
        typeText = root.findViewById(R.id.typeLabel);
        colorBox = root.findViewById(R.id.colorBox);

        cdb = ColorRoomDatabase.getDatabase(getContext());
        Log.i("color_db", String.valueOf(getContext()));
        colorDao = cdb.colorDao();

        sc_db = SavedColorRoomDatabase.getDatabase(getContext());
        Log.i("color_db", String.valueOf(getContext()));


        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache(true);
        image.setOnTouchListener((view, motionEvent) -> {
                btnPalettes.setEnabled(true);
                btnSave.setEnabled(true);
                if (motionEvent.getX() < 0 || motionEvent.getY() < 0 ||
                    motionEvent.getX() >= view.getWidth() || motionEvent.getY() >= view.getHeight()) {
                    return true;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ||
                    motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    bm = image.getDrawingCache();
                    int rgb = bm.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());
                    int r = Color.red(rgb);
                    int g = Color.green(rgb);
                    int b = Color.blue(rgb);
                    colorBox.setBackgroundColor(Color.rgb(r, g, b));
                    typeText.setText(ColorCategorizer.getColorCategory(r,g,b));

                    String hexStr = Integer.toHexString(rgb);
                    if (hexStr.length() > 2) {
                        hexStr = "#" + hexStr.substring(2);
                        hexText.setText(hexStr);
                    }


                    nameText = root.findViewById(R.id.nameLabel);
                    nameEdit = root.findViewById(R.id.nameEdit);
                    nameEdit.setText("");
                    nameText.setText("");
                    String colorName = colorDao.getName(hexStr);
                    if (colorName != null) {
                        nameText.setText(colorName);
                    }
                    else{
                        nameEdit.setText("Enter a Name!");
                    }
                }
                return true;
        });

        btnPick.setOnClickListener((v) -> {
              Intent intent = new Intent();
              intent.setType("image/*");
              intent.setAction(Intent.ACTION_GET_CONTENT);
              startActivityForResult(Intent.createChooser(intent, "Choose an image"), REQUEST_CODE);
        });

        btnSave.setOnClickListener((v) -> {

            sc_dao = sc_db.savedColorDao();



            TextView hex_text = (TextView) root.findViewById(R.id.hexLabel);
            String hex = hex_text.getText().toString();

            TextView name_text = (TextView) root.findViewById(R.id.nameLabel);
            String t_name = name_text.getText().toString();

            EditText name_edit = (EditText) root.findViewById(R.id.nameEdit1);
            String e_name = name_edit.getText().toString();

            TextView type_text = (TextView) root.findViewById(R.id.typeLabel);
            String type = type_text.getText().toString();

            String name;
            if(t_name == ""){
                if(e_name == "Enter a Name!" || e_name == "") {
                    name = "No Name";
                }
                else{
                    name = e_name;
                }
            }
            else{
                name = t_name;
            }

            SavedColor savedColor = new SavedColor();
            if (sc_dao.getColor(hex) == null) {
                savedColor.hex = hex;
                savedColor.name = name;
                savedColor.type = type;
                sc_dao.insertSavedColor(savedColor);
            }
            if(colorDao.getName(hex) == null){
                com.app.hueman.Color color = new com.app.hueman.Color();
                color.hex = hex;
                color.name = name;
                colorDao.insertColor(color);
            }




        });

        btnPalettes.setOnClickListener((v) -> {
            Intent intent = new Intent(v.getContext(), DisplayPaletteActivity.class);
            TextView hex = (TextView) root.findViewById(R.id.hexLabel);
            String message = hex.getText().toString();
            intent.putExtra("hex", message);
            TextView name = (TextView) root.findViewById(R.id.nameLabel);
            message = name.getText().toString();
            intent.putExtra("name", message);
            TextView type = (TextView) root.findViewById(R.id.typeLabel);
            message = type.getText().toString();
            intent.putExtra("type", message);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, @Nullable Intent data){
      if(reqCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null){
        Uri imageData = data.getData();
        image.setImageURI(imageData);
        bm = image.getDrawingCache();
      }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}