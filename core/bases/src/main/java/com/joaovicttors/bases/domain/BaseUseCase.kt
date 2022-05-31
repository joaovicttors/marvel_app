package com.joaovicttors.bases.domain

import com.joaovicttors.bases.Response
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<In, Out> {

   abstract operator fun invoke(): Flow<Response<Out>>
}