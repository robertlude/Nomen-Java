
/////////////////////////////////////
//                            _    //
//  Nomen                    ( )   //
//                          ( O )  //
//    A number namer and     (_)   //
//      beautifier            | _  //
//                            |/_/ //
//    Robert Lude           _ |    //
//      <rob@ertlu.de>     \_\|    //
//                            |    //
/////////////////////////////////////

package de.ertlu.rob.Nomen;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Nomen {

    //== class methods

    private static BigDecimal BD_100 = new BigDecimal("100");

    public static String format(final BigDecimal value) {
        return format(value, EnglishShortScale);
    }
    public static String format(final BigDecimal value, final GroupingMode mode) {
        return format(value, mode, 0);
    }
    public static String format(final BigDecimal value, final GroupingMode mode, final int decimalPlaces) {
        if ( value.compareTo(BigDecimal.ZERO) == 0 ) return BigDecimal.ZERO.setScale(decimalPlaces).toString() + mode.labelMid;

        final BigDecimal lowerLimit = BigDecimal.ONE.scaleByPowerOfTen(-decimalPlaces);
        final String prefix = value.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
        final BigDecimal groupSizeBD = BigDecimal.valueOf(mode.groupSize);
        BigDecimal workingValue = value.abs();

        if ( workingValue.compareTo(lowerLimit) >= 0 && workingValue.compareTo(groupSizeBD) < 0 ) {
            return prefix + workingValue.setScale(decimalPlaces, RoundingMode.DOWN).toPlainString() + mode.labelMid;
        }

        String digits;
        String suffix;
        int group = -1;
        BigDecimal integerPart;
        BigDecimal fractionalPart;

        if ( workingValue.compareTo(BigDecimal.ONE) < 0 ) {
            while ( workingValue.compareTo(BigDecimal.ONE) < 0 ) {
                ++group;
                workingValue = workingValue.multiply(groupSizeBD);
            }

            integerPart = workingValue.setScale(0, RoundingMode.DOWN);
            fractionalPart = workingValue.subtract(integerPart);

            if ( integerPart.compareTo(BD_100) >= 0 ) workingValue = integerPart.add(fractionalPart.setScale(1, RoundingMode.DOWN));
            else if ( integerPart.compareTo(BigDecimal.TEN) >= 0 ) workingValue = integerPart.add(fractionalPart.setScale(2, RoundingMode.DOWN));
            else workingValue = integerPart.add(fractionalPart.setScale(2, RoundingMode.DOWN));

            digits = workingValue.stripTrailingZeros().toPlainString();
            suffix = group >= mode.labelSmall.size() ? "e-" + (group * 3 + 3) : mode.labelSmall.get(group);
        } else {
            while ( workingValue.compareTo(groupSizeBD) >= 0 ) {
                ++group;
                workingValue = workingValue.divide(groupSizeBD, 3, RoundingMode.DOWN);
            }

            integerPart = workingValue.setScale(0, RoundingMode.DOWN);
            fractionalPart = workingValue.subtract(integerPart);

            if ( integerPart.compareTo(BD_100) >= 0 ) workingValue = integerPart.add(fractionalPart.setScale(1, RoundingMode.DOWN));
            else if ( integerPart.compareTo(BigDecimal.TEN) >= 0 ) workingValue = integerPart.add(fractionalPart.setScale(2, RoundingMode.DOWN));
            else workingValue = integerPart.add(fractionalPart.setScale(2, RoundingMode.DOWN));

            digits = workingValue.stripTrailingZeros().toPlainString();
            suffix = group >= mode.labelLarge.size() ? "e" + (group * 3 + 3) : mode.labelLarge.get(group);
        }

        return prefix + digits + suffix;
    }

    //== grouping mode class

    public static class GroupingMode {
        public final String labelMid;
        public final List<String> labelLarge;
        public final List<String> labelSmall;
        public final int groupSize;

        public GroupingMode(final int groupSize, final String labelMid, final String[] labelLarge, final String[] labelSmall) {
            this(groupSize, labelMid, Arrays.asList(labelLarge), Arrays.asList(labelSmall));
        }
        public GroupingMode(final int groupSize, final String labelMid, final List<String> labelLarge, final List<String> labelSmall) {
            this.groupSize = groupSize;
            this.labelMid = labelMid;
            this.labelLarge = Collections.unmodifiableList(labelLarge);
            this.labelSmall = Collections.unmodifiableList(labelSmall);
        }
    }

    //== grouping modes

    public static final GroupingMode SIPrefixAbbreviated = new GroupingMode(
            1000,
            "",
            new String[] {
                    "k",
                    "M",
                    "G",
                    "B",
                    "T",
                    "P",
                    "E",
                    "Z",
                    "Y"
            },
            new String[] {
                    "m",
                    "µ",
                    "n",
                    "p",
                    "f",
                    "a",
                    "z",
                    "y"
            }
    );

    public static final GroupingMode SIPrefix = new GroupingMode(
            1000,
            "",
            new String[] {
                    " kilo",
                    " mega",
                    " giga",
                    " tera",
                    " peta",
                    " exa",
                    " zeta",
                    " yotta"
            },
            new String[] {
                    " milli",
                    " micro",
                    " nano",
                    " pico",
                    " femto",
                    " atto",
                    " zepto",
                    " yocto"
            }
    );

    public static final GroupingMode EnglishShortScale = new GroupingMode(
            1000,
            "",
            new String[] {
                    " thousand",
                    " million",
                    " billion",
                    " trillion",
                    " quadrillion",
                    " quintillion",
                    " sextillion",
                    " septillion",
                    " octillion",
                    " nonillion",
                    " decillion",
                    " undecillion",
                    " duodecillion",
                    " tredecillion",
                    " quattuordecillion",
                    " quindecillion",
                    " sexdecillion",
                    " septendecillion",
                    " octodecillion",
                    " novemdecillion",
                    " vigintillion"
            },
            new String[] {
                    " thousandth",
                    " millionth",
                    " billionth",
                    " trillionth",
                    " quadrillionth",
                    " quintillionth",
                    " sextillionth",
                    " septillionth",
                    " octillionth",
                    " nonillionth",
                    " decillionth",
                    " undecillionth",
                    " duodecillionth",
                    " tredecillionth",
                    " quattuordecillionth",
                    " quindecillionth",
                    " sexdecillionth",
                    " septendecillionth",
                    " octodecillionth",
                    " novemdecillionth",
                    " vigintillionth"
            }
    );

    public static final GroupingMode EnglishLongScale = new GroupingMode(
            1000,
            "",
            new String[] {
                    " thousand",
                    " million",
                    " milliard",
                    " billion",
                    " billiard",
                    " trillion",
                    " trilliard",
                    " quadrillion",
                    " quadrilliard",
                    " quintillion",
                    " quintilliard",
                    " sextillion",
                    " sextilliard",
                    " septillion",
                    " septilliard",
                    " octillion",
                    " octilliard",
                    " nonillion",
                    " nonilliard",
                    " decillion",
                    " decilliard",
                    " undecillion",
                    " undecilliard",
                    " duodecillion",
                    " duodecilliard",
                    " tredecillion",
                    " tredecilliard",
                    " quattuordecillion",
                    " quattuordecilliard",
                    " quindecillion",
                    " quindecilliard",
                    " sexdecillion",
                    " sexdecilliard",
                    " septendecillion",
                    " septendecilliard",
                    " octodecillion",
                    " octodecilliard",
                    " novemdecillion",
                    " novemdecilliard",
                    " vigintillion",
                    " vigintilliard"
            },
            new String[]{
                    " thousandth",
                    " millionth",
                    " milliardth",
                    " billionth",
                    " billiardth",
                    " trillionth",
                    " trilliardth",
                    " quadrillionth",
                    " quadrilliardth",
                    " quintillionth",
                    " quintilliardth",
                    " sextillionth",
                    " sextilliardth",
                    " septillionth",
                    " septilliardth",
                    " octillionth",
                    " octilliardth",
                    " nonillionth",
                    " nonilliardth",
                    " decillionth",
                    " decilliardth",
                    " undecillionth",
                    " undecilliardth",
                    " duodecillionth",
                    " duodecilliardth",
                    " tredecillionth",
                    " tredecilliardth",
                    " quattuordecillionth",
                    " quattuordecilliardth",
                    " quindecillionth",
                    " quindecilliardth",
                    " sexdecillionth",
                    " sexdecilliardth",
                    " septendecillionth",
                    " septendecilliardth",
                    " octodecillionth",
                    " octodecilliardth",
                    " novemdecillionth",
                    " novemdecilliardth",
                    " vigintillionth",
                    " vigintilliardth"
            }
    );

    public static final GroupingMode BytesDecimal = new GroupingMode(
            1000,
            " bytes",
            new String[]{
                    " kilobytes",
                    " megabytes",
                    " gigabytes",
                    " terabytes",
                    " petabytes",
                    " exabytes",
                    " zettabytes",
                    " yottabytes"
            },
            new String[]{}
    );

    public static final GroupingMode BytesDecimalAbbreviated = new GroupingMode(
            1000,
            "B",
            new String[]{
                    "kB",
                    "MB",
                    "GB",
                    "TB",
                    "PB",
                    "EB",
                    "ZB",
                    "YB"
            },
            new String[]{}
    );

    public static final GroupingMode BytesBinary = new GroupingMode(
            1024,
            " bytes",
            new String[]{
                    " kibibytes",
                    " mebibytes",
                    " gibibytes",
                    " tebibytes",
                    " pebibytes",
                    " exbibytes",
                    " zebibytes",
                    " yobibytes"
            },
            new String[]{}
    );

    public static final GroupingMode BytesBinaryAbbreviated = new GroupingMode(
            1024,
            "B",
            new String[]{
                    "KiB",
                    "MiB",
                    "GiB",
                    "TiB",
                    "PiB",
                    "EiB",
                    "ZiB",
                    "YiB"
            },
            new String[]{}
    );
}
