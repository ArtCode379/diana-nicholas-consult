package service.diananicholasconsult.app.data.repository

import service.diananicholasconsult.app.R
import service.diananicholasconsult.app.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalTime

class ServiceRepository {
    private val services: List<ServiceModel> = listOf(
        ServiceModel(
            id = 1,
            name = "Cybersecurity Audit",
            description = "Comprehensive security assessment of your digital infrastructure, identifying vulnerabilities and implementing robust protection strategies to safeguard your business assets.",
            price = 250.00,
            availableTime = listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
            ),
            imageRes = R.drawable.img_cybersecurity,
        ),
        ServiceModel(
            id = 2,
            name = "Cloud Migration Strategy",
            description = "Seamless transition of legacy systems to cloud platforms with minimal disruption and maximum efficiency gains, tailored to your organisation's unique requirements and growth objectives.",
            price = 225.00,
            availableTime = listOf(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
            ),
            imageRes = R.drawable.img_cloud_computing,
        ),
        ServiceModel(
            id = 3,
            name = "IT Strategy Consulting",
            description = "Aligning technology roadmaps with business objectives through expert analysis and strategic planning, ensuring your IT investments deliver measurable, sustainable returns.",
            price = 200.00,
            availableTime = listOf(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
            ),
            imageRes = R.drawable.img_it_strategy,
        ),
        ServiceModel(
            id = 4,
            name = "Business Process Optimisation",
            description = "Streamlining workflows using intelligent automation and data-driven decision frameworks to eliminate inefficiencies and accelerate your operational velocity.",
            price = 175.00,
            availableTime = listOf(
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
            ),
            imageRes = R.drawable.img_business_process,
        ),
        ServiceModel(
            id = 5,
            name = "Digital Transformation",
            description = "End-to-end modernisation of your technology stack, culture, and operations for the digital age, delivered by seasoned transformation experts who have led enterprise-scale change.",
            price = 300.00,
            availableTime = listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
            ),
            imageRes = R.drawable.img_digital_transformation,
        ),
        ServiceModel(
            id = 6,
            name = "Data Analytics & Intelligence",
            description = "Unlocking the hidden value in your data through advanced analytics, AI, and machine learning solutions that transform raw information into decisive strategic advantage.",
            price = 225.00,
            availableTime = listOf(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
            ),
            imageRes = R.drawable.img_data_analytics,
        ),
        ServiceModel(
            id = 7,
            name = "Infrastructure Architecture",
            description = "Designing resilient, scalable IT infrastructure tailored to your organisation's growth trajectory, ensuring robust performance under any workload and future-proof expandability.",
            price = 200.00,
            availableTime = listOf(
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
            ),
            imageRes = R.drawable.img_infrastructure,
        ),
        ServiceModel(
            id = 8,
            name = "Compliance & Governance",
            description = "Ensuring your technology practices meet industry regulations and international standards, protecting your organisation from compliance risk and reputational damage.",
            price = 175.00,
            availableTime = listOf(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(14, 0),
            ),
            imageRes = R.drawable.img_compliance,
        ),
    )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}
