package `in`.hahow.android_recruit_project.ui.courselist

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.ui.UiState
import `in`.hahow.android_recruit_project.ui.theme.AndroidrecruitprojectTheme
import `in`.hahow.android_recruit_project.ui.theme.BlackAlpha50
import `in`.hahow.android_recruit_project.ui.theme.Blue3075E3
import `in`.hahow.android_recruit_project.ui.theme.Gray5A5A5A
import `in`.hahow.android_recruit_project.ui.theme.GrayA0A0A0
import `in`.hahow.android_recruit_project.ui.theme.GrayE8E8E8
import `in`.hahow.android_recruit_project.ui.theme.GrayF0F0F0
import `in`.hahow.android_recruit_project.ui.theme.Green19434C
import `in`.hahow.android_recruit_project.ui.theme.Green4FB1AE
import `in`.hahow.android_recruit_project.ui.theme.Green51AFA2
import `in`.hahow.android_recruit_project.ui.theme.Green5BC8B4
import `in`.hahow.android_recruit_project.ui.theme.GreenEBF7F6
import `in`.hahow.android_recruit_project.ui.theme.RedD44A40

@Composable
fun CourseListScreen(
    uiState: UiState<List<CourseItem>>,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        UiState.Loading -> {
            PlaceholderText(
                text = R.string.loading,
                modifier = modifier
            )
        }

        is UiState.Ready -> {
            if (uiState.data.isEmpty()) {
                PlaceholderText(
                    text = R.string.no_course,
                    modifier = modifier
                )
            } else {
                CourseList(
                    courses = uiState.data,
                    modifier = modifier
                )
            }
        }

        is UiState.Fail -> {
            PlaceholderText(
                text = uiState.message,
                modifier = modifier
            )
        }
    }
}

@Composable
fun PlaceholderText(
    @StringRes
    text: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(text),
            fontSize = 26.sp
        )
    }
}

@Composable
fun CourseList(
    courses: List<CourseItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(
                start = 12.dp,
                end = 12.dp,
                bottom = 12.dp
            )
    ) {
        items(
            items = courses,
            key = { it.id }
        ) {
            CourseItem(it)
        }
    }
}

