package com.example.rgc.opendotaktor.heroes.mapping

import org.jetbrains.exposed.sql.ResultRow

interface EntityMapper<Entity, DomainModel, LocalModel> {

    fun mapFromEntityLocal(entity : Entity) : LocalModel
    fun mapToDomainModel(localModel: LocalModel) : DomainModel
    fun mapToLocalModel(dbModel : ResultRow) : LocalModel

    fun mapFromEntityList(entities: List<Entity>) : List<LocalModel>
    fun mapToLocalList(entities: List<LocalModel>) : List<DomainModel>

}