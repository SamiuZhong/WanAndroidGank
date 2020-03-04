package com.samiu.host.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

/**
 * @author Samiu 2020/3/4
 */
data class CoroutineDispatcherProvider(
    val main: CoroutineDispatcher = Main,
    val computation: CoroutineDispatcher = Default,
    val io: CoroutineDispatcher = IO
) {
    constructor() : this(Main, Default, IO)
}