package com.cabify.challenge.core.domain

import com.cabify.challenge.core.api.model.ApiFailure
import org.junit.Assert.assertEquals
import org.junit.Test

class FailureTest {

  @Test
  fun shouldMapApiFailureUnknownErrorToFailureNetworkError() {
    val failure = ApiFailure.UnknownError(418)
    assertEquals(Failure.NetworkError, failure.toDomain())
  }

  @Test
  fun shouldMapApiFailureBadRequestToFailureGenericError() {
    val failure = ApiFailure.BadRequest
    assertEquals(Failure.GenericError, failure.toDomain())
  }

  @Test
  fun shouldMapApiFailureNotFoundToFailureNotFound() {
    val failure = ApiFailure.NotFound
    assertEquals(Failure.NotFound, failure.toDomain())
  }

  @Test
  fun shouldMapApiFailureServerErrorToFailureServerError() {
    val failure = ApiFailure.ServerError
    assertEquals(Failure.ServerError, failure.toDomain())
  }

  @Test
  fun shouldMapApiFailureNoNetworkToFailureNetworkError() {
    val failure = ApiFailure.NoNetwork
    assertEquals(Failure.NetworkError, failure.toDomain())
  }
}
