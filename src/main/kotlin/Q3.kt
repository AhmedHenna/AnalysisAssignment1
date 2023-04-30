/*
The time complexity of the dynamic programming algorithm for the rod cutting problem is O(n^2), where n is the length
of the rod. This is because we have two nested loops that iterate over all possible lengths and cuts of the rod.

The space complexity of the algorithm is also O(n), because we use an array of length n+1 to store the maximum profit
we can obtain for each possible length of the rod, and a list of length n to store the lengths of the cuts that result
in the maximum profit.
 */

fun cuttingRod(prices: IntArray, n: Int): Int {
    val storeMaxProfit = IntArray(n + 1)

    for (i in 1..n) {
        var maxPrice = 0
        for (j in 1..i) {
            maxPrice = maxOf(maxPrice, prices[j - 1] + storeMaxProfit[i - j])
        }
        storeMaxProfit[i] = maxPrice
    }

    return storeMaxProfit[n]
}

fun main(){
    val prices = intArrayOf(1, 5, 8, 9, 10, 17, 17, 20)
    val n = 4
    val maxProfit = cuttingRod(prices, n)
    println("Maximum profit: $maxProfit")
}