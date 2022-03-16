package com.app.hueman.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.hueman.R;
import com.app.hueman.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ImageView image;
    TextView rgbText;
    TextView hexText;
    TextView colorBox;
    Bitmap bm;


    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        image = root.findViewById(R.id.image);
        rgbText = root.findViewById(R.id.rgbLabel);
        hexText = root.findViewById(R.id.hexLabel);
        colorBox = root.findViewById(R.id.colorBox);

        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache(true);
        image.setOnTouchListener((view, motionEvent) -> {
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

                    String rgbStr = r + " " + g + " " + b;
                    rgbText.setText(rgbStr);

                    String hexStr = Integer.toHexString(rgb);
                    hexText.setText("#" + hexStr);
                }
                return true;
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}