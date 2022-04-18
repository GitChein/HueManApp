package com.app.hueman;

import androidx.core.graphics.ColorUtils;

import java.util.Vector;
import android.graphics.Color;


public class ColorCategorizer {
    static float[] getHSLvalues(float r, float g, float b) {
        // convert RGB to HSL
        r /= 255;
        g /= 255;
        b /= 255;

        float max = Math.max(Math.max(r, g),b);
        float min = Math.min(Math.min(r, g),b);

        float h,s,l = (max + min) / 2;

        if(max == min) {
            h = s = 0;
        }
        else {
            float dif = max - min;
            s = (l > 0.5) ? ( dif / (2 - max - min)) : (dif/ (max + min));

            if(max == r){
                h = (g - b) / dif + ((g < b) ? 6 : 0);
            }
            else if (max == g){
                h = (b - r) / dif + 2;
            }
            else if (max == b){
                h = (r - g) / dif + 4;
            }
            else {
                h = 0;
            }

            h *= 60;
            if (h < 0) {
                h += 360;
            }
        }

        float[] hsl = {h, s, l};
        return hsl;
    }
    public static String getColorCategory(float r, float g, float b) {

        float[] hsl = getHSLvalues(r, g, b);
        float h = hsl[0];
        float s = hsl[1];
        float l = hsl[2];


        if (l < 0.12) return "Black";
        if (l > 0.98) return "White";
        if (s < 0.2) return "Gray";

        if (h < 15) return "Red";
        if (h < 45) return "Orange";
        if (h < 75) return "Yellow";
        if (h < 90) return "Yellow Green";
        if (h < 135) return "Green";
        if (h < 150) return "Green Cyan";
        if (h < 165) return "Cyan Green";
        if (h < 195) return "Cyan";
        if (h < 210) return "Blue Cyan";
        if (h < 245) return "Blue";
        if (h < 265) return "Violet";
        if (h < 285) return "Purple";
        if (h < 315) return "Magenta";
        if (h < 345) return "Pink";
        return "Red";
    }

    public static int[][][] getColorPalettes(float r, float g, float b) {
        int[][][] palettes = {
                getMonochromatic(r,g,b),
                getAnalogous(r,g,b),
                getComplementaryDichromatic(r,g,b),
                getSplitTrichromatic(r,g,b)
        };
        return palettes;
    }

    private static int[][] getMonochromatic(float r, float g, float b){
        // hsl values
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
        float[] hsl = getHSLvalues(r, g, b);
        float h = hsl[0];

        float[] hsv_1 = {h, (float)0.40, (float)0.40};
        float[] hsv_2 = {h, (float)0.60, (float)0.60};
        float[] hsv_3 = {h, (float)0.80, (float)0.80};
        float[] hsv_4 = {h, (float)0.95, (float)0.95};

        int c1 = Color.HSVToColor(hsv_1);
        int c2 = Color.HSVToColor(hsv_2);
        int c3 = Color.HSVToColor(hsv_3);
        int c4 = Color.HSVToColor(hsv_4);

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                {Color.red(c1),Color.green(c1), Color.blue(c1)},
                {Color.red(c2),Color.green(c2), Color.blue(c2)},
                {Color.red(c3),Color.green(c3), Color.blue(c3)},
                {Color.red(c4),Color.green(c4), Color.blue(c4)}
        };

        return pallete;
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
    }

    private static int[][] getComplementaryDichromatic(float r, float g, float b){
        // hsl values
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
        float[] hsl = getHSLvalues(r, g, b);
        float h = hsl[0];

        float[] hsv_1 = {h, 0.60f, 0.80f};
        float[] hsv_2 = {(h+180)%360, 0.50f, 0.45f};
        float[] hsv_3 = {(h+180)%360, 0.80f, 0.80f};
        float[] hsv_4 = {(h+180)%360, 0.85f, 0.750f};

        int c1 = Color.HSVToColor(hsv_1);
        int c2 = Color.HSVToColor(hsv_2);
        int c3 = Color.HSVToColor(hsv_3);
        int c4 = Color.HSVToColor(hsv_4);

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                {Color.red(c1),Color.green(c1), Color.blue(c1)},
                {Color.red(c2),Color.green(c2), Color.blue(c2)},
                {Color.red(c3),Color.green(c3), Color.blue(c3)},
                {Color.red(c4),Color.green(c4), Color.blue(c4)}
        };

        return pallete;
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
    }

    private static int[][] getSplitTrichromatic(float r, float g, float b){
        // hsl values
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
        float[] hsl = getHSLvalues(r, g, b);
        float h = hsl[0];

        float[] hsv_1 = {(h+180+50)%360, 0.60f, 0.80f};
        float[] hsv_2 = {(h+180+50)%360, 0.80f, 0.50f};
        float[] hsv_3 = {(h+180)%360, 0.80f, 0.80f};
        float[] hsv_4 = {(h+180-50)%360, 0.80f, 0.50f};

        int c1 = Color.HSVToColor(hsv_1);
        int c2 = Color.HSVToColor(hsv_2);
        int c3 = Color.HSVToColor(hsv_3);
        int c4 = Color.HSVToColor(hsv_4);

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                {Color.red(c1),Color.green(c1), Color.blue(c1)},
                {Color.red(c2),Color.green(c2), Color.blue(c2)},
                {Color.red(c3),Color.green(c3), Color.blue(c3)},
                {Color.red(c4),Color.green(c4), Color.blue(c4)}
        };

        return pallete;
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
    }

    private static int[][] getAnalogous(float r, float g, float b){
        // hsl values
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
        float[] hsl = getHSLvalues(r, g, b);
        float h = hsl[0];

        float[] hsv_1 = {(h+45)%360, 0.90f, 0.50f};
        float[] hsv_2 = {(h+60)%360, 0.90f, 0.90f};
        float[] hsv_3 = {(h+310)%360, 0.90f, 0.90f};
        float[] hsv_4 = {(h+300)%360, 0.90f, 0.90f};

        int c1 = Color.HSVToColor(hsv_1);
        int c2 = Color.HSVToColor(hsv_2);
        int c3 = Color.HSVToColor(hsv_3);
        int c4 = Color.HSVToColor(hsv_4);

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                {Color.red(c1),Color.green(c1), Color.blue(c1)},
                {Color.red(c2),Color.green(c2), Color.blue(c2)},
                {Color.red(c3),Color.green(c3), Color.blue(c3)},
                {Color.red(c4),Color.green(c4), Color.blue(c4)}
        };

        return pallete;
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
    }


};
