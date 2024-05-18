package it.pippo.wisewordspro.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoProverb {
    @Insert
    fun insertAll(proverbs: List<Proverb>)
    @Insert
    fun insert(proverb: Proverb)
    @Update
    fun update(proverb: Proverb)
    @Delete
    fun delete(proverb: Proverb)

    @Query("""SELECT text FROM Proverb 
        WHERE text like :filter
        """
            )
    fun readALl(filter:String): List<String>

    @Query(
        """
        SELECT * FROM Proverb
            WHERE favorite = :favorite OR (:favorite = 0) 
            ORDER BY RANDOM()
            LIMIT 1
    """
    )
    fun loadRandomProverb(favorite: Int): Proverb?
    // Form CP
    @Query("SELECT * FROM Proverb")
    fun selectAll(): Cursor?

    @Query("SELECT * FROM Proverb WHERE id=:id")
    fun selectById(id: Int): Cursor?

    @Query("""SELECT * FROM Proverb 
                WHERE text like :filter
                    AND (favorite = :favorite OR (:favorite = 0))
                ORDER BY RANDOM() LIMIT 1""")
    fun readFilteredNext(filter: String, favorite: Int): Proverb?

}