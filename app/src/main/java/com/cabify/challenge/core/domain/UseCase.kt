package com.cabify.challenge.core.domain

import com.cabify.challenge.core.type.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

open class UseCase<Error, Result> {
  fun execute(
    background: () -> Either<Error, Result>,
    ui: (Either<Error, Result>) -> Unit
  ) {
    CoroutineScope(Dispatchers.Main).launch {
      val deferred = async(Dispatchers.IO) { background() }
      ui(deferred.await())
    }
  }
}
