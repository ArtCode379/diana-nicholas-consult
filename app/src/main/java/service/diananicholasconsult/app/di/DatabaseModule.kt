package service.diananicholasconsult.app.di

import androidx.room.Room
import service.diananicholasconsult.app.data.database.Database
import org.koin.dsl.module

private const val DB_NAME = "_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = Database::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<Database>().bookingDao()}

}