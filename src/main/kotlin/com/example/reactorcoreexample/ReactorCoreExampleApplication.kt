package com.example.reactorcoreexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import java.time.LocalTime

//@SpringBootApplication
//class ReactorCoreExampleApplication

fun main(args: Array<String>) {
    val currentMinuteOfDay: Int = (LocalTime.now().hour * 60) + LocalTime.now().minute
    println(currentMinuteOfDay)
}
