package com.italo.myweather.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCity(@PrimaryKey val id: Long)
