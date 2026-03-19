package service.diananicholasconsult.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import service.diananicholasconsult.app.ui.composable.approot.AppRoot
import service.diananicholasconsult.app.ui.theme.ServiceSkeletonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServiceSkeletonTheme {
                AppRoot()
            }
        }
    }
}