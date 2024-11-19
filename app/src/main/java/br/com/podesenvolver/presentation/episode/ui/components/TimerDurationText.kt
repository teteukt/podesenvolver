package br.com.podesenvolver.presentation.episode.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.extensions.toTimeDisplayText

@Composable
fun TimerDurationText(
    positionDuration: PositionDuration
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(positionDuration.position.toTimeDisplayText())
        Text(positionDuration.duration.toTimeDisplayText())
    }
}
