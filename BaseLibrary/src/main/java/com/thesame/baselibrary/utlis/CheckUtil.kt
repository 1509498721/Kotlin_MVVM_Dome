package com.thesame.baselibrary.utlis

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * @Description String
 * Created by whz  on 2019-06-27
 */
object CheckUtil {
    /**
     * 判断两个string是否相等
     */
    fun checkEquels(strObj0: Any, strObj1: Any): Boolean {
        val str0 = strObj0.toString() + ""
        val str1 = strObj1.toString() + ""
        return if (str0 == str1) {
            true
        } else false
    }

    /**
     * 同时判断多个参数是否为空
     *
     * @param strArray
     * @return
     */
    fun isNull(vararg strArray: Any): Boolean {
        for (obj in strArray) {
            if ("" != obj.toString() + "") {
                return false
            }
        }
        return true
    }

    /**
     * 判是否是字母
     *
     * @return
     */
    fun isLetter(txt: String): Boolean {
        if (isNull(txt)) {
            return false
        }
        val p = Pattern.compile("[a-zA-Z]")
        val m = p.matcher(txt)
        return if (m.matches()) {
            true
        } else false
    }

    /**
     * 判断对象是否为空
     */
    fun isNull(strObj: Any): Boolean {
        val str = strObj.toString() + ""
        return if ("" != str && "null" != str) {
            false
        } else true
    }

    /**
     * 判断是否邮箱
     *
     * @param strObj
     * @return
     */
    fun checkEmail(strObj: Any): Boolean {
        val str = strObj.toString() + ""
        if (!str.endsWith(".com")) {
            return false
        }
        val match = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"
        val pattern = Pattern.compile(match)
        val matcher = pattern.matcher(str)
        return if (matcher.matches()) {
            true
        } else false
    }

    /**
     * 判断是否为电话号码
     */
    fun checkPhone(phoneNumber: Any): Boolean {
        var isValid = false
        val expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$"
        val expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$"
        val inputStr: CharSequence = phoneNumber.toString() + ""
        val pattern = Pattern.compile(expression)
        val matcher = pattern.matcher(inputStr)
        val pattern2 = Pattern.compile(expression2)
        val matcher2 = pattern2.matcher(inputStr)
        if (matcher.matches() || matcher2.matches()) {
            isValid = true
        }
        return isValid
    }

    /**
     * 监测是否为正确的手机号码
     *
     * @param mobile
     * @return
     */
    fun isMobileCorrect(mobile: String?): Boolean {
        val regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([6-8]|0))|(18[0-9]))\\d{8}$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(mobile)
        return matcher.matches()
    }



    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private fun isNumeric(str: String): Boolean {
        val pattern = Pattern.compile("[0-9]*")
        val isNum = pattern.matcher(str)
        return if (isNum.matches()) {
            true
        } else {
            false
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param
     * @return
     */
    fun isDate(strDate: String?): Boolean {
        val pattern = Pattern
            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$")
        val m = pattern.matcher(strDate)
        return if (m.matches()) {
            true
        } else {
            false
        }
    }

    /**
     * 判断是否为min到max位的字母或数字
     *
     * @param s
     * @param min
     * @param max
     * @return
     */
    fun isAlphanumericRange(s: String?, min: Int, max: Int): Boolean {
        val regex = "^[a-z0-9A-Z]{$min,$max}$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(s)
        return matcher.matches()
    }

    /**
     * 判断是否为n为数字的验证码
     *
     * @param s
     * @param n
     * @return
     */
    fun isVerificationCode(s: String?, n: Int): Boolean {
        val regex = "^[0-9]{$n}$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(s)
        return matcher.matches()
    }

    /**
     * 检查内容的长度是否大于等于要求
     *
     * @param
     * @param
     * @return
     */
    fun checkLength(strObj: Any, length: Int): Boolean {
        val str = strObj.toString() + ""
        return if (str.length >= length) {
            true
        } else false
    }

    /**
     * 检查字符串的长度
     *
     * @param strObj
     * @param length
     * @return
     */
    fun checkLengthEq(strObj: Any, length: Int): Boolean {
        val str = strObj.toString() + ""
        return if (str.length == length) {
            true
        } else false
    }

    /**
     * @param @param  strObj
     * @param @param  min
     * @param @param  max
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkNum
     * @Description: 检查是否为数字，以及这个数在min与max之间，包含min与max
     */
    fun checkNum(strObj: Any, min: Int, max: Int): Boolean {
        val str = strObj.toString() + ""
        return try {
            val number = str.toInt()
            if (number <= max && number >= min) {
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * @param @param  strObj
     * @param @param  min
     * @param @param  max
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkNumWithDecimal
     * @Description: 检查是否为数字，以及这个数在min与max之间，包含min与max,可以为小数
     */
    fun checkNumWithDecimal(strObj: Any, min: Float, max: Float): Boolean {
        val str = strObj.toString() + ""
        return try {
            val number = str.toFloat()
            if (number <= max && number >= min) {
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * @param @param  strObj
     * @param @param  start
     * @param @param  end
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkLength
     * @Description: 检查字符串的长度范围是否符合要求
     */
    fun checkLength(strObj: Any, start: Int, end: Int): Boolean {
        val str = strObj.toString() + ""
        return if (str.length >= start && str.length <= end) {
            true
        } else false
    }

    /**
     * @param @param  strObj
     * @param @param  num  倍数
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkMoney
     * @Description: 检查金额是否为数字以及是否为一个数的倍数
     */
    fun checkMoney(strObj: Any, num: Int): Boolean {
        val str = strObj.toString() + ""
        return try {
            val money = str.toInt()
            if (money % num == 0) {
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 检查请求返回是否正确
     */
    fun checkStatusOk(status: String): Boolean {
        return if ("2000000" == status) {
            true
        } else false
    }

    fun checkStatusOk(status: Int): Boolean {
        return if (2000000 == status) {
            true
        } else false
    }

    /**
     * 检查string是否为o
     *
     * @param value
     * @return
     */
    fun checkZero(value: String?): Boolean {
        val valueInt = StringUtil.toInt(value)
        return if (valueInt == 0) {
            true
        } else false
    }

    /**
     * 检查是否是网络链接
     *
     * @param url
     * @return
     */
    fun checkURL(url: String): Boolean {
        if (isNull(url)) {
            return false
        }
        val regular = "(http|https)://[\\S]*"
        return Pattern.matches(regular, url)
    }

    /**
     * 检测文件链接
     * @param url
     * @return
     */
    fun checkFileURL(url: String): Boolean {
        if (isNull(url)) {
            return false
        }
        val regular = "(file)://[\\S]*"
        return Pattern.matches(regular, url)
    }

    /**
     * 检查是否合法金额
     *
     * @param goal
     * @return
     */
    fun checkIsGoal(goal: String?): Boolean {
        val regular = "^(([1-9]\\d*)|([0]))(\\.(\\d){0,2})?$"
        return Pattern.matches(regular, goal)
    }


}