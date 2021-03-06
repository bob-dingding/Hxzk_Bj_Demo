package com.hxzk_bj_demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 *作者：created by zjt on 2020/9/15
 *描述:
 *
 */

fun ViewModel.launch(
  block : suspend CoroutineScope.() -> Unit ,
  onError :(e :Throwable) -> Unit ={},
  onComplete :() -> Unit ={}
){
viewModelScope.launch(CoroutineExceptionHandler{
    _,e -> onError(e)
}) {
    try {
        block.invoke(this)
    } finally {
        onComplete()
    }
}
}