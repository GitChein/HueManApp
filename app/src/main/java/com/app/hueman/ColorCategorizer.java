package com.app.hueman;

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
};
