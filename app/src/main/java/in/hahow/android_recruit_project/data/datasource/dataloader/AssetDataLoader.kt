package `in`.hahow.android_recruit_project.data.datasource.dataloader

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class AssetDataLoader @Inject constructor(
    @ApplicationContext private val context: Context
) : DataLoader {
    @Throws(IOException::class)
    override fun readFileAsString(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
}