@Composable
fun CourseItem(course: CourseItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 12.dp)
    ) {
        CourseCover(
            coverImageUrl = course.coverImageUrl,
            type = course.type,
            assignment = course.assignment,
            lastViewedAt = course.lastViewedAt,
            modifier = Modifier
                .width(154.dp)
                .height(88.dp)
        )
        Spacer(modifier = Modifier.size(14.dp))
        CourseInfo(
            rule = course.assignment?.rule,
            title = course.title,
            passedAt = course.passedAt,
            completionPercentage = course.completionPercentage,
            assignment = course.assignment,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CourseCover(
    coverImageUrl: String,
    type: CourseItem.Type?,
    assignment: CourseItem.Assignment?,
    lastViewedAt: String?,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = coverImageUrl,
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        )
        TopStartLabel(
            type = type,
            modifier = Modifier.align(Alignment.TopStart)
        )
        BottomEndLabel(
            assignment = assignment,
            lastViewedAt = lastViewedAt,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
private fun TopStartLabel(
    type: CourseItem.Type?,
    modifier: Modifier = Modifier
) {
    if (type == CourseItem.Type.ENTERPRISE_ASSIGNED_COURSE) {
        Text(
            text = stringResource(R.string.enterprise_assigned_course),
            fontSize = 10.sp,
            color = Color.White,
            modifier = modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .background(Blue3075E3)
                .padding(
                    start = 4.dp,
                    end = 4.dp
                )
        )
    }
}

@Composable
fun BottomEndLabel(
    assignment: CourseItem.Assignment?,
    lastViewedAt: String?,
    modifier: Modifier = Modifier
) {
    if (assignment != null) {
        BottomEndText(
            text = stringResource(R.string.assign, assignment.assignerName),
            backgroundColor = Green19434C,
            modifier = modifier
        )
    } else if (lastViewedAt != null) {
        BottomEndText(
            text = lastViewedAt,
            backgroundColor = BlackAlpha50,
            modifier = modifier
        )
    }
}

@Composable
private fun BottomEndText(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.White,
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
            .background(backgroundColor)
            .padding(
                start = 6.dp,
                end = 6.dp
            )
    )
}

@Composable
fun CourseInfo(
    rule: CourseItem.Assignment.Rule?,
    title: String,
    passedAt: String?,
    completionPercentage: Double?,
    assignment: CourseItem.Assignment?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TitleRow(
            rule = rule,
            title = title,
            modifier = modifier.weight(2f)
        )
        Spacer(modifier = modifier.size(4.dp))
        StatusRow(
            passedAt = passedAt,
            completionPercentage = completionPercentage,
            assignment = assignment,
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
fun TitleRow(
    rule: CourseItem.Assignment.Rule?,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        rule?.let {
            TypeTag(it)
            Spacer(modifier = Modifier.size(8.dp))
        }
        Text(
            text = title,
            fontSize = 18.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun TypeTag(rule: CourseItem.Assignment.Rule) {
    val (text, textColor, backgroundColor) = when (rule) {
        CourseItem.Assignment.Rule.ELECTIVE -> {
            Triple(R.string.elective, Gray5A5A5A, GrayF0F0F0)
        }

        CourseItem.Assignment.Rule.COMPULSORY -> {
            Triple(R.string.compulsory, Green51AFA2, GreenEBF7F6)
        }
    }
    Text(
        text = stringResource(text),
        fontSize = 14.sp,
        color = textColor,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                start = 8.dp,
                end = 8.dp
            )
    )
}

@Composable
fun StatusRow(
    passedAt: String?,
    completionPercentage: Double?,
    assignment: CourseItem.Assignment?,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        passedAt?.let {
            PassedAt(passedAt = it)
            Spacer(modifier = Modifier.size(14.dp))
        }
        completionPercentage?.let {
            CompletionPercentage(
                completionPercentage = it,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
        if (passedAt == null) {
            DueStatus(
                assignment = assignment
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
        Image(
            painter = painterResource(R.drawable.icon_more_vert_24px),
            contentDescription = null
        )
    }
}

@Composable
private fun PassedAt(
    passedAt: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_check_24px),
            contentDescription = null,
            modifier = Modifier
                .size(15.dp)
                .background(
                    color = Green5BC8B4,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(2.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = passedAt,
            fontSize = 14.sp,
            color = Green5BC8B4
        )
    }
}

@Composable
private fun CompletionPercentage(
    completionPercentage: Double,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.percent, completionPercentage.times(100).toInt()),
            fontSize = 14.sp,
            color = GrayA0A0A0,
        )
        Spacer(modifier = Modifier.size(4.dp))
        LinearProgressIndicator(
            progress = { completionPercentage.toFloat() },
            color = Green4FB1AE,
            trackColor = GrayE8E8E8,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun DueStatus(
    assignment: CourseItem.Assignment?,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        val color = when (assignment?.rule) {
            CourseItem.Assignment.Rule.COMPULSORY -> {
                RedD44A40
            }

            else -> {
                GrayA0A0A0
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.icon_schedule_24px),
            tint = color,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        val text = when {
            assignment == null -> {
                stringResource(R.string.no_due_date)
            }

            else -> {
                assignment.dueAt.orEmpty()
            }
        }
        Text(
            text = text,
            fontSize = 14.sp,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CourseListPreview() {
    AndroidrecruitprojectTheme {
        CourseList(
            courses = (0..9).map {
                CourseItem(
                    id = it.toString(),
                    title = "課程名稱",
                    coverImageUrl = "",
                    completionPercentage = 0.8,
                    type = CourseItem.Type.ENTERPRISE_ASSIGNED_COURSE,
                    passedAt = "2024-07-11 通過",
                    assignment = CourseItem.Assignment(
                        assignerName = "Kevin",
                        rule = CourseItem.Assignment.Rule.ELECTIVE,
                        dueAt = "1 天內截止"
                    ),
                    lastViewedAt = "5 天前觀看"
                )
            }
        )
    }
}