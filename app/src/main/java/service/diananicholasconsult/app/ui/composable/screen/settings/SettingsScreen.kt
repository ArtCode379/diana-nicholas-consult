package service.diananicholasconsult.app.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import service.diananicholasconsult.app.R
import service.diananicholasconsult.app.ui.theme.CardSurface
import service.diananicholasconsult.app.ui.theme.CopperAccent
import service.diananicholasconsult.app.ui.theme.DarkBackground
import service.diananicholasconsult.app.ui.theme.DarkSurface
import service.diananicholasconsult.app.ui.theme.ElevatedSurface
import service.diananicholasconsult.app.ui.theme.TextMuted
import service.diananicholasconsult.app.ui.theme.TextPrimary
import service.diananicholasconsult.app.ui.theme.TextSecondary
import service.diananicholasconsult.app.ui.theme.WarmGold

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    SettingsScreenContent(modifier = modifier)
}

@Composable
fun SettingsScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState()),
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(colors = listOf(DarkSurface, DarkBackground)))
                .padding(horizontal = 20.dp, vertical = 24.dp),
        ) {
            Text(
                text = "SETTINGS",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = CopperAccent,
                    letterSpacing = 3.sp,
                    fontWeight = FontWeight.Medium,
                ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Account & Preferences",
                style = MaterialTheme.typography.headlineSmall.copy(color = TextPrimary),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(2.dp)
                    .background(
                        Brush.horizontalGradient(colors = listOf(CopperAccent, Color.Transparent))
                    )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Company section
        SettingsSectionLabel(label = "Company")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = CopperAccent.copy(alpha = 0.2f),
            ),
        ) {
            SettingsRow(
                label = stringResource(R.string.settings_screen_company_label),
                value = stringResource(R.string.company_name),
            )
            SettingsDivider()
            SettingsRow(
                label = "Address",
                value = "3rd Floor, 86-90 Paul Street\nLondon, EC2A 4NE",
                valueLines = 2,
            )
            SettingsDivider()
            SettingsRow(
                label = "Industry",
                value = "IT Consultancy",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // App section
        SettingsSectionLabel(label = "Application")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = CopperAccent.copy(alpha = 0.2f),
            ),
        ) {
            SettingsRow(
                label = stringResource(R.string.settings_screen_version_label),
                value = stringResource(R.string.app_version),
            )
            SettingsDivider()
            SettingsRow(
                label = "Build",
                value = "Production",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Support section
        SettingsSectionLabel(label = "Support")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = CopperAccent.copy(alpha = 0.2f),
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(context.getString(R.string.customer_support_link)),
                        )
                        context.startActivity(intent)
                    }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.settings_screen_customer_support_label),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary),
                )
                Text(
                    text = "Visit Website →",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = WarmGold,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
            SettingsDivider()
            SettingsRow(
                label = "Email",
                value = "info@diananicholasteam.uk",
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Footer
        Text(
            text = "© 2025 Diana Nicholas Limited\nAll rights reserved.",
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextMuted,
                lineHeight = 18.sp,
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp),
        )
    }
}

@Composable
private fun SettingsSectionLabel(label: String) {
    Text(
        text = label.uppercase(),
        style = MaterialTheme.typography.labelSmall.copy(
            color = TextMuted,
            letterSpacing = 1.5.sp,
        ),
        modifier = Modifier.padding(start = 20.dp, bottom = 8.dp),
    )
}

@Composable
private fun SettingsRow(
    label: String,
    value: String,
    valueLines: Int = 1,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = if (valueLines > 1) Alignment.Top else Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextPrimary,
                fontWeight = FontWeight.Medium,
            ),
        )
    }
}

@Composable
private fun SettingsDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(ElevatedSurface),
    )
}
