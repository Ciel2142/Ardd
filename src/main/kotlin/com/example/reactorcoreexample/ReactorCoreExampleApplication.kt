package com.example.reactorcoreexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import java.time.LocalTime

//@SpringBootApplication
//class ReactorCoreExampleApplication

fun main(args: Array<String>) {
    val minutes = 111;
    val div = if (minutes >= 100) 100.0 else minutes.toDouble();
    println((60_000 * (minutes / div)).toLong())
}
