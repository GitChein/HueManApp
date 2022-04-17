package com.app.hueman;

import androidx.core.graphics.ColorUtils;

import java.util.Vector;


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
    public static int[] hsvToRgb(float hue, float saturation, float value) {

        int h = (int)(hue/360 * 6);
        float f = hue * 6 - h;
        float p = value * (1 - saturation);
        float q = value * (1 - f * saturation);
        float t = value * (1 - (1 - f) * saturation);

        switch (h) {
            case 0: return rgbToArray(value, t, p);
            case 1: return rgbToArray(q, value, p);
            case 2: return rgbToArray(p, value, t);
            case 3: return rgbToArray(p, q, value);
            case 4: return rgbToArray(t, p, value);
            case 5: return rgbToArray(value, p, q);
            default: throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
        }
    }

    public static int[] rgbToArray(float r, float g, float b) {
        int rs = (int)(r * 256);
        int gs = (int)(g * 256);
        int bs = (int)(b * 256);
        int[] rgb = {rs, gs, bs};
        return rgb;
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

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                hsvToRgb(h, 0.30f, 0.30f),
                hsvToRgb(h, 0.50f, 0.50f),
                hsvToRgb(h, 0.60f, 0.75f),
                hsvToRgb(h, 0.90f, 0.90f)
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

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                hsvToRgb(h, 0.60f, 0.80f),
                hsvToRgb(h, 0.50f, 0.45f),
                hsvToRgb((h+180)%360, 0.80f, 0.80f),
                hsvToRgb((h+180)%360, 0.75f, 0.60f)
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

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                hsvToRgb(h, 0.60f, 0.80f),
                hsvToRgb((h+180+30)%360, 0.50f, 0.50f),
                hsvToRgb((h+180)%360, 0.80f, 0.80f),
                hsvToRgb((h+180-30)%360, 0.50f, 0.50f)
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

        int[][] pallete = {
                {(int) r, (int) g, (int) b},
                hsvToRgb((h+15)%360, 0.50f, 0.50f),
                hsvToRgb((h+30)%360, 0.50f, 0.50f),
                hsvToRgb((h+330)%360, 0.50f, 0.50f),
                hsvToRgb((h+345)%360, 0.50f, 0.50f)
        };

        return pallete;
        // for monochromatic we just change saturation and lightness
        // we want a darker muted
    }


};
