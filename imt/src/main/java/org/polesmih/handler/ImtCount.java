package org.polesmih.handler;

import static org.polesmih.bot.settings.MessagesConst.*;

public class ImtCount {

    public static double imt(String s1, String s2) {
        int weight = Integer.parseInt(s1);
        int height = Integer.parseInt(s2);

        return (((double) weight / (height * height)) * 10000);
    }

    public static String description(String s1, String s2) {

        double imt = imt(s1, s2);
        String imtRes = String.format("%.1f", imt);


        String description = null;

        if (imt < 18.5) {
            description = "ИМТ = " + imtRes + "\n" + DEFICIT;

        } else if (imt >= 18.5 && imt <= 24.9) {
            description = "ИМТ = " + imtRes + "\n" + NORMAL;

        } else if (imt >= 25.0 && imt <= 29.9) {
            description = "ИМТ = " + imtRes + "\n" + EXTRA;

        } else if (imt >= 30.0 && imt <= 34.9) {
            description = "ИМТ = " + imtRes + "\n" + FAT_ONE;

        } else if (imt >= 35.0 && imt <= 39.9) {
            description = "ИМТ = " + imtRes + "\n" + FAT_TWO;

        } else if (imt >= 40) {
            description = "ИМТ = " + imtRes + "\n" + FAT_THREE;
        }

        return description;

    }

}
