package vn.vano.vicall.ui.base

import com.example.baseapp.ui.base.BaseView

abstract class BasePresenter<T : BaseView>() {

    abstract fun attachView(view: T)

    abstract fun detachView()
}