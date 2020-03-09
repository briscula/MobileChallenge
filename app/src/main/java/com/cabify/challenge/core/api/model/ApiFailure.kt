package com.cabify.challenge.core.api.model

sealed class ApiFailure {
  data class UnknownError(val code: Int) : ApiFailure()
  object NotFound : ApiFailure()
  object BadRequest : ApiFailure()
  object ServerError : ApiFailure()
  object NoNetwork : ApiFailure()
}
