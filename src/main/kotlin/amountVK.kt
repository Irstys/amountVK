package ru.netology
const val USER_CARD_MIR = "mir"
const val USER_CARD_MAESTRO="maestro"
const val USER_CARD_MASTERCARD = "mastercard"
const val USER_CARD_VKPAY = "vkpay"
const val USER_CARD_VISA = "visa"

const val MINLIMIT_MONTH_CARD =7500000


const val COEFFICIENT_MIR: Double =0.0075
const val COEFFICIENT_MAESTRO:Double =0.006
const val COEFFICIENT_MASTERCARD: Double =0.006
const val COEFFICIENT_VKPAY: Double =0.0
const val COEFFICIENT_VISA: Double =0.0075

const val MIN_FEE_VISA: Int =3500
const val MIN_FEE_MIR: Int =3500
const val MIN_FEE_MAESTRO: Int =0
const val MIN_FEE_MASTERCARD: Int =0
const val MIN_FEE_VKPAY: Int =0


const val LIMIT_DAY_MIR =15000000
const val LIMIT_MONTH_MIR =60000000
const val LIMIT_DAY_MAESTRO =15000000
const val LIMIT_MONTH_MAESTRO =60000000
const val LIMIT_DAY_VISA =15000000
const val LIMIT_MONTH_VISA =60000000
const val LIMIT_DAY_MASTERCARD =15000000
const val LIMIT_MONTH_MASTERCARD =60000000
const val LIMIT_MONTH_VKPAY =1500000
const val LIMIT_DAY_VKPAY =4000000

fun main() {
    print("Введите сумму перевода в рублях: ")
    val read = readln().toDouble()
    val amount:Int = (read*100).toInt()
    val userCard = USER_CARD_MASTERCARD
    val sumPayMonth=0*100
    val totalFee: Double = if(limit(userCard,sumPayMonth,amount)) amount.toDouble() else amountVK(userCard,sumPayMonth,amount)
    val kop:Int = (totalFee%100).toInt()
    val rub:Int =(totalFee/100).toInt()
    val text: String =if(totalFee==amount.toDouble())"Превышен лимит" else "Сумма комиссии составляет $rub руб. $kop  коп."
    println(text)
}

fun amountVK(
    userCard: String = USER_CARD_VKPAY,
    sumPayMonth: Int = 0,
    amount: Int
): Double {

        val totalFee: Double = when(userCard){
            USER_CARD_MAESTRO ->if(sumPayMonth>MINLIMIT_MONTH_CARD)COEFFICIENT_MAESTRO*amount+2000 else 0.0
            USER_CARD_MASTERCARD->if(sumPayMonth>MINLIMIT_MONTH_CARD)COEFFICIENT_MASTERCARD*amount+2000 else 0.0
            USER_CARD_MIR-> (if(COEFFICIENT_MIR*amount>MIN_FEE_MIR)COEFFICIENT_MIR*amount else MIN_FEE_MIR) as Double
            USER_CARD_VISA->(if(COEFFICIENT_VISA*amount>MIN_FEE_VISA)COEFFICIENT_VISA*amount else MIN_FEE_VISA) as Double
            USER_CARD_VKPAY->COEFFICIENT_VKPAY*amount
            else->0.0
        }
        return  totalFee
}

fun limit(userCard: String = USER_CARD_VKPAY,
          sumPayMonth: Int = 0,
          amount: Int): Boolean {
    val limitDay: Int = when (userCard) {
        USER_CARD_MAESTRO -> LIMIT_DAY_MAESTRO
        USER_CARD_MASTERCARD -> LIMIT_DAY_MASTERCARD
        USER_CARD_MIR -> LIMIT_DAY_MIR
        USER_CARD_VISA -> LIMIT_DAY_VISA
        USER_CARD_VKPAY -> LIMIT_DAY_VKPAY
        else -> 0
    }

    val limitMonth: Int = when (userCard) {
        USER_CARD_MAESTRO -> LIMIT_MONTH_MAESTRO
        USER_CARD_MASTERCARD -> LIMIT_MONTH_MASTERCARD
        USER_CARD_MIR -> LIMIT_MONTH_MIR
        USER_CARD_VISA -> LIMIT_MONTH_VISA
        USER_CARD_VKPAY -> LIMIT_MONTH_VKPAY
        else -> 0
    }
   return !(amount>limitDay || sumPayMonth>limitMonth)

}