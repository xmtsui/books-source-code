package numbercruncher.program13_2;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import numbercruncher.mathutils.BigFunctions;
import numbercruncher.piutils.PiFormula;

/**
 * PROGRAM 13-2: Arctangent Formulas for pi
 *
 * Compute pi with two arctangent formulas.
 */
public class PiArctan extends PiFormula
{
    private static final DecimalFormat DECIMAL_FORMAT =
                                new DecimalFormat("00");
    private static final SimpleDateFormat TIME_FORMAT =
                                new SimpleDateFormat("HH:mm:ss.SSS");

    /**
     * Compute the digits of pi using two arctangent formulas.
     * @param digits the number of digits of pi to compute
     */
    private void compute(int digits)
    {
        int  scale     = digits + 3;
        long startTime = System.currentTimeMillis();
        long markTime;

        System.out.println("digits = " + digits);
        System.out.println("scale  = " + scale);

        BigDecimal big1 = BigDecimal.valueOf(1);
        BigDecimal big4 = BigDecimal.valueOf(4);
        BigDecimal big8 = BigDecimal.valueOf(8);

        // --- First formula ---

        System.out.println("\n----- First arctan formula -----\n");
        System.out.println(timestamp(startTime) + " START TIME\n");

        System.out.println(timestamp(startTime) + " Initializing");
        markTime = System.currentTimeMillis();

        BigDecimal r5   = big1.divide(BigDecimal.valueOf(5), scale,
                                      BigDecimal.ROUND_HALF_EVEN);
        BigDecimal r239 = big1.divide(BigDecimal.valueOf(239), scale,
                                      BigDecimal.ROUND_HALF_EVEN);

        System.out.println(timestamp(markTime) +
                           " Computing arctan(1/5)");
        markTime = System.currentTimeMillis();

        BigDecimal arctan5 = BigFunctions.arctan(r5, scale);

        System.out.println(timestamp(markTime) +
                           " Computing arctan(1/239)");
        markTime = System.currentTimeMillis();

        BigDecimal arctan239 = BigFunctions.arctan(r239, scale);

        System.out.println(timestamp(markTime) + " Computing pi");
        markTime = System.currentTimeMillis();

        BigDecimal term = big4.multiply(arctan5)
                            .setScale(scale,
                                      BigDecimal.ROUND_HALF_EVEN)
                            .subtract(arctan239);
        BigDecimal pi = big4.multiply(term)
                            .setScale(digits, BigDecimal.ROUND_DOWN);

        System.out.println(timestamp(markTime) + " pi computed");
        printPi(pi.toString());

        System.out.println("\n" + timestamp(startTime) +
                           " TOTAL TIME");

        // --- Second formula ---

        startTime = System.currentTimeMillis();

        System.out.println("\n----- Second arctan formula -----\n");
        System.out.println(timestamp(startTime) + " START TIME\n");

        System.out.println(timestamp(startTime) + " Initializing");
        markTime = System.currentTimeMillis();

        BigDecimal r10  = big1.divide(BigDecimal.valueOf(10), scale,
                                      BigDecimal.ROUND_HALF_EVEN);
        BigDecimal r515 = big1.divide(BigDecimal.valueOf(515), scale,
                                      BigDecimal.ROUND_HALF_EVEN);
        r239 = big1.divide(BigDecimal.valueOf(239), scale,
                           BigDecimal.ROUND_HALF_EVEN);

        System.out.println(timestamp(markTime) +
                           " Computing arctan(1/10)");
        markTime = System.currentTimeMillis();

        BigDecimal arctan10 = BigFunctions.arctan(r10, scale);

        System.out.println(timestamp(markTime) +
                           " Computing arctan(1/515)");
        markTime = System.currentTimeMillis();

        BigDecimal arctan515 = BigFunctions.arctan(r515, scale);

        System.out.println(timestamp(markTime) +
                           " Computing arctan(1/239)");
        markTime = System.currentTimeMillis();

        arctan239 = BigFunctions.arctan(r239, scale);

        System.out.println(timestamp(markTime) + " Computing pi");
        markTime = System.currentTimeMillis();

        term =
            big8.multiply(arctan10)
                .setScale(scale, BigDecimal.ROUND_HALF_EVEN)
                .subtract(big4.multiply(arctan515)
                            .setScale(scale,
                                      BigDecimal.ROUND_HALF_EVEN))
                .subtract(arctan239);
        pi = big4.multiply(term)
                .setScale(digits, BigDecimal.ROUND_DOWN);

        System.out.println(timestamp(markTime) + " pi computed");
        printPi(pi.toString());

        System.out.println("\n" + timestamp(startTime) +
                           " TOTAL TIME");
    }

