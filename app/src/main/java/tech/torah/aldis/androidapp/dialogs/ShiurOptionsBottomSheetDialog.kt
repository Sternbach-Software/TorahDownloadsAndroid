package tech.torah.aldis.androidapp.dialogs

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.activities.IndividualSpeakerPageActivity
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS.INTENT_EXTRA_SPEAKER_DETAILS
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.formatted
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.toHrMinSec
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Speaker
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage

class ShiurOptionsBottomSheetDialog(private val shiur: Shiur) : BottomSheetDialogFragment() {
    private lateinit var shiurTitleTextView:TextView
    private lateinit var shiurCategoryTextView:TextView
    private lateinit var shiurSeriesTextView:TextView
    private lateinit var shiurLengthTextView:TextView
    private lateinit var shiurSpeakerTextView:TextView
    private lateinit var bottomSheetViewSpeakerButton:MaterialButton
    private lateinit var bottomSheetViewAddToPlaylistButton:MaterialButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.shiur_bottom_sheet_layout, container, false)
        shiurTitleTextView = view.findViewById(R.id.bottom_sheet_shiur_title)
        shiurCategoryTextView = view.findViewById(R.id.bottom_sheet_shiur_category)
        shiurSeriesTextView = view.findViewById(R.id.bottom_sheet_shiur_series)
        shiurLengthTextView = view.findViewById(R.id.bottom_sheet_shiur_length)
        shiurSpeakerTextView = view.findViewById(R.id.bottom_sheet_shiur_speaker)
        bottomSheetViewSpeakerButton = view.findViewById(R.id.bottom_sheet_view_speaker_button)
        bottomSheetViewAddToPlaylistButton = view.findViewById(R.id.bottom_sheet_add_to_playlist_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        shiurTitleTextView.text = shiur.baseTitle
        if(shiur is ShiurFullPage) shiurCategoryTextView.text = boldUntilColon("Category: "+shiur.category)
        if(shiur is ShiurFullPage) shiurSeriesTextView.text = boldUntilColon("Series: "+shiur.series)
        shiurLengthTextView.text = boldUntilColon("Length: "+shiur.baseLength?.toInt()?.toHrMinSec()?.formatted(false))
        shiurSpeakerTextView.text = boldUntilColon("Speaker: "+shiur.baseSpeaker)
        bottomSheetViewSpeakerButton.setOnClickListener {
            val speakerObject = getSpeaker(shiur.baseSpeaker)
            val name = speakerObject.name
            val description = speakerObject.description
            val context = view.context
            val intent = Intent(context, IndividualSpeakerPageActivity::class.java).apply {
                putStringArrayListExtra(INTENT_EXTRA_SPEAKER_DETAILS,
                    arrayListOf(name, description))
            }
            context.startActivity(intent)
        }
    }

    private fun boldUntilColon(string:String) =
        SpannableStringBuilder(string).apply{setSpan(StyleSpan(Typeface.BOLD), 0, string.indexOf(":"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)} // make first 4 characters Bold


    private fun getSpeaker(baseSpeaker: String?): Speaker {
        //TODO to implement
        return Speaker(name = baseSpeaker?:"TESTTT")
    }

    private fun getShiurSize(shiur: ShiurFullPage): String {
        //TODO to implement
        return "99.99 MB"
    }
}