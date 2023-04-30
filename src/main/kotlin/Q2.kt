import kotlin.math.max

/*
a) Case 1 - LCS[i, j] = max(LCS[i, j - 1], LCS[i - 1, j])
   Case 2 - LCS[i, j] = LCS[i-1, j-1]

d) We can find the sequence by going backwards. We can do this by starting off
   from the bottom right corner and check if LCS[i,j] = LCS[i-1,j] then we don't
   take that in our sequence and move up 1 row. If LCS[i,j] = LCS[i,j-1] then we
   don't take it in our sequence and move left 1 column. If LCS[i,j] = LCS[i-1,j-1] + 1
   then we take both S[i] and T[j] in our sequence. Then we move 1 to the left and up,
   and continue doing the same thing till we reach the top left corner.
 */

fun maxLcsLength(s: String, t: String): Int {
    val matrix = Array(s.length) { Array(t.length) { 0 } }

    for (i in s.indices) {
        for (j in t.indices) {
            val prevJ = if (j - 1 >= 0) j - 1 else j
            val prevI = if (i - 1 >= 0) i - 1 else i

            if (s[i] != t[j]) {
                matrix[i][j] = max(matrix[i][prevJ], matrix[prevI][j])
            } else {
                matrix[i][j] = 1 + matrix[prevI][prevJ]
            }
        }
    }

    print("    ")
    println(t.toCharArray().joinToString( " | " ))
    for(i in matrix.indices){
        print("${s[i]} | ")
        println(matrix[i].joinToString(" | "))
    }

    return matrix[s.lastIndex][t.lastIndex]
}


fun main() {
    val s = "ABAZDC"
    val t = "BACBAD"

    println("Max LCS Length: ${maxLcsLength(s, t)}")
}