package `in`.hahow.android_recruit_project.ui.courselist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import `in`.hahow.android_recruit_project.ui.theme.AndroidrecruitprojectTheme

@AndroidEntryPoint
class CourseListActivity : ComponentActivity() {
    private val courseListViewModel: CourseListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val courseListUiState = courseListViewModel.courseListUiState.observeAsState()
            AndroidrecruitprojectTheme {
            }
        }
    }
}