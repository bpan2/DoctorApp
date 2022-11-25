package com.example.doctorapp.data

import java.util.*

class SampleDataProvider {
    companion object{
        private val sampleText1 = "A simple note"
        private val sampleText2 = "A note a\nline feed"
        private val sampleText3 = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac imperdiet dolor, eget aliquet diam. Ut nec euismod mauris, quis congue neque. Donec pellentesque ipsum vitae eros sagittis euismod. Cras eget commodo arcu. Nunc id diam lectus. Cras et est enim. In ipsum justo, porta ut auctor et, dictum eget augue. Cras non posuere nisi. In vehicula, risus sed congue auctor, ligula quam tristique sem, gravida porttitor tellus mi nec ex. Integer nisi justo, sodales in porta vitae, tempor et libero. Ut scelerisque turpis id tellus hendrerit, quis gravida urna consectetur. Vestibulum malesuada urna at posuere mollis. Etiam lacinia erat nec dui facilisis tempus.

            Nulla rutrum massa enim, eget tristique dui laoreet id. Duis dignissim iaculis pellentesque. Vestibulum aliquet massa non consequat euismod. Vestibulum vehicula pellentesque orci a aliquam. Vestibulum ac ante quis lectus gravida pretium. Vestibulum consectetur elementum lacus, convallis auctor purus venenatis sed. Donec sed vulputate purus, venenatis viverra massa. Integer molestie tortor in mollis suscipit. Sed accumsan, felis quis cursus dignissim, massa tellus volutpat augue, vel mollis dui est at enim. 
        """.trimIndent()

        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getNotes() = arrayListOf(
            NoteEntity(1, getDate(0), sampleText1),
            NoteEntity(2, getDate(1), sampleText2),
            NoteEntity(3, getDate(2), sampleText3)
        )
    }
}