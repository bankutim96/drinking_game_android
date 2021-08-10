package com.drinking.game.android.ui.register

import com.drinking.game.android.interactor.AuthInteractor
import com.drinking.game.android.interactor.event.auth.RegisterEvent
import com.drinking.game.android.model.auth.RegisterRequest
import com.drinking.game.android.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class RegisterPresenter @Inject constructor(
    private val executor: Executor,
    private val authInteractor: AuthInteractor
): Presenter<RegisterScreen> () {

    fun register(registerRequest: RegisterRequest) {
        executor.execute {
            authInteractor.register(registerRequest)
        }
    }

    override fun attachScreen(screen: RegisterScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: RegisterEvent) {
        if (event.throwable != null) {
            if (screen != null) {
                screen?.handleFailedRegister(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.user != null) {
                    screen?.handleSuccessRegister()
                }
            }
        }
    }
}