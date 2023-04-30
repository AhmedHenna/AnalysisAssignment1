import kotlin.math.max

/*

1.
-  p[i] + S(i + 1 + f[i])
- S(i+1)

 */


private val solvedQuestions = mutableSetOf<Int>()


fun maxScore(p: IntArray, f: IntArray): Int {
    solvedQuestions.clear()
    val pointsCache = Array<Int?>(p.size) { null }
    return maxScoreRec(0, p, f, pointsCache)
}

fun maxScoreRec(i: Int, p: IntArray, f: IntArray, cache: Array<Int?>): Int {
    if (cache[i] != null) {
        return cache[i]!!
    }

    if (i == p.lastIndex) {
        return p[i]
    }
    val pointsIfSolved = if (i + 1 + f[i] > p.lastIndex) p[i] else p[i] + maxScoreRec(i + 1 + f[i], p, f, cache)
    val pointIfSkipped = if (i + 1 > p.lastIndex) 0 else maxScoreRec(i + 1, p, f, cache)

    return if (pointsIfSolved > pointIfSkipped) {
        solvedQuestions.add(i)
        cache[i] = pointsIfSolved
        pointsIfSolved
    } else {
        cache[i] = pointIfSkipped
        pointIfSkipped
    }
}

fun main() {
    val p = intArrayOf(1,5,2,4,3)
    val f = intArrayOf(3,2,1,1,2)
    val max = maxScore(p, f)
    val solved = solvedQuestions.sorted().map { it +1 }.joinToString(",")

    println("Max score is: $max")
    println("Solved questions: $solved")

}
