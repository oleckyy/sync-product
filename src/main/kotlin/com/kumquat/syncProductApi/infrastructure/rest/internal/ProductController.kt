package com.kumquat.syncProductApi.infrastructure.rest.internal

import com.kumquat.syncProductApi.domain.const.Privilege.Companion.ADMIN
import com.kumquat.syncProductApi.domain.const.Privilege.Companion.USER
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductFiltersDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.service.ProductService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {
    @GetMapping
    @Secured(value = [USER, ADMIN])
    fun findAll(
        pageable: Pageable,
        @RequestBody filtersDto: ProductFiltersDto
    ): ResponseEntity<*> =
        ResponseEntity.ok(productService.findAll(pageable, filtersDto))
}