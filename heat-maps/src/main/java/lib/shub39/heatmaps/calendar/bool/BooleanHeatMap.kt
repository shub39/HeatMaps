package lib.shub39.heatmaps.calendar.bool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lib.shub39.heatmaps.calendar.bool.component.DayBox
import java.time.LocalDate

@Composable
fun BooleanHeatMap(
    dates: List<LocalDate>,
    modifier: Modifier = Modifier,
    maxWeeks: Long = 17,
    editEnabled: Boolean = false,
    onClick: (LocalDate) -> Unit = {},
) {
    val listState = rememberLazyListState()

    var weeks by remember { mutableStateOf<List<List<LocalDate?>>>(emptyList()) }
    val daysSet by remember { mutableStateOf(dates.map { it }.toSet()) }

    LaunchedEffect(daysSet) {
        weeks = withContext(Dispatchers.Default) {
            val startDate = LocalDate.now().minusDays(LocalDate.now().dayOfWeek.value.toLong())
                .minusWeeks(maxWeeks)
            val allDays = generateSequence(startDate) { it.plusDays(1) }
                .takeWhile { !it.isAfter(LocalDate.now()) }
                .toList()

            val daysWithEmptyDays = mutableListOf<LocalDate?>()
            var currentMonth = startDate.monthValue

            for (day in allDays) {
                daysWithEmptyDays.add(day)

                if (day.plusDays(1).monthValue != currentMonth) {
                    currentMonth = day.plusDays(1).monthValue
                    repeat(7) {
                        daysWithEmptyDays.add(null)
                    }
                }
            }

            daysWithEmptyDays.chunked(7)
        }

        listState.scrollToItem(weeks.size - 1)
    }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        state = listState
    ) {
        items(weeks) { week ->
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                week.forEach { day ->
                    if (day == null) {

                        Box(modifier = Modifier.size(30.dp))

                    } else {
                        val containsDay by remember { mutableStateOf(dates.contains(day)) }

                        DayBox(
                            done = containsDay,
                            day = day,
                            editEnabled = editEnabled,
                            onClick = { onClick(day) }
                        )
                    }
                }
            }
        }
    }

}