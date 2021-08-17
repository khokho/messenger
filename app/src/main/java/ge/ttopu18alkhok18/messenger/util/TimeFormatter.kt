package ge.ttopu18alkhok18.messenger.util

import android.graphics.ColorSpace.match
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Long.formatTime(now: Long): String {
    return when(now - this) {
        in 0..HOUR -> "${(now-this)/ MINUTE} min"
        in 0..DAY -> "${(now-this)/ HOUR} hour"
        else -> {
            val c = Calendar.getInstance()
            c.timeInMillis = this
            val output = SimpleDateFormat("MMM dd")
            return output.format(c.time)
        }
    }
}