package service.diananicholasconsult.app.ui.composable.screen.servicedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import service.diananicholasconsult.app.R
import service.diananicholasconsult.app.data.model.ServiceModel
import service.diananicholasconsult.app.ui.composable.shared.DataBasedContainer
import service.diananicholasconsult.app.ui.composable.shared.DataEmptyContent
import service.diananicholasconsult.app.ui.state.DataUiState
import service.diananicholasconsult.app.ui.theme.CardSurface
import service.diananicholasconsult.app.ui.theme.CopperAccent
import service.diananicholasconsult.app.ui.theme.DarkBackground
import service.diananicholasconsult.app.ui.theme.ElevatedSurface
import service.diananicholasconsult.app.ui.theme.TextMuted
import service.diananicholasconsult.app.ui.theme.TextPrimary
import service.diananicholasconsult.app.ui.theme.TextSecondary
import service.diananicholasconsult.app.ui.theme.WarmGold
import service.diananicholasconsult.app.ui.viewmodel.ServiceDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun ServiceDetailsScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val serviceState by viewModel.serviceState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeServiceById(serviceId)
    }

    ServiceDetailsContent(
        serviceState = serviceState,
        modifier = modifier,
        onNavigateToCheckout = onNavigateToCheckout,
    )
}

@Composable
private fun ServiceDetailsContent(
    serviceState: DataUiState<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
    ) {
        DataBasedContainer<ServiceModel>(
            dataState = serviceState,
            dataPopulated = {
                ServicesDetailsPopulated(
                    service = (serviceState as DataUiState.Populated).data,
                    onNavigateToCheckout = onNavigateToCheckout,
                )
            },
            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.services_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ServicesDetailsPopulated(
    service: ServiceModel,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                ) {
                    Image(
                        painter = painterResource(id = service.imageRes),
                        contentDescription = stringResource(R.string.service_image_description),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, DarkBackground),
                                    startY = 0.5f,
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp),
                    ) {
                        Text(
                            text = service.name,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = TextPrimary,
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                        Text(
                            text = stringResource(R.string.price_per_hour, service.price),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = WarmGold,
                                fontWeight = FontWeight.SemiBold,
                            ),
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color.Transparent, CopperAccent, Color.Transparent),
                            )
                        )
                )
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
                    Text(
                        text = "About this Service",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = CopperAccent,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                        ),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = service.description,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = TextSecondary,
                            lineHeight = 26.sp,
                        ),
                    )
                }
            }

            if (!service.availableTime.isNullOrEmpty()) {
                item {
                    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                        Text(
                            text = "Available Time Slots",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = CopperAccent,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp,
                            ),
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        LazyRow(
                            contentPadding = PaddingValues(end = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(service.availableTime!!) { time ->
                                Card(
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                                    border = androidx.compose.foundation.BorderStroke(
                                        width = 1.dp,
                                        color = CopperAccent.copy(alpha = 0.4f),
                                    ),
                                ) {
                                    Text(
                                        text = time.format(timeFormatter),
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = WarmGold,
                                            fontWeight = FontWeight.Medium,
                                        ),
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                    Text(
                        text = "What's Included",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = CopperAccent,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                        ),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    listOf(
                        "Initial discovery call & requirements analysis",
                        "Detailed findings report with executive summary",
                        "Tailored recommendations and action roadmap",
                        "30-day post-engagement follow-up support",
                    ).forEach { bullet ->
                        Row(
                            modifier = Modifier.padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(CopperAccent, shape = RoundedCornerShape(3.dp))
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = bullet,
                                style = MaterialTheme.typography.bodyMedium.copy(color = TextMuted),
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }

        Button(
            onClick = { onNavigateToCheckout(service.id) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .height(52.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CopperAccent,
                contentColor = DarkBackground,
            ),
        ) {
            Text(
                text = stringResource(R.string.button_book_consultation_text),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                ),
            )
        }
    }
}
