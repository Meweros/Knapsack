import java.util.*

// Метод решения задачи о рюкзаве - Жадный алгоритм
object KnapsackGreedy {
    fun knapsackGreedy(capacity: Int, items: Array<KnapsackBranchBound.Item>): Int {
        var capacity = capacity
        Arrays.sort(
            items
        ) { o1, o2 -> java.lang.Double.compare(o2.ratio.toDouble(), o1.ratio.toDouble()) }

        var totalValue = 0
        for (item in items) {
            if (capacity - item.weight >= 0) {
                capacity -= item.weight
                totalValue += item.value
            } else {
                val fraction = (capacity.toDouble() / item.weight.toDouble())
                totalValue += (fraction * item.value).toInt()
                break
            }
        }
        return totalValue
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val capacity = 50
        val items = arrayOf(
            KnapsackBranchBound.Item(10, 60), KnapsackBranchBound.Item(20, 100),
            KnapsackBranchBound.Item(30, 120), KnapsackBranchBound.Item(40, 200)
        )
        val totalValue = knapsackGreedy(capacity, items)
        println("Максимальная стоимость которая может быть помещена в рюкзак вместимостью $capacity это: $totalValue")
    }
}