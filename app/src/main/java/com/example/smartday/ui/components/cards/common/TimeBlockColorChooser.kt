package com.example.smartday.ui.components.cards.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartday.ui.models.ColorModel

@Composable
fun TimeBlockColorChooser(
    timeBlockTagIsActive: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    OneLineTagChooser(
        modifier = Modifier.fillMaxWidth(),
        tagsText = "Включить в тайм-блок",
        tagIsChosen = timeBlockTagIsActive,
        onCheckedChange = onCheckedChange
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TimeBlockTagsList(
    tags: List<ColorModel>,
    currentTag: String,
    onSelect: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        SingleTimeBlockColorTag(
            tagColor = tags[0].hex,
            tagIsChecked = tags[0].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
        SingleTimeBlockColorTag(
            tagColor = tags[1].hex,
            tagIsChecked = tags[1].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
        SingleTimeBlockColorTag(
            tagColor = tags[2].hex,
            tagIsChecked = tags[2].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
        SingleTimeBlockColorTag(
            tagColor = tags[3].hex,
            tagIsChecked = tags[3].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
        SingleTimeBlockColorTag(
            tagColor = tags[4].hex,
            tagIsChecked = tags[4].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
        SingleTimeBlockColorTag(
            tagColor = tags[5].hex,
            tagIsChecked = tags[5].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
        SingleTimeBlockColorTag(
            tagColor = tags[6].hex,
            tagIsChecked = tags[6].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
        SingleTimeBlockColorTag(
            tagColor = tags[7].hex,
            tagIsChecked = tags[7].hex == currentTag,
            onSelect = {
                onSelect(it)
            })
    }
}

@Composable
fun SingleTimeBlockColorTag(
    tagColor: String,
    tagIsChecked: Boolean,
    onSelect: (String) -> Unit
) {
    val itemColor = android.graphics.Color.parseColor(tagColor)
    Row {
        RadioButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            selected = tagIsChecked,
            onClick = {
                onSelect(tagColor)
            }
        )
        Canvas(modifier = Modifier
            .size(28.dp)
            .align(Alignment.CenterVertically),
            onDraw = {
                drawCircle(
                    color = Color(itemColor)
                )
            }
        )
    }
}