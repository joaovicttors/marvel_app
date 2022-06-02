package com.joaovicttors.bases.data

import com.joaovicttors.bases.domain.BaseEntity

abstract class BaseMapper<T: BaseModel, R: BaseEntity> {

    abstract fun mapToDomainEntity(input: T): R

    abstract fun mapFromDomainEntity(input: R): T
}