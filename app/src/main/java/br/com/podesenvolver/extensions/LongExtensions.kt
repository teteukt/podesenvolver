package br.com.podesenvolver.extensions

fun Long?.orZero() = this ?: 0

fun Long.toTimeDisplayText(): String {
    val seconds = (this / 1000L).toInt()
    val minutes = seconds / 60
    val hours = minutes / 60
    val secondsText = (seconds % 60).toString().padStart(2, '0')
    val minutesText = (minutes % 60).toString().padStart(2, '0')
    val hoursText = hours.toString().padStart(2, '0')
    return "$hoursText:$minutesText:$secondsText"
}
