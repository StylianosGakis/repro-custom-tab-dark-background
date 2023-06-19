package xyz.stylianosgakis.custom_tab_dark_background

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.stylianosgakis.custom_tab_dark_background.ui.theme.CustomtabdarkbackgroundTheme

class MainActivity : ComponentActivity() {
    private val customTab = registerForActivityResult(
        object : ActivityResultContract<String, Int>() {
            override fun createIntent(context: Context, input: String): Intent {
                return CustomTabsIntent.Builder()
                    .setInitialActivityHeightPx(500)
                    .setToolbarCornerRadiusDp(16)
                    .build()
                    .intent
                    .apply {
                        setData(Uri.parse(input))
                    }
            }

            override fun parseResult(resultCode: Int, intent: Intent?): Int {
                return resultCode
            }
        },
    ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomtabdarkbackgroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize().padding(48.dp),
                    ) {
                        Text("Custom tab shouldn't make this hide")
                        Spacer(Modifier.height(24.dp))
                        Button(
                            onClick = { customTab.launch("https://www.google.com") },
                        ) {
                            Text("Open a website")
                        }
                    }
                }
            }
        }
    }
}
