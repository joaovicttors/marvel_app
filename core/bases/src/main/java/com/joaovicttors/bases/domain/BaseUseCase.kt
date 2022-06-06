package com.joaovicttors.bases.domain

import com.joaovicttors.bases.Response

abstract class BaseUseCase<In, Out> {

   abstract suspend operator fun invoke(param: In): Response<Out>
}