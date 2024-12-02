package com.shub39.heatmaps

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import lib.shub39.heatmaps.calendar.bool.BooleanHeatMap
import java.time.LocalDate

@Composable
fun HeatMaps() {
    var currentType by remember { mutableStateOf<Types>(Types.BOOLEAN_HEAT_MAP) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(Types.entries.toList()) { type ->
                    AssistChip(
                        onClick = { currentType = type },
                        label = { Text(type.name) }
                    )
                }
            }

            AnimatedContent(
                targetState = currentType,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                when (it) {
                    Types.BOOLEAN_HEAT_MAP -> {
                        var data by remember { mutableStateOf(listOf<LocalDate>(LocalDate.now())) }

                        LaunchedEffect(data) {
                            println(data.toString())
                        }

                        Column {
                            Card {
                                BooleanHeatMap(
                                    dates = data,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    editEnabled = true,
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
                }
            }
        }

    }
}