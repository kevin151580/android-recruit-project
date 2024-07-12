package `in`.hahow.android_recruit_project.data.datasource.dataloader

import java.io.IOException

interface DataLoader {
    @Throws(IOException::class)
    fun readFileAsString(fileName: String): String
}