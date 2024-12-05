package com.shub39.heatmaps

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shub39.heatmaps.types.BooleanHeatMapPreview
import com.shub39.heatmaps.types.Types

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HeatMaps() {
    var currentType by remember { mutableStateOf<Types>(Types.BOOLEAN_HEAT_MAP) }
    var showTypePicker by remember { mutableStateOf(false) }

    if (showTypePicker) {
        ModalBottomSheet(
            onDismissRequest = { showTypePicker = false }
        ) {
            FlowRow(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Types.entries.forEach { types ->
                    AssistChip(
                        onClick = { currentType = types },
                        label = { Text(types.name) },
                        enabled = types != currentType
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showTypePicker = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentType,
                modifier = Modifier.padding(16.dp)
            ) {
                when (it) {
                    Types.BOOLEAN_HEAT_MAP -> {
                        BooleanHeatMapPreview()
                    }

                    Types.CONCENTRATION_HEAT_MAP -> {

                    }
                }
            }
        }

    }
}