package com.xy.assignment2.controller

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.xy.assignment2.entity.InvoiceEntity
import com.xy.assignment2.service.impl.InvoiceServiceImpl
import com.xy.assignment2.utils.JsonUtils
import com.xy.assignment2.utils.PageUtils
import com.xy.assignment2.validator.ValidatorUtils
import com.xy.assignment2.validator.group.AddGroup
import com.xy.assignment2.validator.group.UpdateGroup
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 3:12 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

@RestController
@RequestMapping("/invoice")
@CrossOrigin(originPatterns = ["*"], allowCredentials = "true")
class InvoiceController {

    @Resource
    private lateinit var invoiceService: InvoiceServiceImpl

    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun list(@RequestParam params: Map<String?, String?>?): ResponseEntity<Any?>? {
        val page: PageUtils = invoiceService.queryAll(params)
        return ResponseEntity.ok().body(JSON.toJSONString(page))
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun info(@PathVariable("id") id: Long?): ResponseEntity<Any?>? {
        if (id != null && id > 0) {
            val invoice: InvoiceEntity? = invoiceService.getById(id)
            return if (invoice != null) {
                ResponseEntity.ok().body(JsonUtils.toJsonString("result", invoice))
            } else {
                ResponseEntity.badRequest().body("No invoice matched.")
            }
        }
        return ResponseEntity.badRequest().body("Invalid id")
    }

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun save(@RequestBody json: String?): ResponseEntity<Any?>? {
        val invoice: InvoiceEntity = JSONObject.parseObject(json, InvoiceEntity::class.java)
        val validate: Boolean = ValidatorUtils.validateEntity(invoice, AddGroup::class.java)
        if (!validate) {
            return ResponseEntity.badRequest().body(JsonUtils.toJsonString("result", "Invalid invoice format."))
        }
        val save: Boolean = invoiceService.save(invoice)
        return if (save) {
            ResponseEntity.ok().body(JsonUtils.toJsonString("result", "Success."))
        } else ResponseEntity.badRequest().body(JsonUtils.toJsonString("result", "Failed"))
    }

    @RequestMapping(value = [""], method = [RequestMethod.PATCH])
    fun update(@RequestBody json: String?): ResponseEntity<Any?>? {
        val invoice: InvoiceEntity = JSONObject.parseObject(json, InvoiceEntity::class.java)
        val validate: Boolean = ValidatorUtils.validateEntity(invoice, UpdateGroup::class.java)
        if (!validate) {
            return ResponseEntity.badRequest().body(JsonUtils.toJsonString("result", "Invalid invoice format."))
        }
        val update: Boolean = invoiceService.updateById(invoice)
        return if (update) {
            ResponseEntity.ok().body(JsonUtils.toJsonString("result", "Success."))
        } else ResponseEntity.badRequest().body(JsonUtils.toJsonString("result", "Failed"))
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun delete(@PathVariable("id") id: Long?): ResponseEntity<Any?>? {
        if (id != null && id > 0) {
            val delete: Boolean = invoiceService.removeById(id)
            return if (delete) {
                ResponseEntity.ok().body(JsonUtils.toJsonString("result", "Success."))
            } else ResponseEntity.badRequest().body(JsonUtils.toJsonString("result", "Failed"))
        }
        return ResponseEntity.badRequest().body("Invalid id")
    }
}