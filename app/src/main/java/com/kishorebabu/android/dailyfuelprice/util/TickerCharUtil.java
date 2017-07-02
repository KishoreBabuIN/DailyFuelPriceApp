package com.kishorebabu.android.dailyfuelprice.util;

/**
 * Created by kishore on 02/07/17.
 */

public class TickerCharUtil {
    public static final char EMPTY_CHAR = (char) 0;

    public static char[] getDefaultListForINRCurrency() {
        final int indexOf0 = (int) '0';
        final int indexOfPeriod = (int) '.';
        final int indexOfSlash = (int) '/';

        final int start = 33;
        final int end = 256;

        final char[] charList = new char[end - start + 1];
        for (int i = start; i < indexOf0; i++) {
            charList[i - start] = (char) i;
        }

        // Special case EMPTY_CHAR and '.' <-> '/'
        charList[indexOf0 - start] = EMPTY_CHAR;
        charList[indexOfPeriod - start] = '/';
        charList[indexOfSlash - start] = '.';

        for (int i = indexOf0 + 1; i < end + 1; i++) {
            charList[i - start] = (char) (i - 1);
        }

        for (int i = 0; i < charList.length; i++) {
            if (charList[i] == '$') {
                charList[i] = '₹';
            }
        }

        return charList;
    }

}
