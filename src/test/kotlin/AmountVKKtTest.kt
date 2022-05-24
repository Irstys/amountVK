import org.junit.Test
import org.junit.Assert.*
import ru.netology.USER_CARD_VKPAY
import ru.netology.amountVK
import ru.netology.limit

@Suppress("DEPRECATION")
class AmountVKKtTest {

    @Test
    fun main() {
    }

    @Test
    fun amountVK() {
        // arrange
      val  userCard1: String = USER_CARD_VKPAY
      val  sumPayMonth1: Int = 0
      val amount1: Int = 1000
        // act
        val results= amountVK(
        userCard = userCard1,
        sumPayMonth = sumPayMonth1,
        amount = amount1
        )
        //assert
        assertEquals ( 0.0, results,0.0)
    }

    @Test
    fun limit() {
        val userCard1: String = USER_CARD_VKPAY
        val sumPayMonth1: Int = 0
        val amount1: Int = 100
        val result = limit(
            userCard = userCard1,
            sumPayMonth = sumPayMonth1,
            amount = amount1)
        assertEquals ( true, result)
    }
}