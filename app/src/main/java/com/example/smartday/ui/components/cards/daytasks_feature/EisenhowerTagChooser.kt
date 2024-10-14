package com.example.smartday.ui.components.cards.daytasks_feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartday.data.daytasks_feature.EisenhowerTag
import com.example.smartday.ui.components.cards.common.OneLineTagChooser


@Composable
fun EisenhowerTagChooser(
    eisenhowerTagIsActive: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    OneLineTagChooser(
        modifier = Modifier.fillMaxWidth(),
        tagsText = "Матрица Эйзенхауэра",
        tagIsChosen = eisenhowerTagIsActive,
        onCheckedChange = onCheckedChange
    )
}

@Composable
fun EisenhowerTagsList(
    tags: List<EisenhowerTag>,
    currentTag: Int,
    onSelect: (Int) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 36.dp)) {
        SingleEisenhowerTag(
            tagId = tags[0].eisenhowerTagId,
            tagTitle = tags[0].eisenhowerTagName,
            tagIsChecked = (tags[0].eisenhowerTagId == currentTag),
            onSelect = {
                onSelect(it)
            }
        )
        SingleEisenhowerTag(
            tagId = tags[1].eisenhowerTagId,
            tagTitle = tags[1].eisenhowerTagName,
            tagIsChecked = (tags[1].eisenhowerTagId == currentTag),
            onSelect = {
                onSelect(it)
            }
        )
        SingleEisenhowerTag(
            tagId = tags[2].eisenhowerTagId,
            tagTitle = tags[2].eisenhowerTagName,
            tagIsChecked = (tags[2].eisenhowerTagId == currentTag),
            onSelect = {
                onSelect(it)
            }
        )
        SingleEisenhowerTag(
            tagId = tags[3].eisenhowerTagId,
            tagTitle = tags[3].eisenhowerTagName,
            tagIsChecked = (tags[3].eisenhowerTagId == currentTag),
            onSelect = {
                onSelect(it)
            }
        )
    }
}

@Composable
fun SingleEisenhowerTag(
    tagId: Int,
    tagTitle: String,
    tagIsChecked: Boolean,
    onSelect: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row {
            RadioButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = tagIsChecked,
                onClick = {
                    onSelect(tagId)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = tagTitle,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}