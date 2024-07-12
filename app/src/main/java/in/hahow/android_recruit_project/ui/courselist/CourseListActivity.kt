package `in`.hahow.android_recruit_project.ui.courselist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import `in`.hahow.android_recruit_project.ui.theme.AndroidrecruitprojectTheme

@AndroidEntryPoint
class CourseListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidrecruitprojectTheme {
            }
        }
    }
}