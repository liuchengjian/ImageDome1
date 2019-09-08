package com.dqgb.imagedome;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideUtils {

    public static double divide(double left, double right) {
        double result = 0;
        BigDecimal bdLeft = new BigDecimal(String.valueOf(left));
        BigDecimal bdRight = new BigDecimal(String.valueOf(right));
        String strResult = bdLeft.divide(bdRight, 20, RoundingMode.HALF_UP).toString();
        result = Double.parseDouble(strResult);
        Log.e("1111111111111", "除法运算：" + left + "/" + right +
                "=\n" + strResult);
        return result;
    }
}
