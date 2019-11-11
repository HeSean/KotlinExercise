import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*

class OrdersAnalyzer {

    private fun Order.sumQuantity(): Int {

        var sum = 0
        orderLines.forEach { sum += it.quantity }
        return sum
    }

    private inline fun <reified K : Enum<K>, V> mapEnumTo(values: List<V>): EnumMap<K, V> {

        return enumValues<K>().zip(values).toMap(EnumMap(K::class.java))
    }

    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {

        val map = mapEnumTo<DayOfWeek, Int>(listOf(0,0,0,0,0,0,0))
        orders.forEach { order ->
            val sum = order.sumQuantity()
            when (order.creationDate.dayOfWeek) {
                DayOfWeek.MONDAY -> map[DayOfWeek.MONDAY] = map[DayOfWeek.MONDAY]!!.plus(sum)
                DayOfWeek.TUESDAY -> map[DayOfWeek.TUESDAY] = map[DayOfWeek.TUESDAY]!!.plus(sum)
                DayOfWeek.WEDNESDAY -> map[DayOfWeek.WEDNESDAY] = map[DayOfWeek.WEDNESDAY]!!.plus(sum)
                DayOfWeek.THURSDAY -> map[DayOfWeek.THURSDAY] = map[DayOfWeek.THURSDAY]!!.plus(sum)
                DayOfWeek.FRIDAY -> map[DayOfWeek.FRIDAY] = map[DayOfWeek.FRIDAY]!!.plus(sum)
                DayOfWeek.SATURDAY -> map[DayOfWeek.SATURDAY] = map[DayOfWeek.SATURDAY]!!.plus(sum)
                DayOfWeek.SUNDAY -> map[DayOfWeek.SUNDAY] = map[DayOfWeek.SUNDAY]!!.plus(sum)
                else -> {
                    println("DayOfWeek entered wasn't listed in the bible :p")
                }
            }
        }
        with(map.iterator()) {
            forEach { if (it.value < 1) remove() }
        }
        return map
    }
}


fun main() {

//    val orderLine1 = OrdersAnalyzer.OrderLine(9872, "Pencil", 3, BigDecimal.valueOf(3))
//    val orderLineList1 = listOf(orderLine1)
//    val order1 = OrdersAnalyzer.Order(554, LocalDateTime.parse("2017-03-25T10:35:20"), orderLineList1)
//
//    val orderLine2 = OrdersAnalyzer.OrderLine(9872, "Pencil", 2, BigDecimal.valueOf(3))
//    val orderLine3 = OrdersAnalyzer.OrderLine(1746, "Eraser", 1, BigDecimal.valueOf(1))
//    val orderLineList2 = listOf(orderLine2, orderLine3)
//    val order2 = OrdersAnalyzer.Order(555, LocalDateTime.parse("2017-03-25T11:24:20"), orderLineList2)
//
//    val orderLine4 = OrdersAnalyzer.OrderLine(5723, "Pen", 4, BigDecimal.valueOf(4.22))
//    val orderLine5 = OrdersAnalyzer.OrderLine(9872, "Pencil", 3, BigDecimal.valueOf(3.12))
//    val orderLine6 = OrdersAnalyzer.OrderLine(1746, "Erasers Set", 1, BigDecimal.valueOf(6.15))
//    val orderLineList3 = listOf(orderLine4, orderLine5, orderLine6)
//    val order3 = OrdersAnalyzer.Order(453, LocalDateTime.parse("2017-03-27T14:53:12"), orderLineList3)
//
//    val orderLine7 = OrdersAnalyzer.OrderLine(5723, "Pen", 7, BigDecimal.valueOf(4.22))
//    val orderLine8 = OrdersAnalyzer.OrderLine(3433, "Erasers Set", 2, BigDecimal.valueOf(6.15))
//    val orderLineList4 = listOf(orderLine7, orderLine8)
//    val order4 = OrdersAnalyzer.Order(431, LocalDateTime.parse("2017-03-20T12:15:02"), orderLineList4)
//
//    val orderLine9 = OrdersAnalyzer.OrderLine(9872, "Pencil", 4, BigDecimal.valueOf(3.12))
//    val orderLine10 = OrdersAnalyzer.OrderLine(4098, "Marker", 5, BigDecimal.valueOf(4.5))
//    val orderLineList5 = listOf(orderLine9, orderLine10)
//    val order5 = OrdersAnalyzer.Order(690, LocalDateTime.parse("2017-03-26T11:14:00"), orderLineList5)
//
//    val ordersAnalyzer = OrdersAnalyzer()
//    println(ordersAnalyzer.totalDailySales(listOf(order1, order2, order3, order4, order5)))

}