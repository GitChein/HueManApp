package com.app.hueman.ui.colors;

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

import com.app.hueman.SavedColor;
import com.app.hueman.SavedColorAdapter;
import com.app.hueman.SavedColorDao;
import com.app.hueman.SavedColorItem;
import com.app.hueman.SavedColorRoomDatabase;
import com.app.hueman.databinding.FragmentSavedcolorsBinding;
import com.app.hueman.R;

import java.util.ArrayList;

public class SavedcolorsFragment extends Fragment {

    private FragmentSavedcolorsBinding binding;
    private ListView listView;
    private SavedColorRoomDatabase sdb;
    private SavedColorDao sdbDao;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSavedcolorsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sdb = SavedColorRoomDatabase.getDatabase(getContext());
        sdbDao = sdb.savedColorDao();
        com.app.hueman.Color colors[] = sdbDao.loadAllSavedColors();

        listView = root.findViewById(R.id.listView);

        ArrayList<SavedColorItem> arr = new ArrayList<>();
        for (com.app.hueman.Color color : colors) {
            arr.add(new SavedColorItem(color.hex, color.name));
        }

        SavedColorAdapter adapter = new SavedColorAdapter(root.getContext(), arr);

        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}