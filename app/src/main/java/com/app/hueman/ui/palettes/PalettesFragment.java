package com.app.hueman.ui.palettes;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.hueman.PaletteAdapter;
import com.app.hueman.PaletteItem;
import com.app.hueman.SavedColor;
import com.app.hueman.SavedColorAdapter;
import com.app.hueman.SavedColorDao;
import com.app.hueman.SavedColorItem;
import com.app.hueman.SavedPalette;
import com.app.hueman.SavedPaletteDao;
import com.app.hueman.SavedPaletteRoomDatabase;
import com.app.hueman.databinding.FragmentPalettesBinding;
import com.app.hueman.databinding.FragmentSavedcolorsBinding;
import com.app.hueman.R;

import java.util.ArrayList;

public class PalettesFragment extends Fragment {

    private FragmentPalettesBinding binding;
    private ListView listView;
    private SavedPaletteRoomDatabase pdb;
    private SavedPaletteDao pdbDao;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPalettesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pdb = SavedPaletteRoomDatabase.getDatabase(getContext());
        pdbDao = pdb.savedPaletteDao();
        SavedPalette palettes[] = pdbDao.loadAllSavedPalettes();

        listView = root.findViewById(R.id.paletteListView);

        ArrayList<PaletteItem> arr = new ArrayList<>();
        for (SavedPalette palette : palettes) {
            String[] colors = new String[5];
            colors[0] = palette.hex1;
            colors[1] = palette.hex2;
            colors[2] = palette.hex3;
            colors[3] = palette.hex4;
            colors[4] = palette.hex5;

            arr.add(new PaletteItem(colors, palette.name));
        }

        PaletteAdapter adapter = new PaletteAdapter(root.getContext(), arr);

        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}