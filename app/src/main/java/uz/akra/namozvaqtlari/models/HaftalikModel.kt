package uz.akra.namozvaqtlari.models

data class HaftalikModel(
    val region: String,
    val date: String,
    val hijri_date: HijriDateX,
    val weekday: String,
    val times: TimesX
)