package service.diananicholasconsult.app.ui.composable.screen.bookings

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import service.diananicholasconsult.app.R
import service.diananicholasconsult.app.ui.composable.shared.DataBasedContainer
import service.diananicholasconsult.app.ui.composable.shared.DataEmptyContent
import service.diananicholasconsult.app.ui.state.BookingUiState
import service.diananicholasconsult.app.ui.state.DataUiState
import service.diananicholasconsult.app.ui.theme.CardSurface
import service.diananicholasconsult.app.ui.theme.CopperAccent
import service.diananicholasconsult.app.ui.theme.DarkBackground
import service.diananicholasconsult.app.ui.theme.DarkSurface
import service.diananicholasconsult.app.ui.theme.ElevatedSurface
import service.diananicholasconsult.app.ui.theme.StatusError
import service.diananicholasconsult.app.ui.theme.TextMuted
import service.diananicholasconsult.app.ui.theme.TextPrimary
import service.diananicholasconsult.app.ui.theme.TextSecondary
import service.diananicholasconsult.app.ui.theme.WarmGold
import service.diananicholasconsult.app.ui.viewmodel.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val bookingsState by viewModel.bookingsState.collectAsState()
    var canceledBookingNumber by remember { mutableStateOf("") }
    var shouldShowDialog by remember { mutableStateOf(false) }

    BookingsContent(
        bookingsState = bookingsState,
        modifier = modifier,
        onCancelBookingButtonClick = { bookingNumber ->
            canceledBookingNumber = bookingNumber
            shouldShowDialog = true
        },
    )

    if (shouldShowDialog) {
        AlertDialog(
            onDismissRequest = { shouldShowDialog = false },
            title = {
                Text(
                    text = stringResource(R.string.cancel_booking_dialog_title),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.clear_card_dialog_text),
                    style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary),
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.cancelBooking(canceledBookingNumber)
                        shouldShowDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = StatusError,
                        contentColor = DarkBackground,
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Cancel Booking",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { shouldShowDialog = false },
                    colors = ButtonDefaults.textButtonColors(contentColor = TextMuted),
                ) {
                    Text("Keep Booking")
                }
            },
            containerColor = DarkSurface,
            shape = RoundedCornerShape(16.dp),
        )
    }
}

@Composable
private fun BookingsContent(
    bookingsState: DataUiState<List<BookingUiState>>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
    ) {
        DataBasedContainer(
            dataState = bookingsState,
            dataPopulated = {
                BookingsPopulated(
                    bookings = (bookingsState as DataUiState.Populated).data,
                    onCancelBookingButtonClick = onCancelBookingButtonClick,
                )
            },
            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.bookings_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun BookingsPopulated(
    bookings: List<BookingUiState>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(bookings) { booking ->
            BookingCard(
                booking = booking,
                onCancelClick = { onCancelBookingButtonClick(booking.bookingNumber) },
            )
        }
    }
}

@Composable
private fun BookingCard(
    booking: BookingUiState,
    onCancelClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = CopperAccent.copy(alpha = 0.25f),
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.booking_number, booking.bookingNumber),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = CopperAccent,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                    ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(WarmGold),
                    )
                    Text(
                        text = "Confirmed",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = WarmGold,
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(ElevatedSurface),
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = booking.serviceName,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Client: ",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextMuted),
                )
                Text(
                    text = "${booking.customerFirstName} ${booking.customerLastName}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Booked: ",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextMuted),
                )
                Text(
                    text = booking.timestamp,
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary),
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            TextButton(
                onClick = onCancelClick,
                colors = ButtonDefaults.textButtonColors(contentColor = StatusError),
                contentPadding = PaddingValues(horizontal = 0.dp),
            ) {
                Text(
                    text = "Cancel Booking",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = StatusError,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
        }
    }
}
