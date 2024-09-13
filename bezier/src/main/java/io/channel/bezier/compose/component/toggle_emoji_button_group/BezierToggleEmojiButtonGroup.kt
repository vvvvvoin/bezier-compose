package io.channel.bezier.compose.component.toggle_emoji_button_group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.channel.bezier.BezierTheme
import io.channel.bezier.compose.component.toggle_emoji_button_group.property.BezierToggleEmojiButtonGroupFillDirectionType
import io.channel.bezier.compose.component.toggle_emoji_button_group.source.toggle_emoji_button.BezierToggleEmojiButton
import io.channel.bezier.compose.component.toggle_emoji_button_group.source.toggle_emoji_button.properties.BezierToggleEmojiButtonVariant
import io.channel.bezier.extension.thenIf

@Composable
fun BezierToggleEmojiButtonGroup(
        emojis: List<String>,
        type: BezierToggleEmojiButtonGroupFillDirectionType,
        onEmojiClick: (index: Int) -> Unit,
        modifier: Modifier = Modifier,
        selectedIndex: Int? = null,
) {
    val horizontalArrangement = when (type) {
        BezierToggleEmojiButtonGroupFillDirectionType.Undefined -> Arrangement.spacedBy(
                space = 6.dp,
                alignment = Alignment.CenterHorizontally,
        )

        BezierToggleEmojiButtonGroupFillDirectionType.Horizontal -> Arrangement.spacedBy(6.dp)
        BezierToggleEmojiButtonGroupFillDirectionType.All -> Arrangement.spacedBy(6.dp)
    }

    Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
    ) {
        emojis.forEachIndexed { index, emoji ->
            BezierToggleEmojiButton(
                    selected = index == selectedIndex,
                    name = emoji,
                    variant = BezierToggleEmojiButtonVariant.Primary,
                    onClick = { onEmojiClick.invoke(index) },
                    modifier = Modifier
                            .thenIf(type == BezierToggleEmojiButtonGroupFillDirectionType.All) {
                                aspectRatio(1f).weight(1f)
                            }
                            .thenIf(type == BezierToggleEmojiButtonGroupFillDirectionType.Horizontal) {
                                weight(1f)
                            },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BezierToggleEmojiButtonGroupPreview() {
    BezierTheme {
        Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val emojis = remember { listOf("1", "2", "3", "4") }
            var selectedIndex by remember { mutableStateOf<Int?>(null) }

            BezierToggleEmojiButtonGroup(
                    emojis = emojis,
                    type = BezierToggleEmojiButtonGroupFillDirectionType.Undefined,
                    onEmojiClick = { selectedIndex = it },
                    selectedIndex = selectedIndex,
                    modifier = Modifier.fillMaxWidth(),
            )

            BezierToggleEmojiButtonGroup(
                    emojis = emojis,
                    type = BezierToggleEmojiButtonGroupFillDirectionType.Horizontal,
                    onEmojiClick = { selectedIndex = it },
                    selectedIndex = selectedIndex,
                    modifier = Modifier.fillMaxWidth(),
            )

            BezierToggleEmojiButtonGroup(
                    emojis = emojis,
                    type = BezierToggleEmojiButtonGroupFillDirectionType.All,
                    onEmojiClick = { selectedIndex = it },
                    selectedIndex = selectedIndex,
                    modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
