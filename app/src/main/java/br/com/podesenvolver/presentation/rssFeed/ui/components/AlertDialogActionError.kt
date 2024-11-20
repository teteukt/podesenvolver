package br.com.podesenvolver.presentation.rssFeed.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.podesenvolver.R

@Composable
fun AlertDialogActionError(onDismiss: () -> Unit, title: String, text: String) {
    AlertDialog(onDismissRequest = onDismiss, confirmButton = {
        Button(onClick = onDismiss) { Text(stringResource(R.string.ok)) }
    }, title = { Text(title) }, text = { Text(text) })
}
