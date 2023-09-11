val data = arrayOf(1,2,3,4,5,6,7,8,9,10);

const val N = 5
const val R = 3
var count = 0
val out = arrayOfNulls<Int>(N).apply { fill(0) }

fun swap(x: Int, y: Int) {
    var temp = out[x]
    out[x] = out[y]
    out[y] = temp
}

fun perm(start: Int, end: Int) {
    if(start == end) {
        var i=0
        while (i<end) {
            print("${out[i]} ")
            i++
        }
        println()
        count++
        return
    }

    var i=start
    while (i<N) {
        swap(i,start)
        perm(start+1,end)
        swap(i,start)
        i++;
    }
}

fun dPerm(start: Int, end: Int) {
    if (start == end) {
        var i=0
        while (i<end) {
            print("${out[i]} ")
            i++
        }
        println()
        count++
        return
    }

    var i=0
    while (i<N) {
        out[start] = data[i]
        dPerm(start+1,end)
        i++
    }
}

fun comb(start: Int, end: Int, index: Int) {
    if (end == 0) {
        var i=0
        while (i<start) {
            print("${out[i]} ")
            i++
        }
        println()
        count++
    }
    else if(index == N) return
    else {
        out[start] = data[index]
        comb(start+1,end-1,index+1)
        comb(start,end,index+1)
    }
}

fun dComb(start: Int, end: Int, index: Int) {
    if (end == 0) {
        var i=0
        while (i<start) {
            print("${out[i]} ")
            i++
        }
        println()
        count++
    }
    else if (index == N) return
    else {
        out[start] = data[index]
        dComb(start+1,end-1,index)
        dComb(start,end,index+1)
    }
}
fun main() {
    var i=0
    while (i<N) {
        out[i] = data[i]
        i++
    }

    count = 0;
    perm(0,R)
    println("permutation ${count}")

    count = 0
    dPerm(0,R)
    println("duplicate permutation ${count}")

    count = 0
    comb(0,R,0)
    println("combination ${count}")

    count = 0
    dComb(0,R,0)
    println("duplicate combination ${count}")
}