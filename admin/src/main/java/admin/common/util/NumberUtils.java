package admin.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.text.NumberFormat;

public class NumberUtils {
	/**
     * 3자리 마다 comma 로 구분지어 주는 문자열 생성
     * @param val
     * @return
     */
    public static String formatSeperatedByComma(long val) {
        NumberFormat format = NumberFormat.getNumberInstance();
        return format.format(val);
    }
    
    /**
     * List에 있어서, 특정 필드의 합을 구한다.
     *
     * @param list
     * @param fieldName
     * @param doFormat
     * @return
     */
    public static String summaryField(List list, String fieldName, boolean doFormat) {
        if (list == null || list.size() == 0) {
            return "";
        } else {
            return summaryField(list.toArray(), fieldName, doFormat);
        }
    }

    /**
     * List에 있어서, 특정 필드의 합을 구한후에 세자리마다 코마를 찍어준다. <br>
     * summaryField(list, fieldName, true)와 동일
     *
     * @param list
     * @param fieldName
     * @return
     */
    public static String summaryField(List list, String fieldName) {
        return summaryField(list, fieldName, true);
    }

    /**
     * 배열에 있어서, 특정 필드의 합을 구한다. <br>
     * 배열중에 null 또는 공백문자(" ")가 있는 것은 무시하고 합을 구한다.
     *
     * @param arr
     * @param fieldName
     * @param doFormat
     * @return
     */
    public static String summaryField(Object[] arr, String fieldName, boolean doFormat) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return "";
        }

        try {

            Method m = null;
            String mName = "get" + fieldName.substring(0, 1).toUpperCase()
                           + fieldName.substring(1);

            double sum = 0;

            Object tmp = arr[0];
            m = tmp.getClass().getMethod(mName, null);
            Object val = m.invoke(tmp, null);
            boolean isDouble = false;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    continue;
                }

                val = m.invoke(arr[i], null);

                // null 또는 ""일땐 통과
                if (val == null || val.toString().trim().length() == 0) {
                    continue;
                }

                // 쉼표(,)는 제거하고
                String strVal = StringUtils.replace(val.toString().trim(), ",", "");
                BigDecimal bd = new BigDecimal(strVal);
                sum += bd.doubleValue();
            }

            if (doFormat) {
                return formatNumber(sum);
            } else {
                return sum + "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 배열에 있어서, 특정 필드의 합을 구한후에 세자리마다 코마를 찍어준다. <br>
     * summaryField(arr, fieldName, true)와 동일
     *
     * @param arr
     * @param fieldName
     * @return
     */
    public static String summaryField(Object[] arr, String fieldName) {
        return summaryField(arr, fieldName, true);
    }

    /**
     * BigDecimal을 세자리마다 코마(,)를 찍어준다.
     *
     * @param bd
     * @return
     */
    public static String formatNumber(BigDecimal bd) {
        if (bd == null) {
            return "";
        }

        try {
            return formatNumber(bd.doubleValue());
        } catch (Exception e) {
            return bd.toString();
        }
    }

    /**
     * 숫자를 세자리마다 코마(,) 를 찍어준다.<br>
     *    CommUtil.formatNumber(1234567890.123)
     * @param d
     * @return
     */
    public static String formatNumber(double d) {
        boolean hasPoint = hasPoint(d);
        DecimalFormat formatter = null;
        if (hasPoint) {
            formatter = new DecimalFormat("#,##0.#########");
        } else {
            formatter = new DecimalFormat("#,##0");
        }
        return formatter.format(d);
    }

    /**
     * String을 받아서 trim()후 숫자형식으로 변환해서 세자리마다 코마(,) 를 찍어준다.<br>
     * (예:CommUtil.formatNumber("1234567890.123");
     * @param str
     * @return
     */
    public static String formatNumber(String str) {
        if (str == null) {
            return "";
        }

        // double이라고 가정하고서 처리
        str = StringUtils.replace(str.trim(), ",", "");
        try {
            double d = Double.parseDouble(str);
            return formatNumber(new BigDecimal(d));
        } catch (Exception e) {
            return str;
        }
    }

    /**
     * 소숫점 이하를 가지고 있는지 체크
     * Number formatting할때 이용.
     *
     * @param d
     * @return
     */
    private static boolean hasPoint(double d) {
        return d != (long) d;
    }

}
