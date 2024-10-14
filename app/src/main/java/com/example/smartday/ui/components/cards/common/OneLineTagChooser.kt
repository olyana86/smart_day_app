package com.example.smartday.ui.components.cards.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OneLineTagChooser(
    modifier: Modifier,
    tagsText: String,
    tagIsChosen: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Checkbox(
                modifier = Modifier.align(Alignment.CenterVertically),
                checked = tagIsChosen,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = tagsText,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}