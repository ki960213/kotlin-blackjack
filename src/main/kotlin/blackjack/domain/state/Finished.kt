package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Money

abstract class Finished(override val hand: Hand, override val bettingMoney: Money) : State {

    protected abstract val earningRate: Double

    override fun betting(money: Money): State = throw IllegalStateException(ALREADY_BET_ERROR)

    override fun draw(card: Card): State {
        throw IllegalStateException(DRAW_MUST_RUNNING)
    }

    override fun stay(): State {
        throw IllegalStateException(CANT_CHANGE_STATE)
    }

    override fun getProfit(): Double = bettingMoney.toInt() * earningRate

    companion object {
        private const val DRAW_MUST_RUNNING = "게임이 끝난 상태에서 드로우할 수 없습니다."
        private const val CANT_CHANGE_STATE = "게임이 끝난 상태에서 다른 상태가 될 수 없습니다."
        private const val ALREADY_BET_ERROR = "이미 베팅을 했습니다."
    }
}