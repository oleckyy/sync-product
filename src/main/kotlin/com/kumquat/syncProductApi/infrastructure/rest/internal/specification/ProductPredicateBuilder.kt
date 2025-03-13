package com.kumquat.syncProductApi.infrastructure.rest.internal.specification

import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductFiltersDto
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

interface ProductPredicateBuilder {
    fun build(
        productFiltersDto: ProductFiltersDto,
        root: Root<ProductEntity>,
        criteriaBuilder: CriteriaBuilder,
    ): Predicate?
}