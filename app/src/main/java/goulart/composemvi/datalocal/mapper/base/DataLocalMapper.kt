package goulart.composemvi.datalocal.mapper.base

interface DataLocalMapper<L, D> {

    fun toDomain(local: L): D

    fun fromDomain(domain: D): L

    fun toDomain(local: List<L>): List<D> {
        return local.map { toDomain(it) }
    }

    fun fromDomain(domain: List<D>): List<L> {
        return domain.map { fromDomain(it) }
    }

}