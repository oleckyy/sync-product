package com.kumquat.syncProductApi.infrastructure.rest.internal.specification.predicate

import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductFiltersDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.specification.ProductPredicateBuilder
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.stereotype.Component

@Component
class ProductNamePredicate : ProductPredicateBuilder {
    override fun build(
        productFiltersDto: ProductFiltersDto, root: Root<ProductEntity>, criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        return productFiltersDto.name?.let { name ->
                criteriaBuilder.like(root.get("name"), name)
            }
    }
}