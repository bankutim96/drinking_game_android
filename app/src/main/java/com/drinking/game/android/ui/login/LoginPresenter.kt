package com.drinking.game.android.ui.login

import com.drinking.game.android.interactor.AuthInteractor
import com.drinking.game.android.interactor.event.auth.LoginEvent
import com.drinking.game.android.model.auth.TokenResponse
import com.drinking.game.android.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val executor: Executor,
    private val authInteractor: AuthInteractor): Presenter<LoginScreen>() {

    fun login(username: String, password: String) {
        executor.execute{
            authInteractor.login(username, password)
        }
    }

    override fun attachScreen(screen: LoginScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: LoginEvent) {
        if (event.throwable != null) {
            if (screen != null) {
                screen?.handleFailedLogin(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.token != null) {
                    screen?.handleSuccessLogin(event.token as TokenResponse)
                }
            }
        }
    }
}