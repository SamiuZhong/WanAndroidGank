package com.samiu.wangank.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author samiu 2022/11/29
 * @email samiuzhong@outlook.com
 */
data class TagDTO(
    val name: String = "",
    val url: String = ""
)

class TagDTOConvert {

    @TypeConverter
    fun stringToObject(value: String): List<TagDTO> {
        val listTYpe = object : TypeToken<List<TagDTO>>() {}.type
        return Gson().fromJson(value, listTYpe)
    }

    @TypeConverter
    fun objectToString(list: List<TagDTO>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}