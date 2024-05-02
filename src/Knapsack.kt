

// Метод решения задачи о рюкзаве - Метод перебора
object Knapsack {
    var maxValue: Int = 0
    lateinit var bestItems: BooleanArray

    @JvmStatic
    fun main(args: Array<String>) {
        val weights = intArrayOf(2, 5, 4, 3)
        val values = intArrayOf(10, 15, 7, 8)
        val capacity = 11
        knapsack(0, weights, values, BooleanArray(weights.size), capacity)
        println("Максимальная стоимость: " + maxValue)
        print("Предметы: ")
        for (i in bestItems.indices) {
            if (bestItems[i]) {
                print("$i ")
            }
        }
    }

    fun knapsack(itemIndex: Int, weights: IntArray, values: IntArray, items: BooleanArray, capacity: Int) {
        if (itemIndex == weights.size) {
            var totalValue = 0
            var totalWeight = 0
            for (i in items.indices) {
                if (items[i]) {
                    totalValue += values[i]
                    totalWeight += weights[i]
                }
            }
            if (totalWeight <= capacity && totalValue > maxValue) {
                maxValue = totalValue
                bestItems = items.copyOf(items.size)
            }
            return
        }
        items[itemIndex] = true
        knapsack(itemIndex + 1, weights, values, items, capacity)
        items[itemIndex] = false
        knapsack(itemIndex + 1, weights, values, items, capacity)
    }
}