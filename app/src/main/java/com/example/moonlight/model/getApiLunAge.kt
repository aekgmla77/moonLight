package com.example.moonlight.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class LunAge(
    @field:Element(name = "header")
    var header: Header? = null,
    @field:Element(name = "body")
    var body: Body? = null
)

@Xml(name = "header")
data class Header(
    @field:PropertyElement(name = "resultCode")
    var resultCode: String? = null,
    @field:PropertyElement(name = "resultMsg")
    var resultMsg: String? = null
)

@Xml(name = "body")
data class Body(
    @field:Element(name = "items")
    var items: Items? = null,
    @field:PropertyElement(name = "numOfRows")
    var numOfRows: Int = 0,
    @field:PropertyElement(name = "pageNo")
    var pageNo: Int = 0,
    @field:PropertyElement(name = "totalCount")
    var totalCount: Int = 0
)

@Xml(name="items")
data class Items(
    @field:Element(name = "item")
    var item: Item? = null
)

@Xml(name = "item")
data class Item(
    // 연도
    @field:PropertyElement(name = "solYear")
    var solYear: Int = 0,
    // 월
    @field:PropertyElement(name = "solMonth")
    var solMonth: Int = 0,
    // 일
    @field:PropertyElement(name = "solDay")
    var solDay: Int = 0,
    // 요일
    @field:PropertyElement(name = "solWeek")
    var solWeek: String? = null,
    // 월령
    @field:PropertyElement(name = "lunAge")
    var lunAge: Double = 0.0
)
