package com.xy.assignment2.utils

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 3:27 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

class PageUtils {

    private val count: Int
    private val next: String?
    private val previous: String?
    private val results: List<Any>

    fun getCount(): Int = count
    fun getPrevious(): String? = previous
    fun getNext(): String? = next
    fun getResults(): List<Any> = results

    constructor(count: Int, next: String?, previous: String?, results: List<Any>) {
        this.count = count
        this.next = next
        this.previous = previous
        this.results = results
    }
}