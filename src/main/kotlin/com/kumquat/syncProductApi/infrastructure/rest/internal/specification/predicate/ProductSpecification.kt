package com.kumquat.syncProductApi.infrastructure.rest.internal.specification.predicate

import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductFiltersDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.specification.ProductPredicateBuilder
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class ProductSpecification(
    private val predicateBuilders: List<ProductPredicateBuilder>,
    private val filters: ProductFiltersDto
) : Specification<ProductEntity> {
    override fun toPredicate(
        root: Root<ProductEntity>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val predicates =
            predicateBuilders.mapNotNull { it.build(filters, root, criteriaBuilder) }
                .takeIf { it.isNotEmpty() }
        return predicates?.let { criteriaBuilder.and(*it.toTypedArray()) }
            ?: criteriaBuilder.conjunction()
    }
}