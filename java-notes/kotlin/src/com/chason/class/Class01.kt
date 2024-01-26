package com.chason.`class`

/**
 * 变量定义
 */
fun main() {

    var x : Int
    var _a : Int = 10  // 他可以自动推断你的数据类型
    var _b = 10 // 所以你可以省略具体的类型

    val number = 100  // 使用 val 定义 等同于 Java中的 final

//    number = 88  报错
//    var $a : Int     $ 开头不合法


    var c : UInt = 0u  // 表示无符号的数， 但是无符号之后 + u
    var d : Double = 1.0
    var a1 : Float = 1.0f


    // ==  位运算

    val a2 = 1
    println(a2 shl 2)   // 结果是4


    val a3 = 8
    println(a3 shr 1)  // 结果是4

}
