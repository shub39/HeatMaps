package com.shub39.heatmaps.types

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import lib.shub39.heatmaps.calendar.bool.BooleanHeatMap
import java.time.LocalDate
import kotlin.collections.minus
import kotlin.collections.plus

@Composable
fun BooleanHeatMapPreview() {
    var data by remember { mutableStateOf(listOf<LocalDate>(LocalDate.now())) }

    Column {
        Card {
            BooleanHeatMap(
                dates = data,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                editEnabled = true,
                maxWeeks = 17,
                onClick = { localDate ->
                    if (data.contains(localDate)) {
                        data -= localDate
                    } else {
                        data += localDate
                    }
                }
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        LazyColumn {
            item {
                Text("Dates")
            }

            items(data.toList()) {
                Text(text = it.toString())
            }
        }
    }
}