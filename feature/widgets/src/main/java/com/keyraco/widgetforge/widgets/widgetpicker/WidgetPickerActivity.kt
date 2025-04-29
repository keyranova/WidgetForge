package com.keyraco.widgetforge.widgets.widgetpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.keyraco.widgetforge.ui.LocalNavController
import com.keyraco.widgetforge.ui.theme.WidgetForgeTheme

class WidgetPickerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("id", 0)

        setContent {
            val navController = LocalNavController.current

            WidgetForgeTheme {
                WidgetPicker(
                    context = this,
                    id = id,
                    onSelect = {
                        finish()
                    }
                )
            }
        }
    }
}