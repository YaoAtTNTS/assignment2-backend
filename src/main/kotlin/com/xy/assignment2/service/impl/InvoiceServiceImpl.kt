package com.xy.assignment2.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.xy.assignment2.dao.InvoiceDao
import com.xy.assignment2.entity.InvoiceEntity
import com.xy.assignment2.exception.SQLOperationException
import com.xy.assignment2.service.InvoiceService
import com.xy.assignment2.utils.PageUtils
import com.xy.assignment2.utils.SqlFilter
import com.xy.assignment2.validator.ValidatorUtils
import com.xy.assignment2.validator.group.AddGroup
import org.apache.commons.lang.StringUtils
import org.springframework.stereotype.Service

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:20 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

@Service("invoiceService")
class InvoiceServiceImpl : ServiceImpl<InvoiceDao, InvoiceEntity>(), InvoiceService {

    override fun queryAll(params: Map<String?, String?>?): PageUtils {
        val wrapper: QueryWrapper<InvoiceEntity> = QueryWrapper<InvoiceEntity>()
        var url = "http://localhost:8080/invoice/list?"
        if (params!!.containsKey("field") && params.containsKey("keyword")) {
            val field = params["field"]
            val keyword = params["keyword"]
            if (!StringUtils.isBlank(field) && !StringUtils.isBlank(keyword)) {
                val sqlInjectField = SqlFilter.sqlInject(field!!)
                val sqlInjectKeyword = SqlFilter.sqlInject(keyword!!)
                url += "field=$sqlInjectField&keyword=$sqlInjectKeyword&"
                wrapper.like(sqlInjectField, sqlInjectKeyword)
            }
        }
        val count = count(wrapper)
        val offset = params.getOrDefault("offset", "0")!!.toInt()
        wrapper.last("limit 100 offset $offset")
        val list = list(wrapper)
        var previous: String? = null
        var next: String? = null
        if (offset > 100) {
            previous = url + "limit=100&offset=" + (offset - 100)
        }
        if (count > (offset + 100)) {
            next = url + "limit=100&offset=" + (offset + 100)
        }
        return PageUtils(count, next, previous, list)
    }

    @Throws(SQLOperationException::class)
    override fun saveInvoice(invoice: InvoiceEntity) {
        val isValid: Boolean = ValidatorUtils.validateEntity(invoice, AddGroup::class.java)
        if (!isValid) {
            throw SQLOperationException("Invalid invoice entity.")
        }
        save(invoice)
    }
}