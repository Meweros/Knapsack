import java.util.PriorityQueue

// Метод решения задачи о рюкзаве - Метод ветвей и границ
class KnapsackBranchBound {
    data class Item(var weight: Int, var value: Int) : Comparable<Item> {
        val ratio: Int = value / weight

        override fun compareTo(other: Item): Int {
            return other.ratio.compareTo(ratio)
        }
    }

    data class Node(var level: Int, var weight: Int, var profit: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return other.profit.compareTo(profit)
        }
    }

    companion object {
        fun knapsack(weights: IntArray, values: IntArray, capacity: Int): Int {
            val n = weights.size
            val items = Array(n) { Item(weights[it], values[it]) }
            items.sortByDescending { it.ratio }

            val queue = PriorityQueue<Node>()
            val root = Node(-1, 0, 0)
            queue.offer(root)

            var maxProfit = 0
            while (queue.isNotEmpty()) {
                val node = queue.poll()
                if (node.level == n - 1) {
                    continue
                }
                val level = node.level + 1
                val weight = node.weight
                val profit = node.profit
                val left = Node(level, weight + items[level].weight, profit + items[level].value)
                val right = Node(level, weight, profit)
                if (left.weight <= capacity) {
                    if (left.profit > maxProfit) {
                        maxProfit = left.profit
                    }
                    queue.offer(left)
                }
                val bound = bound(right, items, capacity)
                if (bound > maxProfit) {
                    queue.offer(right)
                }
            }

            return maxProfit
        }

        private fun bound(node: Node, items: Array<Item>, capacity: Int): Double {
            var weight = node.weight
            var profit = node.profit
            for (i in node.level + 1 until items.size) {
                if (weight + items[i].weight <= capacity) {
                    weight += items[i].weight
                    profit += items[i].value
                } else {
                    profit += (capacity - weight) * items[i].ratio
                    break
                }
            }
            return profit.toDouble()
        }
    }
}

fun main() {
    val weights = intArrayOf(10, 20, 30)
    val values = intArrayOf(60, 100, 120)
    val capacity = 50
    val maxProfit = KnapsackBranchBound.knapsack(weights, values, capacity)
    println("Максимальная выгода: $maxProfit")
}

