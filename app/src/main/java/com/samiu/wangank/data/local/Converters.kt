package com.samiu.wangank.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.samiu.wangank.model.TagDTO
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@Singleton
@ProvidedTypeConverter
class Converters @Inject constructor(
    private val gson: Gson
) {

    @TypeConverter
    fun jsonToTagList(tagListJson: String): List<TagDTO> {
        return gson.fromJson(tagListJson, object : TypeToken<List<TagDTO>>() {}.type)
    }

    @TypeConverter
    fun tagListToJson(tagList: List<TagDTO>): String {
        return gson.toJson(tagList)
    }
}