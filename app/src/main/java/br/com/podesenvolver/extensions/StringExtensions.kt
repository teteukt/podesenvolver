package br.com.podesenvolver.extensions

fun String.isLong() = this.toLongOrNull() != null

fun String.timeHhMmSsToTimeMillis() = this.split(":").let { timeComponents ->
    if (timeComponents.size != 3) return@let 0L

    return@let mapOf(
        timeComponents[0].toLongOrNull().orZero() to 3600,
        timeComponents[1].toLongOrNull().orZero() to 60,
        timeComponents[2].toLongOrNull().orZero() to 1
    ).map { it.key * it.value }.sum() * 1000
}
