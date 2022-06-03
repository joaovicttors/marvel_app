package com.joaovicttors.bases.presentation

abstract class BaseViewState<T> {

    abstract var isLoading: Boolean
    abstract var data: T
    abstract var errorMessage: String?
}