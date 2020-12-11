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

    fun isIDCardValidate(IDStr: String): Boolean {
        val ValCodeArr = arrayOf("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2")
        val Wi = arrayOf(
            "7",
            "9",
            "10",
            "5",
            "8",
            "4",
            "2",
            "1",
            "6",
            "3",
            "7",
            "9",
            "10",
            "5",
            "8",
            "4",
            "2"
        )
        var Ai = ""
        // ================ 号码的长度18位 ================
        if (IDStr.length != 18) {
            return false
        }
        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length == 18) {
            Ai = IDStr.substring(0, 17)
        }
        if (isNumeric(Ai) == false) {
            //errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false
        }
        // ================ 出生年月是否有效 ================
        val strYear = Ai.substring(6, 10) // 年份
        val strMonth = Ai.substring(10, 12) // 月份
        val strDay = Ai.substring(12, 14) // 日
        if (isDate("$strYear-$strMonth-$strDay") == false) {
//          errorInfo = "身份证生日无效。";
            return false
        }
        val gc = GregorianCalendar()
        val s = SimpleDateFormat("yyyy-MM-dd")
        try {
            if (gc[Calendar.YEAR] - strYear.toInt() > 150 || gc.time.time - s.parse("$strYear-$strMonth-$strDay").time < 0) {
                //errorInfo = "身份证生日不在有效范围。";
                return false
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (strMonth.toInt() > 12 || strMonth.toInt() == 0) {
            //errorInfo = "身份证月份无效";
            return false
        }
        if (strDay.toInt() > 31 || strDay.toInt() == 0) {
            //errorInfo = "身份证日期无效";
            return false
        }
        // ================ 地区码时候有效 ================
        val h = GetAreaCode()
        if (h[Ai.substring(0, 2)] == null) {
            //errorInfo = "身份证地区编码错误。";
            return false
        }
        // ================ 判断最后一位的值 ================
        var TotalmulAiWi = 0
        for (i in 0..16) {
            TotalmulAiWi = TotalmulAiWi + Ai[i].toString().toInt() * Wi[i].toInt()
        }
        val modValue = TotalmulAiWi % 11
        val strVerifyCode = ValCodeArr[modValue]
        Ai = Ai + strVerifyCode
        if (IDStr.length == 18) {
            if (Ai == IDStr == false) {
                //errorInfo = "身份证无效，不是合法的身份证号码";
                return false
            }
        } else {
            return true
        }
        return true
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    private fun GetAreaCode(): Hashtable<*, *> {
        val hashtable: Hashtable<*, *> = Hashtable<Any?, Any?>()
        hashtable["11"] = "北京"
        hashtable["12"] = "天津"
        hashtable["13"] = "河北"
        hashtable["14"] = "山西"
        hashtable["15"] = "内蒙古"
        hashtable["21"] = "辽宁"
        hashtable["22"] = "吉林"
        hashtable["23"] = "黑龙江"
        hashtable["31"] = "上海"
        hashtable["32"] = "江苏"
        hashtable["33"] = "浙江"
        hashtable["34"] = "安徽"
        hashtable["35"] = "福建"
        hashtable["36"] = "江西"
        hashtable["37"] = "山东"
        hashtable["41"] = "河南"
        hashtable["42"] = "湖北"
        hashtable["43"] = "湖南"
        hashtable["44"] = "广东"
        hashtable["45"] = "广西"
        hashtable["46"] = "海南"
        hashtable["50"] = "重庆"
        hashtable["51"] = "四川"
        hashtable["52"] = "贵州"
        hashtable["53"] = "云南"
        hashtable["54"] = "西藏"
        hashtable["61"] = "陕西"
        hashtable["62"] = "甘肃"
        hashtable["63"] = "青海"
        hashtable["64"] = "宁夏"
        hashtable["65"] = "新疆"
        //      hashtable.put("71", "台湾");
//      hashtable.put("81", "香港");
//      hashtable.put("82", "澳门");
//      hashtable.put("91", "国外");
        return hashtable
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

    /**
     * 密码强度
     *
     * @return Z = 字母 S = 数字 T = 特殊字符
     */
    fun checkPassword(passwordStr: String): String {
        val regexZ = "\\d*"
        val regexS = "[a-zA-Z]+"
        val regexZS = "\\w*"
        if (passwordStr.length > 5 && passwordStr.length < 10 && passwordStr.matches(regexZ)) {
            return "弱"
        }
        if (passwordStr.length > 5 && passwordStr.length < 10 && passwordStr.matches(regexS)) {
            return "弱"
        }
        if (passwordStr.length > 5 && passwordStr.length < 10 && passwordStr.matches(regexZS)) {
            return "中"
        }
        if (passwordStr.length > 9 && passwordStr.matches(regexZ)) {
            return "中"
        }
        if (passwordStr.length > 9 && passwordStr.matches(regexS)) {
            return "中"
        }
        return if (passwordStr.length > 9 && passwordStr.matches(regexZS)) {
            "强"
        } else passwordStr
    }
}