package com.example.NoteApp.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

public class StrUtil {

    /**
     * 如果字符串为空则用默认值
     *
     * @param sStr
     * @param sDefault
     * @return String
     */
    public final static String toStr(Object sStr, String sDefault) {
        return sStr == null ? sDefault : sStr.toString().trim();
    }

    /**
     * 如果字符串为空则用默认值
     *
     * @param sStr
     * @return String
     */
    public final static String toStr(Object sStr) {
        return sStr == null ? "" : sStr.toString().trim();
    }

    /**
     * 如果字符串为空则用默认值
     *
     * @param sStr
     * @param sDefault
     * @return String
     */
    public final static String toStrNOTrim(Object sStr, String sDefault) {
        return sStr == null ? sDefault : sStr.toString();
    }

    /**
     * 把字符串转换成整数
     *
     * @param sValue
     * @param nDefault
     * @return int
     */
    public final static int toInt(String sValue, int nDefault) {
        if (sValue == null || sValue.isEmpty())
            return nDefault;
        try {
            return Integer.valueOf(sValue);
        } catch (Exception ex) {
            return nDefault;
        }

    }

    /**
     * 把字符串转换成Long数
     *
     * @param sValue
     * @param nDefault
     * @return int
     */
    public final static Long toLong(String sValue, Long nDefault) {
        if (sValue == null || sValue.isEmpty())
            return nDefault;
        try {
            return Long.valueOf(sValue);
        } catch (Exception ex) {
            return nDefault;
        }

    }

    /**
     * 把字符串转换成小数
     *
     * @param sValue
     * @param nDefault
     * @return Float
     */
    public final static Float toFloat(String sValue, Float nDefault) {
        if (sValue == null || sValue.isEmpty())
            return nDefault;
        return Float.valueOf(sValue);
    }

    /**
     * 把字符串转换成双精度小数
     *
     * @param sValue
     * @param nDefault
     * @return Double
     */
    public final static Double toDouble(String sValue, Double nDefault) {
        if (sValue == null || sValue.isEmpty())
            return nDefault;
        return Double.valueOf(sValue);
    }

    /**
     * 把字符串转换成浮点
     *
     * @param sValue
     * @param nDefault
     * @return float
     */
    public final static float toFloat(String sValue, float nDefault) {
        if (sValue == null || sValue.isEmpty())
            return nDefault;
        return Float.valueOf(sValue);
    }

    /**
     * 把字符串转换成 布尔型
     *
     * @param sValue
     * @param nDefault
     * @return boolean
     */
    public final static boolean toBoolean(String sValue, boolean nDefault) {
        if (sValue == null || sValue.isEmpty())
            return nDefault;
        return Boolean.valueOf(sValue);
    }

    /**
     * 数据显示小数点问题
     *
     * @param dd
     * @return
     */
    public final static String doubleToStr(Double dd) {
        if (null == dd) {
            return "";
        }
        double d = dd;
        double c = d - (int) d;
        if (c == 0)
            return (int) d + "";
        else {
            BigDecimal b = new BigDecimal(d);
            BigDecimal one = new BigDecimal("1");
            c = b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return String.valueOf(c);
        }
    }

    public final static String floatToStr(float d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

    /**
     * 字符串 ： 解决数据库入库时候的非法符号 转 HTML 符号
     *
     * @param sStr
     * @param sDefault
     * @return
     */
    public final static String toHtml(Object sStr, String sDefault) {
        String sValue = sStr == null ? sDefault : sStr.toString();
        sValue = sValue.replaceAll("<", "&lt;");
        sValue = sValue.replaceAll(">", "&gt;");
        sValue = sValue.replaceAll("&", "&amp;");
        return sValue;
    }

    /**
     * 清空html标签
     *
     * @param str
     * @return
     */
    public static String delHtmlTag(String str) {
        String newstr = "";
        newstr = str.replaceAll("<[.[^>]]*>", "");
        newstr = newstr.replaceAll("&nbsp;", "");
        return newstr;
    }

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param value
     * @return 为空则返回true，否则返回false
     */
    public final static boolean isNullOrEmpty(String value) {
        return value == null || "".equals(value.trim())
                || "null".equals(value.trim());
    }

    /**
     * 获取某一区间随机整数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        if (min == max) {
            return max;
        }
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 以UUID为基础，获取32字符串
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 产生验证
     *
     * @param count    长度
     * @param isLetter
     * @return
     */
    public static String getVerificationCode(int count, Boolean isLetter) {
        String[] beforeLetterShuffle = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] beforeNumberShuffle = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "8"};
        List<String> list = Arrays.asList(isLetter ? beforeLetterShuffle : beforeNumberShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(0, count);
        return result;
    }

    /**
     * 截取字符
     *
     * @param str
     * @param lastindex 字符位置
     * @return
     */
    public static String getSubstring(String str, int lastindex) {
        String newStr = str;
        if (!StrUtil.isNullOrEmpty(str)) {
            if (str.length() > lastindex) {
                newStr = str.substring(0, lastindex) + "...";
            }
        }
        return newStr;
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * 对象转化为Map
     *
     * @param obj
     * @return
     */
    public static Map objectToMap(Object obj) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

    /**
     * Map 转对象
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    /**
     * 字符串向左截取
     *
     * @param str
     * @param len
     * @return
     */
    public static String leftStr(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    /**
     * 字符串向右截取
     *
     * @param str
     * @param len
     * @return
     */
    public static String rightStr(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    /**
     * 根据不同名字的长度返回不同的显示数据
     * @param str
     * @return
     */
    public static String checkNameLength(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 1) {
            return str;
        } else if (str.length() == 2) {
            return "*" + StrUtil.rightStr(str, 1);
        } else if (str.length() == 3) {
            return StrUtil.leftStr(str, 1) + "*" + StrUtil.rightStr(str, 1);
        } else if (str.length() == 4) {
            return StrUtil.leftStr(str, 1) + "**" + StrUtil.rightStr(str, 1);
        }
        return str;
    }
}
