package de.thomasinc.dsaapp.util;
import android.text.InputFilter;
import android.text.Spanned;

/**
 * Class that implements InputFilter in order to set possible minimum und maximum values for an
 * input field (inclusive)
 * Takes either two Strings or two Integers as input
 * @link https://capdroidandroid.wordpress.com/
 * @author Created by npatel on 4/5/2016.
 */

public class MinMaxValueFilter implements InputFilter {
    private int mIntMin, mIntMax;

    public MinMaxValueFilter(int minValue, int maxValue) {
        this.mIntMin = minValue;
        this.mIntMax = maxValue;
    }

    public MinMaxValueFilter(String minValue, String maxValue) {
        this.mIntMin = Integer.parseInt(minValue);
        this.mIntMax = Integer.parseInt(maxValue);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(mIntMin, mIntMax, input))
                return null;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
