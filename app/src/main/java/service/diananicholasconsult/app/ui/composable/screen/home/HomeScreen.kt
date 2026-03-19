package service.diananicholasconsult.app.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import service.diananicholasconsult.app.R
import service.diananicholasconsult.app.data.model.ServiceModel
import service.diananicholasconsult.app.ui.composable.shared.DataEmptyContent
import service.diananicholasconsult.app.ui.state.DataUiState
import service.diananicholasconsult.app.ui.theme.CardSurface
import service.diananicholasconsult.app.ui.theme.CopperAccent
import service.diananicholasconsult.app.ui.theme.DarkBackground
import service.diananicholasconsult.app.ui.theme.DarkSurface
import service.diananicholasconsult.app.ui.theme.ElevatedSurface
import service.diananicholasconsult.app.ui.theme.TextMuted
import service.diananicholasconsult.app.ui.theme.TextPrimary
import service.diananicholasconsult.app.ui.theme.TextSecondary
import service.diananicholasconsult.app.ui.theme.WarmGold
import service.diananicholasconsult.app.ui.viewmodel.ServiceViewModel
import org.koin.androidx.compose.koinViewModel

private data class KnowledgeArticle(
    val title: String,
    val summary: String,
    val readTime: String,
    val category: String,
)

private data class CaseStudy(
    val title: String,
    val description: String,
    val industry: String,
    val result: String,
)

private val knowledgeArticles = listOf(
    KnowledgeArticle(
        title = "The Future of Cybersecurity in 2025",
        summary = "Zero-trust architecture and AI-driven threat detection are reshaping enterprise security. Learn how to stay ahead of emerging threats with adaptive security frameworks.",
        readTime = "6 min read",
        category = "Security",
    ),
    KnowledgeArticle(
        title = "Cloud-First vs Cloud-Native: Making the Right Choice",
        summary = "Understanding the strategic differences between cloud-first adoption and cloud-native development helps organisations make informed architectural decisions.",
        readTime = "8 min read",
        category = "Cloud",
    ),
    KnowledgeArticle(
        title = "Digital Transformation: Beyond the Buzzword",
        summary = "True digital transformation requires cultural change, process redesign, and technology alignment. Discover the practical frameworks that drive measurable outcomes.",
        readTime = "5 min read",
        category = "Strategy",
    ),
)

private val caseStudies = listOf(
    CaseStudy(
        title = "NHS Trust Security Overhaul",
        description = "Redesigned the security posture of a major NHS Trust, implementing zero-trust architecture and real-time threat monitoring across 12,000 endpoints.",
        industry = "Healthcare",
        result = "94% reduction in security incidents",
    ),
    CaseStudy(
        title = "FinTech Cloud Migration",
        description = "Migrated a London-based FinTech platform from on-premises infrastructure to a cloud-native architecture on AWS, with zero downtime during transition.",
        industry = "Financial Services",
        result = "40% cost reduction, 3× performance",
    ),
    CaseStudy(
        title = "Retail Chain Process Automation",
        description = "Automated 60% of back-office workflows for a national retail chain using RPA and AI, reducing manual processing time and human error rates dramatically.",
        industry = "Retail",
        result = "35% operational efficiency gain",
    ),
    CaseStudy(
        title = "Insurance Analytics Platform",
        description = "Built a real-time data analytics platform aggregating policy, claims, and risk data into actionable intelligence dashboards for executive decision-making.",
        industry = "Insurance",
        result = "£2.3M annual cost savings",
    ),
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val servicesState by viewModel.servicesState.collectAsState()

    HomeContent(
        servicesState = servicesState,
        modifier = modifier,
        onNavigateToServiceDetails = onNavigateToServiceDetails,
    )
}

