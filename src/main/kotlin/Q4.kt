import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

//RUNTIME is T(n) = 2T(n/2) + O(n) using master theorem we get --> o(n log n)
fun findClosestAntennas(orgA: IntArray, orgB: IntArray, start: Int, end: Int): Double {

    //only 1 element
    if (end - start == 1) {
        return euclideanDistance(orgA[start],orgB[start])
    }

    //divide and conquer
    val middle = start + (end - start) / 2
    val left = findClosestAntennas(orgA, orgB, start, middle)
    val right = findClosestAntennas(orgA, orgB, middle, end)

    //combine
    var minimumDistance = min(left, right)
    var i = start
    var j = middle


    while (i < middle && j < end) {
        val distance =  euclideanDistance(orgA[i], orgB[j])
        if (distance < minimumDistance) {
            minimumDistance = distance
        }
        if (orgA[i] < orgB[j]) {
            i++
        } else {
            j++
        }
    }
    return minimumDistance
}

fun euclideanDistance(index1: Int, index2: Int): Double{
    return sqrt((index1 - index2).toDouble().pow(2.0))
}


fun main() {
    val orgA = intArrayOf(15,30)
    val orgB = intArrayOf(5, 18)
    val closestDistance = findClosestAntennas(orgA, orgB, 0, orgA.size)
    println("Closest distance between antenna towers: $closestDistance")
}