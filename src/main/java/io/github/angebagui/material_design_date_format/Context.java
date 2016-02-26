package io.github.angebagui.material_design_date_format;

/**
 * Created by angebagui on 15/02/2016.
 */
public enum Context {
    FUTURE(1),
    PAST(2),
    DISTANT_PAST(3),
    WEEKDAY(4),
    DURATION(5);

    private int value;

    private Context(int value){
        this.value = value;
    }



}
