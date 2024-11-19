package br.com.podesenvolver.domain

import br.com.podesenvolver.extensions.orZero

data class PositionDuration(val position: Long = 0, val duration: Long = 0) {
    fun ratio(): Float = (position.toFloat() / duration.toFloat()).takeIf { it > 0 }.orZero()
}