package lib.shub39.heatmaps.calendar.bool.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import java.time.LocalDate

@Composable
internal fun DayBox(
    done: Boolean,
    day: LocalDate,
    editEnabled: Boolean,
    onClick: () -> Unit
) {
    var currentChange by rememberSaveable(day) { mutableStateOf(done) }

    val color by animateColorAsState(
        targetValue = when (currentChange) {
            true -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.background
        }
    )
    val textColor by animateColorAsState(
        targetValue = when (currentChange) {
            true -> MaterialTheme.colorScheme.onPrimary
            else -> MaterialTheme.colorScheme.onBackground
        }
    )

    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color)
            .clickable {
                if (editEnabled) {
                    currentChange = !currentChange
                    onClick()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.dayOfMonth.toString(),
            color = textColor
        )
    }
}