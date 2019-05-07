package com.example.smartoffice.test

import com.example.smartoffice.dataclass.EnumIndicatorsType

class TestDataFlow {
    private var testData:MutableList<TestDataRecordIndicator> = mutableListOf()
    private var nextInd: Int = -1
    constructor() {
        this.nextInd = -1
        testData.add(TestDataRecordIndicator("id123432",EnumIndicatorsType.Temperature,18.0))
        testData.add(TestDataRecordIndicator("id123432",EnumIndicatorsType.Temperature,20.0))
        testData.add(TestDataRecordIndicator("id123432",EnumIndicatorsType.Temperature,36.0))
    }

    fun getNextTestRecord() : TestDataRecordIndicator {
        this.nextInd++
        if (this.nextInd >=testData.size) this.nextInd = 0
        return testData[this.nextInd]
    }
}