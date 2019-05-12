package com.example.smartoffice.dataclass

class DataIndicatorTypeDef {
    var defValue: Double = 0.0
    var defAlarmBorder = arrayOf (0.0, 0.0)
    var defTypeOfAlarm: Int = 0 //0 - left and right borders 1 - up and upper border
    var idBigPicture: Int = 0
    var idBigPictureGrey: Int = 0
    var defTextAlarm = arrayOf ("","","")
    var defTextAlarmIdColor = arrayOf (0,0,0)
    var defTextAlarmIdImage = arrayOf (0,0,0)
    var defOnButtonAlarmIdImage = arrayOf (0,0,0)
    var defFormatString: String = ""
    var defDescribeValue: String = ""
    var defDescribe: String = ""
    var defTextDescribe: String = ""
}
