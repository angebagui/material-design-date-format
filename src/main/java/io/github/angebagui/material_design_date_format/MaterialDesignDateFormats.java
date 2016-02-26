package io.github.angebagui.material_design_date_format;


import org.javatuples.Pair;
import org.joda.time.*;

import java.util.Locale;

/**
 * Created by angebagui on 06/02/2016.
 */
public class MaterialDesignDateFormats {

    //Time and date ranges
    /**
     * Date and time ranges are formatted based on the following:
     * The year is the same on both sides of the range
     * It’s the current year on both sides of the range
     * Whether both times have the same AM or PM
     */

    /**
     *
     * Separate a range of dates or times with an en dash, without spaces.
     * Add spaces when spelling out months, or to remove ambiguity.
     *
     * 8:00 AM–12:30 PM
     * 6 Jan – 2 Feb
     *
     * @param dateDepart
     * @param dateArrivee
     * @return
     */
    public static String enDashRangeElement(long dateDepart,long dateArrivee){
        StringBuilder builder = new StringBuilder();

        return builder.toString();
    }
    //  Date and time modifications by context
    /**
     * Include time to a future day or date : 10 Jan, 08:00
     *
     * @param millis
     * @return
     */
    public static String futureContext(long millis, Locale locale){
        StringBuilder builder = new StringBuilder();
        DateTime dateTime = new DateTime(millis);
           YearMonth yearMonth= YearMonth.fromDateFields(dateTime.toDate());
           builder.append(Utils.get(dateTime.getDayOfMonth()));
           builder.append(" ");
           builder.append(yearMonth.monthOfYear().getAsShortText(locale));
           builder.append(",");
           builder.append(" ");
           builder.append(Utils.get(dateTime.getHourOfDay()));
           builder.append(":");
           builder.append(Utils.get(dateTime.getMinuteOfHour()));


        return builder.toString();
    }
    /**
     * When referring to a past time, display both date and time. : Reminded Jan 5, 7:16 AM
     *
     * @param millis
     * @return
     */
    public static String pastContext(long millis,Locale locale){
        StringBuilder builder = new StringBuilder();
        DateTime dateTime = new DateTime(millis);
        YearMonth yearMonth= YearMonth.fromDateFields(dateTime.toDate());
        builder.append(yearMonth.monthOfYear().getAsShortText(locale));
        builder.append(" ");
        builder.append(Utils.get(dateTime.getDayOfMonth()));

        builder.append(",");
        builder.append(" ");
        builder.append(Utils.get(dateTime.getHourOfDay()));
        builder.append(":");
        builder.append(Utils.get(dateTime.getMinuteOfHour()));


        return builder.toString();
    }
    /**
     * Omit the time for events in the distant past : 3 Jan
     *
     * @param millis
     * @return
     */
    public static String distancePastContext(long millis, Locale locale){
        StringBuilder builder = new StringBuilder();
        DateTime dateTime = new DateTime(millis);
        YearMonth yearMonth= YearMonth.fromDateFields(dateTime.toDate());
        builder.append(Utils.get(dateTime.getDayOfMonth()));
        builder.append(" ");
        builder.append(yearMonth.monthOfYear().getAsShortText(locale));

        return builder.toString();
    }
    /**
     * When referring to a day of the week, such as for Calendar invites,
     * display the abbreviated day separated by a comma. : Mon, Jan 10, 8:00 AM
     *
     * @param millis
     * @return
     */
    public static String weekdayContext(long millis,Locale locale){
        StringBuilder builder = new StringBuilder();
        DateTime dateTime = new DateTime(millis);
        YearMonth yearMonth= YearMonth.fromDateFields(dateTime.toDate());

        builder.append(dateTime.dayOfWeek().getAsShortText(locale));
        builder.append(",");
        builder.append(" ");
        builder.append(yearMonth.monthOfYear().getAsShortText(locale));
        builder.append(" ");
        builder.append(Utils.get(dateTime.getDayOfMonth()));
        builder.append(",");
        builder.append(" ");
        if (locale.equals(Locale.ENGLISH)){
            builder.append(dateTime.getHourOfDay());
            builder.append(":");
            builder.append(Utils.get(dateTime.getMinuteOfHour()));
            builder.append(" ");
            // TODO:: Add AM or PM
            //DateTimeFormat.forPattern(pattern).withLocale(Locale.FRANCE).print(dt);
        }else {
            builder.append(Utils.get(dateTime.getHourOfDay()));
            builder.append(":");
            builder.append(Utils.get(dateTime.getMinuteOfHour()));
        }



        return builder.toString();
    }
    /**
     * Show the duration of a recording, like audio or video, in the format H:MM:SS.
     * Omit hours or seconds if they don’t apply.

     *Use the same format across the same context. A video labelled  “3:15” referring to
     *hours and minutes, should not use that time format later on that page to refer to
     *  minutes and seconds. : 0:30 1:01:05
     *
     * @param startMillis
     * @param endMillis
     * @return
     */
    public static Pair<String,String> durationContext(long startMillis, long endMillis){
        StringBuilder builder = new StringBuilder();
        DateTime dateTime = new DateTime(startMillis);
        builder.append(dateTime.getHourOfDay());
        builder.append(":");
        builder.append(Utils.get(dateTime.getMinuteOfHour()));
        if (dateTime.getSecondOfMinute()>0){
            builder.append(":");
            builder.append(Utils.get(dateTime.getSecondOfMinute()));
        }
        String startDate = builder.toString();

        DateTime dateTime1 = new DateTime(startMillis);
        StringBuilder builder1 = new StringBuilder();
        builder1.append(dateTime1.getHourOfDay());
        builder1.append(":");
        builder1.append(dateTime1.getMinuteOfHour());
        if (dateTime1.getSecondOfMinute()>0){
            builder1.append(":");
            builder1.append(Utils.get(dateTime1.getSecondOfMinute()));
        }
        String endDate = builder1.toString();
        return new Pair<String, String>(startDate,endDate);
    }

    private static boolean currentYear(long millis){
        DateTime dateTime = new DateTime(millis);
        Years dateYears = Years.years(dateTime.year().get());
        Years currentDateYears = Years.years(DateTime.now().year().get());
        return !dateYears.isGreaterThan(currentDateYears) || !dateYears.isLessThan(currentDateYears);
    }
    public static void main(String [] args){
        long startMillis = System.currentTimeMillis();
        long endMillis = System.currentTimeMillis()+1000000;
       Pair<String, String> duration = durationContext(startMillis, endMillis);
        String futureContext = weekdayContext(System.currentTimeMillis(),Locale.ENGLISH);
        System.out.print(String.format("Start at %s and End at %s",duration.getValue0(),duration.getValue1()));
    }
}
