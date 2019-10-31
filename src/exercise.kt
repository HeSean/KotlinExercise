import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class OrdersAnalyzer {


    private fun Order.sumQuantity(): Int {
        var sum = 0
        orderLines.forEach { sum += it.quantity }
        //println("sumQuantity is $sum")
        return sum
    }


    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    /*
    1.  returns a map with a DayOfWeek as a key and the sum of ordered quantity for all products, as a value.
    2.  The method should always return a map. If there are no orders for some weekdays,
        then skip this day entry in the map.
     */
    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {
        val map = LinkedHashMap<DayOfWeek, Int>()

        /*
        adds blank days but working
        for (i in 1..7) {
            when (i) {
                1 -> map[DayOfWeek.MONDAY] = 0
                2 -> map[DayOfWeek.TUESDAY] = 0
                3 -> map[DayOfWeek.WEDNESDAY] = 0
                4 -> map[DayOfWeek.THURSDAY] = 0
                5 -> map[DayOfWeek.FRIDAY] = 0
                6 -> map[DayOfWeek.SATURDAY] = 0
                7 -> map[DayOfWeek.SUNDAY] = 0
            }
        }
        */


        orders.forEach { order ->
            val sum = order.sumQuantity()
            when (order.creationDate.dayOfWeek) {
                DayOfWeek.MONDAY -> map[DayOfWeek.MONDAY] = sum
                DayOfWeek.TUESDAY -> map[DayOfWeek.TUESDAY] = sum
                DayOfWeek.WEDNESDAY -> map[DayOfWeek.WEDNESDAY] = sum
                DayOfWeek.THURSDAY -> map[DayOfWeek.THURSDAY] = sum
                DayOfWeek.FRIDAY -> map[DayOfWeek.FRIDAY] = sum
                DayOfWeek.SATURDAY -> map[DayOfWeek.SATURDAY] = sum
                DayOfWeek.SUNDAY -> map[DayOfWeek.SUNDAY] = sum
                else -> {
                    println("DayOfWeek entered wasn't listed in the bible :p")
                }
            }
        }

//        map.toSortedMap(kotlin.Comparator { a, b ->
//            when {
//                (a.value > b.value) -> 1
//                (a.value < b.value) -> 1
//                else -> 0
//            }
//        })

        return map
    }
}


fun main() {

    val orderLine1 = OrdersAnalyzer.OrderLine(9872, "Pencil", 3, BigDecimal.valueOf(3))
    val orderLineList1 = listOf(orderLine1)
    val order1 = OrdersAnalyzer.Order(554, LocalDateTime.parse("2017-03-25T10:35:20"), orderLineList1)

    val orderLine2 = OrdersAnalyzer.OrderLine(9872, "Pencil", 2, BigDecimal.valueOf(3))
    val orderLine3 = OrdersAnalyzer.OrderLine(1746, "Eraser", 1, BigDecimal.valueOf(1))
    val orderLineList2 = listOf(orderLine2, orderLine3)
    val order2 = OrdersAnalyzer.Order(555, LocalDateTime.parse("2017-03-25T11:24:20"), orderLineList2)

    val orderLine4 = OrdersAnalyzer.OrderLine(5723, "Pen", 4, BigDecimal.valueOf(4.22))
    val orderLine5 = OrdersAnalyzer.OrderLine(9872, "Pencil", 4, BigDecimal.valueOf(3.12))
    val orderLine6 = OrdersAnalyzer.OrderLine(1746, "Erasers Set", 1, BigDecimal.valueOf(6.15))
    val orderLineList3 = listOf(orderLine4, orderLine5, orderLine6)
    val order3 = OrdersAnalyzer.Order(453, LocalDateTime.parse("2017-03-27T14:53:12"), orderLineList3)

    val orderLine7 = OrdersAnalyzer.OrderLine(5723, "Pen", 7, BigDecimal.valueOf(4.22))
    val orderLine8 = OrdersAnalyzer.OrderLine(3433, "Erasers Set", 2, BigDecimal.valueOf(6.15))
    val orderLineList4 = listOf(orderLine7, orderLine8)
    val order4 = OrdersAnalyzer.Order(431, LocalDateTime.parse("2017-03-20T12:15:02"), orderLineList4)

    val orderLine9 = OrdersAnalyzer.OrderLine(9872, "Pencil", 4, BigDecimal.valueOf(3.12))
    val orderLine10 = OrdersAnalyzer.OrderLine(4098, "Marker", 5, BigDecimal.valueOf(4.5))
    val orderLineList5 = listOf(orderLine9, orderLine10)
    val order5 = OrdersAnalyzer.Order(690, LocalDateTime.parse("2017-03-26T11:14:00"), orderLineList5)

    val ordersAnalyzer = OrdersAnalyzer()
    println(ordersAnalyzer.totalDailySales(listOf(order1, order2, order3, order4, order5)))

}