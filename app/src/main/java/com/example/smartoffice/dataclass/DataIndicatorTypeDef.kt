package com.example.smartoffice.dataclass

class DataIndicatorTypeDef {
    var defValue: Double = 0.0
    var defAlarmBorder: Array<Double> = Array(2) {0.0; 0.0}
    var defTypeOfAlarm: Int = 0 //0 - left and right borders 1 - up and upper border
    var idBigPicture: Int = 0
    var defTextAlarm: Array<String> = Array(3) {"";"";""}
    var defTextAlarmIdColor: Array<Int> = Array(3) {0;0;0}
    var defTextAlarmIdImage: Array<Int> = Array(3) {0;0;0}
    var defOnButtonAlarmIdImage: Array<Int> = Array(3) {0;0;0}
    var defFormatString: String = ""
    var defDescribeValue: String = ""
    var defDescribe: String = ""
}
