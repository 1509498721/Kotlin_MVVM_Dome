package com.thesame.baselibrary.utlis

import android.text.TextUtils
import java.math.RoundingMode
import java.security.MessageDigest
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.regex.Pattern
import kotlin.experimental.and

/**
 * @Description: 字符类型转换工具
 * Created by whz  on 2019-06-27
 */
class StringUtil {
    fun snumberFormat(unm: Double): String {
        val df = DecimalFormat("#.00")
        return df.format(unm)
    }

    companion object {
        /**
         * 字符串是否为空
         *
         * @param str
         * @return
         */
        fun isEmpty(str: String?): Boolean {
            return str == null || str.length == 0
        }

        fun toString(obj: Any?): String {
            return obj?.toString() ?: ""
        }

        fun toInt(obj: Any?): Int {
            return if (obj == null || "" == obj || "null" == obj) {
                -1
            } else {
                try {
                    obj.toString().toInt()
                } catch (e: NumberFormatException) {
                    -1
                }
            }
        }

        fun toShort(obj: Any?): Short {
            return if (obj == null || "" == obj || "null" == obj) {
                -1
            } else {
                obj.toString().toShort()
            }
        }

        fun toCount(obj: Any?): Int {
            return if (obj == null || "" == obj || "null" == obj) {
                0
            } else {
                obj.toString().toInt()
            }
        }





        /**
         * 保留两位小数
         * @param num
         * @return
         */
        fun double2Decimal(num: Double): String {
            val df = DecimalFormat()
            df.maximumFractionDigits = 2
            df.groupingSize = 0
            df.roundingMode = RoundingMode.FLOOR
            val style = "#,##0.00" // 定义要显示的数字的格式
            df.applyPattern(style)
            return df.format(num)
        }

        /**
         * 保留两位小数
         * @param num
         * @return
         */
        fun float2Decimal(num: Float): String {
            val df = DecimalFormat()
            df.maximumFractionDigits = 2
            df.groupingSize = 0
            df.roundingMode = RoundingMode.FLOOR
            val style = "#,##0.00" // 定义要显示的数字的格式
            df.applyPattern(style)
            return df.format(num.toDouble())
        }

        /**
         * 将对象转换成Long型空对象默认装换成0
         *
         * @param obj
         * @return
         */
        fun toLong(obj: Any?): Long {
            return if (obj == null || "" == obj) {
                -1L
            } else {
                try {
                    obj.toString().toLong()
                } catch (e: NumberFormatException) {
                    -1L
                }
            }
        }

        /**
         * 将对象转换成boolean类型,默认为false
         *
         * @param obj
         * @return
         */
        fun toBoolean(obj: Any?): Boolean {
            return if (obj == null || "" == obj) {
                false
            } else java.lang.Boolean.valueOf(obj.toString())
        }

        fun checkStr(str: String?): Boolean {
            var bool = true
            if (str == null || "" == str.trim { it <= ' ' }) bool = false
            return bool
        }

        fun buildFirstChar(str: String?): String? {
            return if (str == null) null else str.substring(0, 1).toLowerCase() + str.substring(1)
        }

        fun double2point(ff: Double): Double {
            val j = Math.round(ff * 10000).toInt()
            return j.toDouble() / 100.00
        }

        fun delSpaceString(d: String?): String {
            var ret = ""
            if (d != null) {
                ret = d.trim { it <= ' ' }
            }
            return ret
        }

        /**
         * 数据定长输出
         *
         * @param pattern 长度及其格式（例如：定长�?0位，不足则前面补零，那么pattern�?0000000000"�?
         * @param number
         * @return
         */
        fun getDecimalFormat(pattern: String, number: String?): String {
            val myformat = DecimalFormat()
            myformat.applyPattern(pattern)
            var num = (toInt(number).toString() + "").toInt()
            if ((num.toString() + "").length > pattern.length) {
                val newNumber = (num.toString() + "").substring(0, pattern.length - 1)
                num = newNumber.toInt()
            }
            return myformat.format(num.toLong())
        }

        fun formatDecimal(pattern: String, number: Int): String {
            var number = number
            val myformat = DecimalFormat()
            myformat.applyPattern(pattern)
            if ((number.toString() + "").length > pattern.length) {
                val newNumber = (number.toString() + "").substring(0, pattern.length - 1)
                number = newNumber.toInt()
            }
            return myformat.format(number.toLong())
        }



        fun max(vararg values: Float): Float {
            var max = 0f
            for (item in values) {
                max = if (max == 0f) {
                    item
                } else {
                    Math.max(max, item)
                }
            }
            return max
        }

        fun min(vararg values: Float): Float {
            var min = 0f
            for (item in values) {
                min = if (min == 0f) {
                    item
                } else {
                    if (item == 0f) {
                        continue
                    }
                    Math.min(min, item)
                }
            }
            return min
        }

        fun level(value: Float, level: FloatArray): Int {
            for (i in level.indices) {
                if (value < level[i]) {
                    return i
                }
            }
            return level.size
        }

        /**
         * @param @param  number
         * @param @return 设定文件
         * @return String 返回类型
         * @throws
         * @Title: topercent
         * @Description: 将double类型的数据改为百分数，默认显示两位小数
         */
        fun toPercent(number: Double): String {
            val percentFormat = NumberFormat.getPercentInstance()
            percentFormat.minimumFractionDigits = 2
            return percentFormat.format(number)
        }

        fun toNumberFormat(number: Double): String {
            val numberFormat = NumberFormat.getNumberInstance()
            return numberFormat.format(number)
        }



        /**
         * 判断是否是字母+数字
         *
         * @param number
         * @return
         */
        fun isDigitalAndAlphabet(number: String?): Boolean {
            val p1 = Pattern.compile("[0-9]*$")
            val p2 = Pattern.compile("^[A-Za-z]+$")
            return if (p1.matcher(number).matches() || p2.matcher(number).matches()) {
                false
            } else true
        }

        /**
         * @param @param  phone
         * @param @return 设定文件
         * @return String 返回类型
         * @throws
         * @Title: phone2Unknown
         * @Description: 将手机号中间4位变为****
         */
        fun phone2Unknown(phone: String): String {
            return phone.substring(0, 3) + "****" + phone.substring(phone.length - 4, phone.length)
        }

        /**
         * 将数据的后len位置為****
         *
         * @param data
         * @param len
         * @return
         */
        fun parseToLastUnknown(data: String, len: Int): String {
            val count = data.length
            val sb = StringBuffer()
            return if (count > len) {
                for (i in 0 until len) {
                    sb.append("*")
                }
                data.substring(0, count - len) + sb.toString()
            } else {
                data
            }
        }

        /**
         * 保留前len位
         *
         * @param data
         * @param len
         * @return
         */
        fun remainFirstWords(data: String, len: Int): String {
            val count = data.length
            val sb = StringBuffer()
            for (i in 0 until len) {
                sb.append("*")
            }
            return if (count < len) {
                data
            } else {
                sb.toString() + data.substring(len, count)
            }
        }
        //    /**
        //     * @param @param  object
        //     * @param @return 设定文件
        //     * @return String 返回类型
        //     * @throws
        //     * @Title: to2Decimal
        //     * @Description: 保留两位小数
        //     */
        //    public static String to2Decimal(double object) {
        //        DecimalFormat df = new DecimalFormat();
        //        String style = "#,##0.00";// 定义要显示的数字的格式
        //        df.applyPattern(style);
        //        return df.format(object);
        //    }
        /**
         * @param @param  object
         * @param @return 设定文件
         * @return String 返回类型
         * @throws
         * @Title: to1Decimal
         * @Description: 保留一位小数，四舍五入
         */
        fun to1Decimal(data: Double): String {
            val df = DecimalFormat()
            val style = "#.0" // 定义要显示的数字的格式
            df.applyPattern(style)
            return df.format(data)
        }

        /**
         * @param @param  object
         * @param @return 设定文件
         * @return String 返回类型
         * @throws
         * @Title: to2Decimal
         * @Description: 保留两位小数，不进行四舍五入
         */
        fun to2Decimal(data: Double): String {
            val df = DecimalFormat()
            df.maximumFractionDigits = 2
            df.groupingSize = 0
            df.roundingMode = RoundingMode.FLOOR
            val style = "#,##0.00" // 定义要显示的数字的格式
            df.applyPattern(style)
            return df.format(data)
        }


        fun toFormatLong(data: Long): String {
            val df = DecimalFormat()
            val style = "#,###" // 定义要显示的数字的格式
            df.applyPattern(style)
            return df.format(data)
        }

        fun toHundred(data: Long): String {
            val remainder = (data % 100 / 10).toInt()
            return if (remainder >= 5) {
                ((data / 100 + 1) * 100).toString()
            } else {
                (data / 100 * 100).toString()
            }
        }



        /**
         * 转换数字为中文
         *
         * @param s
         * @return
         */
        fun convertDecimal2Text(s: String?): String {
            val i = toInt(s)
            val strs = arrayOf("", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二")
            return if (i > 0 && i < strs.size) {
                strs[i]
            } else strs[0]
        }

        /**
         * 转换数字y月份转为英文
         *
         * @param s
         * @return
         */
        fun convertMonth(s: String?): String {
            val i = toInt(s)
            val strs = arrayOf(
                "",
                "JAN",
                "FEB",
                "MAR",
                "APR",
                "MAY",
                "JUN",
                "JUL",
                "AUG",
                "SEP",
                "OCT",
                "NOV",
                "DEC"
            )
            return if (i > 0 && i < strs.size) {
                strs[i]
            } else strs[0]
        }

        const val REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)"

        /**
         * 校验身份证
         *
         * @param idCard
         * @return 校验通过返回true，否则返回false
         */
        fun isIDCard(idCard: String?): Boolean {
            return Pattern.matches(REGEX_ID_CARD, idCard)
        }

    }
}