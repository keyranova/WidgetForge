package com.keyraco.widgetforge.builder.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowCircleLeft
import androidx.compose.material.icons.rounded.ArrowCircleRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.keyraco.widgetforge.builder.ui.complications.Complication
import com.keyraco.widgetforge.common.model.WidgetRowType
import com.keyraco.widgetforge.data.model.WidgetComplication
import com.keyraco.widgetforge.data.model.WidgetRow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.SmallItemRow(
    widgetRow: WidgetRow,
    setWidgetRowType: (widgetRowType: WidgetRowType) -> Unit,
    addComplication: (complication: WidgetComplication) -> Unit
) {
    val configuration = LocalConfiguration.current

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = when (widgetRow.type) {
            WidgetRowType.FULL_TWO_WIDE -> 0
            WidgetRowType.TWO_ONE_WIDE -> 1
        },
        pageCount = { 2 }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.distinctUntilChanged().collect { page ->
            when (page) {
                0 -> {
                    setWidgetRowType(WidgetRowType.FULL_TWO_WIDE)
                }

                1 -> {
                    setWidgetRowType(WidgetRowType.TWO_ONE_WIDE)
                }
            }
        }
    }

    val complication0 by remember(widgetRow) {
        derivedStateOf {
            widgetRow.complications?.firstOrNull {
                it.position == 0
            }
        }
    }

    val complication1 by remember(widgetRow) {
        derivedStateOf {
            widgetRow.complications?.firstOrNull {
                it.position == 1
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            enabled = pagerState.currentPage == 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        ) {
            Icon(Icons.Rounded.ArrowCircleLeft, "previous layout")
        }

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> 0.4f
                    Configuration.ORIENTATION_PORTRAIT -> 0.8f
                    else -> 0.5f
                })
                .aspectRatio(2f),
            state = pagerState
        ) { page ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                when (page) {
                    0 -> {
                        if (complication0 != null) {
                            Complication(complication = complication0!!, cellWidth = 2)
                        } else {
                            AddComplicationButton(
                                addComplication = { complicationType ->
                                    addComplication(WidgetComplication(
                                        type = complicationType,
                                        position = 0
                                    ))
                                }
                            )
                        }
                    }

                    1 -> {
                        if (complication0 != null) {
                            Complication(complication = complication0!!)
                        } else {
                            AddComplicationButton(
                                addComplication = { complicationType ->
                                    addComplication(WidgetComplication(
                                        type = complicationType,
                                        position = 0
                                    ))
                                }
                            )
                        }

                        if (complication1 != null) {
                            Complication(complication = complication1!!)
                        } else {
                            AddComplicationButton(
                                addComplication = { complicationType ->
                                    addComplication(WidgetComplication(
                                        type = complicationType,
                                        position = 1
                                    ))
                                }
                            )
                        }
                    }
                }
            }
        }

        IconButton(
            enabled = pagerState.currentPage == 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                }
            }
        ) {
            Icon(Icons.Rounded.ArrowCircleRight, "next layout")
        }
    }
}