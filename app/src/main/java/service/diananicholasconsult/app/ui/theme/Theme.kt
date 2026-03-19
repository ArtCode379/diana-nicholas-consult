package service.diananicholasconsult.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val DNNCCColorScheme = darkColorScheme(
    primary = CopperAccent,
    onPrimary = DarkBackground,
    primaryContainer = DeepGold,
    onPrimaryContainer = TextPrimary,
    secondary = WarmGold,
    onSecondary = DarkBackground,
    secondaryContainer = CardSurface,
    onSecondaryContainer = TextPrimary,
    tertiary = CopperLight,
    onTertiary = DarkBackground,
    tertiaryContainer = ElevatedSurface,
    onTertiaryContainer = TextPrimary,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = CardSurface,
    onSurfaceVariant = TextSecondary,
    outline = CopperAccent,
    outlineVariant = ElevatedSurface,
    error = StatusError,
    onError = DarkBackground,
    inverseSurface = TextPrimary,
    inverseOnSurface = DarkBackground,
    inversePrimary = DeepGold,
)

private val DNNCCShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

@Composable
fun ServiceSkeletonTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = DNNCCColorScheme,
        shapes = DNNCCShapes,
        typography = Typography,
        content = content,
    )
}