    /**
     * Main.
     * @param args the array of program arguments
     */
    public static void main(String args[])
    {
        PiArctan pi = new PiArctan();

        try {
            pi.compute(2035);
        }
        catch(Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
/*
Output:
digits = 2035
scale  = 2038

----- First arctan formula -----

02:16:56.680 (00:00:00) START TIME

02:16:56.680 (00:00:00) Initializing
02:16:57.620 (00:00:01) Computing arctan(1/5)
02:48:19.310 (00:31:22) Computing arctan(1/239)
02:57:35.650 (00:09:16) Computing pi
02:57:35.650 (00:00:00) pi computed

pi = 3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510
       58209 74944 59230 78164 06286 20899 86280 34825 34211 70679
       82148 08651 32823 06647 09384 46095 50582 23172 53594 08128
       48111 74502 84102 70193 85211 05559 64462 29489 54930 38196
       44288 10975 66593 34461 28475 64823 37867 83165 27120 19091
       45648 56692 34603 48610 45432 66482 13393 60726 02491 41273
       72458 70066 06315 58817 48815 20920 96282 92540 91715 36436
       78925 90360 01133 05305 48820 46652 13841 46951 94151 16094
       33057 27036 57595 91953 09218 61173 81932 61179 31051 18548
       07446 23799 62749 56735 18857 52724 89122 79381 83011 94912

       98336 73362 44065 66430 86021 39494 63952 24737 19070 21798
       60943 70277 05392 17176 29317 67523 84674 81846 76694 05132
       00056 81271 45263 56082 77857 71342 75778 96091 73637 17872
       14684 40901 22495 34301 46549 58537 10507 92279 68925 89235
       42019 95611 21290 21960 86403 44181 59813 62977 47713 09960
       51870 72113 49999 99837 29780 49951 05973 17328 16096 31859
       50244 59455 34690 83026 42522 30825 33446 85035 26193 11881
       71010 00313 78387 52886 58753 32083 81420 61717 76691 47303
       59825 34904 28755 46873 11595 62863 88235 37875 93751 95778
       18577 80532 17122 68066 13001 92787 66111 95909 21642 01989

       38095 25720 10654 85863 27886 59361 53381 82796 82303 01952
       03530 18529 68995 77362 25994 13891 24972 17752 83479 13151
       55748 57242 45415 06959 50829 53311 68617 27855 88907 50983
       81754 63746 49393 19255 06040 09277 01671 13900 98488 24012
       85836 16035 63707 66010 47101 81942 95559 61989 46767 83744
       94482 55379 77472 68471 04047 53464 62080 46684 25906 94912
       93313 67702 89891 52104 75216 20569 66024 05803 81501 93511
       25338 24300 35587 64024 74964 73263 91419 92726 04269 92279
       67823 54781 63600 93417 21641 21992 45863 15030 28618 29745
       55706 74983 85054 94588 58692 69956 90927 21079 75093 02955

       32116 53449 87202 75596 02364 80665 49911 98818 34797 75356
       63698 07426 54252 78625 51818 41757 46728 90977 77279 38000
       81647 06001 61452 49192 17321 72147 72350 14144 19735 68548
       16136 11573 52552 13347 57418 49468 43852 33239 07394 14333
       45477 62416 86251 89835 69485 56209 92192 22184 27255 02542
       56887 67179 04946 01653 46680 49886 27232 79178 60857 84383
       82796 79766 81454 10095 38837 86360 95068 00642 25125 20511
       73929 84896 08412 84886 26945 60424 19652 85022 21066 11863
       06744 27862 20391 94945 04712 37137 86960 95636 43719 17287
       46776 46575 73962 41389 08658 32645 99581 33904 78027 59009

       94657 64078 95126 94683 98352 59570 98258

02:57:36.640 (00:40:40) TOTAL TIME

----- Second arctan formula -----

02:57:36.690 (00:00:00) START TIME

02:57:36.690 (00:00:00) Initializing
02:57:37.900 (00:00:01) Computing arctan(1/10)
03:19:34.300 (00:21:56) Computing arctan(1/515)
03:27:41.160 (00:08:07) Computing arctan(1/239)
03:36:57.060 (00:09:16) Computing pi
03:36:57.060 (00:00:00) pi computed

pi = 3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510
       58209 74944 59230 78164 06286 20899 86280 34825 34211 70679
       82148 08651 32823 06647 09384 46095 50582 23172 53594 08128
       48111 74502 84102 70193 85211 05559 64462 29489 54930 38196
       44288 10975 66593 34461 28475 64823 37867 83165 27120 19091
       45648 56692 34603 48610 45432 66482 13393 60726 02491 41273
       72458 70066 06315 58817 48815 20920 96282 92540 91715 36436
       78925 90360 01133 05305 48820 46652 13841 46951 94151 16094
       33057 27036 57595 91953 09218 61173 81932 61179 31051 18548
       07446 23799 62749 56735 18857 52724 89122 79381 83011 94912

       98336 73362 44065 66430 86021 39494 63952 24737 19070 21798
       60943 70277 05392 17176 29317 67523 84674 81846 76694 05132
       00056 81271 45263 56082 77857 71342 75778 96091 73637 17872
       14684 40901 22495 34301 46549 58537 10507 92279 68925 89235
       42019 95611 21290 21960 86403 44181 59813 62977 47713 09960
       51870 72113 49999 99837 29780 49951 05973 17328 16096 31859
       50244 59455 34690 83026 42522 30825 33446 85035 26193 11881
       71010 00313 78387 52886 58753 32083 81420 61717 76691 47303
       59825 34904 28755 46873 11595 62863 88235 37875 93751 95778
       18577 80532 17122 68066 13001 92787 66111 95909 21642 01989

       38095 25720 10654 85863 27886 59361 53381 82796 82303 01952
       03530 18529 68995 77362 25994 13891 24972 17752 83479 13151
       55748 57242 45415 06959 50829 53311 68617 27855 88907 50983
       81754 63746 49393 19255 06040 09277 01671 13900 98488 24012
       85836 16035 63707 66010 47101 81942 95559 61989 46767 83744
       94482 55379 77472 68471 04047 53464 62080 46684 25906 94912
       93313 67702 89891 52104 75216 20569 66024 05803 81501 93511
       25338 24300 35587 64024 74964 73263 91419 92726 04269 92279
       67823 54781 63600 93417 21641 21992 45863 15030 28618 29745
       55706 74983 85054 94588 58692 69956 90927 21079 75093 02955

       32116 53449 87202 75596 02364 80665 49911 98818 34797 75356
       63698 07426 54252 78625 51818 41757 46728 90977 77279 38000
       81647 06001 61452 49192 17321 72147 72350 14144 19735 68548
       16136 11573 52552 13347 57418 49468 43852 33239 07394 14333
       45477 62416 86251 89835 69485 56209 92192 22184 27255 02542
       56887 67179 04946 01653 46680 49886 27232 79178 60857 84383
       82796 79766 81454 10095 38837 86360 95068 00642 25125 20511
       73929 84896 08412 84886 26945 60424 19652 85022 21066 11863
       06744 27862 20391 94945 04712 37137 86960 95636 43719 17287
       46776 46575 73962 41389 08658 32645 99581 33904 78027 59009

       94657 64078 95126 94683 98352 59570 98258

03:36:57.940 (00:39:21) TOTAL TIME
*/