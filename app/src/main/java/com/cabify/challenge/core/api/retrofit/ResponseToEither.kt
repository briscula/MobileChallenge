package com.cabify.challenge.core.api.retrofit

import com.cabify.challenge.core.api.model.ApiFailure
import com.cabify.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_BAD_REQUEST
import com.cabify.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_NOT_FOUND
import com.cabify.challenge.core.api.retrofit.HttpStatus.HTTP_STATUS_SERVER_ERROR
import com.cabify.challenge.core.api.retrofit.HttpStatus.TIME_OUT_ERROR
import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.domain.toDomain
import com.cabify.challenge.core.type.Either
import java.io.IOException
import retrofit2.Call
import retrofit2.Response

fun <T> Call<T>.run(): Either<ApiFailure, T> = try {
  execute().handle()
} catch (exception: IOException) {
  Either.Left(ApiFailure.NoNetwork)
}

fun <T> Response<T>.handle(): Either<ApiFailure, T> {
  return if (isSuccessful) {
    body()?.let { Either.Right(it) } ?: Either.Right(Any() as T)
  } else {
    val error = when {
      isTimeOutError(code()) -> ApiFailure.ServerError
      isNotFoundError(code()) -> ApiFailure.NotFound
      isServerError(code()) -> ApiFailure.ServerError
      isBadRequestError(code()) -> ApiFailure.BadRequest
      else -> ApiFailure.UnknownError(code())
    }
    Either.Left(error)
  }
}

fun <T> Either<ApiFailure, T>.toDomain(): Either<Failure, T> = mapLeft(
  ApiFailure::toDomain
)

private fun isTimeOutError(code: Int): Boolean = code == TIME_OUT_ERROR

private fun isNotFoundError(code: Int): Boolean = code == HTTP_STATUS_NOT_FOUND

private fun isServerError(code: Int): Boolean {
  val isGreaterOrEqualThan500 = code >= HTTP_STATUS_SERVER_ERROR
  val isLessThan600 = code < 600
  return isGreaterOrEqualThan500 && isLessThan600
}

private fun isBadRequestError(code: Int): Boolean {
  val isGreaterOrEqualThan400 = code >= HTTP_STATUS_BAD_REQUEST
  val isLessThan500 = code < HTTP_STATUS_SERVER_ERROR
  return isGreaterOrEqualThan400 && isLessThan500
}

object HttpStatus {
  const val HTTP_STATUS_BAD_REQUEST = 400
  const val HTTP_STATUS_NOT_FOUND = 404
  const val HTTP_STATUS_SERVER_ERROR = 500
  const val TIME_OUT_ERROR = 499
}
