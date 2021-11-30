package com.example.reactorcoreexample

import java.time.LocalTime
import kotlin.math.ceil
import kotlin.math.round

fun main() {
    // main loop
    var tickCounter = 0
    var powerLevel = 0
    var initialized = false
    val intervals = listOf(111, 900, 1000, 1111)
    var incrementing = true

    while (true) {
        val currentMinuteOfDay: Int = (LocalTime.now().hour * 60) + LocalTime.now().minute
        if (!initialized) {
            var powerState: Double = powerLevel.toDouble()
            for (i in intervals.indices step 2) {
                tickCounter = intervals[i]

                val minutes = (intervals[i + 1] - intervals[i])
                if (incrementing) {
                    incrementing = false
                    val tick: Double = (100.0) / minutes
                    while (powerLevel < 100) {
                        tickCounter += ceil(tick).toInt()
                        powerState += tick
                        powerLevel = round(powerState).toInt()
                        if (tickCounter == currentMinuteOfDay) {
                            initialized = true
                            break
                        }
                    }
                } else {
                    val tick: Double = powerState / minutes
                    incrementing = true
                    while (powerLevel > 0) {
                        tickCounter += ceil(tick).toInt()
                        powerState -= tick
                        powerLevel = round(powerState).toInt()
                        if (tickCounter == currentMinuteOfDay) {
                            initialized = true
                            break
                        }
                    }
                }
                if (tickCounter > 1440) {
                    tickCounter -= 1440
                }
                println("resulted power level $powerLevel and minute of day $currentMinuteOfDay == tickCounter $tickCounter")
                if (initialized) break
            }
            initialized = true
        } else {
            var powerState: Double = powerLevel.toDouble()

            for (i in intervals.indices step 2) {
                if (tickCounter >= intervals[i] && tickCounter < intervals[i + 1]) {
                    val minutes = (currentMinuteOfDay - tickCounter) + (intervals[i + 1] - intervals[i])

                    val tts: Long = (60_000 * (minutes / 100.0)).toLong()

                    if (incrementing) {
                        incrementing = false
                        val tick: Double = (100.0 + powerState) / minutes
                        while (powerLevel < 100) {
                            tickCounter += ceil(tick).toInt()
                            powerState += tick
                            powerLevel = round(powerState).toInt()
                            Thread.sleep(tts)
                        }
                    } else {
                        val tick: Double = powerState / minutes
                        incrementing = true
                        while (powerLevel > 0) {
                            tickCounter += ceil(tick).toInt()
                            powerState -= tick
                            powerLevel = round(powerState).toInt()
                            Thread.sleep(tts)
                        }
                    }
                    if (tickCounter > 1440) {
                        tickCounter -= 1440
                    }
                } else {
                    tickCounter = intervals[i + 1]
                }
            }
        }
    }
}