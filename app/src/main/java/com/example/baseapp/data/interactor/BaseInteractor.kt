package com.example.baseapp.data.interactor

import com.example.baseapp.data.ServiceFactory


abstract class BaseInteractor {
    protected val service by lazy { ServiceFactory.create() }
}