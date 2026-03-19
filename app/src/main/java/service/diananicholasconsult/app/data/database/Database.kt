package service.diananicholasconsult.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import service.diananicholasconsult.app.data.dao.BookingDao
import service.diananicholasconsult.app.data.database.converter.Converters
import service.diananicholasconsult.app.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

