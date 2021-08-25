package com.example.rgc.opendotaktor.utils

interface EntityMapper<Entity, DomainModel, LocalModel> {

    fun mapFromEntityLocal(entity : Entity) : LocalModel
    fun mapToDomainModel(localModel: LocalModel) : DomainModel

    fun mapFromEntityList(entities: List<Entity>) : List<LocalModel>
    fun mapToLocalList(entities: List<LocalModel>) : List<DomainModel>

}