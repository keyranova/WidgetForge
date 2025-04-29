package com.keyraco.widgetforge.common.model

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

enum class WidgetSize {
    SMALL
//    SMALL_BOX(210.dp, 110.dp),
//    SMALL_RECTANGLE(330.dp, 110.dp),
//    TINY_TALL_RECTANGLE(110.dp, 310.dp),
//    TALL_RECTANGLE(210.dp, 310.dp),
//    MEDIUM_BOX(330.dp, 310.dp),
//    BIG_RECTANGLE(330.dp, 410.dp)
}

enum class SmallWidgetDimensions(val size: DpSize) {
    SMALL_MIN(DpSize(100.dp, 100.dp)),
    SMALL_MAX(DpSize(250.dp, 250.dp))
}