package com.training.shopcartecom.model.databasecart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.training.shopcartecom.model.data.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(cartItem: Cart)

    @Query("SELECT * FROM Cart" )
    fun fetchProduct():List<Cart>

    @Query("SELECT * FROM Cart WHERE id=:productId" )
    fun fetchProductWithId(productId: String): Cart?

    @Update
    fun updateProduct(cartItem: Cart)

    @Delete
    fun deleteProduct(cartItem: Cart)

    @Query("DELETE FROM Cart")
    fun deleteCart()
}