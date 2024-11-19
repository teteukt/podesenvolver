package br.com.podesenvolver.presentation.episode.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.podesenvolver.domain.PositionDuration
import br.com.podesenvolver.presentation.PodesenvolverTheme
import br.com.podesenvolver.presentation.episode.ui.components.PlayerControls

@Preview(showBackground = true)
@Composable
fun PreviewPlayerControls() {
    PodesenvolverTheme {
        PlayerControls(
            positionDuration = PositionDuration(20000, 60000),
            playing = false,
            onPlay = {},
            onPause = {},
            onGoPrevious = {},
            onGoNext = {},
            onSeek = {}
        )
    }
}
