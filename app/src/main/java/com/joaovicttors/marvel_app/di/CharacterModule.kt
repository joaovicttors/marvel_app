package com.joaovicttors.marvel_app.di

import com.joaovicttors.bases.data.BaseMapper
import com.joaovicttors.bases.domain.BaseUseCase
import com.joaovicttors.data.datasources.local.CharacterLocalDataSource
import com.joaovicttors.data.datasources.local.CharacterLocalDataSourceImpl
import com.joaovicttors.data.datasources.remote.CharacterRemoteDataSource
import com.joaovicttors.data.datasources.remote.CharacterRemoteDataSourceImpl
import com.joaovicttors.data.mappers.CharacterEntityMapper
import com.joaovicttors.data.mappers.CharacterResponseMapper
import com.joaovicttors.data.models.CharacterEntity
import com.joaovicttors.data.models.CharacterResponse
import com.joaovicttors.data.repositories.CharacterRepositoryImpl
import com.joaovicttors.domain.entities.Character
import com.joaovicttors.domain.repositories.CharacterRepository
import com.joaovicttors.domain.usecases.GetCharacterListUseCase
import com.joaovicttors.presentation.character_list.CharacterListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CharacterModule {

    private const val ENTITY_MAPPER: String = "entity_mapper"
    private const val RESPONSE_MAPPER: String = "response_mapper"
    private const val GET_CHARACTER_LIST: String = "get_character_list"


    private val dataModule = module {

        single<BaseMapper<CharacterEntity, Character>>(named(ENTITY_MAPPER)) { CharacterEntityMapper() }
        single<BaseMapper<CharacterResponse.Character, Character>>(named(RESPONSE_MAPPER)) { CharacterResponseMapper() }

        single<CharacterLocalDataSource> { CharacterLocalDataSourceImpl(get(named(ENTITY_MAPPER)), get()) }
        single<CharacterRemoteDataSource> { CharacterRemoteDataSourceImpl(get(named(RESPONSE_MAPPER)), get()) }
    }

    private val domainModule = module {

        single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }

        single<BaseUseCase<Int, List<Character>>>(named(GET_CHARACTER_LIST)) { GetCharacterListUseCase(get()) }
    }

    private val presentationModule = module {

        viewModel { CharacterListViewModel(Dispatchers.IO, get(named(GET_CHARACTER_LIST))) }
    }

    val allModules = arrayOf(
        dataModule,
        domainModule,
        presentationModule,
    )
}