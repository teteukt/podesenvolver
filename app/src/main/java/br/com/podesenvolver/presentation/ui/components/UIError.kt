package br.com.podesenvolver.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.podesenvolver.R
import br.com.podesenvolver.presentation.Typography

@Composable
fun UIError(title: String, message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        Image(
            painterResource(R.drawable.error_image),
            "imagem de microfone caindo indicando erro",
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(horizontal = 56.dp)
        )
        Text(title, style = Typography.titleLarge)
        Text(message)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUIError() {
    UIError("Titulo", "Mensagem descritiva")
}
