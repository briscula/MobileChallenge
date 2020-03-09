package com.cabify.challenge.core.domain

import com.cabify.challenge.core.api.model.ApiFailure

sealed class Failure {
  object NetworkError : Failure()
  object ServerError : Failure()
  object NotFound : Failure()
  object GenericError : Failure()
  object DataBase : Failure()
}

fun ApiFailure.toDomain() = when (this) {
  is ApiFailure.UnknownError -> Failure.NetworkError
  ApiFailure.BadRequest -> Failure.GenericError
  ApiFailure.NotFound -> Failure.NotFound
  ApiFailure.ServerError -> Failure.ServerError
  ApiFailure.NoNetwork -> Failure.NetworkError
}
