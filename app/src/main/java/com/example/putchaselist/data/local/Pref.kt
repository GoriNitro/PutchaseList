@file:Suppress("NAME_SHADOWING")

package com.example.putchaselist.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.putchaselist.data.model.PurModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Pref {
    companion object {
        fun loadData(context: Context, list: ArrayList<PurModel>) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            val gson = Gson()
            val json = sharedPreferences.getString("courses", null)
            val type: Type = object : TypeToken<ArrayList<PurModel>>() {}.type

            if (json != null) {
                val loadedList: ArrayList<PurModel> = gson.fromJson(json, type)

                if (list.isNotEmpty()) {
                    list.clear() // Очищаем старые данные
                    list.addAll(loadedList) // Добавляем загруженные данные
                } else {
                    list.addAll(loadedList)
                }
            } else {
                println("No data found in SharedPreferences")
            }
        }

        fun saveData(context: Context, list: ArrayList<PurModel>) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("shared preferences", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            editor.putString("courses", json)
            editor.apply()
        }
    }
}