@Composable
private fun HomeContent(
    servicesState: DataUiState<List<ServiceModel>>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentPadding = PaddingValues(bottom = 32.dp),
    ) {
        item { HomeHeader() }

        item {
            when (servicesState) {
                is DataUiState.Populated -> HeroCarousel(
                    services = servicesState.data.take(3),
                    onNavigateToServiceDetails = onNavigateToServiceDetails,
                )
                else -> Unit
            }
        }

        item {
            SectionHeader(
                title = "Our Services",
                subtitle = "Transforming businesses through technology excellence",
            )
        }

        item {
            when (servicesState) {
                is DataUiState.Populated -> ServicesGrid(
                    services = servicesState.data,
                    onNavigateToServiceDetails = onNavigateToServiceDetails,
                )
                DataUiState.Empty -> DataEmptyContent(
                    primaryText = stringResource(R.string.services_state_empty_primary_text),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )
                DataUiState.Initial -> Unit
            }
        }

        item {
            SectionHeader(
                title = "Knowledge Base",
                subtitle = "Insights from the frontline of digital innovation",
            )
        }

        item { KnowledgeBaseSection() }

        item {
            SectionHeader(
                title = "Case Studies",
                subtitle = "Proven results across industries",
            )
        }

        item { PortfolioSection() }
    }
}

@Composable
private fun HomeHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(colors = listOf(DarkSurface, DarkBackground))
            )
            .padding(horizontal = 20.dp, vertical = 20.dp),
    ) {
        Text(
            text = "DIANA NICHOLAS",
            style = MaterialTheme.typography.labelLarge.copy(
                color = CopperAccent,
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Medium,
            ),
        )
        Text(
            text = "Digital Alchemist",
            style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimary),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(2.dp)
                .background(Brush.horizontalGradient(colors = listOf(CopperAccent, Color.Transparent)))
        )
    }
}

@Composable
private fun HeroCarousel(
    services: List<ServiceModel>,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { services.size })

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
        ) { index ->
            val service = services[index]
            HeroCarouselItem(service = service, onClick = { onNavigateToServiceDetails(service.id) })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(services.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (isSelected) 10.dp else 6.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) CopperAccent else ElevatedSurface),
                )
            }
        }
    }
}

@Composable
private fun HeroCarouselItem(service: ServiceModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
    ) {
        Image(
            painter = painterResource(id = service.imageRes),
            contentDescription = service.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xCC111111)),
                        startY = 0.3f,
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
        ) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                ),
            )
            Text(
                text = "£%.0f / hr  •  View Details →".format(service.price),
                style = MaterialTheme.typography.bodySmall.copy(color = WarmGold),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    color = CopperAccent.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(16.dp),
                )
        )
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(22.dp)
                    .background(CopperAccent)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall.copy(color = TextMuted),
            modifier = Modifier.padding(start = 13.dp),
        )
    }
}

@Composable
private fun ServicesGrid(
    services: List<ServiceModel>,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        services.chunked(2).forEach { rowServices ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                rowServices.forEach { service ->
                    ServiceCard(
                        service = service,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateToServiceDetails(service.id) },
                    )
                }
                if (rowServices.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ServiceCard(
    service: ServiceModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .height(200.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = CopperAccent.copy(alpha = 0.25f),
        ),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
            ) {
                Image(
                    painter = painterResource(id = service.imageRes),
                    contentDescription = service.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, CardSurface),
                                startY = 0.5f,
                            )
                        )
                )
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = TextPrimary,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "£%.0f / hr".format(service.price),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = CopperAccent,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
        }
    }
}

@Composable
private fun KnowledgeBaseSection() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(knowledgeArticles) { article ->
            KnowledgeArticleCard(article = article)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun KnowledgeArticleCard(article: KnowledgeArticle) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(160.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = CopperAccent.copy(alpha = 0.2f),
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = article.category.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = CopperAccent,
                    letterSpacing = 1.5.sp,
                ),
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.summary,
                style = MaterialTheme.typography.bodySmall.copy(color = TextMuted),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = article.readTime,
                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary),
            )
        }
    }
}

@Composable
private fun PortfolioSection() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(caseStudies) { study ->
            CaseStudyCard(study = study)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun CaseStudyCard(study: CaseStudy) {
    Card(
        modifier = Modifier.width(260.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = CopperAccent.copy(alpha = 0.2f),
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = CopperAccent.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(4.dp),
                    )
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text(
                    text = study.industry,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = CopperAccent,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = study.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = study.description,
                style = MaterialTheme.typography.bodySmall.copy(color = TextMuted),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(ElevatedSurface),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(WarmGold),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = study.result,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = WarmGold,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            }
        }
    }
}

@Composable
private fun ServicesPopulated(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    ServicesGrid(
        services = services,
        onNavigateToServiceDetails = onNavigateToServiceDetails,
    )
}
