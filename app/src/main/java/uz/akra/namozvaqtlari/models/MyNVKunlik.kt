package uz.akra.namozvaqtlari.models

data class MyNVKunlik(
    val date: String,
    val hijri_date: HijriDate,
    val region: String,
    val times: Times,
    val weekday: String
)