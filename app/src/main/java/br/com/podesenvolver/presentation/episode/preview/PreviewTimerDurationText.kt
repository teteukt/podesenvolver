package br.com.podesenvolver.presentation.episode.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.presentation.episode.ui.components.TimerDurationText

@Preview(showBackground = true)
@Composable
fun PreviewTimerDurationText() {
    TimerDurationText(PositionDuration(30000, 60000))
}
