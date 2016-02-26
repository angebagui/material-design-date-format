package io.github.angebagui.material_design_date_format;

/**
 * Created by angebagui on 15/02/2016.
 */
public class Utils {

    public static String get(int number){
        if (number<10){
            return "0"+number;
        }else {
            return String.valueOf(number);
        }
    }
    public static String getHalfdayOfDay(int number){
        return null;
    }
}
